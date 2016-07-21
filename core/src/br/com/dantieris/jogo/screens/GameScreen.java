package br.com.dantieris.jogo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

import br.com.dantieris.jogo.Jogo;
import br.com.dantieris.jogo.components.Dropper;
import br.com.dantieris.jogo.models.Bucket;
import br.com.dantieris.jogo.models.Direction;
import br.com.dantieris.jogo.models.Drop;
import br.com.dantieris.jogo.models.Trend;

public class GameScreen implements Screen {

    final Jogo jogo;

    // components
    private Dropper dropper;

    // vector
    private Vector3 touchPos;

    // assets data
    private Sound dropSound;
    private Music rainMusic;

    // bucket
    private Bucket bucket;

    // rain drops
    private Array<Drop> drops;
    private long lastDropTime;

    // score
    private long totalScore;

    // camera
    private OrthographicCamera camera;

    public GameScreen(Jogo jogo) {
        this.jogo = jogo;

        dropper = new Dropper();

        totalScore = 0;

        // camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // bucket
        bucket = new Bucket(
            new Rectangle(),
            new Texture(Gdx.files.internal("data/bucket.png")),
            500
        );
        bucket.setSize(64);
        spawnBucket();

        // batch

        // load the drop sound effect and the rain background "music"
        dropSound = Gdx.audio.newSound(Gdx.files.internal("data/drop.mp3"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("data/rain.mp3"));

        // start the playback of the background music immediately
        rainMusic.setLooping(true);
        rainMusic.setVolume(0.5f);

        Gdx.app.debug("Create", "Começa a tocar música de chuva em looping com volume " + 0.5f);

        // drops
        drops = new Array<Drop>();
        spawnRaindrop();
    }

    private void spawnBucket() {
        int halfScreenWidth = Gdx.graphics.getWidth() / 2;
        int halfBucketWidth = ((int) bucket.getWidth()) / 2;
        bucket.setPosition(halfScreenWidth - halfBucketWidth, 20);

        Gdx.app.debug("Create", "Spawn Bucket no x " + (halfScreenWidth - halfBucketWidth) + " e y " + 20);
    }

    private void spawnRaindrop() {
        Drop drop = dropper.buildDrop();
        drop.setSize(64);
        drop.setX(MathUtils.random(0, Gdx.graphics.getWidth() - drop.getWidth()));
        drop.setY(Gdx.graphics.getHeight());

        drops.add(drop);

        lastDropTime = TimeUtils.nanoTime();

        Gdx.app.debug("Spawn Drops", "Spawn do drop com tendência " + drop.getTrend() + " e score " + drop.getScore());
    }

    @Override
    public void show() {
        rainMusic.play();
    }

    @Override
    public void render(float delta) {
        Gdx.app.debug("Render", "Inicio do método de rendenização.");

        Gdx.gl.glClearColor(1, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isTouched()) {
            touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            bucket.setX(touchPos.x - bucket.getWidth() / 2);

            Gdx.app.debug("Render", "Toque na tela nas coordenadas (" + touchPos.x + "," + touchPos.y + ")");
            Gdx.app.debug("Render", "Balde posicionado no x " + bucket.getX());
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            bucket.setX(bucket.getX() - bucket.getHorizontalSpeed() * delta);

            Gdx.app.debug("Render", "Balde se movimentado para a esquerda");
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            bucket.setX(bucket.getX() + bucket.getHorizontalSpeed() * delta);

            Gdx.app.debug("Render", "Balde se movimentado para a direita");
        }

        if (bucket.getX() < 0) {
            bucket.setX(0);

            Gdx.app.debug("Render", "Balde passou do limite da esquerda da tela.");
        }

        if (bucket.getX() > Gdx.graphics.getWidth() - bucket.getWidth()) {
            bucket.setX(Gdx.graphics.getWidth() - bucket.getWidth());

            Gdx.app.debug("Render", "Balde passou do limite da direita da tela.");
        }

        if (TimeUtils.nanoTime() - lastDropTime > dropper.getDropInterval()) {
            spawnRaindrop();
        }

        Iterator<Drop> iter = drops.iterator();
        while(iter.hasNext()) {
            Drop drop = iter.next();

            if(drop.getRectangle().overlaps(bucket.getRectangle())) {
                dropSound.play(0.5f);
                iter.remove();

                Gdx.app.debug("Render", "Balde pegou um drop.");

                if (drop.getTrend().equals(Trend.GOOD)) {
                    totalScore += drop.getScore();
                    dropper.increaseDropFrequency();

                    Gdx.app.debug("Render", "Aumentando a frequencia de drops e a pontuação total pois o balde pegou um drop bom.");
                }

                if (drop.getTrend().equals(Trend.BAD)) {
                    totalScore -= drop.getScore();
                    dropper.reduceDropFrequency();

                    Gdx.app.debug("Render", "Diminuino a frequencia de drops e a pontuação total pois o balde pegou um drop ruim.");
                }
                continue;
            }

            drop.setY(drop.getY() - drop.getVerticalSpeed() * delta);

            if (drop.getDirection() != Direction.CENTER) {

                float dropX = drop.getX() - drop.getHorizontalSpeed() * delta;
                if (drop.getDirection().equals(Direction.RIGHT)) {
                    dropX = drop.getX() + drop.getHorizontalSpeed() * delta;
                }

                Gdx.app.debug("Render", "Movendo o drop na horintal para o x " + dropX + " na direção " + drop.getDirection());

                drop.setX(dropX);

                if (drop.getX() > Gdx.graphics.getWidth() - drop.getWidth()) {
                    drop.setX(Gdx.graphics.getWidth() - drop.getWidth());
                    drop.setDirection(Direction.LEFT);

                    Gdx.app.debug("Render", "Alterando a direção do drop para a esquerda.");
                }

                if (drop.getX() < 0) {
                    drop.setX(0);
                    drop.setDirection(Direction.RIGHT);

                    Gdx.app.debug("Render", "Alterando a direção do drop para a direita.");
                }
            }

            if(drop.getY() + drop.getHeight() < 0) {
                iter.remove();

                Gdx.app.debug("Render", "Removendo drop que saiu da tela.");
            }
        }

        // update camera
        camera.update();

        jogo.batch.setProjectionMatrix(camera.combined);
        jogo.batch.begin();

        jogo.renderBackground(jogo.batch);

        jogo.batch.draw(bucket.getTexture(), bucket.getX(), bucket.getY());

        for(Drop drop: drops) {
            jogo.batch.draw(drop.getTexture(), drop.getX(), drop.getY());
        }

        jogo.font.draw(jogo.batch, "Total Score: " + totalScore, 20, Gdx.graphics.getHeight() - 20);

        jogo.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        bucket.dispose();
        dropper.dispose();
        dropSound.dispose();
        rainMusic.dispose();

        Gdx.app.debug("Dispose", "Objetos sendo apagados.");
    }
}
