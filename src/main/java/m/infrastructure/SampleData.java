package m.infrastructure;

import java.util.ArrayList;
import java.util.List;

import m.domain.user.User;

/**
 * wee on 11/30/13.
 */
public class SampleData {

    public static final List<User> list = new ArrayList<User>();

    public static final User currentUser = new User("cherry");

    static {
        addUserRecursively(list, "james", 20);
        addUserRecursively(list, "cindy", 40);
        addUserRecursively(list, "bob", 23);
        addUserRecursively(list, "tom", 70);

        addFollower(20L, 23L, 50L, 2L, 90L, 120L);
        addFollowee(22L, 29L, 50L, 3L, 98L, 129L);
    }

    private static void addFollower(Long... userIds) {
        for (Long id : userIds) {
            currentUser.addFollower(list.get(id.intValue()));
        }
    }

    private static void addFollowee(Long... userIds) {
        for (Long id : userIds) {
            currentUser.addFollowee(list.get(id.intValue()+1));
        }
    }

    private static void addUserRecursively(List<User> users, String name, int times) {

        for (int i = 0; i < times; i++) {
            final User user = new User(name + Integer.toString(i + 1));
            user.setName(name + user.getId());
            users.add(user);
        }
    }

}
