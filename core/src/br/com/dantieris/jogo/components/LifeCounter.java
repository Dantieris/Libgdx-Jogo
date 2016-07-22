package br.com.dantieris.jogo.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class LifeCounter implements Disposable {

    // maximum life
    private int maximumLife;
    // current life
    private int currentLife;

    // texture
    private Texture heart;

    public LifeCounter() {
        this.maximumLife = 10;
        this.currentLife = 5;

        this.heart = new Texture(Gdx.files.internal("data/heart.png"));
    }

    public void reduceLife() {
        if (currentLife > 0) {
            --currentLife;
        }
    }

    public void increaseLife() {
        if (currentLife < maximumLife) {
            ++currentLife;
        }
    }

    public boolean isAlive() {
        return (currentLife <= 0);
    }

    public void drawLifes(SpriteBatch batch) {
        if (currentLife <= 0) {
            return;
        }

        int size = 40;
        int margin = 20;

        int x = Gdx.graphics.getWidth() - size - margin;
        int y = Gdx.graphics.getHeight() - size - margin;

        for (int i = 0 ; i < currentLife ; i++) {
            float currentSpacesWidth = i * size;

            batch.draw(heart, x - currentSpacesWidth, y, size, size);
        }
    }

    @Override
    public void dispose() {
        heart.dispose();
    }
}
