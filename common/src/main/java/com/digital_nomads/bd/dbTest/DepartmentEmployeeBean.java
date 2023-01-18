package com.digital_nomads.bd.dbTest;

import com.digital_nomads.bd.utilsDB.DBConnection;
import lombok.Data;
import org.apache.commons.dbutils.BeanProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Data
public class DepartmentEmployeeBean {

    String dept_no;

    String dept_name;

    public DepartmentEmployeeBean() {}

    public DepartmentEmployeeBean(String dept_no, String dept_name) {
        this.dept_no = dept_no;
        this.dept_name = dept_name;
    }

    public DepartmentEmployeeBean(ResultSet rs) throws SQLException {
        new BeanProcessor().populateBean(rs, this);
    }

    public static List<DepartmentEmployeeBean> getAllDepartments()throws SQLException {
        String query = "SELECT * FROM employees.departments";
        try(ResultSet resultSet = DBConnection.query(query)){
            return new BeanProcessor().toBeanList(resultSet, DepartmentEmployeeBean.class);
        }
    }

    public static DepartmentEmployeeBean getBy(String column, String value) throws SQLException {
        String query = "SELECT * FROM employees.departments WHERE " + column + " = ?;";
        ResultSet rs = DBConnection.query(query, value);
        if(!rs.next()) return null;
        return new BeanProcessor().toBean(rs, DepartmentEmployeeBean.class);
    }
}
