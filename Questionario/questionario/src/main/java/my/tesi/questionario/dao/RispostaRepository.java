package my.tesi.questionario.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import my.tesi.questionario.entity.Risposta;

public interface RispostaRepository extends JpaRepository<Risposta, Integer> {

}
