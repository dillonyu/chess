import javax.swing.ImageIcon;
import java.awt.Image;
/**
 *  This class stores the operations of any standard chess piece.
 * 
 * @author Dillon Yu
 */
public abstract class ChessPiece {
  //chessBoard is the chess board the piece is currently on
  private ChessBoard chessBoard;
  //side stores which side the piece belongs to
  private ChessGame.Side side;
  //row stores the row of the chess piece
  private int row;
  //column stores the column of the chess piece
  private int column;
  //hasMoved stores whether or not the chess piece has moved
  private boolean hasMoved;
  
   /**
   * A constructor for that initializes a chess piece with the input chess board and input side. Also, each piece is initially set to have not moved yet.
   * @param chessBoard the board the piece belongs to
   * @param side the side the piece belongs to
   */
  public ChessPiece(ChessBoard chessBoard, ChessGame.Side side) {
    this.chessBoard = chessBoard;
    this.side = side;
    hasMoved = false;
  }
  
  /**
   * Returns the side the chess piece belongs to.
   * @return the side
   */
  public ChessGame.Side getSide() {
      return side;
  }
  
  /**
   * Returns the label of the chess piece.
   * @return the label
   */
  public abstract String getLabel();
  
  /**
   * Returns the icon of the chess piece
   * @return null for a basic display
   */
  public Object getIcon() {
    return null;
  }
  
  /**
   * Lets the chess piece know its current location
   * @param row the current row of the piece
   * @param column the current column of the piece
   */
  public void setLocation(int row, int column) {
    this.row = row;
    this.column = column;
  }
  
  /**
   * Determines if the input row and column is a legal move for the chess piece. Takes whether or not there is a piece at that location in account.
   * @param toRow the row of the desired move
   * @param toColumn the column of the desired move
   * @return true if the desired move is a legal move
   */
  public boolean isLegalMove(int toRow, int toColumn) {
    if(getChessBoard().hasPiece(toRow, toColumn)) {
      if(getChessBoard().getPiece(toRow, toColumn).getSide() != this.getSide()) {
        return isLegalCaptureMove(toRow, toColumn);
      }
    }
    else {
      return isLegalNonCaptureMove(toRow, toColumn);
    }
    return false;
  }
  
  /**
   * Returns the chess board that the piece is on
   * @return the chess board the piece is on
   */
  public ChessBoard getChessBoard() {
    return chessBoard;
  }
  
  /**
   * Returns the current row of the location the chess piece is on
   * @return the current row
   */
  public int getRow() {
    return row;
  }
  
  /**
   * Returns the current column of the location the chess piece is on
   * @return the current column
   */
  public int getColumn() {
    return column;
  }
  
  /**
   * Returns whether or not the input row and column is a legal non-capture move of the chess piece
   * @param row the row of the desired location
   * @param column the column of the desired location
   * @return true if the input move is a legal non-capture move
   */
  public abstract boolean isLegalNonCaptureMove(int row, int column);
  
  /**
   * Returns whether or not the input row and column is a legal capture move of the chess piece
   * @param row the row of the desired location
   * @param column the column of the desired location
   * @return true if the input move is a legal capture move
   */
  public abstract boolean isLegalCaptureMove(int row, int column);
  
  /**
   * Method used for any processing needed after this chess piece moves. No processing required for this program.
   */
  public void moveDone() {
  }
  
  /**
   * Determines if the piece has moved or not.
   * @return whether or not the piece has moved
   */
  public boolean hasMoved() {
    return hasMoved;
  }
  
  /**
   * Sets whether or not the piece has moved or not to the input
   * @param hasMoved whether or not the piece has moved
   */
  public void setHasMoved(boolean hasMoved) {
    this.hasMoved = hasMoved;
  }

  public ImageIcon resizeIcon(ImageIcon icon) {
    Image image = icon.getImage();
    Image newimg = image.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
    return new ImageIcon(newimg);
  }
}