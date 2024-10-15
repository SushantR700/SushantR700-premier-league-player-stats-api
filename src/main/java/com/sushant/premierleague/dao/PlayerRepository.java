package com.sushant.premierleague.dao;

import com.sushant.premierleague.model.Player;
import io.micrometer.common.lang.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player,String> {
    Page<Player> findAll(@NonNull Pageable pageable);  // Pageable for pagination
    void deleteByPlayerName(String playerName);
    Optional<Player> findByPlayerName(String name);
}
