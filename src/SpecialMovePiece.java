import java.util.ArrayList;
/**
 *  This interface provides the needed functions for a piece that has a special, one-time use move that can only be used as its first move.
 * 
 * @author Dillon Yu
 */
public interface SpecialMovePiece {
  /** Determines if the input location is a legal move from the input current location on the input chess board for castling.
    * @param toRow the desired row of the new location
    * @param toColumn the desired column of the new location
    * @param board the chess board the method is using
    * @param curRow the current row of the location of the piece to be moved
    * @param curCol the current column of location of the piece to be moved
    * @return true if the desired location is a valid move
    */
  public default boolean isLegalMoveCastle(int toRow, int toColumn, ChessBoard board, int curRow, int curCol) {
    //moves stores all possible legal moves
    ArrayList<int[]> moves = legalMovesCastle(board, curRow, curCol);
    //found stores whether or not the desired location was found in the possible legal moves
    boolean found = false;
    /* A loop that searches through the possible legal moves for the input move */
    for(int i = 0; i < moves.size(); i++) {
      if(moves.get(i)[0] == toRow && moves.get(i)[1] == toColumn)
        found = true;
    }
    return found;
  }
  
  /** Creates a list of all the possible locations for a piece at the input current row and column on the input chess board to castle.
    * @param board the chess board to be used for checking
    * @param curRow the current row of the piece to be moved
    * @param curCol the current column of the piece to be moved
    * @return a list of all the possible moves
    */
  public default ArrayList<int[]> legalMovesCastle(ChessBoard board, int curRow, int curCol) {
    //ans stores the possible castle moves
    ArrayList<int[]> ans = new ArrayList<int[]>();
    /* check the left side for pieces in between the king and rook */
    int leftC = curCol - 1;
    //empty stores whether or not the spaces between the king and rook are empty
    boolean empty = true;
    /* A loop that determines if there are pieces in between the king and rook or not */
    while(leftC > 0) {
      if(board.getPiece(curRow, leftC) != null)
        empty = false;
      leftC--;
    }
    /* If there are no pieces in between and the rook has not moved yet, add the move as a legal castle move. */
    if(empty && board.hasPiece(curRow, 0) && !board.getPiece(curRow, 0).hasMoved()) {
        ans.add(new int[]{curRow, curCol/2});
    }
    
    empty = true;
    /* check the right side for pieces in between the king and rook */
    int rightC = curCol + 1;
    /* A loop that determines if there are pieces in between the king and rook or not */
    while(rightC < board.numColumns() - 1) {
      if(board.getPiece(curRow, rightC) != null)
        empty = false;
      rightC++;
    }
    /* If there are no pieces in between and the rook has not moved yet, add the move as a legal castle move. */
    if(empty && board.hasPiece(curRow, board.numColumns() - 1) && !board.getPiece(curRow, board.numColumns() - 1).hasMoved()) {
        ans.add(new int[]{curRow, (curCol + board.numColumns())/2});
    }
    return ans;
  }
  
  /** Determines if the input location is a legal move from the input current location on the input chess board for two moves forward.
    * @param toRow the desired row of the new location
    * @param toColumn the desired column of the new location
    * @param board the chess board the method is using
    * @param curRow the current row of the location of the piece to be moved
    * @param curCol the current column of location of the piece to be moved
    * @return true if the desired location is a valid move
    */
  public default boolean isLegalMoveTwoForward(int toRow, int toColumn, ChessBoard board, int curRow, int curCol) {
    //move stores the valid move to be checked against the desired move
    int[][] move = legalMovesTwoForward(board, curRow, curCol);
    return move[0][0] == toRow && move[0][1] == toColumn;
  }
  
  /** Creates a valid location for the legal move of the special move of moving two forward from the input current row and column.
    * @param board the chess board to be used for checking
    * @param curRow the current row of the piece to be moved
    * @param curCol the current column of the piece to be moved
    * @return a location of the possible move
    */
  public default int[][] legalMovesTwoForward(ChessBoard board, int curRow, int curCol) {
    //ans stores the location for the legal move
    int[][] ans = new int[1][2];
    //moving downwards if the piece is on the north side
    if(board.getPiece(curRow, curCol).getSide() == ChessGame.Side.NORTH)
      ans[0][0] = curRow + 2;
    //moving upwards if the piece is on the south side
    else if(board.getPiece(curRow, curCol).getSide() == ChessGame.Side.SOUTH)
      ans[0][0] = curRow - 2;
    ans[0][1] = curCol;
    return ans;
  }
 }