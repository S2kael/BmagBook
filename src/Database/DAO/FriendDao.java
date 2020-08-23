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
            String sql = "Select * From " + TABLE + " WHERE me = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1,id);
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

    public void addFriend(int me, int friend) {
        try {
            PreparedStatement st;
            String sql = "Insert into tbl_friends Values(null,?,?)";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, me);
            pStmt.setInt(2, friend);
            pStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}