package com.wolfsheep.bot;

import com.wolfsheep.logic.GameRules;
import com.wolfsheep.model.Board;
import com.wolfsheep.model.GameState;
import com.wolfsheep.model.Move;
import com.wolfsheep.player.BotPlayer;

import java.util.HashSet;
import java.util.Set;

public class RandomWolfBot implements BotPlayer {

    private final GameRules rules = new GameRules();

    @Override
    public Move calculateMove(GameState state) {
        int wolfPos = state.getWolfPos();
        Set<Integer> validTargets = new HashSet<>();

        for (int neighbor : Board.getNeighbours(wolfPos)) {
            if (rules.isValidMove(state, wolfPos, neighbor, true)) {
                validTargets.add(neighbor);
            }
        }

        if (validTargets.isEmpty()) return null;

        int randomTarget = validTargets.stream().findAny().orElseThrow();
        return new Move(wolfPos, randomTarget);
    }
}
