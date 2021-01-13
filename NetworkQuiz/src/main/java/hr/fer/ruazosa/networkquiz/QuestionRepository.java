package hr.fer.ruazosa.networkquiz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{
}