package br.com.dantieris.jogo.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import br.com.dantieris.jogo.Jogo;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.title = "Título do Jogo";
        config.width = 800;
        config.height = 480;

        config.foregroundFPS = 0;
        config.backgroundFPS = 0;

        new LwjglApplication(new Jogo(), config);
    }
}
