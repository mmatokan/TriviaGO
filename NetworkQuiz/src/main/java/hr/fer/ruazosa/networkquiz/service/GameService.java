package hr.fer.ruazosa.networkquiz.service;

import com.google.firebase.messaging.*;
import hr.fer.ruazosa.networkquiz.repository.GameRepository;
import hr.fer.ruazosa.networkquiz.model.Game;
import hr.fer.ruazosa.networkquiz.model.Question;
import hr.fer.ruazosa.networkquiz.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
//import sun.security.ssl.Debug;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class GameService implements IGameService {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public int calculateScore(List<Question> questions, List<String> answers, int timeRemaining) {
        return 0;
    }

    @Override
    public int notifyGameStart(List<User> players, Long gameId) {
        List<String> tokens = new ArrayList<String>(){};
        for(User player : players){
            tokens.add(player.getToken());
        }
        MulticastMessage message = MulticastMessage.builder()
                .putData("action", "begin")
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
            notifyGameStart(newGame.getPlayers(), gameId);
        }
        return successCount;
    }

    @Override
    public Integer removeFromGame(Long gameId, Long userId) {
        return gameRepository.removeFromGame(gameId, userId);
    }

    @Override
    @Transactional
    public boolean createNewGame(Game game, String username) {
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
            //Debug.println("ERROR", notSent + " tokens not sent!");
        }
        //else Debug.println("OK", "all notifications sent");
        return true;
    }

    @Override
    public int sendGameInvitations(List<String> token, String username, Long gameId){
        MulticastMessage message = MulticastMessage.builder()
                .putData("message", username + " invited you to join a game")
                .putData("game_id", String.valueOf(gameId))
                .putData("action", "join")
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

    @Override
    public boolean postResult(Long gameId, Long userId, int score) {
        //TODO update ostale statistike usera (br tocnih odgovora umjesto accuracy)
        //TODO post to GameUsers (game user repository)
        //TODO update finished in Game (update finished)
        //TODO check if finished == game.players.size(): if true send batch messages to all players (get game)
        //TODO if true get winner from game_users dobijes user_id pa jos jedan get za username pop user_idju
        return false;
    }

}
