package hr.fer.ruazosa.networkquiz.service;

import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.MulticastMessage;
import hr.fer.ruazosa.networkquiz.repository.GameRepository;
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

    @Override
    public int calculateScore(List<Question> questions, List<String> answers, int timeRemaining) {
        return 0;
    }

    //TODO game id samo posalji
    @Override
    public int notifyGameStart(List<User> players, int gameId) {
        //TODO add question answer and id
        List<String> tokens = new ArrayList<String>(){};
        for(User player : players){
            tokens.add(player.getToken());
        }
        MulticastMessage message = MulticastMessage.builder()
                .putData("game_id", String.valueOf(gameId))
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
    @Transactional
    public Integer updatePending(Long gameId) {
        Integer successCount = gameRepository.updatePending(gameId);
        Game newGame = gameRepository.getGame(gameId);
        if(newGame.getPending() == 0){
            //notifyGameStart(newGame.getPlayers(), gameId);
        }
        return successCount;
    }

    @Override
    public Integer removeFromGame(Long gameId, Long userId) {
        return gameRepository.removeFromGame(gameId, userId);
    }

    @Override
    @Transactional
    public Game createNewGame(Game game, String username) {
        for(Question question : game.getQuestions()){
            question.setGame(game);
        }
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
    public List<User> getPlayers(Long gameId) {
        return gameRepository.getGame(gameId).getPlayers();
    }

}
