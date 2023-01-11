import javax.swing.ImageIcon;

/**
 *  This class creates the pawn piece. Pawns can only move forward one, except for the first move where it has the option to move two spaces. Pawns capture diagonally, and have a promotion option when reaching the opposite side.
 * 
 * @author Dillon Yu
 */
public class PawnPiece extends ChessPiece implements AnyDirectionOnePiece, SpecialMovePiece {
  /**
   * A constructor for the pawn that inherits the values from the ChessPiece class.
   * @param chessBoard the board of the pawn piece
   * @param side the side of the pawn piece
   */
  public PawnPiece(ChessBoard chessBoard, ChessGame.Side side) {
    super(chessBoard, side);
  }
  
  /**
   * Returns a label for the pawn.
   * @return a label for the pawn
   */
  @Override
  public String getLabel() {
    return "P";
  }
  
  /**
   * Returns whether or not the input row and column is a legal non-capture move for the pawn.
   * @param row the row of the desired location
   * @param column the column of the desired location
   * @return true if the input move is a legal non-capture move for the pawn
   */
  @Override
  public boolean isLegalNonCaptureMove(int row, int column) {
    if(!hasMoved()) {
      return isLegalMoveTwoForward(row, column, getChessBoard(), getRow(), getColumn()) || isLegalMoveOneForwardNC(row, column, getChessBoard(), getRow(), getColumn());
    }
    return isLegalMoveOneForwardNC(row, column, getChessBoard(), getRow(), getColumn());
  }
  
  /**
   * Returns whether or not the input row and column is a legal capture move for the pawn.
   * @param row the row of the desired location
   * @param column the column of the desired location
   * @return true if the input move is a legal capture move for the pawn
   */
  @Override
  public boolean isLegalCaptureMove(int row, int column) {
    return isLegalMoveOneDiagonalC(row, column, getChessBoard(), getRow(), getColumn());
  }

  @Override
  public Object getIcon() {
    if (getSide() == ChessGame.Side.SOUTH) {
      return resizeIcon(new ImageIcon("images/wpawn.png"));
    } else {
      return resizeIcon(new ImageIcon("images/bpawn.png"));
    }
  }
}