package in.abhinavmishra.jchess;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
    private static final BitmapFont font = new BitmapFont();
    final private float squareSize;


    public Board(float size) {
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        this.squareSize = size;
        createBoard();
        setPieces();
    }

    private void createBoard() {
        for (int row = 0; row < 8; row++) {
            ArrayList<Square> rowList = new ArrayList<>(8);
            for (int col = 0; col < 8; col++) {
                float x = col * squareSize;
                float y = row * squareSize;
                Square square = new Square(squareSize,
                    shapeRenderer,
                    x,
                    y,
                    (row + col) % 2 == 0 ? Color.WHITE : Color.GRAY,
                    row,
                    col
                );
                rowList.add(square);
            }
            squares.add(rowList);
        }
    }

    private void setPieces() {
        Piece testPawn = new Pawn(PieceColor.WHITE,"Pawn",Assets.PAWN_TEXTURE, 0, 4, (int) (0 * squareSize), (int) (4 * squareSize));
        squares.get(0).get(4).setPiece(testPawn);
    }

    public void renderBoard() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (ArrayList<Square> row : squares) {
            for(Square square : row) {
                square.drawSquare();
            }
        }
        squares.get(0).get(4).drawAllowedMoves(squares);
        shapeRenderer.end();
    }


    public Square getSquareAt(int row, int col) {
        return squares.get(row).get(col);
    }

    public ArrayList<ArrayList<Square>> getSquares() {
        return squares;
    }


    public void renderPieces(){
        batch.begin();
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (ArrayList<Square> row : squares) {
            for (Square square : row) {
                square.drawPiece(batch);
                if (Config.DEV) {
                    font.draw(batch, square.getRow() + "" + square.getCol(), square.getX(), square.getY() + 14);
                }
            }
        }
        batch.end();
//        shapeRenderer.end();
    }

    public void boardDispose() {
        font.dispose();
        batch.dispose();
        shapeRenderer.dispose();
    }
}
