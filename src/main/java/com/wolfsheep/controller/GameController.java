package com.wolfsheep.controller;

import com.wolfsheep.enums.Winner;
import com.wolfsheep.logic.GameRules;
import com.wolfsheep.model.GameState;
import org.springframework.stereotype.Component;

@Component
public class GameController {
    private GameState currentState;
    private final GameRules rules;

    public GameController() {
        this.currentState = new GameState();
        this.rules = new GameRules();
    }

    public String moveWolf(int targetNode) {
        if (currentState.isGameOver()) {
            return "Game over: " + currentState.getWinner() + " wins!";
        }

        if (!currentState.isWolfTurn()) {
            return "It is not the Wolf's turn!";
        }

        if (rules.isValidMove(currentState, currentState.getWolfPos(), targetNode, true)) {
            currentState.setWolfPos(targetNode);
            currentState.switchTurn();
            currentState.incrementRound();
            return checkGameOver();
        } else {
            return "Invalid Wolf Move!";
        }
    }

    public String moveSheep(int currentPos, int targetNode) {
        if (currentState.isGameOver()) {
            return "Game over: " + currentState.getWinner() + " wins!";
        }


        if (currentState.isWolfTurn()) {
            return "It is not the Sheep's turn!";
        }

        if (!currentState.getSheepPos().contains(currentPos)) {
            return "No sheep at position " + currentPos;
        }

        if (rules.isValidMove(currentState, currentPos, targetNode, false)) {
            currentState.getSheepPos().remove(currentPos);
            currentState.getSheepPos().add(targetNode);

            currentState.switchTurn();
            currentState.incrementRound();
            return checkGameOver();
        } else {
            return "Invalid Sheep Move from " + currentPos + " to " + targetNode;
        }
    }

    private String checkGameOver() {
        Winner winner = rules.checkWinCondition(currentState);
        if (winner != Winner.NONE) {
            currentState.setWinner(winner);
            currentState.setGameOver();
            return "Game over: " + winner + " wins!";
        }
        return "Move accepted.";
    }

    public GameState getGameState() {
        return currentState;
    }

    public void resetGame() {
        this.currentState = new GameState();
    }
}
