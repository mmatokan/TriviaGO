package hr.fer.ruazosa.networkquiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @Autowired
    private IGameService gameService;

    @PostMapping("/createNewGame")
    public Game createNewGame(@RequestBody Game game) {
        return gameService.createNewGame(game);
    }
}
