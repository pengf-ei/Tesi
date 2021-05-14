package my.tesi.questionario.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import my.tesi.questionario.entity.Sessione;

public interface SessioneRepository extends JpaRepository<Sessione, Integer> {
	
	public Sessione findByNomesessione(String nomeessione);

}
