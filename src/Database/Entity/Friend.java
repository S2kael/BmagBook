package Database.Entity;

public class Friend {
    private int id;
    private int me;
    private int friend_to;

    public Friend(int id, int me, int friend_to) {
        this.id = id;
        this.me = me;
        this.friend_to = friend_to;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMe() {
        return me;
    }

    public void setMe(int me) {
        this.me = me;
    }

    public int getFriend_to() {
        return friend_to;
    }

    public void setFriend_to(int friend_to) {
        this.friend_to = friend_to;
    }
}
