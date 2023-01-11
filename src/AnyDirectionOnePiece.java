import java.util.ArrayList;
/**
 *  This interface provides the needed functions for a piece that can only move one space but in any direction.
 * 
 * @author Dillon Yu
 */
public interface AnyDirectionOnePiece {
  /** Determines if the input location is a legal move from the input current location on the input chess board for one space in any direction.
    * @param toRow the desired row of the new location
    * @param toColumn the desired column of the new location
    * @param board the chess board the method is using
    * @param curRow the current row of the location of the piece to be moved
    * @param curCol the current column of location of the piece to be moved
    * @return true if the desired location is a valid move
    */
  public default boolean isLegalMoveAnyDirectionOne(int toRow, int toColumn, ChessBoard board, int curRow, int curCol) {
    //moves stores all the possible legal moves for the input current row and column of the piece on the input chess board
    ArrayList<int[]> moves = legalMovesAnyDirectionOne(board, curRow, curCol);
    //found stores whether or not the desired location is one of the possible legal moves in array list "moves"
    boolean found = false;
    /* A loop that goes through moves to check if the desired location is a legal move */
    for(int i = 0; i < moves.size(); i++) {
      if(moves.get(i)[0] == toRow && moves.get(i)[1] == toColumn)
        found = true;
    }
    return found;
  }
  
  /** Creates a list of all the possible locations for a piece at the input current row and column on the input chess board to move in any direction one square.
    * @param board the chess board to be used for checking
    * @param curRow the current row of the piece to be moved
    * @param curCol the current column of the piece to be moved
    * @return a list of all the possible moves
    */
  public default ArrayList<int[]> legalMovesAnyDirectionOne(ChessBoard board, int curRow, int curCol) {
    //ans is the list that stores all the legal moves. Moves are added if they are valid.
    ArrayList<int[]> ans = new ArrayList<int[]>();
    /* This section checks every square around the piece and adds the valid ones. */
    //forward
    if(isOnBoard(board, curRow - 1, curCol))
      ans.add(new int[]{curRow - 1, curCol});
    //top left
    if(isOnBoard(board, curRow - 1, curCol - 1))
      ans.add(new int[]{curRow - 1, curCol - 1});
    //left
    if(isOnBoard(board, curRow, curCol - 1))
      ans.add(new int[]{curRow, curCol - 1});
    //bottom left
    if(isOnBoard(board, curRow + 1, curCol - 1))
      ans.add(new int[]{curRow + 1, curCol - 1});
    //backward
    if(isOnBoard(board, curRow + 1, curCol))
      ans.add(new int[]{curRow + 1, curCol});
    //bottom right
    if(isOnBoard(board, curRow + 1, curCol + 1))
      ans.add(new int[]{curRow + 1, curCol + 1});
    //right
    if(isOnBoard(board, curRow, curCol + 1))
      ans.add(new int[]{curRow, curCol + 1});
    //top right
    if(isOnBoard(board, curRow - 1, curCol + 1))
      ans.add(new int[]{curRow - 1, curCol + 1});
    return ans;
  }
  
  /** Determines whether the input row and column are on the input chess board or not.
    * @param board the chess board to be used for checking
    * @param row the row of the square to be checked
    * @param col the column of the square to be checked
    * @return true if the input row and column is on the input chess board
    */
  public default boolean isOnBoard(ChessBoard board, int row, int col) {
    return ((row >= 0 && row < board.numRows()) && (col >= 0 && col < board.numColumns()));
  }
  
  /** Determines whether the input row and column is a valid move for moving the piece on the input current row and column forward one on the input chess board, assuming there is no piece on the terminating square.
    * @param toRow the row of the desired location
    * @param toColumn the column of the desired location
    * @param board the chess board to be used
    * @param curRow the current row of the piece to be moved
    * @param curCol the current column of the piece to be moved
    * @return true if the input desired location is a valid move
    */
  public default boolean isLegalMoveOneForwardNC(int toRow, int toColumn, ChessBoard board, int curRow, int curCol) {
    int[][] move = legalMovesOneForwardNC(board, curRow, curCol);
    return move[0][0] == toRow && move[0][1] == toColumn;
  }
  
  /** Creates a location that is the valid location for a piece at the input current row and column to go to when moving forward one, assuming that there is no piece on the terminating square.
    * @param board the board to be used
    * @param curRow the current row of the piece to be moved
    * @param curCol the current column of the piece to be moved
    * @return a location that is a valid move
    */
  public default int[][] legalMovesOneForwardNC(ChessBoard board, int curRow, int curCol) {
    //ans will store the location to be returned
    int[][] ans = new int[1][2];
    //set the valid location to be one space downwards from the current location if the piece is on the north side
    if(board.getPiece(curRow, curCol).getSide() == ChessGame.Side.NORTH && isOnBoard(board, curRow + 1, curCol)) {
      ans[0][0] = curRow + 1;
    }
    //set the valid location to be one space upwards from the current location if the piece is on the south side
    else if(board.getPiece(curRow, curCol).getSide() == ChessGame.Side.SOUTH && isOnBoard(board, curRow - 1, curCol)){
      ans[0][0] = curRow - 1;
    }
    ans[0][1] = curCol;
    return ans;
  }
  
  /** Determines if the input location is a legal move from the input current location on the input chess board for one space diagonally forward, assuming there is a piece on the terminating square.
    * @param toRow the desired row of the new location
    * @param toColumn the desired column of the new location
    * @param board the chess board the method is using
    * @param curRow the current row of the location of the piece to be moved
    * @param curCol the current column of location of the piece to be moved
    * @return true if the desired location is a valid move
    */
  public default boolean isLegalMoveOneDiagonalC(int toRow, int toColumn, ChessBoard board, int curRow, int curCol) {
    //moves stores all the possible moves that the piece can move to
    ArrayList<int[]> moves = legalMovesOneDiagonalC(board, curRow, curCol);
    //found stores whether or not the input desired location was found in the possible moves
    boolean found = false;
    /* A loop that goes through moves to check if the desired location is a legal move */ 
    for(int i = 0; i < moves.size(); i++) {
      if(moves.get(i)[0] == toRow && moves.get(i)[1] == toColumn)
        found = true;
    }
    return found;
  }
  
  /** Creates a list of all the possible locations for a piece at the input current row and current column on the input chess board to move one forward diagonally one space, assuming there is a piece on the terminating square.\
    * @param board the board to be used
    * @param curRow the current row of the piece to be moved
    * @param curCol the current column of the piece to be moved
    * @return a list of all possible moves
    */
  public default ArrayList<int[]> legalMovesOneDiagonalC(ChessBoard board, int curRow, int curCol) {
    //ans will store all the possible moves of the piece
    ArrayList<int[]> ans = new ArrayList<int[]>();
    //if the side of the piece is the north side, make the diagonals backward one space
    if(board.getPiece(curRow, curCol).getSide() == ChessGame.Side.NORTH) {
      if(isOnBoard(board, curRow + 1, curCol - 1))
        ans.add(new int[]{curRow + 1, curCol - 1});
      if(isOnBoard(board, curRow + 1, curCol + 1))
        ans.add(new int[]{curRow + 1, curCol + 1});
    }
    //else, it is the south side. make the diagonals forwards one space
    else {
      if(isOnBoard(board, curRow - 1, curCol - 1))
        ans.add(new int[]{curRow - 1, curCol - 1});
      if(isOnBoard(board, curRow - 1, curCol + 1))
        ans.add(new int[]{curRow - 1, curCol + 1});
    }
    return ans;
  }
}