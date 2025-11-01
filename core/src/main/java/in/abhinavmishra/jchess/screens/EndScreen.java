package in.abhinavmishra.jchess.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import in.abhinavmishra.jchess.ChessGame;
import in.abhinavmishra.jchess.pieces.PieceColor;

import java.awt.*;

public class EndScreen implements Screen {

    private Stage stage;
    private Skin skin;
    final ChessGame game;
    private PieceColor winnerColor;
    private Label winnerLabel;

    public EndScreen(ChessGame game, PieceColor winnerColor) {
        this.winnerColor = winnerColor;
        this.game = game;

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("gdx-skins/default/skin/uiskin.json"));

        // Table for layout
        Table table = new Table();
        table.setFillParent(true);

        TextButton startButton = new TextButton("Restart", skin);
        startButton.getLabel().setFontScale(2f);
        // Ensure label text is black on white background
        startButton.getLabel().setColor(Color.BLACK);
        // Also set style fontColor (some skins use this)
        TextButton.TextButtonStyle tstyle = startButton.getStyle();
        tstyle.fontColor = Color.BLACK;
        startButton.setStyle(tstyle);

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }
        });

        // Winner label - text is set in render() based on winnerColor
        this.winnerLabel = new Label("", skin);
        winnerLabel.setFontScale(2f);
        winnerLabel.setColor(Color.BLACK);

        table.center();
        table.add(winnerLabel).padBottom(30).row();
        table.add(startButton).width(300).height(80).pad(10).row();

        stage.addActor(table);
    }

    @Override
    public void show() {
        // Prepare your screen here.
    }

    @Override
    public void render(float delta) {
        // White background and black text to match MenuScreen
        ScreenUtils.clear(Color.WHITE);

        String winnerName = winnerColor == PieceColor.WHITE ? "Black won the match!" : "White won the match!";
        winnerLabel.setText(winnerName);

        stage.act(delta);
        stage.draw();
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
