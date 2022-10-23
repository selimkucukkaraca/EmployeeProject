package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {
    private int id;
    private String name;
    private String lastName;
    private String department;
    private int salary;

    public Employee(String name, String lastName, String department, int salary) {
        this.name = name;
        this.lastName = lastName;
        this.department = department;
        this.salary = salary;
    }



}
