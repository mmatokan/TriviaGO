package hr.fer.ruazosa.networkquiz.model;
import javax.persistence.*;

@Entity
@Table(name="question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "question_id")
    private Long id;

    //@NotBlank(message = "Question text cannot be empty")
    @Column(name = "question_text")
    private String question;

    //@NotBlank(message = "Question answer cannot be empty")
    @Column(name = "question_answer")
    private String answer;

    /*
    @Column(name = "my_game_id")
    private Long my_game_id;

     */

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    public void setId(Long id){
        this.id = id;
    }

    public void setQuestion(String question){
        this.question = question;
    }

    public void setAnswer(String answer){
        this.answer = answer;
    }

    public void setGame(Game game){
        this.game = game;
    }

    //public void setMy_game_id(Long id){ this.my_game_id = id;}
    //public Long getMy_game_id(){ return this.my_game_id;}

    public Long getId(){
        return this.id;
    }

    public String getQuestion(){
        return this.question;
    }

    public String getAnswer(){
        return this.answer;
    }

    public Game getGame(){
        return this.game;
    }
}
