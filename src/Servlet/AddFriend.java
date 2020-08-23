package Servlet;

import Database.DAO.FriendDao;
import Database.Entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/AddFriend")
public class AddFriend extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        HttpSession sess = req.getSession();
        User user = (User) sess.getAttribute("user");

        // Step 2
        String action = req.getParameter("action");
        if(action != null && action.equals("add-friend")) {
            int friendId = Integer.parseInt(req.getParameter("friend-id"));
            FriendDao friendDao = new FriendDao();
            friendDao.addFriend(user.getId(), friendId);
        }

        // Step 1

        List<User> list = user.getSuggestedFriend();
        req.setAttribute("SuggestedList", list);
        req.getRequestDispatcher("SuggestedFriend.jsp").forward(req, resp);
    }
}
