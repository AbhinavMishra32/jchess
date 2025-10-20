package in.abhinavmishra.jchess.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import in.abhinavmishra.jchess.Board;
import in.abhinavmishra.jchess.ChessGame;

public class GameScreen implements Screen {
    final ChessGame game;
    private Board board;
    private float screenWidth;
    private float screenHeight;

    public GameScreen(ChessGame game) {
        this.screenWidth = Gdx.graphics.getWidth();
        this.screenHeight = Gdx.graphics.getHeight();
        this.game = game;
        this.board = new Board(screenWidth/8);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(new Color(227 / 255f, 202 / 255f, 184 / 255f, 0.7f));
        board.renderBoard();
        board.renderPieces();
    }

    @Override
    public void resize(int width, int height) {
        if (width <= 0 || height <= 0 || height != width) return;
        this.screenWidth = Gdx.graphics.getWidth();
        this.screenHeight = Gdx.graphics.getHeight();
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
