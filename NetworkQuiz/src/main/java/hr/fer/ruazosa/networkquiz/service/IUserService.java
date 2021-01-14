package hr.fer.ruazosa.networkquiz.service;

import hr.fer.ruazosa.networkquiz.model.User;

import java.util.List;

public interface IUserService {
    User registerUser(User user);
    boolean checkUsernameUnique(User user);
    User loginUser(User user);
    User getUserStats(String username);
    String getUserToken(String username);
    List<User> getAllOpponents(String usernameToExclude);
    List<User> getAllUsers();
    String setNewToken(String username, String token);
    int sendGameInvitations(List<String> token, String username, int gameId);
    List<User> getLeaderboard();

}
