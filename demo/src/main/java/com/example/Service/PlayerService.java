package com.example.Service;

import org.springframework.stereotype.Service;

import com.example.Repository.GamePlayerRepository;
import com.example.Repository.MatchPlayerRepository;
import com.example.Repository.PlayerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final GamePlayerRepository gamePlayerRepository;
    private final MatchPlayerRepository matchPlayerRepository;

    /* register
     * enterQueue
     * getlast20matches
     * getAllTimeScore
     * getRankinginaGame
     */
    
}
