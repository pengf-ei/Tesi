package my.tesi.questionario.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import my.tesi.questionario.entity.Risposta;

public interface RispostaRepository extends JpaRepository<Risposta, Integer> {
	
	@Modifying
	@Query(value = "Delete from Risposta WHERE id_risposta = :id_risposta")
	public void deleteRisposta(@Param("id_risposta") int id_risposta);

}
