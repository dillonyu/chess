import javax.swing.ImageIcon;

/**
 *  This class creates the queen piece. Queens can move diagonally, forwards, backwards, left and right.
 * 
 * @author Dillon Yu
 */
public class QueenPiece extends ChessPiece implements DiagonalPiece, StraightPiece {
   /**
   * A constructor for the queen that inherits the values from the ChessPiece class.
   * @param chessBoard the board of the queen piece
   * @param side the side of the queen piece
   */
  public QueenPiece(ChessBoard chessBoard, ChessGame.Side side) {
    super(chessBoard, side);
  }
  
  /**
   * Returns a label for the queen.
   * @return a label for the queen
   */
  @Override
  public String getLabel() {
    return "Q";
  }
  
  /**
   * Returns whether or not the input row and column is a legal non-capture move for the queen.
   * @param row the row of the desired location
   * @param column the column of the desired location
   * @return true if the input move is a legal non-capture move for the queen
   */
  @Override
  public boolean isLegalNonCaptureMove(int row, int column) {
    return isLegalMoveDiagonalNC(row, column, getChessBoard(), getRow(), getColumn()) || isLegalMoveStraightNC(row, column, getChessBoard(), getRow(), getColumn());
  }
  
  /**
   * Returns whether or not the input row and column is a legal capture move for the queen.
   * @param row the row of the desired location
   * @param column the column of the desired location
   * @return true if the input move is a legal capture move for the queen
   */
  @Override
  public boolean isLegalCaptureMove(int row, int column) {
    return isLegalMoveDiagonalC(row, column, getChessBoard(), getRow(), getColumn()) || isLegalMoveStraightC(row, column, getChessBoard(), getRow(), getColumn());
  }

  @Override
  public Object getIcon() {
    if (getSide() == ChessGame.Side.SOUTH) {
      return resizeIcon(new ImageIcon("images/wqueen.png"));
    } else {
      return resizeIcon(new ImageIcon("images/bqueen.png"));
    }
  }
}