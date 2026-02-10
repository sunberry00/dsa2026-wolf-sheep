package com.wolfsheep.view;

import com.wolfsheep.model.GameState;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ConsoleView {
    public void printBoard(GameState state) {
        int w = state.getWolfPos();
        Set<Integer> s = state.getSheepPos();

        // Helper to get string char for a node
        String[] marks = new String[11];
        for (int i = 0; i <= 10; i++) {
            if (i == w) marks[i] = " W ";
            else if (s.contains(i)) marks[i] = "(S)";
            else marks[i] = " " + i + " ";
        }

        System.out.println("\n---------------------------------");
        System.out.println("Round: " + state.getRoundCounter() + " | Turn: " + (state.isWolfTurn() ? "WOLF" : "SHEEP"));
        System.out.println("---------------------------------");

        System.out.println("      " + marks[1] + "---" + marks[4] + "---" + marks[7]);
        System.out.println("     /  |  \\   |   /  |  \\");
        System.out.println(marks[0] + "---" + marks[2] + "---" + marks[5] + "---" + marks[8] + "---" + marks[10]);
        System.out.println("     \\  |  /   |   \\  |  /");
        System.out.println("      " + marks[3] + "---" + marks[6] + "---" + marks[9]);
        System.out.println("---------------------------------");
    }
}
