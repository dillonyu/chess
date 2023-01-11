import javax.swing.ImageIcon;

/**
 *  This class creates the bishop piece. Bishops can move diagonally in any direction.
 * 
 * @author Dillon Yu
 */
public class BishopPiece extends ChessPiece implements DiagonalPiece {
  /**
   * A constructor for the bishop that inherits the values from the ChessPiece class.
   * @param chessBoard the board of the bishop piece
   * @param side the side of the bishop piece
   */
  public BishopPiece(ChessBoard chessBoard, ChessGame.Side side) {
    super(chessBoard, side);
  }
  
  /**
   * Returns a label for the bishop.
   * @return a label for the bishop
   */
  @Override
  public String getLabel() {
    return "B";
  }
  
  /**
   * Returns whether or not the input row and column is a legal non-capture move for the bishop.
   * @param row the row of the desired location
   * @param column the column of the desired location
   * @return true if the input move is a legal non-capture move for the bishop
   */
  @Override
  public boolean isLegalNonCaptureMove(int row, int column) {
    return isLegalMoveDiagonalNC(row, column, getChessBoard(), getRow(), getColumn());
  }
  
  /**
   * Returns whether or not the input row and column is a legal capture move for the bishop.
   * @param row the row of the desired location
   * @param column the column of the desired location
   * @return true if the input move is a legal capture move for the bishop
   */
  @Override
  public boolean isLegalCaptureMove(int row, int column) {
    return isLegalMoveDiagonalC(row, column, getChessBoard(), getRow(), getColumn());
  }

  @Override
  public Object getIcon() {
    if (getSide() == ChessGame.Side.SOUTH) {
      return resizeIcon(new ImageIcon("images/wbishop.png"));
    } else {
      return resizeIcon(new ImageIcon("images/bbishop.png"));
    }
  }
}