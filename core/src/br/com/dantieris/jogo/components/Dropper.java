package br.com.dantieris.jogo.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;

import br.com.dantieris.jogo.models.Direction;
import br.com.dantieris.jogo.models.Drop;
import br.com.dantieris.jogo.models.Trend;

public class Dropper implements Disposable {

    private long dropInterval;

    private Texture[] textures = {
        new Texture(Gdx.files.internal("data/coin.png")),
        new Texture(Gdx.files.internal("data/pumpkin.png")),
        new Texture(Gdx.files.internal("data/droplet.png")),
        new Texture(Gdx.files.internal("data/eye.png"))
    };

    public Dropper() {
        this.dropInterval = 1000 * 1000 * 1000;
    }

    public Drop buildDrop() {
        switch (MathUtils.random(0, 3)) {
            case 0 :
                return new Drop(
                        new Rectangle(),
                        textures[0],
                        300,
                        Trend.GOOD,
                        100
                );
            case 1 :
                return new Drop(
                        new Rectangle(),
                        textures[1],
                        250,
                        Trend.BAD,
                        100
                );
            case 2 :
                return new Drop(
                        new Rectangle(),
                        textures[2],
                        200,
                        Trend.GOOD,
                        50
                );
            case 3 :
                return new Drop(
                        new Rectangle(),
                        textures[3],
                        200,
                        200,
                        Trend.BAD,
                        200,
                        randomDirection()
                );
            default:
                return new Drop(
                    new Rectangle(),
                    textures[2],
                    200,
                    Trend.GOOD,
                    50
                );
        }
    }

    private Direction randomDirection() {
        int random = MathUtils.random(1, 2);

        switch (random) {
            case 1 : return Direction.LEFT;
            case 2 :
                // sem break;
            default:
                return Direction.RIGHT;
        }
    }

    public long getDropInterval() {
        return dropInterval;
    }

    public Dropper setDropInterval(long dropInterval) {
        this.dropInterval = dropInterval;
        return this;
    }

    public void  increaseDropFrequency() {
        this.dropInterval -= 1000;
        Gdx.app.debug("Drop Interval", "Aumento da taxa para segundos: " + this.dropInterval);
    }

    public void reduceDropFrequency() {
        this.dropInterval += 500;

        Gdx.app.debug("Drop Interval", "Diminuição da taxa para segundos: " + this.dropInterval);
    }

    @Override
    public void dispose() {
        for (Texture texture : textures) {
            texture.dispose();
        }
    }
}
