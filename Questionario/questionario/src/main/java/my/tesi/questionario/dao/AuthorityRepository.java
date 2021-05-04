package my.tesi.questionario.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import my.tesi.questionario.entity.Authority;
import my.tesi.questionario.entity.AuthorityPK;

public interface AuthorityRepository extends JpaRepository<Authority, AuthorityPK> {

}
