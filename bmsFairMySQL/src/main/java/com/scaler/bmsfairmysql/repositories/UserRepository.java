package com.scaler.bmsfairmysql.repositories;

import com.scaler.bmsfairmysql.models.User;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<User> findById(Long aLong);
}
