package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import model.User;


public interface UserRepository extends JpaRepository<User , Long> {

	Optional<User> findByLogin(String login);

	boolean existsByLogin(String login);

	boolean existsByEmail(String email);


}
