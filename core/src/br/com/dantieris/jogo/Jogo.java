package br.com.dantieris.jogo;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

import br.com.dantieris.jogo.components.Dropper;
import br.com.dantieris.jogo.models.Bucket;
import br.com.dantieris.jogo.models.Direction;
import br.com.dantieris.jogo.models.Drop;
import br.com.dantieris.jogo.models.Trend;
import br.com.dantieris.jogo.screens.MainMenuScreen;

public class Jogo extends Game {

    // font
    public BitmapFont font;

    // batch
    public SpriteBatch batch;

    // background
    private Texture backgroundTexture;
    private Sprite backgroundSprite;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        font = new BitmapFont();
        batch = new SpriteBatch();

        backgroundTexture = new Texture("data/rain.jpg");
        backgroundSprite = new Sprite(backgroundTexture);

        this.setScreen(new MainMenuScreen(this));
    }

    public void renderBackground(SpriteBatch batch) {
        backgroundSprite.draw(batch);

        Gdx.app.debug("Render", "Rendenizando background.");
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        backgroundTexture.dispose();
    }
}
