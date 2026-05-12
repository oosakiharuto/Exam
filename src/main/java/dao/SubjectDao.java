package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {
    // 科目の登録・更新処理
    public boolean save(Subject subject) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;
        try {
            // データベースの仕様（H2やPostgreSQL等）に合わせて MERGE や ON DUPLICATE KEY に調整してください
            String sql = "INSERT INTO subject (cd, name, school_cd) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, subject.getCd());
            statement.setString(2, subject.getName());
            statement.setString(3, subject.getSchool().getCd());
            count = statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) statement.close();
            connection.close();
        }
        return count > 0;
    }

    // 全科目一覧の取得
    public List<Subject> filter(School school) throws Exception {
        List<Subject> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM subject WHERE school_cd = ? ORDER BY cd");
            statement.setString(1, school.getCd());
            rSet = statement.executeQuery();
            while (rSet.next()) {
                Subject subject = new Subject();
                subject.setCd(rSet.getString("cd"));
                subject.setName(rSet.getString("name"));
                subject.setSchool(school);
                list.add(subject);
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