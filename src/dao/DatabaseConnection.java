package dao;

import model.Employee;
import utils.DatabaseInformation;
import utils.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {

    private Connection con = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;




    public DatabaseConnection(){
        String url = "jdbc:mysql://" + DatabaseInformation.host + ":" + DatabaseInformation.port + "/" + DatabaseInformation.db_name+ "?useUnicode=true&characterEncoding=utf8";


        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException ex) {
            System.out.println("Driver Bulunamadı....");
        }

        try {
            con = DriverManager.getConnection(url, DatabaseInformation.user_name, DatabaseInformation.password);
            System.out.println("Bağlantı Başarılı...");

        } catch (SQLException ex) {
            System.out.println("Bağlantı Başarısız...");
        }
    }




    public List<Employee> findEmployeeList() throws SQLException {
        statement = con.createStatement();
        final List<Employee> employeeList = new ArrayList<>();

        String query =  Query.findAllQuery + "calisanlar";

        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("ad");
                String lastName = resultSet.getString("soyad");
                String department = resultSet.getString("departman");
                int salary = resultSet.getInt("maas");

                employeeList.add(new Employee(id, name, lastName, department, salary));
            }
            return employeeList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteEmployeeById(int id){
        String query = Query.deleteById;

        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    public Employee save(String name,String lastname,String department,int salary){
        String query = Query.save;

        try {
            preparedStatement = con.prepareStatement(query);
            Employee employee = new Employee(name, lastname, department, salary);


                preparedStatement.setString(1, employee.getName());
                preparedStatement.setString(2, employee.getLastName());
                preparedStatement.setString(3, employee.getDepartment());
                preparedStatement.setString(4, String.valueOf(employee.getSalary()));

                preparedStatement.executeUpdate();

            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public boolean login(String username,String password){
        String query = Query.login;

        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }







}

