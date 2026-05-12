package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends Dao {
    public boolean save(ClassNum classNum) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;
        try {
            String sql = "INSERT INTO CLASS_NUM (SCHOOL_CD, CLASS_NUM) VALUES (?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, classNum.getSchool().getCd());
            statement.setString(2, classNum.getClassNum());
            count = statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) statement.close();
            connection.close();
        }
        return count > 0;
    }

    public List<String> filter(School school) throws Exception {
        List<String> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;
        try {
            String sql = "SELECT CLASS_NUM FROM CLASS_NUM WHERE SCHOOL_CD = ? ORDER BY CLASS_NUM";
            statement = connection.prepareStatement(sql);
            statement.setString(1, school.getCd());
            rSet = statement.executeQuery();
            while (rSet.next()) {
                list.add(rSet.getString("CLASS_NUM"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rSet != null) rSet.close();
            if (statement != null) statement.close();
            connection.close();
        }
        return list;
    }
}