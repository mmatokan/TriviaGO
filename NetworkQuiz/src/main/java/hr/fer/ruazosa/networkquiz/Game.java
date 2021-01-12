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
    @Column(name = "question")
    private int[] questionIds;

    @Size(min=2, message = "Game must have at least 2 players")
    @Column(name = "players")
    private int[] userIds;

    /*
    private List<User> users;
    private int numOfPlayers;

    public Game(int[] userIds){
        this.numOfPlayers = userIds.length;
    }

    public void addPlayer(User user, boolean answer){
        if(answer){
            users.add(user);
        }
        numOfPlayers--;
        if(numOfPlayers == 0){
            //zapocni igru
        }
    }
*/

    public void setId(Long id){
        this.id = id;
    }

    public void setQuestionIds(int[] questionIds){
        this.questionIds = questionIds;
    }

    public void setPlayerIds(int[] userIds){
        this.userIds = userIds;
    }

    public Long getId(){
        return this.id;
    }

    public int[] getQuestionIds(){
        return this.questionIds;
    }

    public int[] getPlayerIds(){
        return this.userIds;
    }



}
