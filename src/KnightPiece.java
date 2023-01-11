import java.util.ArrayList;
import javax.swing.ImageIcon;
/**
 *  This class creates the knight piece. Knights move in L-shapes.
 * 
 * @author Dillon Yu
 */
public class KnightPiece extends ChessPiece implements LPiece {
  /**
   * A constructor for the knight that inherits the values from the ChessPiece class.
   * @param chessBoard the board of the knight piece
   * @param side the side of the knight piece
   */
  public KnightPiece(ChessBoard chessBoard, ChessGame.Side side) {
    super(chessBoard, side);
  }
  
  /**
   * Returns a label for the knight.
   * @return a label for the knight
   */
  @Override
  public String getLabel() {
    return "N";
  }
    
  /**
   * Returns whether or not the input row and column is a legal non-capture move for the knight.
   * @param row the row of the desired location
   * @param column the column of the desired location
   * @return true if the input move is a legal non-capture move for the knight
   */
  @Override
  public boolean isLegalNonCaptureMove(int row, int column) {
    return isLegalMoveL(row, column, getChessBoard(), getRow(), getColumn());
  }
  
  /**
   * Returns whether or not the input row and column is a legal capture move for the knight.
   * @param row the row of the desired location
   * @param column the column of the desired location
   * @return true if the input move is a legal capture move for the knight
   */
  @Override
  public boolean isLegalCaptureMove(int row, int column) {
    return isLegalMoveL(row, column, getChessBoard(), getRow(), getColumn());
  }

  @Override
  public Object getIcon() {
    if (getSide() == ChessGame.Side.SOUTH) {
      return resizeIcon(new ImageIcon("images/wnight.png"));
    } else {
      return resizeIcon(new ImageIcon("images/bnight.png"));
    }
  }
}