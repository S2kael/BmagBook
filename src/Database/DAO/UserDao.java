package Database.DAO;

import Database.DatabaseConnection;
import Database.Entity.User;

import java.io.InputStream;
import java.sql.*;
import java.util.Optional;

public class UserDao{

    private static final String CLASSNAME = "User";
    private static final String TABLE = "tbl_profile";
    private static Connection conn = DatabaseConnection.getInstance();

    public int insert(User user) {
        try {
            PreparedStatement st;
            String sql = "SELECT * FROM " + TABLE + " WHERE email_mobile = ?";
            st = conn.prepareStatement(sql);
            st.setString(1, user.getEmail_mobile());
            ResultSet rs = st.executeQuery();
            if (rs.first()) {
                System.out.println("Exists email_mobile");
                return 1;
            } else {
                sql = "Insert Into " + TABLE + " (id, first_name ,last_name, email_mobile, password, sex, birthday) value (?,?,?,?,?,?,?)";
                st = conn.prepareStatement(sql);
                st.setInt(1, user.getId());
                st.setString(2, user.getFirst_name());
                st.setString(3, user.getLast_name());
                st.setString(4, user.getEmail_mobile());
                st.setString(5, user.getPassword());
                st.setString(6, user.getSex());
                st.setString(7, user.getBirthday());
                if (st.executeUpdate() > 0) {
                    System.out.println("Insert "+CLASSNAME+" Complete!");
                    return 0;
                } else {
                    System.out.println("Something went wrong");
                    return -1;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    public int update(User user) {
        try {
            PreparedStatement st;
            String sql = "SELECT * FROM " + TABLE + " WHERE id = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1, user.getId());
            ResultSet rs = st.executeQuery();
            if (rs.first()) {
                sql = "SELECT * FROM " + TABLE + " WHERE email_mobile = ?";
                st = conn.prepareStatement(sql);
                st.setString(1, user.getEmail_mobile());
                rs = st.executeQuery();
                if (rs.first()) {
                    if (rs.getInt("id") != user.getId()){
                        System.out.println("Exists email_mobile");
                        return 2;
                    } else {
                        return UpdateUser(user);
                    }
                }else {
                    return UpdateUser(user);
                }
            } else {
                System.out.println("Don't Exists Id");
                return 1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return -1;
        }
    }


    private int UpdateUser(User user){
        try{
            String sql = "UPDATE " + TABLE + " set first_name = ? , last_name = ? , email_mobile = ? , sex = ? , birthday = ? where id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, user.getFirst_name());
            st.setString(2, user.getLast_name());
            st.setString(3, user.getEmail_mobile());
            st.setString(4, user.getSex());
            st.setString(5, user.getBirthday());
            st.setInt(6, user.getId());
            if (st.executeUpdate() > 0) {
                System.out.println("Update "+CLASSNAME+" Complete!");
                return 0;
            } else {
                System.out.println("Something went wrong");
                return -1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return -1;
        }

    }

    public Optional<User> get(String email_mobile) {
        User result = null;
        try {
            PreparedStatement st;
            String sql = "SHOW TABLES LIKE '" + TABLE + "'";
            st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.first()) {
                sql = "Select * From " + TABLE + " WHERE email_mobile = ?";
                st = conn.prepareStatement(sql);
                st.setString(1, email_mobile);
                rs = st.executeQuery();
                if (rs.first()) {
                    int id = rs.getInt("id");
                    String password = rs.getString("password");
                    String first_name = rs.getString("first_name");
                    String last_name = rs.getString("last_name");
                    String sex = rs.getString("sex");
                    String birthday = rs.getString("birthday");
                    result = new User(id, first_name, last_name, email_mobile, password, sex, birthday);
                }
                System.out.println("Collect Data " + CLASSNAME + " Complete!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.ofNullable(result);
    }

    public Optional<User> getTempUser(int id) {
        User result = null;
        try {
            PreparedStatement st;
            String sql = "Select * From " + TABLE + " WHERE id = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.first()) {
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                result = new User(id, first_name, last_name);
            }
            System.out.println("Collect Data " + CLASSNAME + " Complete!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.ofNullable(result);
    }

    public byte[] getImageData(int id){
        try {
            PreparedStatement st;
            String sql = "SELECT avatar FROM " + TABLE + " WHERE id = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.first()) {
                return rs.getBytes("avatar");
            } else {
                System.out.println("Don't Exists Id");
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public boolean setAvatar(int id, InputStream is){
        try {
            PreparedStatement st;
            String sql = "UPDATE " + TABLE + " SET avatar = ? WHERE id = ?";
            st = conn.prepareStatement(sql);
            st.setBlob(1, is);
            st.setInt(2, id);
            if (st.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return false;
    }


    public int getLastId(){
        int id = 0;
        try {
            PreparedStatement st;
            String sql = "SHOW TABLES LIKE '" + TABLE + "'";
            st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.first()) {
                sql = "Select max(id) From " + TABLE ;
                st = conn.prepareStatement(sql);
                rs = st.executeQuery();
                if (rs.first()) {
                    id = rs.getInt("max(id)");
                }
                System.out.println("Collect Last Id Complete!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }
}
