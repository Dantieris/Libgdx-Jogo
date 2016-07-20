package br.com.dantieris.jogo.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import br.com.dantieris.jogo.models.contracts.Scoring;
import br.com.dantieris.jogo.models.contracts.Trending;

public class Drop extends BasicShapeModel implements Scoring, Trending {

    private int score;
    private Trend trend;

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
}
