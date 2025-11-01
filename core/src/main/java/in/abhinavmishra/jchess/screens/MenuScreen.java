package in.abhinavmishra.jchess.screens;

import com.badlogic.gdx.Game;
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

/** First screen of the application. Displayed after the application is created. */
public class MenuScreen implements Screen {

    private Stage stage;
    private Skin skin;
    final ChessGame game;

    public MenuScreen(ChessGame game) {
        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("gdx-skins/default/skin/uiskin.json"));

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Title label
        Label titleLabel = new Label("JChess", skin);
        titleLabel.setFontScale(4f);
        titleLabel.setColor(Color.BLACK);

        // Subtitle
        Label subtitleLabel = new Label("A Chess Game", skin);
        subtitleLabel.setFontScale(2f);
        subtitleLabel.setColor(Color.BLACK);

        // Author label
        Label authorLabel = new Label("Made by Abhinav Mishra", skin);
        authorLabel.setFontScale(1.2f);
        authorLabel.setColor(Color.BLACK);
        
        Label githubLabel = new Label("@AbhinavMishra32", skin);
        githubLabel.setFontScale(1f);
        githubLabel.setColor(Color.BLACK);

        // Start button
        TextButton startButton = new TextButton("Start Game", skin);
        startButton.getLabel().setFontScale(2f);
        startButton.getLabel().setColor(Color.BLACK);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });

        // Layout
        table.add(titleLabel).padBottom(30).row();
        table.add(subtitleLabel).padBottom(50).row();
        table.add(startButton).width(300).height(80).pad(10).row();
        table.add(authorLabel).padTop(100).row();
        table.add(githubLabel).padTop(10);
    }

    @Override
    public void show() {
        // Prepare your screen here.
    }

    @Override
    public void render(float delta) {
        // White background
        ScreenUtils.clear(Color.WHITE);
        stage.act(delta);
        stage.draw();
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
