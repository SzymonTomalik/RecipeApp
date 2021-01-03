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

@WebServlet(name = "AppEditPassword", value = "/app/password/edit")
public class AppEditPassword extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession ses = request.getSession();
        int adminId =(int)ses.getAttribute("loggedUser");
        String newPassword = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");
        if(!newPassword.equals(repeatPassword)){
            request.setAttribute("delete", 0);
            getServletContext().getRequestDispatcher("/appEditPassword.jsp").forward(request,response);
        } else if (newPassword.matches("[\\s]+")) {
            request.setAttribute("noData", 0);
            getServletContext().getRequestDispatcher("/appEditPassword.jsp").forward(request,response);
        } else {
            AdminsDao adminsDao = new AdminsDao();
            Admins admins = adminsDao.read(adminId);
            admins.setPassword(newPassword);
            adminsDao.update(admins);
            response.sendRedirect("/appPasswordChanged.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.sendRedirect("/appEditPassword.jsp");
    }
}
