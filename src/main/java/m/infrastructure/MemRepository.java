package m.infrastructure;

import java.util.List;

import m.domain.user.User;

/**
 * read entries from memory
 * <p/>
 * Created by wee on 11/23/13.
 */
public abstract class MemRepository {
    protected static List<User> users = SampleData.list;
}
