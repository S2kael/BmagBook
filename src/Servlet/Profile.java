package Servlet;

import Database.DAO.UserDao;
import Database.Entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/profile")
public class Profile extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") == null) {
            resp.sendRedirect("/");
        } else {
            req.getRequestDispatcher("profile.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user")!=null){
            String first_name = req.getParameter("first-name");
            String last_name = req.getParameter("last-name");
            String email_mobile = req.getParameter("mobile-or-email");
            String year = req.getParameter("year");
            String day = req.getParameter("day");
            String month = req.getParameter("month");
            String sex = req.getParameter("sex");
            String birthday = day + "-" + month + "-" + year;
            UserDao userDao = new UserDao();

            User user = (User) req.getSession().getAttribute("user");
            user.setBirthday(birthday);
            user.setLast_name(last_name);
            user.setFirst_name(first_name);
            user.setEmail_mobile(email_mobile);
            user.setSex(sex);

            switch (userDao.update(user)){
                case 0:
                    req.getSession().setAttribute("user", user);
                    resp.sendRedirect("/profile");
                    return;
                case 1:
                    req.setAttribute("error","Don't Exists Id");
                    break;
                case 2:
                    req.setAttribute("error","Exists Email or Mobile phone");
                    break;
                case -1:
                    req.setAttribute("error","Database Error");
                    break;
            }   
            req.setAttribute("first-name",first_name);
            req.setAttribute("last-name",last_name);
            req.setAttribute("mobile-or-email",email_mobile);
            req.setAttribute("day",day);
            req.setAttribute("month",month);
            req.setAttribute("year",year);
            req.setAttribute("sex",sex);
            req.getRequestDispatcher("profile.jsp.jsp").forward(req,resp);
        }else {
            resp.sendRedirect("/");
        }
    }
}
