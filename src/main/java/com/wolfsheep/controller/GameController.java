package com.wolfsheep.controller;

import com.wolfsheep.bot.RandomSheepBot;
import com.wolfsheep.bot.RandomWolfBot;
import com.wolfsheep.enums.Winner;
import com.wolfsheep.logic.GameRules;
import com.wolfsheep.model.GameState;
import com.wolfsheep.model.Move;
import com.wolfsheep.player.BotPlayer;
import org.springframework.stereotype.Component;

@Component
public class GameController {
    private GameState currentState;
    private final GameRules rules;
    private BotPlayer wolfBot;
    private BotPlayer sheepBot;
    private String currentMode = "PvP";

    public GameController() {
        this.currentState = new GameState();
        this.rules = new GameRules();
    }

    public void setGameMode(String mode) {
        this.currentMode = mode;
        switch (mode) {
            case "PvP" -> {
                this.wolfBot = null;
                this.sheepBot = null;

            }
            case "PlayerVsWolfBot" -> {
                this.wolfBot = new RandomWolfBot();
                this.sheepBot = null;
            }
            case "SheepBotVsPlayer" -> {
                this.wolfBot = null;
                this.sheepBot = new RandomSheepBot();
            }
            case "BotVsBot" -> {
                this.wolfBot = new RandomWolfBot();
                this.sheepBot = new RandomSheepBot();
            }
        }
    }

    public String playBotTurn() {
        if (currentState.isGameOver()) return "";

        if (currentState.isWolfTurn() && wolfBot != null) {
            Move move = wolfBot.calculateMove(currentState);
            if (move != null) {
                moveWolf(move.getTo());
                return " (WolfBot moved to " + move.getTo() + ")";
            }
        } else if (!currentState.isWolfTurn() && sheepBot != null) {
            Move move = sheepBot.calculateMove(currentState);
            if (move != null) {
                moveSheep(move.getFrom(), move.getTo());
                return " (SheepBot moved " + move.getFrom() + " to " + move.getTo() + ")";
            }
        }
        return "";
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
        setGameMode(this.currentMode);
    }

    public String getCurrentMode() {
        return currentMode;
    }
}
