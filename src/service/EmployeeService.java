package service;

import dao.DatabaseConnection;
import model.Employee;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class EmployeeService {

    private final DatabaseConnection databaseConnection = new DatabaseConnection();

    public Employee save(Employee employee) {
        if (checkNewEmployeeFields(employee)) {
            return databaseConnection.save(employee.getName(), employee.getLastName(), employee.getDepartment(), employee.getSalary());
        }
        return null;
    }

    private boolean checkNewEmployeeFields(Employee employee) {
        return !employee.getName().isBlank() && !employee.getLastName().isBlank();
    }


    public void deleteEmployeeById(int id) {
        databaseConnection.deleteEmployeeById(id);

    }


    public List<Employee> getAll() {
        try {
            return databaseConnection.findEmployeeList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Object[][] getAllEmployeeObject() throws SQLException {

        Object[][] data = new Object[1000][];

        List<Employee> employeeList = databaseConnection.findEmployeeList();

        for (int i = 0; i < databaseConnection.findEmployeeList().size(); i++) {
            data = new Object[][]{
                    {employeeList.get(i).getId(), employeeList.get(i).getName()}
            };
        }
        return data;
    }

    public Vector<Employee> getVectorEmployee() throws SQLException {
        Vector<Employee> employeeVector = new Vector<>();

        employeeVector.addAll(databaseConnection.findEmployeeList());

        return employeeVector;
    }


}



