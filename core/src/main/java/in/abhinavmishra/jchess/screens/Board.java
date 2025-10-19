package in.abhinavmishra.jchess.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.*;
import java.util.ArrayList;

public class Board {
    private ArrayList<ArrayList<Square>> squares = new ArrayList<>();
    ShapeRenderer shapeRenderer;

    public Board(float size) {
        shapeRenderer = new ShapeRenderer();
        createBoard(size);
    }

    private void createBoard(final float squareSize) {
        for (int i = 0; i < 8; i++) {
            ArrayList<Square> row = new ArrayList<>(8);
            for (int j = 0; j < 8; j++) {
                Square square = new Square(squareSize, shapeRenderer,i * squareSize, j * squareSize, (i + j) % 2 == 0 ? Color.WHITE : Color.BLACK);
                row.add(j, square);
            }
            squares.add(row);
        }
    }

    public void render() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (ArrayList<Square> row : squares) {
            for(Square square : row) {
                square.draw();
            }
        }
        shapeRenderer.end();
    }
}
