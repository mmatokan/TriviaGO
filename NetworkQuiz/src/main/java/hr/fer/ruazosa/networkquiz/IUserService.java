package hr.fer.ruazosa.networkquiz;

import java.util.List;

public interface IUserService {
    User registerUser(User user);
    boolean checkUsernameUnique(User user);
    User loginUser(User user);
    User getUserRank(String username);
    String getUserToken(String username);
    List<String> getAllUsernames(String usernameToExclude);
    String setNewToken(String username, String token);
    void sendGameInvitations(List<String> usernames, String message);
    Game createGame(int questionCategory, List<User> players);
    void joinGame(boolean answer);
    List<User> getLeaderboard();
}
