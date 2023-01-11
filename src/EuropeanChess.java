/**
 *  This interface creates the rules for European Chess
 * 
 * @author Dillon Yu
 */
public class EuropeanChess implements ChessGame {
  //turn is the current turn of the chess game
  private ChessGame.Side turn;
  
  /** Determines if the input chess piece at the input row and column is currently a legal piece to play.
    *@param piece the piece to be checked
    *@param row the current row of the piece
    *@param column the current column of the piece
    *@return true if this piece is able to be played
    */
  @Override
  public boolean legalPieceToPlay(ChessPiece piece, int row, int column) {
    return getTurn() == piece.getSide();
  }
  
  /** Determines if the input move for the input chess piece can be made, and if so, makes it. Also adjusts for special moves such as castling or promoting.
    * @param piece the chess piece to be moved
    * @param toRow the row of the desired location
    * @param toColumn the column of the desired location
    * @return true if the piece was able to move successfully.
    */
  @Override
  public boolean makeMove(ChessPiece piece, int toRow, int toColumn) {
    if(piece.isLegalMove(toRow, toColumn)) {
      if(!piece.hasMoved()) {
        if(piece.getLabel().equals("K")) {
          //does a left castle if applicable
          if(piece.getColumn()/2 == toColumn && piece.getChessBoard().hasPiece(toRow, 0)) {
            //rook is the rook to be moved in the castle
            ChessPiece rook = piece.getChessBoard().getPiece(toRow, 0);
            piece.getChessBoard().removePiece(toRow, 0);
            piece.getChessBoard().addPiece(rook, toRow, (toColumn + piece.getColumn())/2);
            rook.setHasMoved(true);
          }
          //does a right castle if applicable
          if((piece.getColumn() + piece.getChessBoard().numColumns())/2 == toColumn && piece.getChessBoard().hasPiece(toRow, piece.getChessBoard().numColumns() - 1)) {
            //rook is the rook to be moved in the castle
            ChessPiece rook = piece.getChessBoard().getPiece(toRow, piece.getChessBoard().numColumns() - 1);
            piece.getChessBoard().removePiece(toRow, piece.getChessBoard().numColumns() - 1);
            piece.getChessBoard().addPiece(rook, toRow, (toColumn + piece.getColumn())/2);
            rook.setHasMoved(true);
          }
        }
      }
      if(piece.getLabel().equals("P")) {
        //promoting for the side of south
        if(toRow == 0) {
          //input represents the user input for the promotion
          String input = javax.swing.JOptionPane.showInputDialog("Promote to? (Queen, Bishop, Knight, Rook)");
          /* Keep displaying the box until the user enters a valid piece. */
          while(!(input.equals("Queen") || input.equals("Bishop") || input.equals("Knight") || input.equals("Rook"))) {
              input =  javax.swing.JOptionPane.showInputDialog("Not a piece. Promote to? (Queen, Bishop, Knight, Rook)");
          }
          /* Promote the piece to the input piece */
          piece.getChessBoard().removePiece(piece.getRow(), piece.getColumn());
          if(input.equals("Queen")) {
            piece.getChessBoard().addPiece(new QueenPiece(piece.getChessBoard(), ChessGame.Side.SOUTH), toRow, toColumn);
          }
          if(input.equals("Bishop")) {
            piece.getChessBoard().addPiece(new BishopPiece(piece.getChessBoard(), ChessGame.Side.SOUTH), toRow, toColumn);
          }
          if(input.equals("Knight")) {
            piece.getChessBoard().addPiece(new KnightPiece(piece.getChessBoard(), ChessGame.Side.SOUTH), toRow, toColumn);
          }
          if(input.equals("Rook")) {
            piece.getChessBoard().addPiece(new RookPiece(piece.getChessBoard(), ChessGame.Side.SOUTH), toRow, toColumn);
          }
          switchTurn();
          return true;
        }
        //promoting for the side of north
        if(toRow == piece.getChessBoard().numRows() - 1) {
          //input represents the user input for the promotion
          String input = javax.swing.JOptionPane.showInputDialog("Promote to? (Queen, Bishop, Knight, Rook)");
          /* Keep displaying the box until the user enters a valid piece. */
          while(!(input.equals("Queen") || input.equals("Bishop") || input.equals("Knight") || input.equals("Rook"))) {
            input =  javax.swing.JOptionPane.showInputDialog("Not a piece. Promote to? (Queen, Bishop, Knight, Rook)");
          }
          /* Promote the piece to the input piece */
          piece.getChessBoard().removePiece(piece.getRow(), piece.getColumn());
          if(input.equals("Queen")) {
            piece.getChessBoard().addPiece(new QueenPiece(piece.getChessBoard(), ChessGame.Side.NORTH), toRow, toColumn);
          }
          if(input.equals("Bishop")) {
            piece.getChessBoard().addPiece(new BishopPiece(piece.getChessBoard(), ChessGame.Side.NORTH), toRow, toColumn);
          }
          if(input.equals("Knight")) {
            piece.getChessBoard().addPiece(new KnightPiece(piece.getChessBoard(), ChessGame.Side.NORTH), toRow, toColumn);
          }
          if(input.equals("Rook")) {
            piece.getChessBoard().addPiece(new RookPiece(piece.getChessBoard(), ChessGame.Side.NORTH), toRow, toColumn);
          }
          switchTurn();
          return true;
        }
      }
      /* Perform the move if it is legal */
      piece.getChessBoard().removePiece(piece.getRow(), piece.getColumn());
      piece.getChessBoard().addPiece(piece, toRow, toColumn);
      piece.setLocation(toRow, toColumn);
      piece.setHasMoved(true);
      switchTurn();
      return true;
    }
    return false;
  }
  
  /** Return the current turn of the chess game.
    * @return the current turn
    */
  public ChessGame.Side getTurn() {
    return turn;
  }
  
  /** Set the current turn of the chess game to the input.
    * @param turn the turn to be set
    */
  public void setTurn(ChessGame.Side turn) {
    this.turn = turn;
  }
  
  /** Swap turns with the opposite side.
    */
  public void switchTurn() {
    if(getTurn() == ChessGame.Side.NORTH)
      setTurn(ChessGame.Side.SOUTH);
    else
      setTurn(ChessGame.Side.NORTH);
  }
}