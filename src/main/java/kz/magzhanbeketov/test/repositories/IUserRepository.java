package kz.magzhanbeketov.test.repositories;


import kz.magzhanbeketov.test.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {

    Boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);


}
