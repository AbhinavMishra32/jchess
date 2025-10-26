package in.abhinavmishra.jchess.pieces;

import com.badlogic.gdx.graphics.Texture;
import in.abhinavmishra.jchess.Board;
import in.abhinavmishra.jchess.Piece;
import in.abhinavmishra.jchess.Square;

import java.util.ArrayList;

public class Knight extends Piece {

    public Knight(PieceColor pieceColor, String name, Texture texture, int row, int col, int x, int y, Board board) {
        super(pieceColor, name, texture, row, col, x, y, board);
    }

    @Override
    protected void setAllowedMoves() {
        ArrayList<int[]> moves = new ArrayList<>();
        moves.add(new int[]{row, col});

        if (isVisibleOnBoard(row + 2, col + 1)){
            moves.add(new int[]{row + 2, col + 1});
        }
        if (isVisibleOnBoard(row + 1, col + 2)){
            moves.add(new int[]{row + 1, col + 2});
        }
        if (isVisibleOnBoard(row - 1, col - 2)){
            moves.add(new int[]{row - 1, col - 2});
        }
        if (isVisibleOnBoard(row - 2, col - 1)){
            moves.add(new int[]{row - 2, col - 1});
        }
        if (isVisibleOnBoard(row + 2, col - 1)){
            moves.add(new int[]{row + 2, col - 1});
        }
        if (isVisibleOnBoard(row + 1, col - 2)){
            moves.add(new int[]{row + 1, col - 2});
        }
        if (isVisibleOnBoard(row - 1, col + 2)){
            moves.add(new int[]{row - 1, col + 2});
        }
        if (isVisibleOnBoard(row - 2, col + 1)){
            moves.add(new int[]{row - 2, col + 1});
        }

        allowedMoves = moves.toArray(new int[moves.size()][]);

    }

    @Override
    public int[][] getAllowedMoves() {
        return allowedMoves;
    }

}
