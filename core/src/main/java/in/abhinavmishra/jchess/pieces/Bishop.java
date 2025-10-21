package in.abhinavmishra.jchess.pieces;

import com.badlogic.gdx.graphics.Texture;
import in.abhinavmishra.jchess.Piece;

import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(PieceColor pieceColor, String name, Texture texture, int row, int col, int x, int y) {
        super(pieceColor, name, texture, row, col, x, y);
    }

    @Override
    protected void setAllowedMoves() {
        ArrayList<int[]> moves = new ArrayList<>();
        int colToRight = this.col;
        int rowToRight = this.row;
        int colToLeft = this.col;
        int rowToLeft = this.row;

        while(isVisibleOnBoard(rowToRight, colToRight)) {
            moves.add(new int[]{rowToRight, colToRight});
            rowToRight++;
            colToRight++;
        }

        rowToRight = this.row;
        colToRight = this.col;

        while(isVisibleOnBoard(rowToRight, colToRight)) {
            rowToRight--;
            colToRight++;
            moves.add(new int[]{rowToRight, colToRight});
        }

        while(isVisibleOnBoard(rowToLeft, colToLeft)) {
            rowToLeft++;
            colToLeft--;
            moves.add(new int[]{rowToLeft, colToLeft});
        }

        rowToLeft = this.row;
        colToLeft = this.col;

        while(isVisibleOnBoard(rowToLeft, colToLeft)) {
            rowToLeft--;
            colToLeft--;
            moves.add(new int[]{rowToLeft, colToLeft});
        }

        allowedMoves = moves.toArray(new int[moves.size()][]);
    }

    @Override
    public int[][] getAllowedMoves() {
        return allowedMoves;
    }
}
