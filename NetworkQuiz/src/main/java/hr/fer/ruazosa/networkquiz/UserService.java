package hr.fer.ruazosa.networkquiz;

import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.MulticastMessage;
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
    public User getUserStats(String username) {
        return userRepository.getUserStats(username);
    }


    @Override
    public List<User> getAllUsers(String usernameToExclude) {
        return userRepository.getAllUsers(usernameToExclude);
    }


    @Override
    public String setNewToken(String username, String token) {
        return userRepository.setNewToken(username, token);
    }

    @Override
    public String getUserToken(String username){ return userRepository.getUserToken(username);}

    @Override
    public int sendGameInvitations(List<String> token, String username, int gameId){
        MulticastMessage message = MulticastMessage.builder()
                .putData("message", username + " invited you to join a game")
                .putData("game_id", String.valueOf(gameId))
                .addAllTokens(token)
                .build();
        try{
            BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(message);
            return response.getSuccessCount();
        }
        catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<User> getLeaderboard() {
        return null;
    }
}
