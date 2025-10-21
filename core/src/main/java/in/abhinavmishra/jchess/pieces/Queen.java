package in.abhinavmishra.jchess.pieces;

import com.badlogic.gdx.graphics.Texture;
import in.abhinavmishra.jchess.Piece;

import java.util.ArrayList;

public class Queen extends Piece {

    public Queen(PieceColor pieceColor, String name, Texture texture, int row, int col, int x, int y) {
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

        int r = this.row;
        int c = this.col;
        while (isVisibleOnBoard(r, c)) {
            r++;
            moves.add(new int[]{r, c});
        }
        r = this.row;
        while(isVisibleOnBoard(r, c)) {
            r--;
            moves.add(new int[]{r, c});
        }
        r = this.row;
        while(isVisibleOnBoard(r,c)) {
            c++;
            moves.add(new int[]{r, c});
        }
        c = this.col;
        while(isVisibleOnBoard(r, c)) {
            c--;
            moves.add(new int[]{r, c});
        }
        c = this.col;

        allowedMoves = moves.toArray(new int[moves.size()][]);
    }

    @Override
    protected int[][] getAllowedMoves() {
        return allowedMoves;
    }
}
