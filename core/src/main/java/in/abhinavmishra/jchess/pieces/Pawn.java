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
        if (board.getSquareAt(Math.min(row + 1, 7), row).getPiece() != null) {
            if (isVisibleOnBoard(row + 1, col)) {
                moves.add(new int[]{row + 1, col - 1});
            }

            if (isVisibleOnBoard(row - 1, col)) {
                moves.add(new int[]{row + 1, col + 1});
            }
        } else {
            moves.add(new int[]{++row, col});
            moves.add(new int[]{++row, col});
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
