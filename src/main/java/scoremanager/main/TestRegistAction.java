package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // ログイン中の教員（ユーザー）情報を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // クラスのプルダウンメニュー用に、クラス一覧を取得
        ClassNumDao cDao = new ClassNumDao();
        List<String> classList = cDao.filter(teacher.getSchool());

        // 科目のプルダウンメニュー用に、科目一覧を取得
        SubjectDao sDao = new SubjectDao();
        List<Subject> subjects = sDao.filter(teacher.getSchool());

        // テストの回数（1回目、2回目）のリストを作成
        List<Integer> numList = new ArrayList<>();
        numList.add(1);
        numList.add(2);

        // 取得したデータをJSPで使えるようにセット
        req.setAttribute("class_list", classList);
        req.setAttribute("subjects", subjects);
        req.setAttribute("num_list", numList);

        // 成績登録画面（test_regist.jsp）へ移動
        req.getRequestDispatcher("/scoremanager/main/test_regist.jsp").forward(req, res);
    }
}