package br.com.dantieris.jogo.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;

import br.com.dantieris.jogo.models.contracts.Coordinates2D;
import br.com.dantieris.jogo.models.contracts.Dimensions2D;

public class StaticShape implements Coordinates2D, Dimensions2D, Disposable {

    private Rectangle rectangle;
    private Texture texture;

    public StaticShape() {
        this.rectangle = null;
        this.texture = null;
    }

    public StaticShape(Rectangle rectangle, Texture texture, float x, float y, float size) {
        this();

        this.rectangle = rectangle;
        this.texture = texture;

        setX(x);
        setY(y);
        setSize(size);
    }

    @Override
    public void setPosition(float x, float y) {
        rectangle.setPosition(x, y);
    }

    @Override
    public float getX() {
        return rectangle.getX();
    }

    @Override
    public void setX(float x) {
        rectangle.setX(x);
    }

    @Override
    public float getY() {
        return rectangle.getY();
    }

    @Override
    public void setY(float y) {
        rectangle.setY(y);
    }

    @Override
    public float getWidth() {
        return rectangle.getWidth();
    }

    @Override
    public void setWidth(float width) {
        rectangle.setWidth(width);
    }

    @Override
    public float getHeight() {
        return rectangle.getHeight();
    }

    @Override
    public void setHeight(float height) {
        rectangle.setHeight(height);
    }

    @Override
    public void setSize(float size) {
        rectangle.setSize(size);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public StaticShape setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
        return this;
    }

    public Texture getTexture() {
        return texture;
    }

    public StaticShape setTexture(Texture texture) {
        this.texture = texture;
        return this;
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
