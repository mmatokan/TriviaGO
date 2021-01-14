package hr.fer.ruazosa.networkquiz.repository;

import hr.fer.ruazosa.networkquiz.model.Game;
import hr.fer.ruazosa.networkquiz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

    @Query(value = "UPDATE game SET pending = pending - 1, players = ?2 WHERE gameId = ?1 " +
            "RETURNING ROW", nativeQuery = true)
    Game joinGame(int gameId, List<User> players);

    @Query(value = "SELECT g FROM Game g where g.gameId = ?1")
    Game getPlayers(int gameId);

}
