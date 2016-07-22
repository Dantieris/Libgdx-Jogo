package br.com.dantieris.jogo.models.powerups;

import br.com.dantieris.jogo.models.powerups.contracts.Action;
import br.com.dantieris.jogo.screens.GameScreen;

/**
 * Created by dantieris on 22/07/16.
 */
public class LoseLifeAction implements Action {
    private GameScreen ruler;

    public LoseLifeAction(GameScreen ruler) {
        this.ruler = ruler;
    }

    @Override
    public void action() {
        ruler.reduceLife();
    }
}
