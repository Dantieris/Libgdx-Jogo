package br.com.dantieris.jogo.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import br.com.dantieris.jogo.models.contracts.Scoring;
import br.com.dantieris.jogo.models.contracts.Trending;
import br.com.dantieris.jogo.models.powerups.GiveLifeAction;
import br.com.dantieris.jogo.models.powerups.contracts.Action;

public class Drop extends MovableShape implements Scoring, Trending {

    private int score;
    private Trend trend;
    private Action action;

    public Drop() {
        super();

        this.score = 0;
        this.trend = Trend.NEUTRAL;
    }

    public Drop(Rectangle rectangle, Texture texture, int verticalSpeed, Trend trend, int score) {
        super(rectangle, texture, verticalSpeed, 0, Direction.CENTER);

        this.trend = trend;
        this.score = score;
    }

    public Drop(Rectangle rectangle, Texture texture, int verticalSpeed, int horizontalSpeed, Trend trend, int score, Direction direction) {
        super(rectangle, texture, verticalSpeed, horizontalSpeed, direction);

        this.trend = trend;
        this.score = score;
    }

    public Drop(Rectangle rectangle, Texture texture, int verticalSpeed, Trend trend, int score, Action action) {
        super(rectangle, texture, verticalSpeed, 0, Direction.CENTER);

        this.trend = trend;
        this.score = score;
        this.action = action;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public void setTrend(Trend trend) {
        this.trend = trend;
    }

    @Override
    public Trend getTrend() {
        return this.trend;
    }

    public boolean hasAction() {
        return action != null;
    }

    public void action() {
        this.action.action();
    }
}
