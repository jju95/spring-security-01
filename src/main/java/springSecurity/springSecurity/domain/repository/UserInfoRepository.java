package springSecurity.springSecurity.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springSecurity.springSecurity.domain.UserEntity;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findOneWithAuthoritiesByUserName(String user);

    Optional<UserEntity> findByEmail(String email);
}
