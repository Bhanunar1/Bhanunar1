import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Client implements ActionListener {
    JTextField text;
    static JPanel a1;
    static Box vertical = Box.createVerticalBox();
    static JFrame f = new JFrame();
    static DataOutputStream dout;
    static DataInputStream din;
    static Socket s;

    Client() {
        f.setLayout(null);
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(10, 100, 80));
        p1.setBounds(0, 0, 450, 70);
        p1.setLayout(null);
        f.add(p1);

        ImageIcon i1 = new ImageIcon("C:/chatting/back.png");
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(5, 20, 25, 25);
        p1.add(back);

        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);
            }
        });

        ImageIcon i4 = new ImageIcon("C:/chatting/profile.jpg");
        Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel profile = new JLabel(i6);
        profile.setBounds(40, 10, 50, 50);
        p1.add(profile);

        ImageIcon i7 = new ImageIcon("C:/chatting/video.png");
        Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel video = new JLabel(i9);
        video.setBounds(300, 20, 30, 30);
        p1.add(video);
        video.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae) {
                JOptionPane.showMessageDialog(f, "Video call functionality not implemented yet.");
            }
        });

        ImageIcon i10 = new ImageIcon("C:/chatting/phone.png");
        Image i11 = i10.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel phone = new JLabel(i12);
        phone.setBounds(360, 20, 35, 30);
        p1.add(phone);
        phone.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae) {
                JOptionPane.showMessageDialog(f, "Voice call functionality not implemented yet.");
            }
        });


        ImageIcon i13 = new ImageIcon("C:/chatting/more.png");
        Image i14 = i13.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel moreVert = new JLabel(i15);
        moreVert.setBounds(420, 20, 10, 25);
        p1.add(moreVert);
        moreVert.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae) {
                JPopupMenu popup = new JPopupMenu();
                JMenuItem fileItem = new JMenuItem("File");
                JMenuItem heartItem = new JMenuItem("❤️");
                JMenuItem smileItem = new JMenuItem("😄");
                JMenuItem emojiItem = new JMenuItem("😊");

                fileItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JFileChooser chooser = new JFileChooser();
                        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp");
                        chooser.setFileFilter(filter);
                        int returnVal = chooser.showOpenDialog(f);
                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            File file = chooser.getSelectedFile();
                            try {
                                dout.writeUTF("file:" + file.getAbsolutePath());
                                FileInputStream fis = new FileInputStream(file);
                                byte[] buffer = new byte[4096];
                                int bytesRead;
                                while ((bytesRead = fis.read(buffer))!= -1) {
                                    dout.write(buffer, 0, bytesRead);
                                }
                                fis.close();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });

                heartItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String out = "❤️";
                        JPanel p2 = formatLabel(out);

                        vertical.add(p2);
                        vertical.add(Box.createVerticalStrut(15));
                        a1.add(vertical, BorderLayout.PAGE_START);

                        try {
                            dout.writeUTF(out);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                        text.setText("");
                        f.repaint();
                        f.invalidate();
                        f.validate();
                    }
                });

                smileItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String out = "😄";
                        JPanel p2 = formatLabel(out);

                        vertical.add(p2);
                        vertical.add(Box.createVerticalStrut(15));
                        a1.add(vertical, BorderLayout.PAGE_START);

                        try {
                            dout.writeUTF(out);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                        text.setText("");
                        f.repaint();
                        f.invalidate();
                        f.validate();
                    }
                });

                emojiItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String out = "😊";
                        JPanel p2 = formatLabel(out);

                        vertical.add(p2);
                        vertical.add(Box.createVerticalStrut(15));
                        a1.add(vertical, BorderLayout.PAGE_START);

                        try {
                            dout.writeUTF(out);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                        text.setText("");
                        f.repaint();
                        f.invalidate();
                        f.validate();
                    }
                });

                popup.add(fileItem);
                popup.add(heartItem);
                popup.add(smileItem);
                popup.add(emojiItem);

                popup.show(moreVert, ae.getX(), ae.getY());
            }
        });
        
        JLabel name = new JLabel("Tony Stark");
        name.setBounds(110, 15, 100, 18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        p1.add(name);

        JLabel status = new JLabel("Active now");
        status.setBounds(110, 35, 100, 18);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF", Font.BOLD, 14));
        p1.add(status);

        
        a1 = new JPanel();
        a1.setLayout(new BorderLayout());
        vertical = Box.createVerticalBox();
        a1.add(vertical, BorderLayout.PAGE_START);
        JScrollPane scrollPane = new JScrollPane(a1);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(5, 75, 440, 570);
        f.add(scrollPane);

        text = new JTextField();
        text.setBounds(5, 655, 310, 40);
        text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f.add(text);

        JButton send = new JButton("Send");
        send.setBounds(320, 655, 123, 40);
        send.setBackground(new Color(7, 94, 84));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        send.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f.add(send);

        f.setSize(450, 700);
        f.setLocation(200, 50);
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.RED);
        f.setVisible(true);

        try {
            s = new Socket("127.0.0.1", 6001);
            dout = new DataOutputStream(s.getOutputStream());
            din = new DataInputStream(s.getInputStream());
            new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            String msg = din.readUTF();
                            JPanel panel = formatLabel(msg);
                            JPanel left = new JPanel(new BorderLayout());
                            left.add(panel, BorderLayout.LINE_START);
                            vertical.add(left);
                            vertical.add(Box.createVerticalStrut(15));
                            a1.add(vertical, BorderLayout.PAGE_START);
                            f.validate();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void actionPerformed(ActionEvent ae) {
        try {
            String out = text.getText();
            JPanel p2 = formatLabel(out);

            JPanel left = new JPanel(new BorderLayout());
            left.add(p2, BorderLayout.LINE_END);
            vertical.add(left);
            vertical.add(Box.createVerticalStrut(15));
            a1.add(vertical, BorderLayout.PAGE_START);

            dout.writeUTF(out);
            text.setText("");

            f.repaint();
            f.invalidate();
            f.validate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JPanel formatLabel(String out) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel output = new JLabel("<html><p style=\"width:150px\">" + out + "</p></html>");
        output.setFont(new Font("Tahoma", Font.PLAIN, 16));
        output.setBackground(new Color(0, 0, 255));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15, 15, 15, 50));
        panel.add(output);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);
        return panel;
    }

    public static void main(String[] args) {
        new Client();
    }
}