package scoremanager.main;

import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassListAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // ログイン中の教員情報を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // DAOを使って、自分の学校のクラス一覧を取得
        ClassNumDao dao = new ClassNumDao();
        List<String> list = dao.filter(teacher.getSchool());

        // JSPにデータを渡す
        req.setAttribute("class_list", list);
        
        // クラス一覧画面へフォワード
        req.getRequestDispatcher("/scoremanager/main/class_list.jsp").forward(req, res);
    }
}