package hr.fer.ruazosa.networkquiz;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "game_id")
    private Long id;

    @Size(min=5, message = "Game must have at least 5 questions")
    @Column(name = "questions")
    private List<Question> questions;

    @Size(min=2, message = "Game must have at least two players")
    @Column(name = "players")
    private List<User> players;

    public void setId(Long id){
        this.id = id;
    }

    public void setQuestions(List<Question> questions){
        this.questions = questions;
    }

    public void setPlayers(List<User> players){
        this.players = players;
    }

    public Long getId(){
        return this.id;
    }

    public List<Question> getQuestions(){
        return this.questions;
    }

    public List<User> getPlayers(){
        return this.players;
    }

}
