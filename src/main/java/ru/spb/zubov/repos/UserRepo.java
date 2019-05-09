package ru.spb.zubov.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spb.zubov.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByActivationCode(String code);
}
