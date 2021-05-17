package my.tesi.questionario.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import my.tesi.questionario.entity.Domanda;

public interface DomandaRepository extends JpaRepository<Domanda, Integer> {

}
