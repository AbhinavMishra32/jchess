package in.abhinavmishra.jchess;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Square {
    private Piece piece;
    private float size;
    private boolean hovered = false;
    private ShapeRenderer shapeRenderer;
    private Color color;
    private float x;
    private float y;
    private int row;
    private int col;

    public Square(float size, ShapeRenderer shapeRenderer, float x, float y, Color color, int row, int col) {
        this.size = size;
        this.shapeRenderer = shapeRenderer;
        this.x = x;
        this.y = y;
        this.row = row;
        this.col = col;
        this.color = color;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void drawSquare() {
        shapeRenderer.setColor(color);
        shapeRenderer.rect(x, y, size, size);
        // use this end after rendering every shape... in the board renderer
//        shapeRenderer.end();
    }


    public void drawPiece(SpriteBatch batch) {
//        shapeRenderer.setColor(Color.GREEN);
//        shapeRenderer.rect(x, y, size/2, size/2);
        if (piece != null && piece.texture != null) {
            batch.draw(piece.texture, x, y, size, size);
        }
    }

    public void drawAllowedMoves(ArrayList<ArrayList<Square>> squares) {
        if (piece == null) return;
        int[][] allowedMoves = piece.getAllowedMoves();
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for(ArrayList<Square> row : squares) {
            for(Square square : row) {
                boolean isAllowed = Arrays.stream(allowedMoves)
                    .anyMatch(move -> move[0] == square.getRow() && move[1] == square.getCol());

                if (isAllowed) {
                    shapeRenderer.setColor(Color.GREEN);
                    shapeRenderer.rect(square.getX(), square.getY(), square.getSize(), square.getSize());
                }
            }
        }
    }

    public int getRow() {
        return row;
    }

    public int getCol(){
        return col;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getSize() {
        return size;
    }


}
