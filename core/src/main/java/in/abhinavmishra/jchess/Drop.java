package in.abhinavmishra.jchess;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Drop extends Game {
    public SpriteBatch batch;
    public BitmapFont font;
    public FitViewport viewport;

    public void create() {
        batch = new SpriteBatch();
        viewport = new FitViewport(8, 5);

        this.setScreen(new MainMenuScreen(this)); // font will be created inside the screen
    }

    @Override
    public void dispose() {
        batch.dispose();
        if (font != null) font.dispose();
    }
}
