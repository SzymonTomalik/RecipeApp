package pl.coderslab.web;

import pl.coderslab.dao.AdminsDao;
import pl.coderslab.model.Admins;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AppEditUserData", value = "/app/userData/edit")
public class AppEditUserData extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        int loggedUser = (Integer)session.getAttribute("loggedUser");
        String userFirstName = request.getParameter("userFirstName");
        String userLastName = request.getParameter("userLastName");
        String userEmail = request.getParameter("userEmail");
        AdminsDao adminsDao = new AdminsDao();
        Admins user = adminsDao.read(loggedUser);
/*        if (!userFirstName.matches("[A-Za-z-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]+") || !userLastName.matches("[A-Za-z-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]+")) {
            request.setAttribute("noData", 0);
            request.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/appEditUserData.jsp").forward(request,response);
            return;
        }*/
        user.setFirstName(userFirstName);
        user.setLastName(userLastName);
        user.setEmail(userEmail);
        adminsDao.update(user);
        response.sendRedirect("/app");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        AdminsDao adminsDao = new AdminsDao();
        Admins user = adminsDao.read((Integer)session.getAttribute("loggedUser"));
        request.setAttribute("user", user);
        getServletContext().getRequestDispatcher("/appEditUserData.jsp").forward(request,response);
    }
}
