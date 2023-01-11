public class LaunchGame {
  public static void main(String[] args) {
    EuropeanChess e = new EuropeanChess();
    EuropeanChessDisplay ed = new EuropeanChessDisplay();
    e.setTurn(ChessGame.Side.SOUTH);
    ChessBoard board = new ChessBoard(8, 8, ed, e);
    KnightPiece k = new KnightPiece(board, ChessGame.Side.SOUTH);
    KnightPiece k2 = new KnightPiece(board, ChessGame.Side.NORTH);
    KnightPiece testSouth = new KnightPiece(board, ChessGame.Side.SOUTH);
    KnightPiece testNorth = new KnightPiece(board, ChessGame.Side.NORTH);
    RookPiece r = new RookPiece(board, ChessGame.Side.SOUTH);
    board.addPiece(k, 7, 1);
    board.addPiece(k2, 4, 7);
    board.addPiece(testSouth, 5, 0);
    board.addPiece(testNorth, 5, 2);
    board.addPiece(r, 7, 7);
  }
}