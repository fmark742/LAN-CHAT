package Test;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class gui_act6 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel mainPane;
    private JTextField textField;
    private JTextField textField_2;
    private JTextField textField_1;
    private JPanel input;
    private JPanel input_1;
    private JPanel input_2;
    private JPanel computePanel;
    private JTextField tuitionField;
    private JTextField miscField;
    private JTextField totalField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    gui_act6 frame = new gui_act6();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public gui_act6() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 733, 528);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setToolTipText("File");
        setJMenuBar(menuBar);

        JMenu mnNewMenu = new JMenu("File");
        menuBar.add(mnNewMenu);

        JMenuItem mntmNewMenuItem = new JMenuItem("New");
        mntmNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainPane.setVisible(true);
                input.setVisible(true);
                input_1.setVisible(false);
                input_2.setVisible(false);
                computePanel.setVisible(false);
            }
        });
        mnNewMenu.add(mntmNewMenuItem);

        JMenuItem mntmNewMenuItem_1 = new JMenuItem("Show");
        mntmNewMenuItem_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainPane.setVisible(true);
                input_1.setVisible(true);
                input.setVisible(false);
                input_2.setVisible(false);
                computePanel.setVisible(false);
            }
        });
        mnNewMenu.add(mntmNewMenuItem_1);

        JMenuItem mntmNewMenuItem_2 = new JMenuItem("Exit");
        mntmNewMenuItem_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mnNewMenu.add(mntmNewMenuItem_2);

        JMenu mnNewMenu2 = new JMenu("Fees");
        menuBar.add(mnNewMenu2);

        JMenuItem mntmNewMenuItem_3 = new JMenuItem("Input fees");
        mntmNewMenuItem_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainPane.setVisible(true);
                input_2.setVisible(true);
                input_1.setVisible(false);
                input.setVisible(false);
                computePanel.setVisible(false);
            }
        });
        mnNewMenu2.add(mntmNewMenuItem_3);

        JMenuItem mntmNewMenuItem_4 = new JMenuItem("Compute");
        mntmNewMenuItem_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainPane.setVisible(true);
                computePanel.setVisible(true);
                input_1.setVisible(false);
                input.setVisible(false);
                input_2.setVisible(false);
            }
        });
        mnNewMenu2.add(mntmNewMenuItem_4);

        JMenu mnNewMenu3 = new JMenu("Help");
        menuBar.add(mnNewMenu3);

        JMenuItem mntmNewMenuItem_5 = new JMenuItem("Topics");
        mnNewMenu3.add(mntmNewMenuItem_5);

        JMenuItem mntmNewMenuItem_6 = new JMenuItem("About");
        mnNewMenu3.add(mntmNewMenuItem_6);

        mainPane = new JPanel();
        mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        mainPane.setVisible(false);
        setContentPane(mainPane);
        mainPane.setLayout(null);

        input = new JPanel();
        input.setBounds(0, 0, 246, 188);
        mainPane.add(input);
        input.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        JLabel lblNewLabel = new JLabel("Student Number: ");
        input.add(lblNewLabel);

        textField = new JTextField();
        textField.setColumns(10);
        input.add(textField);

        JLabel lblNewLabel_2 = new JLabel("Student Name:    ");
        input.add(lblNewLabel_2);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        input.add(textField_2);

        JPanel panel = new JPanel();
        input.add(panel);

        JButton btnNewButton = new JButton("save");
        panel.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("clear");
        panel.add(btnNewButton_1);

        input_1 = new JPanel();
        input_1.setLayout(null);
        input_1.setBounds(0, 0, 719, 469);
        mainPane.add(input_1);

        JTextArea txtrStudentNumber = new JTextArea();
        txtrStudentNumber.setText("Student Number\tStudent Name\t\tTotal Fees");
        txtrStudentNumber.setBounds(140, 123, 417, 198);
        input_1.add(txtrStudentNumber);

        input_2 = new JPanel();
        input_2.setLayout(null);
        input_2.setBounds(0, 0, 370, 240);
        mainPane.add(input_2);

        JLabel lblNewLabel_1 = new JLabel("Tuition Fee: ");
        lblNewLabel_1.setBounds(24, 10, 73, 13);
        input_2.add(lblNewLabel_1);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(107, 7, 96, 19);
        input_2.add(textField_1);

        JLabel lblNewLabel_3 = new JLabel("Miscellanous:");
        lblNewLabel_3.setBounds(23, 33, 86, 13);
        input_2.add(lblNewLabel_3);

        JCheckBox chckbxNewCheckBox = new JCheckBox("Library - 150");
        chckbxNewCheckBox.setBounds(110, 52, 107, 21);
        input_2.add(chckbxNewCheckBox);

        JCheckBox chckbxId = new JCheckBox("ID - 75");
        chckbxId.setBounds(107, 75, 110, 21);
        input_2.add(chckbxId);

        JCheckBox chckbxMedical = new JCheckBox("Medical - 250");
        chckbxMedical.setBounds(223, 52, 107, 21);
        input_2.add(chckbxMedical);

        JCheckBox chckbxNewCheckBox_2_1 = new JCheckBox("Council - 50");
        chckbxNewCheckBox_2_1.setBounds(223, 75, 107, 21);
        input_2.add(chckbxNewCheckBox_2_1);

        JButton btnNewButton_2 = new JButton("ok");
        btnNewButton_2.setBounds(24, 102, 57, 21);
        input_2.add(btnNewButton_2);

        // Compute Panel
        computePanel = new JPanel();
        computePanel.setLayout(null);
        computePanel.setBounds(0, 0, 719, 469);
        mainPane.add(computePanel);

        JLabel lblTuition = new JLabel("Tuition:");
        lblTuition.setBounds(140, 123, 80, 25);
        computePanel.add(lblTuition);

        tuitionField = new JTextField();
        tuitionField.setBounds(230, 123, 150, 25);
        tuitionField.setEditable(false);
        computePanel.add(tuitionField);

        JLabel lblMisc = new JLabel("Miscellaneous:");
        lblMisc.setBounds(140, 153, 80, 25);
        computePanel.add(lblMisc);

        miscField = new JTextField();
        miscField.setBounds(230, 153, 150, 25);
        miscField.setEditable(false);
        computePanel.add(miscField);

        JButton btnTotal = new JButton("Total");
        btnTotal.setBounds(140, 183, 80, 25);
        btnTotal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double tuition = Double.parseDouble(tuitionField.getText());
                    double miscellaneous = Double.parseDouble(miscField.getText());
                    double total = tuition + miscellaneous;
                    totalField.setText(String.valueOf(total));
                } catch (NumberFormatException ex) {
                    totalField.setText("Invalid Input");
                }
            }
        });
        computePanel.add(btnTotal);

        totalField = new JTextField();
        totalField.setBounds(230, 183, 150, 25);
        totalField.setEditable(false);
        computePanel.add(totalField);

        // Initially hide the compute panel
        computePanel.setVisible(false);
    }
}
