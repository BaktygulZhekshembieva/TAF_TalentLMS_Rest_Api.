package com.digital_nomads.bd.dbTest;



import com.digital_nomads.bd.utilsDB.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Tst {
    public static void main(String[] args) throws SQLException {
        DBConnection.open("employees");
//        DBConnection.insertBean("INSERT INTO tab VALUES(?, ?, ? ,?, ?)", new Bean(6, "John", "Doe", null, 10));
        ResultSet rs = DBConnection.query("SELECT * FROM employees WHERE first_name = ?;", "Remzi");
        rs.next();
        System.out.println(new EmployeeBean(rs));
//        EmployeeBean.getAllUsers().forEach(System.out::println);
//        System.out.println(EmployeeBean.getBy("first_name", "lion"));
        DBConnection.queryToList("SELECT * FROM employees WHERE first_name = 'Parto'';").forEach(System.out::println);
        DBConnection.close();

    }
}