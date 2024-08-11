package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entity.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long>{}

