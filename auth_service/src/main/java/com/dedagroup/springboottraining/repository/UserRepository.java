package com.dedagroup.springboottraining.repository;

import com.dedagroup.springboottraining.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    List<User> findUserByFlagAttivoIs(boolean flag);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByActivationToken(String authToken);
}
