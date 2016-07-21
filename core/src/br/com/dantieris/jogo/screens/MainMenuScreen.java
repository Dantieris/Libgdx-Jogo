package br.com.dantieris.jogo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import br.com.dantieris.jogo.Jogo;

public class MainMenuScreen implements Screen {

    final Jogo jogo;

    // camera
    private OrthographicCamera camera;

    public MainMenuScreen(Jogo jogo) {
        this.jogo = jogo;

        // camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        jogo.batch.setProjectionMatrix(camera.combined);

        jogo.batch.begin();

        jogo.renderBackground(jogo.batch);

        jogo.font.draw(jogo.batch, "Welcome to Drop!!! ", 100, 150);
        jogo.font.draw(jogo.batch, "Tap anywhere to begin!", 100, 100);

        jogo.batch.end();

        if (Gdx.input.isTouched()) {
            jogo.setScreen(new GameScreen(jogo));
            dispose();
        }
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

    }
}
