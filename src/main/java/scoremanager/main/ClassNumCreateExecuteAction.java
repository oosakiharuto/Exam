package scoremanager.main;

import bean.ClassNum;
import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassNumCreateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        String classNumStr = req.getParameter("class_num");

        ClassNum classNum = new ClassNum();
        classNum.setClass_num(classNumStr);
        classNum.setSchool(teacher.getSchool());

        ClassNumDao dao = new ClassNumDao();
        dao.save(classNum);

        req.getRequestDispatcher("/scoremanager/main/class_num_create_done.jsp").forward(req, res);
    }
}