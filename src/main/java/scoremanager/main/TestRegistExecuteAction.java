package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String subjectCd = req.getParameter("subject_cd");
        String classNum = req.getParameter("class_num");
        int no = Integer.parseInt(req.getParameter("no"));

        String[] studentNos = req.getParameterValues("student_no");
        String[] points = req.getParameterValues("point");

        List<Test> testList = new ArrayList<>();
        School school = teacher.getSchool();
        Subject subject = new Subject();
        subject.setCd(subjectCd);

        if (studentNos != null) {
            for (int i = 0; i < studentNos.length; i++) {
                // 空欄でなければ登録対象にする
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
            TestDao testDao = new TestDao();
            testDao.save(testList);
        }

        req.getRequestDispatcher("/scoremanager/main/test_regist_done.jsp").forward(req, res);
    }
}