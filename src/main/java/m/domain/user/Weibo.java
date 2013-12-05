/**
 * 
 */
package m.domain.user;

/**
 * 微博model
 * 
 * @author wei.xinw
 * 
 */
public class Weibo {
	private Long id;
	private Long senderId;// 发送者
	private Long ReceiverId;// 接受者
	private String Content;
	private String sendTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public Long getReceiverId() {
		return ReceiverId;
	}

	public void setReceiverId(Long receiverId) {
		ReceiverId = receiverId;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

}
