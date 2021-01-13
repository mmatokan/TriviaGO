package hr.fer.ruazosa.networkquiz;

import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.MulticastMessage;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

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
    @Transactional
    public Game createNewGame(Game game) {
        Game newGame = gameRepository.save(game);
        for(Question question: newGame.getQuestions()){
            //question.setGame(newGame);
            //ovo je privremeno rjesenje dok ne skuzim kako povezati OneToMany
            question.setMy_game_id(newGame.getGameId());
            questionRepository.save(question);
        }
        return newGame;
    }

    @Override
    public List<User> getPlayers(int gameId) {
        return gameRepository.getPlayers(gameId).getPlayers();
    }

}
