package in.abhinavmishra.jchess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import in.abhinavmishra.jchess.pieces.Pawn;
import in.abhinavmishra.jchess.pieces.PieceColor;
import in.abhinavmishra.jchess.screens.EndScreen;
import in.abhinavmishra.jchess.screens.GameScreen;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        board.getSquares().forEach(squares -> {
            squares.forEach(square -> {
                if (square.isInSquare(screenX, correctedY) && square.getPiece() != null
                        && square.getPiece().getPieceColor() == board.getTurn()) {
                    selectedSquare = square;
                    selectedPiece = selectedSquare.getPiece();
                    selectedPiece.setSelected(true);
                    selectedSquare.setSelected(true);
                    selectedPiece.setSize((int) selectedSquare.getSize());
                    deltaX = selectedSquare.getPiece().getX() - screenX;
                    deltaY = selectedSquare.getPiece().getY() - correctedY;
                }
            });
        });
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
                    selectedSquare);

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

            PieceColor nextTurn;
            if (board.getTurn() == PieceColor.WHITE) {
                nextTurn = PieceColor.BLACK;
            } else {
                nextTurn = PieceColor.WHITE;
            }

            if (isAllowed) {
                PieceColor movingPlayerColor = board.getTurn();

                selectedSquare.setPiece(null);
                newSquare.setPiece(selectedPiece);

                // Recalculate all pieces' allowed moves after the move
                for (ArrayList<Square> row : board.getSquares()) {
                    for (Square sq : row) {
                        if (sq.getPiece() != null) {
                            sq.getPiece().setAllowedMoves();
                        }
                    }
                }

                if (newSquare != selectedSquare) {
                    board.setTurn(nextTurn);
                }

                // Check if the player who just moved exposed their own King
                if (Utils.isKingInCheck(board, movingPlayerColor)) {
                    System.out.println(movingPlayerColor + " is in check after their move!");

                    if (!Utils.canEscapeCheck(board, movingPlayerColor)) {
                        System.out.println("CHECKMATE - " + movingPlayerColor + " loses!");
                        game.setScreen(new EndScreen(game, movingPlayerColor));
                    }
                }

                // Check if the opponent is in check or checkmate
                if (Utils.isKingInCheck(board, nextTurn)) {
                    System.out.println("check by placing " + selectedPiece.getName() + " at " + newSquare.getRow()
                            + ", " + newSquare.getCol());

                    if (!Utils.canEscapeCheck(board, nextTurn)) {
                        System.out.println("CHECKMATE");
                        game.setScreen(new EndScreen(game, nextTurn));
                    }
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
