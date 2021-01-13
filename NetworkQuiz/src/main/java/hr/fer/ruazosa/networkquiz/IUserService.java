package hr.fer.ruazosa.networkquiz;

import java.util.List;

public interface IUserService {
    User registerUser(User user);
    boolean checkUsernameUnique(User user);
    User loginUser(User user);
    User getUserStats(String username);
    String getUserToken(String username);
    List<User> getAllUsers(String usernameToExclude);
    String setNewToken(String username, String token);
    int sendGameInvitations(List<String> token, String username, int gameId);
    List<User> getLeaderboard();

}
