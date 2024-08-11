package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>{
    
}
