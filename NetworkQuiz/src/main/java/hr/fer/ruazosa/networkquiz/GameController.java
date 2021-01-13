package hr.fer.ruazosa.networkquiz;

import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bind.annotation.FieldValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class GameController {

    @Autowired
    private IGameService gameService;

    @PostMapping("/createNewGame")
    public Game createNewGame(@RequestBody Game game){
        return gameService.createNewGame(game);
    }

}
