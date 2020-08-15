package Database.DAO;

import Database.DatabaseConnection;
import Database.Entity.Friend;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FriendDao {

    private static final String CLASSNAME = "Friend";
    private static final String TABLE = "tbl_friends";
    private static Connection conn = DatabaseConnection.getInstance();

    public List<Friend> getListFriend(int id){
        ArrayList<Friend> result = new ArrayList<>();
        try {
            PreparedStatement st;
            String sql = "Select * From " + TABLE + " WHERE me = ? or friend_to = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1,id);
            st.setInt(2,id);
            ResultSet rs = st.executeQuery();
            if (rs.first()) {
                do {
                    int idRecord = rs.getInt("id");
                    int me = rs.getInt("me");
                    int friend_to = rs.getInt("friend_to");
                    Friend friend = new Friend(idRecord,me,friend_to);
                    result.add(friend);
                } while (rs.next());
                System.out.println("Collect "+CLASSNAME+" Complete!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}