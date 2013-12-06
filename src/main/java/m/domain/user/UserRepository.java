package m.domain.user;

import java.util.List;

/**
 * Created by wee on 11/23/13.
 */
public interface UserRepository {

    public List<User> fuzzySearchByUserName(String userName);
}
