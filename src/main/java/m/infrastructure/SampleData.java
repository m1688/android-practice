package m.infrastructure;

import java.util.ArrayList;
import java.util.List;

import m.domain.user.User;

/**
 * wee on 11/30/13.
 */
public class SampleData {

    public static final List<User> list = new ArrayList<User>();

    static {
        addUserRecursively(list, "james", 20);
        addUserRecursively(list, "cindy", 40);
        addUserRecursively(list, "bob", 23);
        addUserRecursively(list, "tom", 70);
    }


    private static void addUserRecursively(List<User> users, String name, int times) {

        for (int i = 0; i < times; i++) {
            users.add(new User(name + Integer.toString(i + 1)));
        }
    }

}
