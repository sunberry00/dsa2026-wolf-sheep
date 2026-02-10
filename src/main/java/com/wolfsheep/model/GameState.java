package com.wolfsheep.model;

import com.wolfsheep.enums.Winner;

import java.util.HashSet;
import java.util.Set;

public class GameState {
    private int wolfPos;
    private Set<Integer> sheepPos;
    private boolean isWolfTurn;
    private int roundCounter;
    private boolean isGameOver;
    private Winner winner;

    public GameState() {
        this.wolfPos = 5;
        this.sheepPos = new HashSet<>(Set.of(0, 1, 3));
        this.isWolfTurn = false;
        this.roundCounter = 0;
        this.isGameOver = false;
        this.winner = Winner.NONE;
    }

    public void incrementRound() {
        this.roundCounter++;
    }

    public int getWolfPos() {
        return wolfPos;
    }

    public Set<Integer> getSheepPos() {
        return sheepPos;
    }

    public boolean isWolfTurn() {
        return isWolfTurn;
    }

    public void setWolfPos(int pos) {
        this.wolfPos = pos;
    }

    public void switchTurn() {
        this.isWolfTurn = !isWolfTurn;
    }

    public int getRoundCounter() {
        return roundCounter;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver() {
        this.isGameOver = true;
    }

    public void setWinner(Winner winner) {
        this.winner = winner;
    }

    public String getWinner() {
        return winner.label;
    }
}
