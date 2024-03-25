package org.example.gamebackend;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import java.util.Map;

@Controller
public class GameWebSocketController {
    private final GameSession gameSession = new GameSession();

    @MessageMapping("/move")
    @SendTo("/topic/positions")
    public Map<String, GameSession.Position> movePlayer(PlayerMovement movement) {
        gameSession.updatePosition(movement.getUsername(), new GameSession.Position(movement.getPosition().getX(), movement.getPosition().getY()));
        return gameSession.getAllPositions();
    }

    // Eine einfache Klasse, um Bewegungsdaten zu empfangen
    public static class PlayerMovement {
        private String username;
        private GameSession.Position position;

        public PlayerMovement() {}

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public GameSession.Position getPosition() {
            return position;
        }

        public void setPosition(GameSession.Position position) {
            this.position = position;
        }
    }
}
