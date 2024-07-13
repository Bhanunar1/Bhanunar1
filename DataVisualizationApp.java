import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;

public class DataVisualizationApp {

    private final List<Map<String, Double>> records = new ArrayList<>();
    private List<String> columnNames = new ArrayList<>();
    private BorderPane root = new BorderPane();

    public static void main(String[] args) {
        // Manually launch JavaFX application
        Platform.startup(() -> {
            Stage stage = new Stage();
            DataVisualizationApp app = new DataVisualizationApp();
            app.initUI(stage);
        });
    }

    private void initUI(Stage stage) {
        stage.setTitle("Data Visualization App");

        // Create UI components
        ComboBox<String> chartTypeComboBox = new ComboBox<>();
        chartTypeComboBox.getItems().addAll("Line Chart", "Bar Chart", "Pie Chart", "Scatter Plot");
        chartTypeComboBox.setValue("Line Chart");

        Button loadFileButton = new Button("Load Data File");
        TextArea filePathArea = new TextArea();
        filePathArea.setEditable(false);
        filePathArea.setPrefHeight(40);

        VBox controlPanel = new VBox(10, chartTypeComboBox, loadFileButton, filePathArea);
        root.setLeft(controlPanel);

        loadFileButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Data Files", ".csv", ".json", "*.xlsx"));
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                try {
                    filePathArea.setText(file.getAbsolutePath());
                    loadDataFromFile(file);
                    updateChart(chartTypeComboBox.getValue());
                } catch (Exception ex) {
                    showError("Error loading file: " + ex.getMessage());
                }
            }
        });

        chartTypeComboBox.setOnAction(e -> updateChart(chartTypeComboBox.getValue()));

        Scene scene = new Scene(root, 1200, 800);
        stage.setScene(scene);
        stage.show();
    }

    private void loadDataFromFile(File file) {
        records.clear();
        columnNames.clear();
        String fileName = file.getName().toLowerCase();

        try {
            if (fileName.endsWith(".csv")) {
                loadCSVData(file);
            } else if (fileName.endsWith(".json")) {
                loadJSONData(file);
            } else if (fileName.endsWith(".xlsx")) {
                loadExcelData(file);
            }
        } catch (Exception e) {
            showError("Error reading data from file: " + e.getMessage());
        }
    }

    private void loadCSVData(File file) throws Exception {
        try (Reader reader = new FileReader(file)) {
            CSVParser parser = CSVFormat.DEFAULT.parse(reader);
            List<String> headerNames = parser.getHeaderNames();
            List<CSVRecord> csvRecords = parser.getRecords();
    
            for (int i = 1; i < csvRecords.size(); i++) { // Start from index 1 to skip header
                CSVRecord record = csvRecords.get(i);
                Map<String, Double> row = new HashMap<>();
                for (int j = 0; j < headerNames.size(); j++) {
                    row.put(headerNames.get(j), parseDouble(record.get(j)));
                }
                records.add(row);
            }
        }
    }
    
    private void loadJSONData(File file) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(file);
    
        if (rootNode.isArray()) {
            for (JsonNode node : rootNode) {
                Map<String, Double> row = new HashMap<>();
                Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
                while (fields.hasNext()) {
                    Map.Entry<String, JsonNode> entry = fields.next();
                    row.put(entry.getKey(), parseDouble(entry.getValue().asText()));
                }
                records.add(row);
            }
        }
    }
    
    private void loadExcelData(File file) throws Exception {
        Workbook workbook = null;
        try (InputStream inputStream = new FileInputStream(file)) {
            workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();
    
            if (iterator.hasNext()) {
                Row headerRow = iterator.next();
                List<String> header = new ArrayList<>();
                for (org.apache.poi.ss.usermodel.Cell cell : headerRow) {
                    header.add(cell.getStringCellValue());
                }
                columnNames.addAll(header);
            }
    
            while (iterator.hasNext()) {
                Row row = iterator.next();
                Map<String, Double> record = new HashMap<>();
                for (int i = 0; i < columnNames.size(); i++) {
                    org.apache.poi.ss.usermodel.Cell cell = row.getCell(i);
                    if (cell != null) {
                        record.put(columnNames.get(i), getCellValue(cell));
                    } else {
                        record.put(columnNames.get(i), 0.0); // or some other default value
                    }
                }
                records.add(record);
            }
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
    }
    

    private void updateChart(String chartType) {
        if (records.isEmpty() || columnNames.size() < 2) {
            root.setCenter(new Label("No data available or insufficient columns."));
            return;
        }

        switch (chartType) {
            case "Line Chart":
                root.setCenter(createLineChart());
                break;
            case "Bar Chart":
                root.setCenter(createBarChart());
                break;
            case "Pie Chart":
                root.setCenter(createPieChart());
                break;
            case "Scatter Plot":
                root.setCenter(createScatterChart());
                break;
        }
    }

    private LineChart<Number, Number> createLineChart() {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Line Chart Structure");

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Data");

        for (Map<String, Double> record : records) {
            series.getData().add(new XYChart.Data<>(record.get(columnNames.get(0)), record.get(columnNames.get(1))));
        }

        lineChart.getData().add(series);
        return lineChart;
    }

    private BarChart<String, Number> createBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Bar Chart Structure");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Data");

        for (Map<String, Double> record : records) {
            series.getData().add(new XYChart.Data<>(record.get(columnNames.get(0)).toString(), record.get(columnNames.get(1))));
        }

        barChart.getData().add(series);
        return barChart;
    }

    private PieChart createPieChart() {
        PieChart pieChart = new PieChart();
        pieChart.setTitle("Pie Chart Structure");

        for (Map<String, Double> record : records) {
            pieChart.getData().add(new PieChart.Data(record.get(columnNames.get(0)).toString(), record.get(columnNames.get(1))));
        }

        return pieChart;
    }

    private ScatterChart<Number, Number> createScatterChart() {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        ScatterChart<Number, Number> scatterChart = new ScatterChart<>(xAxis, yAxis);
        scatterChart.setTitle("Scatter Plot Example");

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Data");

        for (Map<String, Double> record : records) {
            series.getData().add(new XYChart.Data<>(record.get(columnNames.get(0)), record.get(columnNames.get(1))));
        }

        scatterChart.getData().add(series);
        return scatterChart;
    }

    private Double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private double getCellValue(org.apache.poi.ss.usermodel.Cell cell) {
        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            try {
                return Double.parseDouble(cell.getStringCellValue());
            } catch (NumberFormatException e) {
                return 0.0; // or NaN
            }
        } else {
            return 0.0; // or NaN
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
