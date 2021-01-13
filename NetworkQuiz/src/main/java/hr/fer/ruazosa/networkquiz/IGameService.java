package hr.fer.ruazosa.networkquiz;

import java.util.List;

public interface IGameService {

    int calculateScore(List<Question> questions, List<String> answers, int timeRemaining); //tocni odgovori*broj preostalih sekunda
    int notifyGameStart(List<User> players, List<Question> questions);
    Game startGame(int gameId);
    void sendWinner(User user, int score);
    Game joinGame(int gameId, List<User> players);
    Game createNewGame(Game game);
    List<User> getPlayers(int gameId);
}
