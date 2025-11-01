package in.abhinavmishra.jchess;

import in.abhinavmishra.jchess.pieces.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    public static void setPawns(PieceColor pieceColor, Board board) {
        for(int i = 0; i < 8; i++) {
            if (pieceColor == PieceColor.WHITE) {
                Piece pawn = new Pawn(PieceColor.WHITE,"Pawn",Assets.PAWN_WHITE, 1, i, (int) (1 * board.getSquareSize()), (int) (i * board.getSquareSize()), board);
                board.getSquares().get(1).get(i).setPiece(pawn);
            } else {
                Piece pawn = new Pawn(PieceColor.BLACK,"Pawn",Assets.PAWN_BLACK, 6, i, (int) (6 * board.getSquareSize()), (int) (i * board.getSquareSize()), board);
                board.getSquares().get(6).get(i).setPiece(pawn);
            }
        }
    }

    public static void setKnights(PieceColor pieceColor, Board board) {
        if (pieceColor == PieceColor.WHITE) {
            Piece knight = new Knight(PieceColor.WHITE, "Knight", Assets.KNIGHT_WHITE, 0, 1, (int) (0 * board.getSquareSize()), (int) (1 * board.getSquareSize()), board);
            board.getSquares().get(0).get(1).setPiece(knight);
            Piece knight2 = new Knight(PieceColor.WHITE, "Knight", Assets.KNIGHT_WHITE, 0, 6, (int) (0 * board.getSquareSize()), (int) (6 * board.getSquareSize()), board);
            board.getSquares().get(0).get(6).setPiece(knight2);
        } else {
            Piece knight = new Knight(PieceColor.BLACK, "Knight", Assets.KNIGHT_BLACK, 7, 1, (int) (7 * board.getSquareSize()), (int) (1 * board.getSquareSize()), board);
            board.getSquares().get(7).get(1).setPiece(knight);
            Piece knight2 = new Knight(PieceColor.BLACK, "Knight", Assets.KNIGHT_BLACK, 7, 6, (int) (7 * board.getSquareSize()), (int) (6 * board.getSquareSize()), board);
            board.getSquares().get(7).get(6).setPiece(knight2);

        }
    }

    public static void setBishops(PieceColor pieceColor, Board board) {
        if (pieceColor == PieceColor.WHITE) {
            Piece bishop = new Bishop(PieceColor.WHITE, "Bishop", Assets.BISHOP_WHITE, 0, 2, (int) (0 * board.getSquareSize()), (int) (2 * board.getSquareSize()), board);
            board.getSquares().get(0).get(2).setPiece(bishop);
            Piece bishop2 = new Bishop(PieceColor.WHITE, "Bishop", Assets.BISHOP_WHITE, 0, 5, (int) (0 * board.getSquareSize()), (int) (5 * board.getSquareSize()), board);
            board.getSquares().get(0).get(5).setPiece(bishop2);
        } else {
            Piece bishop = new Bishop(PieceColor.BLACK, "Bishop", Assets.BISHOP_BLACK, 7, 2, (int) (7 * board.getSquareSize()), (int) (2 * board.getSquareSize()), board);
            board.getSquares().get(7).get(2).setPiece(bishop);
            Piece bishop2 = new Bishop(PieceColor.BLACK, "Bishop", Assets.BISHOP_BLACK, 7, 5, (int) (7 * board.getSquareSize()), (int) (5 * board.getSquareSize()), board);
            board.getSquares().get(7).get(5).setPiece(bishop2);

        }
    }

    public static void setRooks(PieceColor pieceColor, Board board) {
        if (pieceColor == PieceColor.WHITE) {
            Piece rook = new Rook(PieceColor.WHITE, "Rook", Assets.ROOK_WHITE, 0,  0, (int) (0 * board.getSquareSize()), (int) (0 * board.getSquareSize()), board);
            board.getSquares().get(0).get(0).setPiece(rook);
            Piece rook1 = new Rook(PieceColor.WHITE, "Rook", Assets.ROOK_WHITE, 0,  7, (int) (0 * board.getSquareSize()), (int) (7 * board.getSquareSize()), board);
            board.getSquares().get(0).get(7).setPiece(rook1);
        } else {
            Piece rook = new Rook(PieceColor.BLACK, "Rook", Assets.ROOK_BLACK, 7,  0, (int) (7 * board.getSquareSize()), (int) (0 * board.getSquareSize()), board);
            board.getSquares().get(7).get(0).setPiece(rook);
            Piece rook1 = new Rook(PieceColor.BLACK, "Rook", Assets.ROOK_BLACK, 7,  7, (int) (7 * board.getSquareSize()), (int) (7 * board.getSquareSize()), board);
            board.getSquares().get(7).get(7).setPiece(rook1);
        }
    }

    public static void setQueens(PieceColor pieceColor, Board board) {
        if (pieceColor == PieceColor.WHITE) {
            Piece queen = new Queen(PieceColor.WHITE, "Queen", Assets.QUEEN_WHITE, 0, 3, (int) (0 * board.getSquareSize()), (int) (3 * board.getSquareSize()), board);
            board.getSquares().get(0).get(3).setPiece(queen);
        } else {
            Piece queen = new Queen(PieceColor.BLACK, "Queen", Assets.QUEEN_BLACK, 7, 3, (int) (7 * board.getSquareSize()), (int) (3 * board.getSquareSize()), board);
            board.getSquares().get(7).get(3).setPiece(queen);
        }
    }
    public static void setKings(PieceColor pieceColor, Board board) {
        if (pieceColor == PieceColor.WHITE) {
            Piece king = new King(PieceColor.WHITE, "King", Assets.KING_WHITE, 0, 4, (int) (0 * board.getSquareSize()), (int) (4 * board.getSquareSize()), board);
            board.getSquares().get(0).get(4).setPiece(king);
        } else {
            Piece king = new King(PieceColor.BLACK, "King", Assets.KING_BLACK, 7, 4, (int) (7 * board.getSquareSize()), (int) (4 * board.getSquareSize()), board);
            board.getSquares().get(7).get(4).setPiece(king);
        }
    }

    public static int[][] mirrorAllowedMoves(int[][] moves) {
        int[][] mirrored = new int[moves.length][2];
        for (int i = 0; i < moves.length; i++) {
            int row = moves[i][0];
            int col = moves[i][1];
            mirrored[i][0] = 7 - row;
            mirrored[i][1] = col;
        }
        return mirrored;
    }

    public static boolean isKingInCheck(Board board, PieceColor color) {
        PieceColor opponent = (color == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
        
        // Find the King's position
        Square kingSquare = null;
        for (ArrayList<Square> row : board.getSquares()) {
            for (Square square : row) {
                if (square.getPiece() != null && 
                    "King".equals(square.getPiece().getName()) && 
                    square.getPiece().getPieceColor() == color) {
                    kingSquare = square;
                    break;
                }
            }
            if (kingSquare != null) break;
        }
        
        if (kingSquare == null) return false;
        
        final int kingRow = kingSquare.getRow();
        final int kingCol = kingSquare.getCol();
        
        // Check if any opponent piece can attack the King's position
        return board.getAllowedSquaresOfAllPieces(opponent).stream()
            .flatMap(List::stream)
            .anyMatch(square -> square.getRow() == kingRow && square.getCol() == kingCol);
    }

    public static ArrayList<Square> getSquaresOfColor(Board board, PieceColor color) {
        List<Square> sList =  board.getSquares().stream()
            .flatMap(List::stream)
            .filter(square -> square.getPiece() != null && square.getPiece().getPieceColor() == color)
            .collect(Collectors.toList());

        return new ArrayList<Square>(sList);
    }

    public static boolean canEscapeCheck(Board board, PieceColor color) {
        for (Square square : getSquaresOfColor(board, color)) {
            ArrayList<Square> allowed = board.getAllowedSquares(square);
            if (allowed == null) continue;

            for (Square allowedSquare : allowed) {
                if (allowedSquare.getRow() == square.getRow() && allowedSquare.getCol() == square.getCol()) {
                    continue;
                }

                Board cloned = board.clone();
                Square fromClone = cloned.getSquareAt(square.getRow(), square.getCol());
                Square toClone = cloned.getSquareAt(allowedSquare.getRow(), allowedSquare.getCol());

                Piece movingPiece = fromClone.getPiece();
                toClone.setPiece(movingPiece, true);
                fromClone.setPiece(null);

                if (!isKingInCheck(cloned, color)) {
                    if (Config.DEV) {
                        System.out.println("Check cleared by: " + movingPiece.getName() + " moving from ("
                                + square.getRow() + ", " + square.getCol() + ") to ("
                                + allowedSquare.getRow() + ", " + allowedSquare.getCol() + ")");
                    }
                    return true;
                }
            }
        }
        return false;
    }

}
