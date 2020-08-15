package Servlet;

import Database.DAO.UserDao;
import Database.Entity.User;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/individualProfile")
public class IndividualProfile extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        UserDao userDao = new UserDao();
        if (userDao.getTempUser(id).isPresent()) {
            User user = userDao.getTempUser(id).get();
            byte[] avatar = userDao.getImageData(id);
            String imageData = "data:image/jpeg;base64," +
                    DatatypeConverter.printBase64Binary(avatar);
            Map<String, String> options = new LinkedHashMap<>();
            options.put("imageString", imageData);
            options.put("fullName", user.getFirst_name() + " " +
                    user.getLast_name());
            String json = new Gson().toJson(options);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(json);
        }
    }
}