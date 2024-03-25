package org.example.gamebackend;

public class GameMap {
    private static final int[][] map = {
            {0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {1, 1, 0, 0, 0},
            {0, 0, 0, 1, 1},
            {0, 1, 0, 0, 0}
    };

    public static boolean isPositionFree(int x, int y) {
        // Überprüfe, ob x und y innerhalb der Grenzen der Karte liegen
        if (y >= 0 && y < map.length && x >= 0 && x < map[y].length) {
            return map[y][x] == 0; // 0 bedeutet freier Bereich
        }
        // Rückgabe false, wenn außerhalb der Grenzen
        return false;
    }
}
