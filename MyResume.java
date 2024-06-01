import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

class MyResume extends JFrame {
    private JTextField t2, t3, t4;
    private JTextArea ta1, ta2, ta3, ta4, ta5, ta6, ta7, ta8;
    private JRadioButton r1, r2, r3;
    private JLabel imageLabel;
    private File imageFile;

    MyResume() {
        setTitle("Resume Builder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        setLayout(new BorderLayout());        
        JLabel l0 = new JLabel("MY RESUME BUILDER", JLabel.CENTER);
        l0.setFont(new Font("Arial", Font.BOLD, 24));
        add(l0, BorderLayout.NORTH);
        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        JPanel jp3 = new JPanel();

        jp2.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

        JLabel uploadImageLabel = new JLabel("Upload Image:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        jp1.add(uploadImageLabel, gbc);

        imageLabel = new JLabel();
        JButton uploadButton = new JButton("Browse...");
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showOpenDialog(null);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    ImageIcon originalIcon = new ImageIcon(file.getAbsolutePath());
                    Image originalImage = originalIcon.getImage();
                    Image resizedImage = originalImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                    ImageIcon resizedIcon = new ImageIcon(resizedImage);
                    imageLabel.setIcon(resizedIcon);
                }
            }
        });
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        jp1.add(uploadButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        jp1.add(imageLabel, gbc);
        
        JScrollPane scrollPane = new JScrollPane(jp2);
        add(scrollPane, BorderLayout.CENTER);

        JLabel l2 = new JLabel("Name :");
        gbc.gridx = 0;
        gbc.gridy = 0;
        jp2.add(l2, gbc);

        t2 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jp2.add(t2, gbc);

        JLabel l3 = new JLabel("PROFESSION :");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        jp2.add(l3, gbc);

        t3 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jp2.add(t3, gbc);

        JLabel l4 = new JLabel("DOB :");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        jp2.add(l4, gbc);

        t4 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jp2.add(t4, gbc);

        JLabel l5 = new JLabel("GENDER :");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        jp2.add(l5, gbc);

        r1 = new JRadioButton("MALE");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        jp2.add(r1, gbc);

        r2 = new JRadioButton("FEMALE");
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        jp2.add(r2, gbc);

        r3 = new JRadioButton("OTHERS");
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        jp2.add(r3, gbc);

        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        bg.add(r3);

        JLabel l6 = new JLabel("Summary :");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        jp2.add(l6, gbc);

        ta1 = new JTextArea(5, 40);
        JScrollPane sp1 = new JScrollPane(ta1);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        jp2.add(sp1, gbc);

        JLabel l7 = new JLabel("CONTACT ADDRESS :");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        jp2.add(l7, gbc);

        ta2 = new JTextArea(3, 40);
        JScrollPane sp2 = new JScrollPane(ta2);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        jp2.add(sp2, gbc);

        JLabel l8 = new JLabel("WEBSITE LINKS :");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        jp2.add(l8, gbc);

        ta3 = new JTextArea(3, 40);
        JScrollPane sp3 = new JScrollPane(ta3);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        jp2.add(sp3, gbc);

        JLabel l9 = new JLabel("EDUCATION :");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        jp2.add(l9, gbc);

        ta4 = new JTextArea(5, 40);
        JScrollPane sp4 = new JScrollPane(ta4);
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        jp2.add(sp4, gbc);

        JLabel l10 = new JLabel("EXPERIENCE :");
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        jp2.add(l10, gbc);

        ta5 = new JTextArea(5, 40);
        JScrollPane sp5 = new JScrollPane(ta5);
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        jp2.add(sp5, gbc);

        JLabel l11 = new JLabel("SKILLS :");
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 1;
        jp2.add(l11, gbc);

        ta6 = new JTextArea(3, 40);
        JScrollPane sp6 = new JScrollPane(ta6);
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        jp2.add(sp6, gbc);

        JLabel l12 = new JLabel("CERTIFICATIONS :");
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 1;
        jp2.add(l12, gbc);

        ta7 = new JTextArea(3, 40);
        JScrollPane sp7 = new JScrollPane(ta7);
        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        jp2.add(sp7, gbc);

        JLabel l13 = new JLabel("LANGUAGES :");
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 1;
        jp2.add(l13, gbc);

        ta8 = new JTextArea(3, 40);
        JScrollPane sp8 = new JScrollPane(ta8);
        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        jp2.add(sp8, gbc);

        JButton b1 = new JButton("OK");
        JButton b2 = new JButton("SAVE");
        JButton b3 = new JButton("CANCEL");
        JButton b4 = new JButton("RESET");

        // Add action listeners for the buttons
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "OK button clicked");
            }
        });

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveToFile();
            }
        });

        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Reset all fields
                t2.setText("");
                t3.setText("");
                t4.setText("");
                ta1.setText("");
                ta2.setText("");
                ta3.setText("");
                ta4.setText("");
                ta5.setText("");
                ta6.setText("");
                ta7.setText("");
                ta8.setText("");
                bg.clearSelection();
                imageLabel.setIcon(null);
            }
        });

        jp1.setLayout(new FlowLayout());
        jp3.setLayout(new FlowLayout());

        jp1.add(l0);
        jp3.add(b1);
        jp3.add(b2);
        jp3.add(b3);
        jp3.add(b4);

        add(jp1, BorderLayout.NORTH);
        add(jp2, BorderLayout.CENTER);
        add(jp3, BorderLayout.SOUTH);

        setSize(1400, 900);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void saveToFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                writer.write("Name: " + t2.getText() + "\n");
                writer.write("Profession: " + t3.getText() + "\n");
                writer.write("DOB: " + t4.getText() + "\n");
                writer.write("Gender: " + (r1.isSelected() ? "Male" : r2.isSelected() ? "Female" : "Others") + "\n");
                writer.write("Summary: " + ta1.getText() + "\n");
                writer.write("Contact Address: " + ta2.getText() + "\n");
                writer.write("Website Links: " + ta3.getText() + "\n");
                writer.write("Education: " + ta4.getText() + "\n");
                writer.write("Experience: " + ta5.getText() + "\n");
                writer.write("Skills: " + ta6.getText() + "\n");
                writer.write("Certifications: " + ta7.getText() + "\n");
                writer.write("Languages: " + ta8.getText() + "\n");

                if (imageFile != null) {
                    writer.write("Image Path: " + imageFile.getAbsolutePath() + "\n");
                } else {
                    writer.write("Image: Not uploaded\n");
                }

                JOptionPane.showMessageDialog(this, "File saved successfully.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage());
            }
        }
    }
    public static void main(String args[]) {
        new MyResume();
    }
}