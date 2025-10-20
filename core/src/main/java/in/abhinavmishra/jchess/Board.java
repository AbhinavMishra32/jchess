package in.abhinavmishra.jchess;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import in.abhinavmishra.jchess.pieces.Pawn;
import in.abhinavmishra.jchess.pieces.PieceColor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private ArrayList<ArrayList<Square>> squares = new ArrayList<>();
    ShapeRenderer shapeRenderer;
    SpriteBatch batch;

    public Board(float size) {
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        createBoard(size);
        setPieces();
    }

    private void createBoard(final float squareSize) {
        for (int i = 0; i < 8; i++) {
            ArrayList<Square> row = new ArrayList<>(8);
            for (int j = 0; j < 8; j++) {
                Square square = new Square(squareSize, shapeRenderer,i * squareSize, j * squareSize, (i + j) % 2 == 0 ? Color.WHITE : Color.GRAY, i, j);
                row.add(j, square);
            }
            squares.add(row);
        }
    }

    private void setPieces() {
        Piece testPawn = new Pawn(PieceColor.WHITE,"Pawn", new Texture("pieces/white-pawn.png"), 2, 2);
        squares.get(2).get(2).setPiece(testPawn);
    }

    public void renderBoard() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (ArrayList<Square> row : squares) {
            for(Square square : row) {
                square.drawSquare();
            }
        }
        squares.get(2).get(2).drawAllowedMoves(squares);
        shapeRenderer.end();
    }




    public void renderPieces(){
        batch.begin();
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (ArrayList<Square> row : squares) {
            for (Square square : row) {
                square.drawPiece(batch);
            }
        }
        batch.end();
//        shapeRenderer.end();
    }
}
