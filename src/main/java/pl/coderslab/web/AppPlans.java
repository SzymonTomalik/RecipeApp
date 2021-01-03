package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AppPlans", value = "/app/plan/list")
public class AppPlans extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("loggedUser");
//        System.out.println(id);
        PlanDao planDao = new PlanDao();
        request.setAttribute("plan", planDao.findAllByDate(id));
        getServletContext().getRequestDispatcher("/appPlanList.jsp").forward(request,response);
    }
}
