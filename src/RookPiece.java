import javax.swing.ImageIcon;

/**
 *  This class creates the rook piece. Rooks can move forwards, left, right, or backwards.
 * 
 * @author Dillon Yu
 */
public class RookPiece extends ChessPiece implements StraightPiece {
  /**
   * A constructor for the rook that inherits the values from the ChessPiece class.
   * @param chessBoard the board of the rook piece
   * @param side the side of the rook piece
   */
  public RookPiece(ChessBoard chessBoard, ChessGame.Side side) {
    super(chessBoard, side);
  }
  
  /**
   * Returns a label for the rook.
   * @return a label for the rook
   */
  @Override
  public String getLabel() {
    return "R";
  }

  /**
   * Returns whether or not the input row and column is a legal non-capture move for the rook.
   * @param row the row of the desired location
   * @param column the column of the desired location
   * @return true if the input move is a legal non-capture move for the rook
   */
  @Override
  public boolean isLegalNonCaptureMove(int row, int column) {
    return isLegalMoveStraightNC(row, column, getChessBoard(), getRow(), getColumn());
  }
  
  /**
   * Returns whether or not the input row and column is a legal capture move for the rook.
   * @param row the row of the desired location
   * @param column the column of the desired location
   * @return true if the input move is a legal capture move for the rook
   */
  @Override
  public boolean isLegalCaptureMove(int row, int column) {
    return isLegalMoveStraightC(row, column, getChessBoard(), getRow(), getColumn());
  }

  @Override
  public Object getIcon() {
    if (getSide() == ChessGame.Side.SOUTH) {
      return resizeIcon(new ImageIcon("images/wrook.png"));
    } else {
      return resizeIcon(new ImageIcon("images/brook.png"));
    }
  }
}