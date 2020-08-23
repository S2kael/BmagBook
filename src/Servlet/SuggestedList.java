package Servlet;

import Database.Entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/SuggestedList")
public class SuggestedList extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            req.setAttribute("SuggestedList", user.getSuggestedFriend());
            req.getRequestDispatcher("SuggestedFriend.jsp").forward(req, resp);
        }
    }
}
