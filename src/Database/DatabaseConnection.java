package Database;

import java.sql.*;

public class DatabaseConnection {
    private static final String USERNAME= "root";
    private static final String PASSWORD= "10021999";
    private static final String HOSTNAME = "localhost";
    private static final String DBNAME = "bmagbook";
    private static final String PORT = "3306";
    private static final String CONN_STRING_TEMP= "jdbc:mysql://"+HOSTNAME+":"+PORT+"/";
    private static final String CONN_STRING= "jdbc:mysql://"+HOSTNAME+":"+PORT+"/"+DBNAME+"?useUnicode=yes&characterEncoding=UTF-8";
    private static Connection conn=null;

    private DatabaseConnection() {
    }

    public static Connection getInstance(){
        if (conn==null) {
            try{
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(CONN_STRING_TEMP, USERNAME, PASSWORD);
                PreparedStatement st;
                String sql = "SHOW DATABASES LIKE '" + DBNAME + "'";
                st = conn.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                if (rs.first()) {
                    conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                    System.out.println("Connect Database Success!");
                } else {
                    sql = "Create Database " + DBNAME + " CHARACTER SET utf8 COLLATE utf8_general_ci;";
                    st = conn.prepareStatement(sql);
                    if (st.executeUpdate() > 0) {
                        conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                        System.out.println("Create Database " + DBNAME + " Complete");
                        System.out.println("Connect Database Success!");
                    } else {
                        System.out.println("Cannot Create Database");
                    }
                }
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return conn;
    }

    public static void closeConnection(){
        try {
            conn.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
