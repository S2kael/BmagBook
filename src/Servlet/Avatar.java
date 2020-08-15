package Servlet;

import Database.DAO.UserDao;
import Database.Entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(urlPatterns = "/avatar")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class Avatar extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        UserDao userDao = new UserDao();
        if (req.getParameter("id") != null){
            int id = Integer.parseInt(req.getParameter("id"));
            byte[] content = userDao.getImageData(id);
            resp.setContentType("image/jpeg");
            resp.setContentLength(content.length);
            resp.getOutputStream().write(content);
        }else{
            if (req.getSession().getAttribute("user") != null){
                User user = (User) req.getSession().getAttribute("user");
                byte[] content = userDao.getImageData(user.getId());
                resp.setContentType("image/jpeg");
                resp.setContentLength(content.length);
                resp.getOutputStream().write(content);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("avatar");
        if (part != null){
            String fileName = extractFileName(part);
            if (fileName != null && fileName.length() > 0) {
                // Dữ liệu file.
                InputStream is = part.getInputStream();
                // Ghi vào file.
                UserDao userDao = new UserDao();
                User user = (User) req.getSession().getAttribute("user");
                if (user!= null) {
                    userDao.setAvatar(user.getId(), is);
                }
            }
        }

    }


    private String extractFileName(Part part) {
        // form-data; name="file"; filename="C:\file1.zip"
        // form-data; name="file"; filename="C:\Note\file2.zip"
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                // C:\file1.zip
                // C:\Note\file2.zip
                String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                clientFileName = clientFileName.replace("\\", "/");
                int i = clientFileName.lastIndexOf('/');
                // file1.zip
                // file2.zip
                return clientFileName.substring(i + 1);
            }
        }
        return null;
    }
}
