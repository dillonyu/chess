import java.awt.Image;

import javax.swing.ImageIcon;
/**
 *  This class creates the king piece. Kings can move in any direction in one square. They also have the castling ability if there are no spaces between the king and the rook and neither the king nor rook have moved.
 * 
 * @author Dillon Yu
 */
public class KingPiece extends ChessPiece implements AnyDirectionOnePiece, SpecialMovePiece {
  /**
   * A constructor for the king that inherits the values from the ChessPiece class.
   * @param chessBoard the board of the king piece
   * @param side the side of the king piece
   */
  public KingPiece(ChessBoard chessBoard, ChessGame.Side side) {
    super(chessBoard, side);
  }
  
  /**
   * Returns a label for the king.
   * @return a label for the king
   */
  @Override
  public String getLabel() {
    return "K";
  }

  @Override
  public Object getIcon() {
    if (getSide() == ChessGame.Side.SOUTH) {
      return resizeIcon(new ImageIcon("images/wking.png"));
    } else {
      return resizeIcon(new ImageIcon("images/bking.png"));
    }
  }
  /**
   * Returns whether or not the input row and column is a legal non-capture move for the king.
   * @param row the row of the desired location
   * @param column the column of the desired location
   * @return true if the input move is a legal non-capture move for the king
   */
  @Override
  public boolean isLegalNonCaptureMove(int row, int column) {
    if(!hasMoved()) {
        return isLegalMoveCastle(row, column, getChessBoard(), getRow(), getColumn()) || isLegalMoveAnyDirectionOne(row, column, getChessBoard(), getRow(), getColumn());
    }
    else {
      return isLegalMoveAnyDirectionOne(row, column, getChessBoard(), getRow(), getColumn());
    }
  }
  
  /**
   * Returns whether or not the input row and column is a legal capture move for the king.
   * @param row the row of the desired location
   * @param column the column of the desired location
   * @return true if the input move is a legal capture move for the king
   */
  @Override
  public boolean isLegalCaptureMove(int row, int column) {
    return isLegalMoveAnyDirectionOne(row, column, getChessBoard(), getRow(), getColumn());
  }
}