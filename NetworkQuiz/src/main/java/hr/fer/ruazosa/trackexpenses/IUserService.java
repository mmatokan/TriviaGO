package hr.fer.ruazosa.trackexpenses;

public interface IUserService {
    User registerUser(User user);
    boolean checkUsernameUnique(User user);
    User loginUser(User user);
}
