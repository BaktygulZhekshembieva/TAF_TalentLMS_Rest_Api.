package com.digital_nomads.bd.dbTest;

import com.digital_nomads.bd.utilsDB.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDepartment {
    public static void main(String[] args) throws SQLException {
        DBConnection.open("employees");
//        DBConnection.insertBean("INSERT INTO tab VALUES(?, ?, ? ,?, ?)", new Bean(6, "John", "Doe", null, 10));
        ResultSet rs = DBConnection.query("SELECT * FROM employees.departments WHERE dept_name = ?;", "Customer Service");
        rs.next();
        System.out.println(new DepartmentEmployeeBean(rs));
//        DepartmentEmployeeBean.getAllDepartments().forEach(System.out::println);
//        System.out.println(DepartmentEmployeeBean.getBy("dept_name","Customer Service"));
        DBConnection.close();

    }
}
