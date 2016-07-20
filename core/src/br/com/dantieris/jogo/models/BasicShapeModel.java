package br.com.dantieris.jogo.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;

import br.com.dantieris.jogo.models.contracts.Coordinates2D;
import br.com.dantieris.jogo.models.contracts.Dimensions2D;
import br.com.dantieris.jogo.models.contracts.Movimentable;

public class BasicShapeModel implements Coordinates2D, Dimensions2D, Disposable, Movimentable {
    private Rectangle rectangle;
    private Texture texture;

    private int verticalSpeed;
    private int horizontalSpeed;
    private Direction direction;

    public BasicShapeModel() {
        this.rectangle = null;
        this.texture = null;

        this.verticalSpeed = 0;
        this.horizontalSpeed = 0;
        this.direction = Direction.CENTER;
    }

    public BasicShapeModel(Rectangle rectangle, Texture texture) {
        this();

        this.rectangle = rectangle;
        this.texture = texture;
    }

    public BasicShapeModel(Rectangle rectangle, Texture texture, int horizontalSpeed) {
        this();

        this.rectangle = rectangle;
        this.texture = texture;

        this.horizontalSpeed = horizontalSpeed;
    }

    public BasicShapeModel(Rectangle rectangle, Texture texture, int verticalSpeed, int horizontalSpeed, Direction direction) {
        this();

        this.rectangle = rectangle;
        this.texture = texture;

        this.verticalSpeed = verticalSpeed;
        this.horizontalSpeed = horizontalSpeed;

        this.direction = direction;
    }

    @Override
    public void setX(float x) {
        rectangle.setX(x);
    }

    @Override
    public void setY(float y) {
        rectangle.setY(y);
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
    public float getY() {
        return rectangle.getY();
    }

    @Override
    public float getWidth () {
        return rectangle.getWidth();
    }

    @Override
    public void setWidth (float width) {
        rectangle.setWidth(width);
    }

    @Override
    public float getHeight () {
        return rectangle.getHeight();
    }

    @Override
    public void setHeight (float height) {
        rectangle.setHeight(height);
    }

    @Override
    public void setSize(float size) {
        rectangle.setSize(size);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public BasicShapeModel setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
        return this;
    }

    public Texture getTexture() {
        return texture;
    }

    public BasicShapeModel setTexture(Texture texture) {
        this.texture = texture;
        return this;
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    @Override
    public void setVerticalSpeed(int verticalSpeed) {
        this.verticalSpeed = verticalSpeed;
    }

    @Override
    public int getVerticalSpeed() {
        return this.verticalSpeed;
    }

    @Override
    public void setHorizontalSpeed(int horizontalSpeed) {
        this.horizontalSpeed = horizontalSpeed;
    }

    @Override
    public int getHorizontalSpeed() {
        return this.horizontalSpeed;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }
}
