package Servlet;

import Database.DAO.UserDao;
import Database.Entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user")==null){
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }else{
            resp.sendRedirect("/");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") == null){
            String email_phone = req.getParameter("user-login");
            String password = req.getParameter("user-login-password");
            Optional<User> userOptional = new UserDao().get(email_phone);
            if (userOptional.isPresent()){
                User user = userOptional.get();
                if (user.getPassword().equals(password)){
                    req.getSession().setAttribute("user",user);
                    resp.sendRedirect("/");
                }else {
                    req.getRequestDispatcher("login.jsp").forward(req,resp);
                }
            }else {
                req.getRequestDispatcher("login.jsp").forward(req,resp);
            }
        }else{
            resp.sendRedirect("/");
        }
    }
}
