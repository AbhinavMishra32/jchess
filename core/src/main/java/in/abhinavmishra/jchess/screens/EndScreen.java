package in.abhinavmishra.jchess.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import in.abhinavmishra.jchess.ChessGame;
import in.abhinavmishra.jchess.pieces.PieceColor;

public class EndScreen implements Screen {

    private Stage stage;
    private Skin skin;
    final ChessGame game;
    BitmapFont font;
    SpriteBatch batch;
    PieceColor winnerColor;

    public EndScreen(ChessGame game, PieceColor winnerColor) {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(2f);

        this.game = game;
        this.winnerColor = winnerColor;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("gdx-skins/default/skin/uiskin.json"));

        TextButton startButton = new TextButton("Restart", skin);
        startButton.setSize(200, 50);
        startButton.setPosition(200, 200);

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });

        stage.addActor(startButton);
    }

    @Override
    public void show() {
        // Prepare your screen here.
    }

    @Override
    public void render(float delta) {
        String winnerName = winnerColor == PieceColor.BLACK ? "White won the match!" : "Black won the match!";
        batch.begin();
        font.draw(batch, winnerName, 100, 200); // x=100, y=200

        ScreenUtils.clear(Color.BLACK);
        stage.act(delta);
        stage.draw();
        batch.end();
        // Draw your screen here. "delta" is the time since last render in seconds.
    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a normal size before updating.
        if(width <= 0 || height <= 0) return;

        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        // Destroy screen's assets here.
    }
}
