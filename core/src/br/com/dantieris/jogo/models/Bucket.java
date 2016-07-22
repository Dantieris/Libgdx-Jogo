package br.com.dantieris.jogo.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Bucket extends MovableShape {

    public Bucket() {
        super();
    }

    public Bucket(Rectangle rectangle, Texture texture) {
        super(rectangle, texture);
    }

    public Bucket(Rectangle rectangle, Texture texture, int horizontalSpeed) {
        super(rectangle, texture, horizontalSpeed);
    }
}
