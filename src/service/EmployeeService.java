package service;

import dao.DatabaseConnection;
import model.Employee;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService {

    private final DatabaseConnection databaseConnection = new DatabaseConnection();

    public Employee save(Employee employee){
        if (checkNewEmployeeFields(employee)){
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









}



