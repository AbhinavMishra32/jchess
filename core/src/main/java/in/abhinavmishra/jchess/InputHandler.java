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

            PieceColor nextTurn;
                if (board.getTurn() == PieceColor.WHITE) {
                    nextTurn = PieceColor.BLACK;
                } else {
                    nextTurn = PieceColor.WHITE;
                }

                if (isAllowed) {
                    selectedSquare.setPiece(null);
                    newSquare.setPiece(selectedPiece);

                    boolean canGetAway = false;
                    if (Utils.isKingInCheck(board, nextTurn)) {
                        System.out.println("check by placing " + newSquare.getPiece().getName() + " at " + newSquare.getRow() + ", " + newSquare.getCol());                    for (Square square : Utils.getSquaresOfColor(board, nextTurn)) {
                        ArrayList<Square> allowed = board.getAllowedSquares(square);
                        if (allowed == null) continue;

                        for (Square allowedSquare : allowed) {
                            // Skip if it's not actually a move (same position)
                            if (allowedSquare.getRow() == square.getRow() && allowedSquare.getCol() == square.getCol()) {
                                continue;
                            }

                            Board cloned = board.clone();
                            Square fromClone = cloned.getSquareAt(square.getRow(), square.getCol());
                            Square toClone = cloned.getSquareAt(allowedSquare.getRow(), allowedSquare.getCol());

                            Piece movingPiece = fromClone.getPiece();
                            toClone.setPiece(movingPiece, true);
                            fromClone.setPiece(null);

                            if (!Utils.isKingInCheck(cloned, nextTurn)) {
                                if (movingPiece != null) {
                                    System.out.println("Check cleared by: " + movingPiece.getName() + " moving from ("
                                        + square.getRow() + ", " + square.getCol() + ") to ("
                                        + allowedSquare.getRow() + ", " + allowedSquare.getCol() + ")");
                                }
                                canGetAway = true;
                                break;
                            }

                        }
                        if (canGetAway) break;
                    }
                    if (!canGetAway) {
                        System.out.println("CHECKMATE");
                        game.setScreen(new EndScreen(game, nextTurn));

                    }
                }

                if (newSquare != selectedSquare) {
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
