package hr.fer.ruazosa.networkquiz;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @Column(name = "category_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = " created_at")
    private String created;

    @Column(name = "updated")
    private String updated;

    @Column(name = "clues_count")
    private int clues;


    public void setId(Long id){
        this.id = id;
    }

    public void setName(String name){
        this.title = name;
    }
    public void setCreated(String datecreat){
        this.created = datecreat;
    }
    public void setUpdated(String dateupdated){
        this.updated = dateupdated;
    }

    public void setClues(int nClues){
        this.clues = nClues;
    }


    public Long getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public int getClues(){
        return this.clues;
    }
}
