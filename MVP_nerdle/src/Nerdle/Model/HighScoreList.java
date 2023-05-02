package Nerdle.Model;

import java.util.Comparator;
import java.util.List;

public class HighScoreList {

    private List<User> users;

    public HighScoreList(){
        users = User.getAllUsers();

        //remove where avg is Integer.MAX_VALUE (have not finished any game)
        /**
        for (User user: users) {
            if (user.getAvgAttempts() == Integer.MAX_VALUE){
                users.remove(user);
            }
        }**/
    }

    public List<User> getByAverageAttempts(){


        users.sort(new Comparator<User>() {

            @Override
            public int compare(User o1, User o2) {
                return Double.compare(o1.getAvgAttempts(), o2.getAvgAttempts());
            }
        });

        return users;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (User u: users){
            builder.append(String.format("%s | %f | %d%n",u.getName(),u.getAvgAttempts(),u.getScore()));
        }
        return builder.toString();
    }
}
