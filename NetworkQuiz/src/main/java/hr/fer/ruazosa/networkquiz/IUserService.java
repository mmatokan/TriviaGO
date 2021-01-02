package hr.fer.ruazosa.networkquiz;

public interface IUserService {
    User registerUser(User user);
    boolean checkUsernameUnique(User user);
    User loginUser(User user);
    Integer getUserRank(String username);
}
