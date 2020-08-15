package Servlet;

import Database.DAO.UserDao;
import Database.Entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user")==null){
            req.getRequestDispatcher("register.jsp").forward(req,resp);
        }else {
            resp.sendRedirect("/");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user")==null){
            String first_name = req.getParameter("first-name");
            String last_name = req.getParameter("last-name");
            String email_mobile = req.getParameter("mobile-or-email");
            String re_email_mobile = req.getParameter("re-mobile-or-email");
            String password = req.getParameter("user-password");
            String day = req.getParameter("day");
            String month = req.getParameter("month");
            String year = req.getParameter("year");
            String sex = req.getParameter("sex");
            String birthday = day + "-" + month + "-" + year;

            UserDao userDao = new UserDao();

            int id = userDao.getLastId() + 1;

            User user = new User(id, first_name, last_name, email_mobile, password, sex, birthday);


            switch (userDao.insert(user)){
                case 0:
                    req.getSession().setAttribute("user", user);
                    resp.sendRedirect("/");
                    return;
                case 1:
                    req.setAttribute("error","Exists Email or Mobile phone");
                    break;
                case -1:
                    req.setAttribute("error","Database Error");
                    break;
            }

            req.setAttribute("first-name",first_name);
            req.setAttribute("last-name",last_name);
            req.setAttribute("mobile-or-email",email_mobile);
            req.setAttribute("re-mobile-or-email",re_email_mobile);
            req.setAttribute("day",day);
            req.setAttribute("month",month);
            req.setAttribute("year",year);
            req.setAttribute("sex",sex);
            req.getRequestDispatcher("register.jsp").forward(req,resp);
        }else {
            resp.sendRedirect("/");
        }
    }
}
