package com.stuff.sector_app.repository;

import com.stuff.sector_app.domain.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDataRepository extends JpaRepository<UserData, Long> {
    Optional<UserData> findBySessionId(String sessionId);
}
