package pl.coderslab.web;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.dao.AdminsDao;
import pl.coderslab.model.Admins;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminsDao adminsDao = new AdminsDao();
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Admins user = adminsDao.readByEmail(email);
//        System.out.println(user);

        boolean checkPass = BCrypt.checkpw(password, user.getPassword());
        if (checkPass && user.getEnable() == 1) {
            HttpSession oldSession = request.getSession();
            if (oldSession.getAttribute("loggedUser") == null || !oldSession.getAttribute("loggedUser").equals(user.getId())) {
                oldSession.invalidate();
                HttpSession newSession = request.getSession(true);
                newSession.setMaxInactiveInterval(1200);
                newSession.setAttribute("loggedUser", user.getId());
                response.sendRedirect("/app");
            }
        } else if (checkPass && user.getEnable() != 1) {
            response.sendRedirect("/notActiveUser.jsp");
        } else {
            response.sendRedirect("/wrongData.jsp");
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        HttpSession session = request.getSession();
        String msg = request.getParameter("msg");
        if (msg != null) {
            request.setAttribute("msg", msg);
        }
        if (session.getAttribute("loggedUser") == null) {
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        } else response.sendRedirect("/app");
    }
}
