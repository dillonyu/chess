import java.util.ArrayList;
/**
 *  This interface provides the needed functions for a piece that moves in L-shapes.
 * 
 * @author Dillon Yu
 */
public interface LPiece {
  /** Determines if the input location is a legal move from the input current location on the input chess board for an L-shape in any direction.
    * @param toRow the desired row of the new location
    * @param toColumn the desired column of the new location
    * @param board the chess board the method is using
    * @param curRow the current row of the location of the piece to be moved
    * @param curCol the current column of location of the piece to be moved
    * @return true if the desired location is a valid move
    */
  public default boolean isLegalMoveL(int toRow, int toColumn, ChessBoard board, int curRow, int curCol) {
    //moves is the list of all possible legal moves
    ArrayList<int[]> moves = new ArrayList<int[]>();
    moves = legalMovesL(board, curRow, curCol);
    //found represents whether or not the input move is part of the possible legal moves
    boolean found = false;
    /* A loop that searches moves to find the input legal move */
    for(int i = 0; i < moves.size(); i++) {
      if(moves.get(i)[0] == toRow && moves.get(i)[1] == toColumn)
        found = true;
    }
    return found;
  }
  
  /** Creates a list of all the possible locations for a piece at the input current row and column on the input chess board to move in an L-shape in any direction.
    * @param board the chess board to be used for checking
    * @param curRow the current row of the piece to be moved
    * @param curCol the current column of the piece to be moved
    * @return a list of all the possible moves
    */
  public default ArrayList<int[]> legalMovesL(ChessBoard board, int curRow, int curCol) {
    //ans is the final list that contains all the legal moves
    ArrayList<int[]> ans = new ArrayList<int[]>();
    /* Check every possible L-shape around the piece. Add it to ans if it is legal */
    if(isOnBoard(board, curRow - 2, curCol + 1))
      ans.add(new int[]{curRow - 2, curCol + 1});
    if(isOnBoard(board, curRow - 2, curCol - 1))
      ans.add(new int[]{curRow - 2, curCol - 1});
    if(isOnBoard(board, curRow - 1, curCol - 2))
      ans.add(new int[]{curRow - 1, curCol - 2});
    if(isOnBoard(board, curRow - 1, curCol + 2))
      ans.add(new int[]{curRow - 1, curCol + 2});
    if(isOnBoard(board, curRow + 1, curCol - 2))
      ans.add(new int[]{curRow + 1, curCol - 2});
    if(isOnBoard(board, curRow + 1, curCol + 2))
      ans.add(new int[]{curRow + 1, curCol + 2});
    if(isOnBoard(board, curRow + 2, curCol - 1))
      ans.add(new int[]{curRow + 2, curCol - 1});
    if(isOnBoard(board, curRow + 2, curCol + 1))
      ans.add(new int[]{curRow +2, curCol + 1});
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
}