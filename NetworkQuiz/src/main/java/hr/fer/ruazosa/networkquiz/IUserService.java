package hr.fer.ruazosa.networkquiz;

import java.util.List;

public interface IUserService {
    User registerUser(User user);
    boolean checkUsernameUnique(User user);
    User loginUser(User user);
    Integer getUserRank(String username);
    List<String> getAllUsernames(String usernameToExclude);
}
