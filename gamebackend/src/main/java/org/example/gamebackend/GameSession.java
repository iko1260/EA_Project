package org.example.gamebackend;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.HashMap;

public class GameSession {
    private static final Map<String, Position> playerPositions = new ConcurrentHashMap<>();

    public void updatePosition(String username, Position newPosition) {
        // Überprüfe, ob die neue Position innerhalb der Grenzen liegt und frei ist
        if (GameMap.isPositionFree(newPosition.getX(), newPosition.getY())) {
            playerPositions.put(username, newPosition);
        } else {
            System.out.println("Bewegung ungültig: Position ist blockiert oder außerhalb der Karte.");
        }
    }

    public Map<String, Position> getAllPositions() {
        return new HashMap<>(playerPositions);
    }

    public static class Position {
        private int x;
        private int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
