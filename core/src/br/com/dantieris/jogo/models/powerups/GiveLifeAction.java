package br.com.dantieris.jogo.models.powerups;

import br.com.dantieris.jogo.models.powerups.contracts.Action;
import br.com.dantieris.jogo.screens.GameScreen;

public class GiveLifeAction implements Action {
    private GameScreen ruler;

    public GiveLifeAction(GameScreen ruler) {
        this.ruler = ruler;
    }

    @Override
    public void action() {
        ruler.increaseLife();
    }
}
