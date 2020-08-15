package Database.Entity;
import Database.DAO.FriendDao;
import Database.DAO.UserDao;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String first_name;
    private String last_name;
    private String email_mobile;
    private String password;
    private String sex;
    private String birthday;
    private byte[] avatar;

    public User(int id, String first_name, String last_name, String email_mobile, String password, String sex, String birthday) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email_mobile = email_mobile;
        this.password = password;
        this.sex = sex;
        this.birthday = birthday;
    }

    public User(int id, String first_name, String last_name) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail_mobile() {
        return email_mobile;
    }

    public void setEmail_mobile(String email_mobile) {
        this.email_mobile = email_mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public List<User> getListFriend(){
        List<User> result = new ArrayList<>();
        UserDao userDao = new UserDao();
        FriendDao friendDao = new FriendDao();
        List<Friend> friendList = friendDao.getListFriend(id);
        for (Friend friend: friendList
             ) {
            int tmpId = friend.getMe() == id ? friend.getFriend_to() : friend.getMe();
            result.add(userDao.getTempUser(tmpId).get());
        }
        return result;
    }

}
