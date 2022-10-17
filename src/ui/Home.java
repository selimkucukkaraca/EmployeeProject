package ui;

import model.Employee;
import service.EmployeeService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Vector;


public class Home {
    public JPanel rootPanel;
    private JTextField userNameField;
    private JButton saveButton;
    private JTextField lastnameField;
    private JTextField departmentField;
    private JTextField salaryField;
    private JButton kullaniciSilButton;
    private JTable employeeTable;
    private JButton tabloyuGuncelleButton;
    private JTextField aramaCubugu;


    private final EmployeeService employeeService = new EmployeeService();


    public void dinamikAra(String ara) {

        TableRowSorter<DefaultTableModel> tableModelTableRowSorter = new TableRowSorter<DefaultTableModel>((DefaultTableModel) employeeTable.getModel());
        employeeTable.setRowSorter(tableModelTableRowSorter);
        tableModelTableRowSorter.setRowFilter(RowFilter.regexFilter(ara));

    }


    public Home() throws SQLException {

        for (Employee employee : employeeService.getAll()) {

            Vector<Employee> data = employeeService.getVectorEmployee();

            Vector<String> column = new Vector<>();
            column.add("id");
            column.add("name");
            column.add("lastname");
            column.add("department");
            column.add("salary");

            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.setColumnIdentifiers(column);


            for (Employee employee1 : employeeService.getAll()) {
                Object[] row = {employee1.getId(), employee1.getName(), employee1.getLastName(), employee1.getDepartment(), employee1.getSalary()};
                tableModel.addRow(row);
            }
            employeeTable.setModel(tableModel);
        }


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userNameField.getText();
                String lastname = lastnameField.getText();
                String department = departmentField.getText();
                String salary = salaryField.getText();

                Employee employee = new Employee(username, lastname, department, Integer.parseInt(salary));

                employeeService.save(employee);
                departmentField.setText("");
                lastnameField.setText("");
                userNameField.setText("");
                salaryField.setText("");
                JOptionPane.showMessageDialog(rootPanel, employee + " calisan eklendi");

            }
        });


        kullaniciSilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        employeeTable.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });

        kullaniciSilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String id = JOptionPane.showInputDialog(rootPanel, "id");
                int response = JOptionPane.showConfirmDialog(rootPanel, "silmek istediginize emin misiniz ?");
                if (response == JOptionPane.YES_OPTION) {
                    employeeService.deleteEmployeeById(Integer.parseInt(id));
                } else {
                    JOptionPane.showMessageDialog(rootPanel, "silme islemi iptal edildi.");
                }

            }
        });


        aramaCubugu.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String ara = aramaCubugu.getText();
                dinamikAra(ara);
            }
        });
    }
}
