package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        HttpSession session = req.getSession();

        Teacher teacher = (Teacher) session.getAttribute("user");

        ClassNumDao cDao = new ClassNumDao();
        List<String> classList = cDao.filter(teacher.getSchool());

        SubjectDao sDao = new SubjectDao();
        List<Subject> subjects = sDao.filter(teacher.getSchool());

        List<Integer> numList = new ArrayList<>();
        numList.add(1);
        numList.add(2);

        req.setAttribute("class_list", classList);
        req.setAttribute("subjects", subjects);
        req.setAttribute("num_list", numList);

        String classNum = req.getParameter("class_num");
        String subjectCd = req.getParameter("subject_cd");
        String noStr = req.getParameter("no");

        // 検索ボタンが押された場合
        if (classNum != null
                && subjectCd != null
                && noStr != null
                && !noStr.isEmpty()) {

            int no = Integer.parseInt(noStr);

            TestDao testDao = new TestDao();

            List<Test> tests =
                    testDao.filterForRegist(
                            classNum,
                            subjectCd,
                            no,
                            teacher.getSchool());

            req.setAttribute("tests", tests);
        }

        req.getRequestDispatcher("/scoremanager/main/test_regist.jsp")
                .forward(req, res);
    }
}