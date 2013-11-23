package m.domain.user;

/**
 * who sends message(a.k.a wei bo)
 *
 * Created by jyu on 11/23/13.
 */
public class User {

    private String name;
    private String nickName;
    private String email;

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
}
