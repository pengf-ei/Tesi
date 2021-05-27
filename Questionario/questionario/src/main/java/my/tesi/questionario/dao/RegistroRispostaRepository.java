package my.tesi.questionario.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import my.tesi.questionario.entity.Domanda;
import my.tesi.questionario.entity.RegistroRisposta;
import my.tesi.questionario.entity.User;

public interface RegistroRispostaRepository extends JpaRepository<RegistroRisposta, Integer> {
	
	@Query(value = "FROM RegistroRisposta WHERE id_domanda_reg = :id_domanda_reg and username_reg = :username")
	public List<RegistroRisposta> findByDomandaAndUsername(@Param("id_domanda_reg") Domanda id_domanda_reg, @Param("username") User username);

}
