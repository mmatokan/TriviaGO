package hr.fer.ruazosa.networkquiz;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "game_id")
    private Long id;

    @Size(min=5, message = "Game must have at least 5 questions")
    @Column(name = "question")
    @ElementCollection
    private Set<Question> questions;

    @Size(min=2, message = "Game must have at least 2 players")
    @Column(name = "players")
    @ElementCollection
    private Set<String> playerTokens;

    @Column(name = "pending")
    private int pending;

    public void setId(Long id){
        this.id = id;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public void setPlayers(Set<String> playerTokens){
        this.playerTokens = playerTokens;
    }


    public void setPending(int pending){
        this.pending = pending;
    }


    public Long getId(){
        return this.id;
    }

    public Set<Question> getQuestions(){
        return this.questions;
    }

    public Set<String> getPlayerTokens(){
        return this.playerTokens;
    }

    public int getPending(){
        return this.pending;
    }

    public void removePlayer(String token){
        this.playerTokens.remove(token);
    }

}
