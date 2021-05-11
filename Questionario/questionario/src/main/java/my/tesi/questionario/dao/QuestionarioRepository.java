package my.tesi.questionario.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import my.tesi.questionario.entity.Questionario;

public interface QuestionarioRepository extends JpaRepository<Questionario, Integer> {

}
