package org.dongguk.study.repository;

import org.dongguk.study.domain.User;
import org.dongguk.study.type.ELoginProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findBySocialIdAndProvider(String socialId, ELoginProvider provider);
}
