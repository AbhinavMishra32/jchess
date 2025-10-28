package in.abhinavmishra.jchess.pieces;

import com.badlogic.gdx.graphics.Texture;
import in.abhinavmishra.jchess.*;

import java.util.ArrayList;

public class Pawn extends Piece {
    private int[][] allowedMoves;

    public Pawn(PieceColor pieceColor, String name, Texture texture, int row, int col, int x, int y, Board board) {
        super(pieceColor, name, texture, row, col, x, y, board);
    }

    @Override
    protected void setAllowedMoves() {
        // TODO: still pawn attack is buggy...
        ArrayList<int[]> moves = new ArrayList<>();
        moves.add(new int[]{row, col});
        if (getPieceColor() == PieceColor.WHITE) {
            Piece upperRight = board.getPieceAt(Math.min(row + 1, 7), Math.min(col + 1, 7));
            if (upperRight != null && upperRight.getPieceColor() == PieceColor.BLACK) {
                if (Config.DEV) {
                    System.out.println("Adding upper right attack move for white pawn at " + row + "," + col);
                }
                if (isVisibleOnBoard(row + 1, col + 1)) {
                    moves.add(new int[]{row + 1, col + 1});
                }
            }
            Piece upperLeft = board.getPieceAt(Math.min(row + 1, 7), Math.max(col - 1, 0));
            if (upperLeft != null && upperLeft.getPieceColor() == PieceColor.BLACK) {
                if (isVisibleOnBoard(row + 1, col - 1)) {
                    moves.add(new int[]{row + 1, col - 1});
                }
            }

            Piece upper = board.getPieceAt(Math.min(row + 1, 7), col);
            if (upper == null) {
                moves.add(new int[]{row + 1, col});
                if (this.row == 1) {
                    moves.add(new int[]{row + 2, col});
                }
            }

        } else {
            Piece bottomRight = board.getPieceAt(Math.max(row - 1, 0), Math.min(col + 1, 7));
            if (bottomRight != null && bottomRight.getPieceColor() == PieceColor.WHITE) {
                if (isVisibleOnBoard(row - 1, col + 1)) {
                    moves.add(new int[]{row - 1, col + 1});
                }
            }
            Piece bottomLeft = board.getPieceAt(Math.max(row - 1, 0), Math.max(col - 1, 7));
            if (bottomLeft != null &&  bottomLeft.getPieceColor() == PieceColor.WHITE) {
                if (isVisibleOnBoard(row - 1, col - 1)) {
                    moves.add(new int[]{row - 1, col - 1});
                }
            }

            Piece below = board.getPieceAt(Math.min(row - 1, 7), col);
            if (below == null) {
                moves.add(new int[]{row - 1, col});
                if (this.row == 6) {
                    moves.add(new int[]{row - 2, col});
                }
            }
        }
        allowedMoves = moves.toArray(new int[moves.size()][]);
    }

    @Override
    public int[][] getAllowedMoves() {
        // update the moves (if some piece appeared in front of it)
        setAllowedMoves();
        return allowedMoves;
    }

}
