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


    public float getSquareSize() {
        return squareSize;
    }

    public Board(float size) {
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        this.squareSize = size;
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
                    (row + col) % 2 == 0 ? Color.WHITE : Color.GRAY,
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
//        Piece pawn = new Pawn(PieceColor.WHITE,"Pawn",Assets.PAWN_WHITE, 2, 4, (int) (4 * squareSize), (int) (2 * squareSize));
//        squares.get(2).get(4).setPiece(pawn);

        Piece knight = new Knight(PieceColor.WHITE, "Horse", Assets.HORSE_WHITE, 4, 4, (int) (4 * squareSize), (int) (4 * squareSize));
        squares.get(4).get(4).setPiece(knight);

        Piece bishop = new Bishop(PieceColor.WHITE, "Bishop", Assets.BISHOP_WHITE, 3,3, (int) (3 * squareSize), (int) (4 * squareSize));
        squares.get(3).get(3).setPiece(bishop);

        Piece rook = new Rook(PieceColor.WHITE, "Rook", Assets.ROOK_WHITE, 2,5, (int) (2 * squareSize), (int) (5 * squareSize));
        squares.get(2).get(5).setPiece(rook);

        Piece queen = new Queen(PieceColor.WHITE, "Queen", Assets.QUEEN_WHITE, 5, 6, (int) (5 * squareSize), (int) (6 * squareSize));
        squares.get(5).get(6).setPiece(queen);

        Piece king = new King(PieceColor.WHITE, "King", Assets.KING_WHITE, 2, 2, (int) (2 * squareSize), (int) (2 * squareSize));
        squares.get(2).get(2).setPiece(king);
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

    public void boardDispose() {
        font.dispose();
        batch.dispose();
        shapeRenderer.dispose();
    }
}
