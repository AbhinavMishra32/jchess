package in.abhinavmishra.jchess;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import in.abhinavmishra.jchess.pieces.*;

import java.util.ArrayList;

public class Board {
    private ArrayList<ArrayList<Square>> squares = new ArrayList<>();
    ShapeRenderer shapeRenderer;
    SpriteBatch batch;
    private static final BitmapFont font = new BitmapFont();
    final private float squareSize;
    PieceColor turn;


    public float getSquareSize() {
        return squareSize;
    }

    public Board(float size) {
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        this.squareSize = size;
        this.turn = PieceColor.WHITE;
        createBoard();
        setPieces();
    }

    public void setSquares(ArrayList<ArrayList<Square>> squares) {
        this.squares = squares;
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
                    (row + col) % 2 == 0 ? Color.WHITE : Color.LIME,
                    row,
                    col
                );
                rowList.add(square);
            }
            squares.add(rowList);
        }
    }

    public ArrayList<Square> getAllowedSquares(Square square) {
        ArrayList<Square> allowedSquares = new ArrayList<>();
        if (square.getPiece() != null) {
            for (int[] allowedMoves : square.getPiece().getAllowedMoves()) {
                if (allowedMoves == null || allowedMoves.length < 2) continue;
                int row = allowedMoves[0];
                int col = allowedMoves[1];
                if (row >= 0 && row < 8 && col >= 0 && col < 8) {
                    allowedSquares.add(this.getSquareAt(row, col));
                }
            }
        }
        return allowedSquares;
    }

    private void setPieces() {
        Utils.setPawns(PieceColor.WHITE, this);
        Utils.setPawns(PieceColor.BLACK, this);

        Utils.setKnights(PieceColor.WHITE, this);
        Utils.setKnights(PieceColor.BLACK, this);

        Utils.setBishops(PieceColor.WHITE, this);
        Utils.setBishops(PieceColor.BLACK, this);

        Utils.setRooks(PieceColor.WHITE, this);
        Utils.setRooks(PieceColor.BLACK, this);

        Utils.setQueens(PieceColor.WHITE, this);
        Utils.setQueens(PieceColor.BLACK, this);

        Utils.setKings(PieceColor.WHITE, this);
        Utils.setKings(PieceColor.BLACK, this);
    }

    public void renderBoard() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (ArrayList<Square> row : squares) {
            for(Square square : row) {
                square.drawSquare();
            }
        }
        squares.forEach(squares -> {squares.forEach(square -> {
            if (square.isSelected()) {
                square.drawAllowedMoves(this.squares);
            }
        });});
        shapeRenderer.end();
    }


    public Square getSquareAt(int row, int col) {
        return squares.get(row).get(col);
    }

    public Piece getPieceAt(int row, int col) {
        Square square = getSquareAt(row, col);
        return square == null ? null : square.getPiece();
    }

    public ArrayList<ArrayList<Square>> getSquares() {
        return squares;
    }

    public Square getSquareAtCoords(int x, int y, Square fallbackSquare) {
        for (ArrayList<Square> row : squares) {
            for (Square square : row) {
                if (square.isInSquare(x, y)) {
                    return square;
                }
            }
        }
        return fallbackSquare;
    }

    public void renderPieces(){
        batch.begin();
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (ArrayList<Square> row : squares) {
            for (Square square : row) {
                square.drawPiece(batch);
                if (Config.DEV) {
                    font.draw(batch, square.getRow() + "," + square.getCol(), square.getX(), square.getY() + 14);
                }
            }
        }
        batch.end();
//        shapeRenderer.end();
    }

    public PieceColor getTurn() {
        return turn;
    }

    public void setTurn(PieceColor turn) {
        this.turn = turn;
    }

    public void boardDispose() {
        font.dispose();
        batch.dispose();
        shapeRenderer.dispose();
    }
}
