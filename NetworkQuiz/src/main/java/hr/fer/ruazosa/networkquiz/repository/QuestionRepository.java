package hr.fer.ruazosa.networkquiz.repository;

import hr.fer.ruazosa.networkquiz.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{
}