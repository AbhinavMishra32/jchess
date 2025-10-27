package in.abhinavmishra.jchess.pieces;

import com.badlogic.gdx.graphics.Texture;
import in.abhinavmishra.jchess.Board;
import in.abhinavmishra.jchess.Piece;
import in.abhinavmishra.jchess.Square;
import in.abhinavmishra.jchess.Utils;

import java.util.ArrayList;

public class Pawn extends Piece {
    private int[][] allowedMoves;

    public Pawn(PieceColor pieceColor, String name, Texture texture, int row, int col, int x, int y, Board board) {
        super(pieceColor, name, texture, row, col, x, y, board);
    }

    @Override
    protected void setAllowedMoves() {
        ArrayList<int[]> moves = new ArrayList<>();
        moves.add(new int[]{row, col});
        if (getPieceColor() == PieceColor.WHITE) {
            Piece upperRight = board.getPieceAt(Math.min(row + 1, 7), Math.min(col + 1, 7));
            if (upperRight != null && upperRight.getPieceColor() == PieceColor.BLACK) {
                if (isVisibleOnBoard(row + 1, col + 1)) {
                    moves.add(new int[]{row + 1, col + 1});
                }
            }
            Piece upperLeft = board.getPieceAt(Math.min(row + 1, 7), Math.min(col + 1, 7));
            if (upperLeft != null && upperLeft.getPieceColor() == PieceColor.BLACK) {
                if (isVisibleOnBoard(row + 1, col - 1)) {
                    moves.add(new int[]{row + 1, col - 1});
                }
            }

            Piece upperOnce = board.getPieceAt(Math.min(row + 1, 7), Math.min(col + 1, 7));
            if (upperOnce == null) {
                moves.add(new int[]{row + 1, col});
                Piece upperTwice = board.getPieceAt(Math.min(row + 1, 7), Math.min(col + 1, 7));
                if (upperTwice == null) {
                    moves.add(new int[]{row + 2, col});
                }
            }
        } else {
            Piece upperRight = board.getPieceAt(Math.max(row - 1, 0), Math.min(col - 1, 7));
            if (upperRight != null && upperRight.getPieceColor() == PieceColor.BLACK) {
                if (isVisibleOnBoard(row - 1, col + 1)) {
                    moves.add(new int[]{row - 1, col + 1});
                }
            }
            Piece upperLeft = board.getPieceAt(Math.max(row - 1, 0), Math.min(col - 1, 7));
            if (upperLeft != null &&  upperLeft.getPieceColor() == PieceColor.BLACK) {
                if (isVisibleOnBoard(row - 1, col - 1)) {
                    moves.add(new int[]{row - 1, col - 1});
                }
            }

            Piece upperOnce = board.getPieceAt(Math.max(row - 1, 0), Math.min(col - 1, 7));
            if (upperOnce == null) {
                moves.add(new int[]{row - 1, col});
                Piece upperTwice = board.getPieceAt(Math.max(row - 1, 0), Math.min(col - 1, 7));
                if (upperTwice == null) {
                    moves.add(new int[]{row - 2, col});
                }
            }
        }
        allowedMoves = moves.toArray(new int[moves.size()][]);
    }

    @Override
    public int[][] getAllowedMoves() {
        if (getPieceColor() == PieceColor.WHITE) {
            return allowedMoves;
        } else {
            return Utils.mirrorAllowedMoves(allowedMoves);
        }
    }

}
