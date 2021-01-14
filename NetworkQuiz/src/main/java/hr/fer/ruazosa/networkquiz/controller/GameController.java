package hr.fer.ruazosa.networkquiz.controller;

import hr.fer.ruazosa.networkquiz.service.IGameService;
import hr.fer.ruazosa.networkquiz.model.Game;
import hr.fer.ruazosa.networkquiz.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {

    @Autowired
    private IGameService gameService;

    @PostMapping("/createNewGame")
    public Game createNewGame(@RequestBody Game game) {
        return gameService.createNewGame(game);
    }

    @PatchMapping("/joinGame/{id}")
    public Game joinGameResponse(@PathVariable("id") int gameId, @RequestParam("response") boolean response, @RequestBody User user){
        List<User> players = gameService.getPlayers(gameId);
        if(!response){
            players.remove(user);
        }
        return gameService.joinGame(gameId, players);
    }

    @GetMapping("/getPlayers/{id}")
    public List<User> getPlayers(@PathVariable("id") int gameId){
        return gameService.getPlayers(gameId);
    }
}
