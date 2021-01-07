package hr.fer.ruazosa.networkquiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean checkUsernameUnique(User user) {
        List userList = userRepository.findByUserName(user.getUsername());
        if (userList.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public User loginUser(User user) {
        List<User> loggedUserList = userRepository.findByUserNameAndPassword(user.getUsername(), user.getPassword());
        if (loggedUserList.isEmpty()) {
            return null;
        }
        return userRepository.findByUserNameAndPassword(user.getUsername(), user.getPassword()).get(0);
    }

    @Override
    public Integer getUserRank(String username) {
        return userRepository.getUserRank(username);
    }

    @Override
    public List<String> getAllUsernames(String usernameToExclude) {
        return userRepository.getAllUsernames(usernameToExclude);
    }

    @Override
    public String setNewToken(String username, String token) {
        return userRepository.setNewToken(username, token);
    }

    @Override
    public String getUserToken(String username){ return userRepository.getUserToken(username);}
}
