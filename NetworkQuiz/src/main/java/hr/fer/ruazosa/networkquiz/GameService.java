package hr.fer.ruazosa.networkquiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService implements IGameService{

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
    public void startGame(List<User> players, List<Question> questions) {

    }

    @Override
    public void sendWinner(User user, int score) {

    }

    @Override
    public void joinGame(boolean answer) {

    }

    @Override
    public Game createNewGame(Game game) {
        return gameRepository.save(game);
    }
}
