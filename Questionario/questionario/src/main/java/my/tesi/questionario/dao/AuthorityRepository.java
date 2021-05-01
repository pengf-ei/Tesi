package my.tesi.questionario.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import my.tesi.questionario.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String> {

}
