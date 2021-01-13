package hr.fer.ruazosa.networkquiz;

import javax.persistence.*;

@Entity
@Table(name="question")
public class Question {
    //TO DO: dodaj sve sta salje json
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

    @Column(name = "value")
    private int value;

    @Column(name = "airdate")
    private String airdate;

    @Column(name = "created")
    private String created_at;

    @Column(name = "category_id")
    private Long category_id;

    @Column(name = "invalid_count")
    private Long invalid_count;

   // @Column(name = "category")
   // private Category category;

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
