import java.util.ArrayList;
/**
 *  This interface provides the needed functions for a piece that can move forwards, backwards, left, or right.
 * 
 * @author Dillon Yu
 */
public interface StraightPiece {
  /** Determines if the input location is a legal move from the input current location on the input chess board for moving forwards, backwards, left, or right. Assumes there is no piece on the terminating square.
    * @param toRow the desired row of the new location
    * @param toColumn the desired column of the new location
    * @param board the chess board the method is using
    * @param curRow the current row of the location of the piece to be moved
    * @param curCol the current column of location of the piece to be moved
    * @return true if the desired location is a valid move
    */
  public default boolean isLegalMoveStraightNC(int toRow, int toColumn, ChessBoard board, int curRow, int curCol) {
    //moves stores the legal moves from the current location of the piece
    ArrayList<int[]> moves = new ArrayList<int[]>();
    moves = legalMovesStraightNC(board, curRow, curCol);
    //found stores whether or not the desired move is a legal move
    boolean found = false;
    /* A loop that checks if the desired move is in the list of legal moves */
    for(int i = 0; i < moves.size(); i++) {
      if(moves.get(i)[0] == toRow && moves.get(i)[1] == toColumn)
        found = true;
    }
    return found;
  }
  
  /** Determines if the input location is a legal move from the input current location on the input chess board for moving forwards, backwards, left, or right. Assumes there is a piece on the terminating square.
    * @param toRow the desired row of the new location
    * @param toColumn the desired column of the new location
    * @param board the chess board the method is using
    * @param curRow the current row of the location of the piece to be moved
    * @param curCol the current column of location of the piece to be moved
    * @return true if the desired location is a valid move
    */
  public default boolean isLegalMoveStraightC(int toRow, int toColumn, ChessBoard board, int curRow, int curCol) {
    //moves stores all the possible legal moves
    ArrayList<int[]> moves = new ArrayList<int[]>();
    moves = legalMovesStraightC(board, curRow, curCol);
    //found stores whether or not the desired move is a legal move
    boolean found = false;
    /* A loop that checks if the desired move is in the list of legal moves */
    for(int i = 0; i < moves.size(); i++) {
      if(moves.get(i)[0] == toRow && moves.get(i)[1] == toColumn)
        found = true;
    }
    return found;
  }
  
  /** Creates a list of all the possible locations for a piece at the input current row and column on the input chess board to move forwards, backwards, left, or right. Assumes there is no piece on the terminating square.
    * @param board the chess board to be used for checking
    * @param curRow the current row of the piece to be moved
    * @param curCol the current column of the piece to be moved
    * @return a list of all the possible moves
    */
  public default ArrayList<int[]> legalMovesStraightNC(ChessBoard board, int curRow, int curCol) {
    //ans stores the legal moves
    ArrayList<int[]> ans = new ArrayList<int[]>();
    int i = curRow - 1;
    /* Loop that adds possible moves forwards */
    while(i >= 0 && !board.hasPiece(i, curCol)) {
      ans.add(new int[]{i, curCol});
      i--;
    }
    int j = curRow + 1;
    /* Loop that adds possible moves backwards */
    while(j < board.numRows() && !board.hasPiece(j, curCol)) {
      ans.add(new int[]{j, curCol});
      j++;
    }
    int k = curCol - 1;
    /* Loop that adds possible moves left */
    while(k >= 0 && !board.hasPiece(curRow, k)) {
      ans.add(new int[]{curRow, k});
      k--;
    }
    int m = curCol + 1;
    /* Loop that adds possible moves right */
    while(m < board.numColumns() && !board.hasPiece(curRow, m)) {
      ans.add(new int[]{curRow, m});
      m++;
    }
    
    return ans;
  }
  
  /** Creates a list of all the possible locations for a piece at the input current row and column on the input chess board to move forwards, backwards, left, or right. Assumes there is a piece on the terminating square.
    * @param board the chess board to be used for checking
    * @param curRow the current row of the piece to be moved
    * @param curCol the current column of the piece to be moved
    * @return a list of all the possible moves
    */
  public default ArrayList<int[]> legalMovesStraightC(ChessBoard board, int curRow, int curCol) {
    //ans stores the legal moves
    ArrayList<int[]> ans = new ArrayList<int[]>();
    
    int i = curRow - 1;
    /* Loop that traverses in the forward direction until a square with a piece is reached. That square is added to ans */
    while(i >= 0 && !board.hasPiece(i, curCol)) {
      i--;
    }
    if(i >= 0)
      ans.add(new int[]{i, curCol});
    
    int j = curRow + 1;
    /* Loop that traverses in the backward direction until a square with a piece is reached. That square is added to ans */
    while(j < board.numRows() && !board.hasPiece(j, curCol)) {
      j++;
    }
    if(j < board.numRows())
      ans.add(new int[]{j, curCol});
    
    int k = curCol - 1;
    /* Loop that traverses in the left direction until a square with a piece is reached. That square is added to ans */
    while(k >= 0 && !board.hasPiece(curRow, k)) {
      k--;
    }
    if(k >= 0)
      ans.add(new int[]{curRow, k});
    
    int m = curCol + 1;
     /* Loop that traverses in the right direction until a square with a piece is reached. That square is added to ans */
    while(m < board.numColumns() && !board.hasPiece(curRow, m)) {
      m++;
    }
    if(m < board.numColumns())
      ans.add(new int[]{curRow, m});
    
    return ans;
  }
}