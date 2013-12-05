package m.domain.tweet;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by geng.cheng on 13-10-15.
 */
public class Tweet{
	 private final static AtomicLong idGenerator = new AtomicLong();

	private Long tid;
    private Long uid;
    private String username;
    private String content;


    public Tweet() {
        this.tid = idGenerator.incrementAndGet();
    }

    public Long getUserId() {
        return uid;
    }

    public void setUserId(Long uid) {
        this.uid = uid;
    }
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }
    
    public Long getTid() {
        return tid;
    }
}
