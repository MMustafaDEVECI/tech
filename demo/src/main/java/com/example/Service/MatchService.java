package com.example.Service;

import org.springframework.stereotype.Service;

import com.example.Repository.MatchPlayerRepository;
import com.example.Repository.MatchRepository;
import com.example.Repository.PlayerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatchService {
    
    private final MatchRepository matchRepository;
    private final MatchPlayerRepository matchPlayerRepository;
    private final PlayerRepository playerRepository;

    /*
     * createMatch
     * getMatchInfo
     */
}
