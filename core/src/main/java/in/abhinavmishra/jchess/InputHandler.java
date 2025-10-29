package in.abhinavmishra.jchess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import in.abhinavmishra.jchess.pieces.PieceColor;
import in.abhinavmishra.jchess.screens.EndScreen;
import in.abhinavmishra.jchess.screens.GameScreen;

import java.util.ArrayList;
import java.util.List;

public class InputHandler extends InputAdapter {
    Board board;
    Square selectedSquare;
    Piece selectedPiece;
    Integer deltaX;
    Integer deltaY;
    ChessGame game;

    public InputHandler(Board board, ChessGame game) {
        this.board = board;
        this.game = game;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        int correctedY = Gdx.graphics.getHeight() - screenY;

        board.getSquares().forEach(squares -> {squares.forEach(square -> {
            if (square.isInSquare(screenX, correctedY) && square.getPiece() != null && square.getPiece().getPieceColor() == board.getTurn()) {
                selectedSquare = square;
                selectedPiece = selectedSquare.getPiece();
                selectedPiece.setSelected(true);
                selectedSquare.setSelected(true);
                selectedPiece.setSize((int) selectedSquare.getSize());
                deltaX = selectedSquare.getPiece().getX() - screenX;
                deltaY = selectedSquare.getPiece().getY() - correctedY;
            }
        });});
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        int correctedY = Gdx.graphics.getHeight() - screenY;
        if (selectedPiece != null && selectedPiece.isSelected && selectedPiece.isInPiece(screenX, correctedY)) {
            selectedPiece.setX(screenX + deltaX);
            selectedPiece.setY(correctedY + deltaY);
        }
        return super.touchDragged(screenX, screenY, pointer);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (selectedSquare != null && selectedPiece != null) {
            selectedSquare.setSelected(false);
            selectedPiece.setSelected(false);

            Square newSquare = board.getSquareAtCoords(
                selectedPiece.getCenterCoords()[0],
                selectedPiece.getCenterCoords()[1],
                selectedSquare
            );

            ArrayList<Square> allowedSquares = board.getAllowedSquares(selectedSquare);

            boolean isAllowed = false;

            if (allowedSquares != null) {
                for (Square square : allowedSquares) {
                    if (square.getRow() == newSquare.getRow() && square.getCol() == newSquare.getCol()) {
                        isAllowed = true;
                        break;
                    }
                }
            }

            if (isAllowed) {
                selectedSquare.setPiece(null);
                newSquare.setPiece(selectedPiece);

                if (board.getAllowedSquaresOfAllPieces(board.getTurn()).stream()
                    .flatMap(List::stream)
                    .anyMatch(square -> square.getPiece() != null && "King".equals(square.getPiece().getName()))){
                        System.out.println("checkmate by placing " + newSquare.getPiece().getName() + " at " + newSquare.getRow() + ", " + newSquare.getCol());
                };
//                if (newSquare.getPiece() != null && newSquare.getPiece().getName() == "King") {
//                    game.setScreen(new EndScreen(game, newSquare.getPiece().getPieceColor()));
//                }

                if (newSquare != selectedSquare) {
                    PieceColor nextTurn;
                    if (board.getTurn() == PieceColor.WHITE) {
                        nextTurn = PieceColor.BLACK;
                    } else {
                        nextTurn = PieceColor.WHITE;
                    }
                    board.setTurn(nextTurn);
                    newSquare.getPiece().setAllowedMoves();
                }

            } else {
                selectedSquare.setPiece(selectedPiece);
            }

            selectedPiece = null;
            selectedSquare = null;
        }
        return super.touchUp(screenX, screenY, pointer, button);
    }
}
