package hr.fer.ruazosa.networkquiz;

import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.MulticastMessage;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService implements IGameService {

    @Autowired
    private GameRepository gameRepository;
    @Override
    public List<Question> getQuestions(int categoryId) {
        return null;
    }

    @Override
    public int calculateScore(List<Question> questions, List<String> answers, int timeRemaining) {
        return 0;
    }

    @Override
    public int startGame(List<User> players, List<Question> questions) {
        //TODO add question answer and id
        List<String> tokens = new ArrayList<String>(){};
        for(User player : players){
            tokens.add(player.getToken());
        }
        MulticastMessage message = MulticastMessage.builder()
                .putData("question1", questions.get(0).getQuestionText())
                .putData("question2", questions.get(1).getQuestionText())
                .putData("question3", questions.get(2).getQuestionText())
                .putData("question4", questions.get(3).getQuestionText())
                .putData("question5", questions.get(4).getQuestionText())
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
    public void sendWinner(User user, int score) {

    }

    @Override
    public Game joinGame(int gameId, List<User> players) {
        Game newGame = gameRepository.joinGame(gameId, players);
        if(newGame.getPending() == 0){
            startGame(newGame.getPlayers(), newGame.getQuestions());
        }
        return newGame;
    }

    @Override
    public Game createNewGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public List<User> getPlayers(int gameId) {
        return gameRepository.getPlayers(gameId).getPlayers();
    }
}
