package m.infrastructure;

import java.util.ArrayList;
import java.util.List;

import m.domain.tweet.Tweet;
import m.domain.user.User;

/**
 * wee on 11/30/13.
 */
public class SampleData {

    public static final List<User> list = new ArrayList<User>();
    
    public static final List<Tweet> twList = new ArrayList<Tweet>();

    public static final User currentUser = new User("cherry");

    static {
        addUserRecursively(list, "james", 20);
        addUserRecursively(list, "cindy", 40);
        addUserRecursively(list, "bob", 23);
        addUserRecursively(list, "tom", 70);

        addFollower(20L, 23L, 50L, 2L, 90L, 120L);
        addFollowee(22L, 29L, 50L, 3L, 98L, 129L);
        
        addTweets(twList,"茅于轼","物价在涨，相当于人民币缩水。现在100元的钞票只够打的，买快餐用。我比较过世界各国钞票的面额，英，美，欧，日，加，港，台等等，以我国的面额为最低。大家都盼500元钞票问世。之所以迟迟不发行，我猜想是毛泽东的像还要不要印上去的争论有关。不妨来一个网上投票试试看，估计一下现在的民心如何。");
        addTweets(twList,"中国书画艺术","和珅 墨迹 历代书法评价中，书品和人品是一个血肉相连的整体，人品历来高于书品，所谓因人废字。其实和珅很有才华，聪明过人，精明权术，在书法、诗文上有较高造诣，他的字和乾隆相似到了以假乱真的地步。");
        addTweets(twList,"2茅于轼","2物价在涨，相当于人民币缩水。现在100元的钞票只够打的，买快餐用。我比较过世界各国钞票的面额，英，美，欧，日，加，港，台等等，以我国的面额为最低。大家都盼500元钞票问世。之所以迟迟不发行，我猜想是毛泽东的像还要不要印上去的争论有关。不妨来一个网上投票试试看，估计一下现在的民心如何。");
        addTweets(twList,"2中国书画艺术","2和珅 墨迹 历代书法评价中，书品和人品是一个血肉相连的整体，人品历来高于书品，所谓因人废字。其实和珅很有才华，聪明过人，精明权术，在书法、诗文上有较高造诣，他的字和乾隆相似到了以假乱真的地步。");
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
            user.setNickName(name + "nickname");
            users.add(user);
        }
    }
    
    private static void addTweets(List<Tweet> tweets, String username, String content){
    	final Tweet tweet = new Tweet();
    	tweet.setUserName(username);
    	tweet.setContent(content);
    	tweets.add(tweet);
    }
}
