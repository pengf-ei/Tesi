package my.tesi.questionario.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import my.tesi.questionario.entity.User;

public interface UserRepository extends JpaRepository<User, String> {


}
