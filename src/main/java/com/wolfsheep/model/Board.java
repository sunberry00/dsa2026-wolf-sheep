package com.wolfsheep.model;

import com.wolfsheep.enums.Direction;

import java.util.*;

public class Board {
    private static final Map<Integer, Set<Integer>> connections = new HashMap<>();

    static {
        connections.put(0, Set.of(1, 2, 3));
        connections.put(1, Set.of(0, 2, 4, 5));
        connections.put(2, Set.of(0, 1, 3, 5));
        connections.put(3, Set.of(0, 2, 5, 6));
        connections.put(4, Set.of(1, 5, 7));
        connections.put(5, Set.of(1, 2, 3, 4, 6, 7, 8, 9));
        connections.put(6, Set.of(3, 5, 9));
        connections.put(7, Set.of(4, 5, 8, 10));
        connections.put(8, Set.of(5, 7, 9, 10));
        connections.put(9, Set.of(5, 6, 8, 10));
        connections.put(10, Set.of(7, 8, 9));
    }

    public static boolean isConnected(int from, int to) {
        return connections.getOrDefault(from, Collections.emptySet()).contains(to);
    }

    public static Set<Integer> getNeighbours(int node) {
        return connections.get(node);
    }

    public static Direction getDirection(int from, int to) {
        return switch (from) {
            case 0 -> switch (to) {
                case 1 -> Direction.NORTH_EAST;
                case 2 -> Direction.EAST;
                case 3 -> Direction.SOUTH_EAST;
                default -> null;
            };
            case 1 -> switch (to) {
                case 0 -> Direction.SOUTH_WEST;
                case 2 -> Direction.SOUTH;
                case 4 -> Direction.EAST;
                case 5 -> Direction.SOUTH_EAST;
                default -> null;
            };
            case 2 -> switch (to) {
                case 0 -> Direction.WEST;
                case 1 -> Direction.NORTH;
                case 3 -> Direction.SOUTH;
                case 5 -> Direction.EAST;
                default -> null;
            };
            case 3 -> switch (to) {
                case 0 -> Direction.NORTH_WEST;
                case 2 -> Direction.NORTH;
                case 5 -> Direction.NORTH_EAST;
                case 6 -> Direction.EAST;
                default -> null;
            };
            case 4 -> switch (to) {
                case 1 -> Direction.WEST;
                case 5 -> Direction.SOUTH;
                case 7 -> Direction.EAST;
                default -> null;
            };
            case 5 -> switch (to) {
                case 1 -> Direction.NORTH_WEST;
                case 2 -> Direction.WEST;
                case 3 -> Direction.SOUTH_WEST;
                case 4 -> Direction.NORTH;
                case 6 -> Direction.SOUTH;
                case 7 -> Direction.NORTH_EAST;
                case 8 -> Direction.EAST;
                case 9 -> Direction.SOUTH_EAST;
                default -> null;
            };
            case 6 -> switch (to) {
                case 3 -> Direction.WEST;
                case 5 -> Direction.NORTH;
                case 9 -> Direction.EAST;
                default -> null;
            };
            case 7 -> switch (to) {
                case 4 -> Direction.WEST;
                case 5 -> Direction.SOUTH_WEST;
                case 8 -> Direction.SOUTH;
                case 10 -> Direction.SOUTH_EAST;
                default -> null;
            };
            case 8 -> switch (to) {
                case 5 -> Direction.WEST;
                case 7 -> Direction.NORTH;
                case 9 -> Direction.SOUTH;
                case 10 -> Direction.EAST;
                default -> null;
            };
            case 9 -> switch (to) {
                case 6 -> Direction.WEST;
                case 5 -> Direction.NORTH_WEST;
                case 8 -> Direction.NORTH;
                case 10 -> Direction.NORTH_EAST;
                default -> null;
            };
            case 10 -> switch (to) {
                case 7 -> Direction.NORTH_WEST;
                case 8 -> Direction.WEST;
                case 9 -> Direction.SOUTH_WEST;
                default -> null;
            };
            default -> throw new IllegalArgumentException("Invalid Node ID: " + from);
        };
    }
}
