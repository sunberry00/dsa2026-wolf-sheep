package com.wolfsheep.player;

import com.wolfsheep.model.GameState;
import com.wolfsheep.model.Move;

public interface BotPlayer {
    Move calculateMove(GameState state);
}
