/**
 * 
 */
package m.infrastructure.impl;

import java.util.List;

import m.domain.user.Weibo;
import m.infrastructure.WeiboService;

/**
 * @author wei.xinw
 *
 */
public class DefaultWeiboService implements WeiboService {

	@Override
	public boolean send(Weibo weibo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Weibo> getList(Long userId, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Weibo> getAll(Long userId) {
		
		return null;
	}

}
