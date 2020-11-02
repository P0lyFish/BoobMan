package main.java.backend.agents;

import backend.GameState;

abstract public class BotAgent extends Agent {

    public BotAgent() {}

    abstract public void updateGameState(GameState gameState);
}
