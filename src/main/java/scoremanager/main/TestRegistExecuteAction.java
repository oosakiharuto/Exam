package scoremanager.main;

import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import tool.Action;

public class TestRegistExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        // JSPから配列としてデータを一括で受け取る
        String[] studentNos = req.getParameterValues("student_no");
        String[] points = req.getParameterValues("point");
        String subjectCd = req.getParameter("subject_cd");
        String classNum = req.getParameter("class_num");
        int no = Integer.parseInt(req.getParameter("no"));

        List<Test> testList = new ArrayList<>();
        
        if (studentNos != null) {
            Subject subject = new Subject();
            subject.setCd(subjectCd);
            subject.setSchool(school);

            for (int i = 0; i < studentNos.length; i++) {
                // 点数が入力されている場合のみ保存対象にする
                if (points[i] != null && !points[i].isEmpty()) {
                    Test test = new Test();
                    Student student = new Student();
                    student.setNo(studentNos[i]);
                    
                    test.setStudent(student);
                    test.setSubject(subject);
                    test.setSchool(school);
                    test.setNo(no);
                    test.setPoint(Integer.parseInt(points[i]));
                    test.setClassNum(classNum);
                    
                    testList.add(test);
                }
            }
            // DAOを呼び出して一括保存
            TestDao dao = new TestDao();
            dao.save(testList);
        }

        req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
    }
}