package com.wolfsheep.logic;

import com.wolfsheep.enums.Direction;
import com.wolfsheep.enums.Winner;
import com.wolfsheep.model.Board;
import com.wolfsheep.model.GameState;

import java.util.Set;

public class GameRules {
    public boolean isValidMove(GameState state, int from, int to, boolean isWolf) {
        // Check if nodes are connected
        if (!Board.isConnected(from, to)) return false;

        // Check if target is empty
        if (state.getWolfPos() == to) return false;
        if (state.getSheepPos().contains(to)) {
            return false;
        }

        if (isWolf) {
            return true;
        }
        return isForwardMove(from, to);
    }

    public Winner checkWinCondition(GameState state) {
        if (state.getWolfPos() == 0) {
            return Winner.WOLF;
        }

        if (!canWolfMove(state)) {
            return Winner.SHEEP;
        }

        if (!canAnySheepMove(state)) {
            return Winner.WOLF;
        }

        if (state.getRoundCounter() > 20) {
            return Winner.WOLF;
        }

        return Winner.NONE;
    }

    private boolean isForwardMove(int from, int to) {
        Direction dir = Board.getDirection(from, to);

        if (dir == null) {
            return false;
        }

        return dir != Direction.WEST && dir != Direction.NORTH_WEST && dir != Direction.SOUTH_WEST;
    }

    private boolean canWolfMove(GameState state) {
        Set<Integer> neighbors = Board.getNeighbours(state.getWolfPos());
        for (int neighbor : neighbors) {
            if (isValidMove(state, state.getWolfPos(), neighbor, true)) {
                return true;
            }
        }
        return false;
    }

    // Helper: Can ANY sheep move?
    private boolean canAnySheepMove(GameState state) {
        for (int sheepPos : state.getSheepPos()) {
            Set<Integer> neighbors = Board.getNeighbours(sheepPos);
            for (int neighbor : neighbors) {
                if (isValidMove(state, sheepPos, neighbor, false)) {
                    return true;
                }
            }
        }
        return false;
    }
}
