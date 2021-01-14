package hr.fer.ruazosa.networkquiz.repository;

import hr.fer.ruazosa.networkquiz.model.Game;
import hr.fer.ruazosa.networkquiz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

    @Modifying
    @Query(value = "UPDATE Game SET pending = pending - 1 WHERE game_id = ?1", nativeQuery = true)
    Integer updatePending(Long gameId);

    @Query(value = "DELETE FROM Game WHERE gameId = ?1 AND userId = ?2")
    Integer removeFromGame(Long gameId, Long userId);

    @Query(value = "SELECT g FROM Game g WHERE g.gameId = ?1")
    Game getGame(Long gameId);

}
