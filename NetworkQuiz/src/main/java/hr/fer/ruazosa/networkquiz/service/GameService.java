package hr.fer.ruazosa.networkquiz.service;

import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.MulticastMessage;
import hr.fer.ruazosa.networkquiz.repository.GameRepository;
import hr.fer.ruazosa.networkquiz.repository.QuestionRepository;
import hr.fer.ruazosa.networkquiz.model.Game;
import hr.fer.ruazosa.networkquiz.model.Question;
import hr.fer.ruazosa.networkquiz.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import sun.security.ssl.Debug;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameService implements IGameService {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public int calculateScore(List<Question> questions, List<String> answers, int timeRemaining) {
        return 0;
    }

    //TODO game id samo posalji
    @Override
    public int notifyGameStart(List<User> players, List<Question> questions) {
        //TODO add question answer and id
        List<String> tokens = new ArrayList<String>(){};
        for(User player : players){
            tokens.add(player.getToken());
        }
        MulticastMessage message = MulticastMessage.builder()
                .addAllTokens(tokens)
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
    public Game startGame(int gameId) {
        return null;
    }

    @Override
    public void sendWinner(User user, int score) {

    }

    @Override
    public Game joinGame(int gameId, List<User> players) {
        Game newGame = gameRepository.joinGame(gameId, players);
        if(newGame.getPending() == 0){
            notifyGameStart(newGame.getPlayers(), newGame.getQuestions());
        }
        return newGame;
    }

    @Override
    public Game createNewGame(Game game, String username) {
        Game newGame = gameRepository.save(game);
        List<String> playerTokens = new ArrayList<>();
        for(User player : newGame.getPlayers()){
            if(!player.getUsername().equals(username)){
                playerTokens.add(player.getToken());
            }
        }
        int sent = sendGameInvitations(playerTokens, username, newGame.getGameId());
        if(sent != playerTokens.size()){
            int notSent = playerTokens.size() - sent;
            Debug.println("ERROR", notSent + " tokens not sent!");
        }
        else Debug.println("OK", "all notifications sent");
        return newGame;
    }

    @Override
    public int sendGameInvitations(List<String> token, String username, Long gameId){
        MulticastMessage message = MulticastMessage.builder()
                //.setNotification()
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
    public List<User> getPlayers(int gameId) {
        return gameRepository.getPlayers(gameId).getPlayers();
    }

}
