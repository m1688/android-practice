/**
 * 
 */
package m.infrastructure;

import java.util.List;

import m.domain.user.Weibo;

/**
 * @author wei.xinw
 *
 */
public interface WeiboService {
	/**
	 * 发送微博
	 * @param weibo
	 * @return
	 */
	boolean send(Weibo weibo);
	
	/**
	 * 获取用户微博列表
	 * @param userId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<Weibo> getList(Long userId,int pageNo,int pageSize);
	
	/**
	 * 获取用户所要微博
	 * @param userId
	 * @return
	 */
	List<Weibo> getAll(Long userId);

}
