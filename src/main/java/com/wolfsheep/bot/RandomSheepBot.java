package com.wolfsheep.bot;

import com.wolfsheep.logic.GameRules;
import com.wolfsheep.model.Board;
import com.wolfsheep.model.GameState;
import com.wolfsheep.model.Move;
import com.wolfsheep.player.BotPlayer;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomSheepBot implements BotPlayer {
    private final GameRules rules = new GameRules();

    @Override
    public Move calculateMove(GameState state) {
        Set<Move> allValidMoves = new HashSet<>();

        for (int sheepPos : state.getSheepPos()) {
            for (int neighbor : Board.getNeighbours(sheepPos)) {
                if (rules.isValidMove(state, sheepPos, neighbor, false)) {
                    allValidMoves.add(new Move(sheepPos, neighbor));
                }
            }
        }

        if (allValidMoves.isEmpty()) return null;

        return allValidMoves.stream().findAny().get();
    }
}
