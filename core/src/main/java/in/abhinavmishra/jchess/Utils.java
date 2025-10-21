package in.abhinavmishra.jchess;

import in.abhinavmishra.jchess.pieces.Pawn;
import in.abhinavmishra.jchess.pieces.PieceColor;

public class Utils {
    public static void setPawns(PieceColor pieceColor, Board board) {
        for(int i = 0; i < 8; i++) {
            if (pieceColor == PieceColor.WHITE) {
                Piece pawn = new Pawn(PieceColor.WHITE,"Pawn",Assets.PAWN_WHITE, 1, i, (int) (1 * board.getSquareSize()), (int) (i * board.getSquareSize()));
                board.getSquares().get(1).get(i).setPiece(pawn);
            } else {
                Piece pawn = new Pawn(PieceColor.BLACK,"Pawn",Assets.PAWN_BLACK, 6, i, (int) (6 * board.getSquareSize()), (int) (i * board.getSquareSize()));
                board.getSquares().get(6).get(i).setPiece(pawn);
            }
        }
    }
}
