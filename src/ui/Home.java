package ui;

import model.Employee;
import service.EmployeeService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

public class Home {
    public JPanel rootPanel;
    private JTextField userNameField;
    private JButton saveButton;
    private JTextField lastnameField;
    private JTextField departmentField;
    private JTextField salaryField;
    private JButton kullaniciSilButton;
    private JTable employeeTable;

    private final EmployeeService employeeService = new EmployeeService();


    public Home() {

        for (Employee employee : employeeService.getAll()) {

            Object[][] data = {
                    {employee.getId(), employee.getName(), employee.getLastName(), employee.getDepartment(), employee.getSalary()}
            };

            employeeTable.setModel(new DefaultTableModel(
                    data ,
                    new String[]{"Id", "Ad", "Soyad", "Departman", "Maas"}
            ));
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
    }
}
