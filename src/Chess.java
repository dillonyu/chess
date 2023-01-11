/**
 *  This class stores the main method of the program.
 * 
 * @author Dillon Yu
 */
public class Chess {
  /**
   * This method runs the chess game, creating a standard European Chess Game by setting the correct number of rows, columns and placing the pieces in their starting positions.
   */
  public static void main(String[] args) {
    //e is the European chess version of playing the chess game
    EuropeanChess e = new EuropeanChess();
    //ed is the european chess display for the chess game
    EuropeanChessDisplay ed = new EuropeanChessDisplay();
    //set the south side player to go first
    e.setTurn(ChessGame.Side.SOUTH);
    //creates a standard European chess board
    ChessBoard board = new ChessBoard(8, 8, ed, e);
    /* Adds the pieces to their starting locations */
    board.addPiece(new RookPiece(board, ChessGame.Side.SOUTH), 7, 0);
    board.addPiece(new RookPiece(board, ChessGame.Side.SOUTH), 7, 7);
    board.addPiece(new RookPiece(board, ChessGame.Side.NORTH), 0, 0);
    board.addPiece(new RookPiece(board, ChessGame.Side.NORTH), 0, 7);
    board.addPiece(new KnightPiece(board, ChessGame.Side.SOUTH), 7, 1);
    board.addPiece(new KnightPiece(board, ChessGame.Side.SOUTH), 7, 6);
    board.addPiece(new KnightPiece(board, ChessGame.Side.NORTH), 0, 1);
    board.addPiece(new KnightPiece(board, ChessGame.Side.NORTH), 0, 6);
    board.addPiece(new BishopPiece(board, ChessGame.Side.SOUTH), 7, 2);
    board.addPiece(new BishopPiece(board, ChessGame.Side.SOUTH), 7, 5);
    board.addPiece(new BishopPiece(board, ChessGame.Side.NORTH), 0, 2);
    board.addPiece(new BishopPiece(board, ChessGame.Side.NORTH), 0, 5);
    board.addPiece(new QueenPiece(board, ChessGame.Side.SOUTH), 7, 3);
    board.addPiece(new QueenPiece(board, ChessGame.Side.NORTH), 0, 3);
    board.addPiece(new KingPiece(board, ChessGame.Side.SOUTH), 7, 4);
    board.addPiece(new KingPiece(board, ChessGame.Side.NORTH), 0, 4);
    for(int i = 0; i < board.numColumns(); i++) {
      board.addPiece(new PawnPiece(board, ChessGame.Side.SOUTH), 6, i);
      board.addPiece(new PawnPiece(board, ChessGame.Side.NORTH), 1, i);
    }
  }
}