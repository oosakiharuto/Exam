package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Test;

public class TestDao extends Dao {
    // 一括保存
    public boolean save(List<Test> testList) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        boolean result = false;
        try {
            connection.setAutoCommit(false);
            String sql = "MERGE INTO TEST KEY(STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO) VALUES (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            for (Test test : testList) {
                statement.setString(1, test.getStudent().getNo());
                statement.setString(2, test.getSubject().getCd());
                statement.setString(3, test.getSchool().getCd());
                statement.setInt(4, test.getNo());
                statement.setInt(5, test.getPoint());
                statement.setString(6, test.getClassNum());
                statement.addBatch();
            }
            statement.executeBatch();
            connection.commit();
            result = true;
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            if (statement != null) statement.close();
            connection.setAutoCommit(true);
            connection.close();
        }
        return result;
    }

    // 成績参照用の検索
    public List<Test> filter(String classNum, String subjectCd, int no, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;
        try {
            String sql = "SELECT t.STUDENT_NO, s.NAME, t.POINT FROM TEST t JOIN STUDENT s ON t.STUDENT_NO = s.NO WHERE t.CLASS_NUM = ? AND t.SUBJECT_CD = ? AND t.NO = ? AND t.SCHOOL_CD = ? ORDER BY t.STUDENT_NO";
            statement = connection.prepareStatement(sql);
            statement.setString(1, classNum);
            statement.setString(2, subjectCd);
            statement.setInt(3, no);
            statement.setString(4, school.getCd());
            rSet = statement.executeQuery();
            while (rSet.next()) {
                Test test = new Test();
                Student student = new Student();
                student.setNo(rSet.getString("STUDENT_NO"));
                student.setName(rSet.getString("NAME"));
                test.setStudent(student);
                test.setPoint(rSet.getInt("POINT"));
                list.add(test);
            }
        } finally {
            if (rSet != null) rSet.close();
            if (statement != null) statement.close();
            connection.close();
        }
        return list;
    }

    // ★追加：成績登録画面用の検索（学生全員＋入力済みの点数）
    public List<Test> filterForRegist(String classNum, String subjectCd, int no, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;
        try {
            String sql = "SELECT s.NO as STUDENT_NO, s.NAME, t.POINT FROM STUDENT s LEFT JOIN TEST t ON s.NO = t.STUDENT_NO AND t.SUBJECT_CD = ? AND t.NO = ? WHERE s.CLASS_NUM = ? AND s.SCHOOL_CD = ? ORDER BY s.NO";
            statement = connection.prepareStatement(sql);
            statement.setString(1, subjectCd);
            statement.setInt(2, no);
            statement.setString(3, classNum);
            statement.setString(4, school.getCd());
            rSet = statement.executeQuery();
            while (rSet.next()) {
                Test test = new Test();
                Student student = new Student();
                student.setNo(rSet.getString("STUDENT_NO"));
                student.setName(rSet.getString("NAME"));
                test.setStudent(student);
                
                if (rSet.getObject("POINT") == null) {
                    test.setPoint(-1); // まだ点数がない場合は-1をセット
                } else {
                    test.setPoint(rSet.getInt("POINT"));
                }
                list.add(test);
            }
        } finally {
            if (rSet != null) rSet.close();
            if (statement != null) statement.close();
            connection.close();
        }
        return list;
    }
}