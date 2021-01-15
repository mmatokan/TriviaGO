package hr.fer.ruazosa.networkquiz.model;

import javax.persistence.*;

@Entity
@Table(name = "game_users")
public class GameUsers {

    @ManyToOne
    @JoinColumn(name = "gameId")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(name = "score")
    private int score;

}


