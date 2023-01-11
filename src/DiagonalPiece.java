import java.util.ArrayList;
/**
 *  This interface provides the needed functions for a piece that can move diagonally in any direction.
 * 
 * @author Dillon Yu
 */
public interface DiagonalPiece {
  /** Determines if the input location is a legal move from the input current location on the input chess board for anywhere diagonally, assuming there is no piece on the terminating square.
    * @param toRow the desired row of the new location
    * @param toColumn the desired column of the new location
    * @param board the chess board the method is using
    * @param curRow the current row of the location of the piece to be moved
    * @param curCol the current column of location of the piece to be moved
    * @return true if the desired location is a valid move
    */
  public default boolean isLegalMoveDiagonalNC(int toRow, int toColumn, ChessBoard board, int curRow, int curCol) {
    //moves stores all the possible moves for the input chess piece location on the input chess board
    ArrayList<int[]> moves = new ArrayList<int[]>();
    moves = legalMovesDiagonalNC(board, curRow, curCol);
    //found stores whether or not the desired location is a legal move
    boolean found = false;
    /* A loop that checks if the desired location is a legal move */
    for(int i = 0; i < moves.size(); i++) {
      if(moves.get(i)[0] == toRow && moves.get(i)[1] == toColumn)
        found = true;
    }
    return found;
  }
  
  /** Determines if the input location is a legal move from the input current location on the input chess board for anywhere diagonally, assuming there is a piece on the terminating square.
    * @param toRow the desired row of the new location
    * @param toColumn the desired column of the new location
    * @param board the chess board the method is using
    * @param curRow the current row of the location of the piece to be moved
    * @param curCol the current column of location of the piece to be moved
    * @return true if the desired location is a valid move
    */
  public default boolean isLegalMoveDiagonalC(int toRow, int toColumn, ChessBoard board, int curRow, int curCol) {
    //moves stores all the possible moves for the input chess piece location on the input chess board
    ArrayList<int[]> moves = new ArrayList<int[]>();
    moves = legalMovesDiagonalC(board, curRow, curCol);
    //found stores whether or not the desired location is a legal move
    boolean found = false;
    /* A loop that checks if the desired location is a legal move */
    for(int i = 0; i < moves.size(); i++) {
      if(moves.get(i)[0] == toRow && moves.get(i)[1] == toColumn)
        found = true;
    }
    return found;
  }
  
  /** Returns all the legal moves for a piece from the current row and current column to move diagonally in any direction. Assumes there is no piece on the terminating square.
    * @param board the chess board to be used
    * @param curRow the current row of the piece
    * @param curCol the current column of the piece
    * @return a list of all legal moves that can be reached diagonally from the current location
    */
  public default ArrayList<int[]> legalMovesDiagonalNC(ChessBoard board, int curRow, int curCol) {
    //ans stores all the possible legal moves
    ArrayList<int[]> ans = new ArrayList<int[]>();
    
    /** Indexes are created for each of the four corners, and the legal moves are added accordingly **/
    int topLeftR = curRow - 1;
    int topLeftC = curCol - 1;
    //adds the legal moves for the top left diagonal
    while(isOnBoard(board, topLeftR, topLeftC) && !board.hasPiece(topLeftR, topLeftC)) {
      ans.add(new int[]{topLeftR, topLeftC});
      topLeftR--;
      topLeftC--;
    }
    
    int botRightR = curRow + 1;
    int botRightC = curCol + 1;
    //adds the legal moves for the bottom right diagonal
    while(isOnBoard(board, botRightR, botRightC) && !board.hasPiece(botRightR, botRightC)) {
      ans.add(new int[]{botRightR, botRightC});
      botRightR++;
      botRightC++;
    }
    
    int botLeftR = curRow + 1;
    int botLeftC = curCol - 1;
    //adds the legal moves for the bottom left diagonal
    while(isOnBoard(board, botLeftR, botLeftC) && !board.hasPiece(botLeftR, botLeftC)) {
      ans.add(new int[]{botLeftR, botLeftC});
      botLeftR++;
      botLeftC--;
    }
    
    int topRightR = curRow - 1;
    int topRightC = curCol + 1;
    //adds the legal moves for the top right diagonal
    while(isOnBoard(board, topRightR, topRightC) && !board.hasPiece(topRightR, topRightC)) {
      ans.add(new int[]{topRightR, topRightC});
      topRightR--;
      topRightC++;
    }
    
    return ans;
  }
  
  /** Returns all the legal moves for a piece with the input current row and column to move anywhere diagonally. Assumes there is a piece on the terminating square.
    * @param board the chess board to be used
    * @param curRow the current row of the piece
    * @param curCol the current column of the piece
    */
  public default ArrayList<int[]> legalMovesDiagonalC(ChessBoard board, int curRow, int curCol) {
    //ans stores all the legal moves
    ArrayList<int[]> ans = new ArrayList<int[]>();
    
    /** Indexes are created for each of the four corners, and the legal moves are added accordingly **/
    int topLeftR = curRow - 1;
    int topLeftC = curCol - 1;
    //going towards top left, add the first square to the legal moves that contains a piece
    while(isOnBoard(board, topLeftR, topLeftC) && !board.hasPiece(topLeftR, topLeftC)) {
      topLeftR--;
      topLeftC--;
    }
    if(isOnBoard(board, topLeftR, topLeftC))
      ans.add(new int[]{topLeftR, topLeftC});
    
     //going towards bottom right, add the first square to the legal moves that contains a piece
    int botRightR = curRow + 1;
    int botRightC = curCol + 1;
    while(isOnBoard(board, botRightR, botRightC) && !board.hasPiece(botRightR, botRightC)) {
      botRightR++;
      botRightC++;
    }
    if(isOnBoard(board, botRightR, botRightC))
      ans.add(new int[]{botRightR, botRightC});
    
    //going towards bottom left, add the first square to the legal moves that contains a piece
    int botLeftR = curRow + 1;
    int botLeftC = curCol - 1;
    while(isOnBoard(board, botLeftR, botLeftC) && !board.hasPiece(botLeftR, botLeftC)) {
      botLeftR++;
      botLeftC--;
    }
    if(isOnBoard(board, botLeftR, botLeftC))
      ans.add(new int[]{botLeftR, botLeftC});
    
    //going towards top right, add the first square to the legal moves that contains a piece
    int topRightR = curRow - 1;
    int topRightC = curCol + 1;
    while(isOnBoard(board, topRightR, topRightC) && !board.hasPiece(topRightR, topRightC)) {
      topRightR--;
      topRightC++;
    }
    if(isOnBoard(board, topRightR, topRightC))
      ans.add(new int[]{topRightR, topRightC});
    
    return ans;
  }
  
  /** Determines whether or not the input location is on the input chess board 
    * @param board the board to be checked
    * @param row the row of the location
    * @param col the column of the location
    * @return true if the input location is on the chess board
    */
  public default boolean isOnBoard(ChessBoard board, int row, int col) {
    return ((row >= 0 && row < board.numRows()) && (col >= 0 && col < board.numColumns()));
  }
}