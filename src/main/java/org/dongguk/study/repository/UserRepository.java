package org.dongguk.study.repository;

import org.dongguk.study.domain.User;
import org.dongguk.study.type.ELoginProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findBySocialIdAndProvider(String socialId, ELoginProvider provider);

    @Query("SELECT u.id AS id, u.role AS role FROM User u WHERE u.id = :userId AND u.isLogin = true AND u.refreshToken is not null")
    Optional<UserLoginForm> findByIdAndRefreshToken(@Param("userId") Long userId);
    interface UserLoginForm {
        Long getId();
        String getRole();
    }
}
