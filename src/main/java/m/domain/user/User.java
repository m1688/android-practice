package m.domain.user;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * who sends message(a.k.a wei bo)
 * <p/>
 * Created by jyu on 11/23/13.
 */
public class User {

    private final static AtomicLong idGenerator = new AtomicLong();

    private Long id;
    private String name;
    private String nickName;
    private String email;

    /**
     * the users follow you
     */
    private List<Long> followers = new ArrayList<Long>();
    /**
     * the users you are following
     */
    private List<Long> followees = new ArrayList<Long>();

    public User(String name) {
        this.id = idGenerator.incrementAndGet();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Long> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Long> followers) {
        this.followers = followers;
    }

    public List<Long> getFollowees() {
        return followees;
    }

    public void setFollowees(List<Long> followees) {
        this.followees = followees;
    }

    public Long getId() {
        return id;
    }

    public void addFollower(User currentUser) {
        getFollowers().add(currentUser.getId());
        currentUser.getFollowees().add(getId());
    }

    public boolean isMyFollower(User user) {
        return getFollowers().contains(user.getId());
    }

    public void unfollow(User currentUser) {
        getFollowers().remove(currentUser.getId());
        currentUser.getFollowees().remove(getId());
    }

    public void addFollowee(User user) {
        getFollowees().add(user.getId());
        user.getFollowers().add(getId());
    }
}
