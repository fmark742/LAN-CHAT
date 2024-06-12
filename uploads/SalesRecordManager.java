package test2_1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

@SuppressWarnings("serial")
public class SalesRecordManager extends JFrame {

    JMenuBar mainMenu = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenu viewMenu = new JMenu("View");
    JMenuItem newRecordMenuItem = new JMenuItem("New Record");
    JMenuItem showMenuItem = new JMenuItem("Show");
    JMenuItem exitMenuItem = new JMenuItem("Exit");
    JTextArea txtInfo = new JTextArea(30, 50);
    JScrollPane scrollPane = new JScrollPane(txtInfo);
    static File salesFile = new File("sales.txt");
    static NewRecordFrame newRecordFrame = new NewRecordFrame("Add New Record");

    public SalesRecordManager(String title) {
        super(title);
        Container container = getContentPane();
        container.setLayout(new BorderLayout(20, 15));

        txtInfo.setEditable(false);
        container.add(scrollPane, BorderLayout.CENTER);

        setJMenuBar(mainMenu);
        mainMenu.add(fileMenu);
        mainMenu.add(viewMenu);
        fileMenu.add(newRecordMenuItem);
        fileMenu.add(exitMenuItem);
        viewMenu.add(showMenuItem);

        MenuActions menuActions = new MenuActions();
        newRecordMenuItem.addActionListener(menuActions);
        showMenuItem.addActionListener(menuActions);
        exitMenuItem.addActionListener(menuActions);

        setSize(600, 400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            if (salesFile.createNewFile()) {
                JOptionPane.showMessageDialog(null, "File Created!", "File", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "File already exists!", "Inform", JOptionPane.WARNING_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public class MenuActions implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == newRecordMenuItem) {
                newRecordFrame.setVisible(true);
            } else if (ae.getSource() == showMenuItem) {
                try {
                    displayRecords();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else if (ae.getSource() == exitMenuItem) {
                dispose();
                System.exit(0);
            }
        }
    }

    private void displayRecords() throws IOException {
        txtInfo.setText("");
        try (BufferedReader br = new BufferedReader(new FileReader(salesFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] record = line.split(",");
                if (record.length == 4) {
                    txtInfo.append("Student Number: " + record[0] + "\n");
                    txtInfo.append("Student Name: " + record[1] + "\n");
                    txtInfo.append("Total Sales: " + record[2] + "\n");
                    txtInfo.append("Commission: " + record[3] + "\n");
                    txtInfo.append("--------------------------\n");
                }
            }
        }
        JOptionPane.showMessageDialog(this, "Records displayed!", "Output", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new SalesRecordManager("Sales Record Manager");
    }
}

class NewRecordFrame extends JFrame {
    JTextField txtSalesmanNumber = new JTextField(10);
    JTextField txtSalesmanName = new JTextField(20);
    JTextField txtTotalSales = new JTextField(10);
    JTextField txtCommission = new JTextField(10);
    JButton btnAdd = new JButton("ADD");
    JButton btnBack = new JButton("BACK");

    public NewRecordFrame(String title) {
        super(title);
        Container container = getContentPane();
        container.setLayout(new GridLayout(5, 2, 10, 10));

        container.add(new JLabel("Salesman Number:"));
        container.add(txtSalesmanNumber);
        container.add(new JLabel("Salesman Name:"));
        container.add(txtSalesmanName);
        container.add(new JLabel("Total Sales:"));
        container.add(txtTotalSales);
        container.add(new JLabel("Commission:"));
        container.add(txtCommission);
        txtCommission.setEditable(false);

        container.add(btnAdd);
        container.add(btnBack);

        btnAdd.addActionListener(e -> addRecord());
        btnBack.addActionListener(e -> this.setVisible(false));

        pack();
        setLocationRelativeTo(null);
    }

    private void addRecord() {
        try {
            int totalSales = Integer.parseInt(txtTotalSales.getText());
            double commission = calculateCommission(totalSales);
            txtCommission.setText(String.valueOf(commission));

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(SalesRecordManager.salesFile, true))) {
                bw.write(txtSalesmanNumber.getText() + "," + txtSalesmanName.getText() + "," + totalSales + "," + commission);
                bw.newLine();
            }

            JOptionPane.showMessageDialog(this, "Record Added!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } catch (NumberFormatException | IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double calculateCommission(int totalSales) {
        if (totalSales <= 5000) return totalSales * 0.15;
        if (totalSales <= 10000) return totalSales * 0.25;
        if (totalSales <= 15000) return totalSales * 0.35;
        if (totalSales <= 20000) return totalSales * 0.45;
        return totalSales * 0.50;
    }

    private void clearFields() {
        txtSalesmanNumber.setText("");
        txtSalesmanName.setText("");
        txtTotalSales.setText("");
        txtCommission.setText("");
    }
}
