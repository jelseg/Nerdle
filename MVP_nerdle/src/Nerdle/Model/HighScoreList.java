package Nerdle.Model;

import java.util.Comparator;
import java.util.List;

/**
 * used as collection of users for hioghscoresScreenView
 */
public class HighScoreList {

    private List<User> users;

    public HighScoreList(){
        users = User.getAllUsers();
    }

    /**
     *
     * @return sorted list of all users, the lowest average first
     */
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
