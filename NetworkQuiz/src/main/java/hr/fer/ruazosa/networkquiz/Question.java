package hr.fer.ruazosa.networkquiz;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="question")
public class Question {
    //TO DO: dodaj sve sta salje json
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "question_id")
    private Long id;

    @NotBlank(message = "Question text cannot be empty")
    @Column(name = "question_text")
    private String questionText;

    @NotBlank(message = "Question answer cannot be empty")
    @Column(name = "question_answer")
    private String questionAnswer;

    public void setId(Long id){
        this.id = id;
    }

    public void setQuestionText(String questionText){
        this.questionText = questionText;
    }

    public void setQuestionAnswer(String questionAnswer){
        this.questionAnswer = questionAnswer;
    }

    public Long getId(){
        return this.id;
    }

    public String getQuestionText(){
        return this.questionText;
    }

    public String getQuestionAnswer(){
        return this.questionAnswer;
    }
}
