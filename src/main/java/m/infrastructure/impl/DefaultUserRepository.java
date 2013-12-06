package m.infrastructure.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import m.domain.user.User;
import m.domain.user.UserRepository;
import m.infrastructure.MemRepository;

/**
 * impl of user repository
 * <p/>
 * Created by wee on 11/23/13.
 */
public class DefaultUserRepository extends MemRepository implements UserRepository {

    @Override
    public List<User> fuzzySearchByUserName(String userName) {
        if (userName == null || userName.isEmpty()) {
            return Collections.emptyList();
        }
        final List<User> usersWithSpecificName = new ArrayList<User>();
        for (User user : users) {
            if (user.getName().contains(userName)) {
                usersWithSpecificName.add(user);
            }
        }
        return usersWithSpecificName;
    }
}
