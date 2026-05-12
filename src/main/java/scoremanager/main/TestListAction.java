package scoremanager.main;

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

public class TestListAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // プルダウン（検索条件）用にクラス一覧と科目一覧を取得
        ClassNumDao cDao = new ClassNumDao();
        List<String> classList = cDao.filter(teacher.getSchool());
        SubjectDao sDao = new SubjectDao();
        List<Subject> subjects = sDao.filter(teacher.getSchool());
        
        req.setAttribute("class_list", classList);
        req.setAttribute("subjects", subjects);

        // 検索ボタンが押されて、条件が送られてきた場合の処理
        String classNum = req.getParameter("class_num");
        String subjectCd = req.getParameter("subject_cd");
        String noStr = req.getParameter("no");

        if (classNum != null && subjectCd != null && noStr != null && !noStr.isEmpty()) {
            int no = Integer.parseInt(noStr);
            TestDao testDao = new TestDao();
            // 条件に合う成績一覧を取得
            List<Test> tests = testDao.filter(classNum, subjectCd, no, teacher.getSchool());
            req.setAttribute("tests", tests);
        }

        // 成績参照画面（test_list.jsp）へ移動
        req.getRequestDispatcher("/scoremanager/main/test_list.jsp").forward(req, res);
    }
}