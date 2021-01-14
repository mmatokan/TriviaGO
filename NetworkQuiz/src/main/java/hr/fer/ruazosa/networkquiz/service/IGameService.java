package hr.fer.ruazosa.networkquiz.service;

import hr.fer.ruazosa.networkquiz.model.Game;
import hr.fer.ruazosa.networkquiz.model.Question;
import hr.fer.ruazosa.networkquiz.model.User;

import java.util.List;

public interface IGameService {

    int calculateScore(List<Question> questions, List<String> answers, int timeRemaining); //tocni odgovori*broj preostalih sekunda
    int notifyGameStart(List<User> players, List<Question> questions);
    Game startGame(int gameId);
    void sendWinner(User user, int score);
    Game joinGame(int gameId, List<User> players);
    Game createNewGame(Game game, String username);
    int sendGameInvitations(List<String> token, String username, Long gameId);
    List<User> getPlayers(int gameId);
}
