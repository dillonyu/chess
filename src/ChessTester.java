import org.junit.*;
import static org.junit.Assert.*;

/**
 * ChessTester tests the methods of each class involved in creating the chess game.
 */
public class ChessTester {
  
  /**
   * Tests all non-abstract methods in class ChessPiece.
   */
  @Test
  public void testChessPiece() {
    /* Initial Setup */
    EuropeanChess e = new EuropeanChess();
    EuropeanChessDisplay ed = new EuropeanChessDisplay();
    e.setTurn(ChessGame.Side.SOUTH);
    ChessBoard board = new ChessBoard(8, 8, ed, e);
    ChessBoard board2 = new ChessBoard(10, 10, ed, e);
    KnightPiece k = new KnightPiece(board, ChessGame.Side.SOUTH);
    KnightPiece k2 = new KnightPiece(board, ChessGame.Side.SOUTH);
    KnightPiece k3 = new KnightPiece(board, ChessGame.Side.NORTH);
    RookPiece r = new RookPiece(board2, ChessGame.Side.NORTH);
    r.setLocation(1, 1);
    board.addPiece(k, 7, 1);
    board.addPiece(k2, 5, 0);
    board.addPiece(k3, 5, 2);
    
    /* Testing each method */
    assertEquals("Test ChessBoard 1 failed", board, k.getChessBoard());                                   //Test chess board 1
    assertEquals("Test side 1 failed", ChessGame.Side.SOUTH, k.getSide());                              //Test side 1
    assertEquals("Test ChessBoard 2 failed", board2, r.getChessBoard());                                 //Test chess board 2
    assertEquals("Test side 2 failed", ChessGame.Side.NORTH, r.getSide());                              //Test side 2
    assertEquals("Test icon failed", null, k.getIcon());                                                                      //Test blank icon
    assertEquals("Test loc row failed", 1, r.getRow());                                                                     //Test new loc row
    assertEquals("Test loc column failed", 1, r.getColumn());                                                        //Test new loc column 
    assertEquals("Test legal move with piece, same side failed", false, k.isLegalMove(5, 0)); //Test legal move with piece, same side
    assertEquals("Test legal move with piece, opp side failed", true, k.isLegalMove(5, 2));    //Test legal move with piece, opp side
    assertEquals("Test legal move no piece failed", true, k.isLegalMove(6, 3));                         //Test legal move no piece
    assertEquals("Test non-legal move failed", false, k.isLegalMove(6, 1));                                //Test non-legal move
  }
  
  /**
   * Tests all methods in class KnightPiece and interface LPiece.
   */
  @Test
  public void testKnightPiece() {
    /* Initial Setup */
    EuropeanChess e = new EuropeanChess();
    EuropeanChessDisplay ed = new EuropeanChessDisplay();
    e.setTurn(ChessGame.Side.SOUTH);
    ChessBoard board = new ChessBoard(8, 8, ed, e);
    KnightPiece k = new KnightPiece(board, ChessGame.Side.SOUTH);
    board.addPiece(k, 4, 4);
    
    /* Test Label */
    assertEquals("Test label failed", "N", k.getLabel());  
    
    /* Test legal and illegal non-capture moves */
    assertEquals("Test legal non-capture move failed", true, k.isLegalMove(3, 6));                                             
    assertEquals("Test legal non-capture move failed", true, k.isLegalMove(5, 6));                                            
    assertEquals("Test legal non-capture move failed", true, k.isLegalMove(6, 5));                                            
    assertEquals("Test legal non-capture move failed", true, k.isLegalMove(6, 3));                                            
    assertEquals("Test legal non-capture move failed", true, k.isLegalMove(5, 2));                                            
    assertEquals("Test legal non-capture move failed", true, k.isLegalMove(3, 2));                                           
    assertEquals("Test legal non-capture move failed", true, k.isLegalMove(2, 3));                                            
    assertEquals("Test legal non-capture move failed", true, k.isLegalMove(2, 5));                                           
    assertEquals("Test illegal non-capture move failed", false, k.isLegalMove(4, 2));  //illegal move: left                                           
    
    /* Test legal capture moves */
    board.addPiece(new KnightPiece(board, ChessGame.Side.NORTH), 3, 6);
    board.addPiece(new KnightPiece(board, ChessGame.Side.NORTH), 5, 6);
    board.addPiece(new KnightPiece(board, ChessGame.Side.NORTH), 6, 5);
    board.addPiece(new KnightPiece(board, ChessGame.Side.NORTH), 6, 3);
    board.addPiece(new KnightPiece(board, ChessGame.Side.NORTH), 5, 2);
    board.addPiece(new KnightPiece(board, ChessGame.Side.NORTH), 3, 2);
    board.addPiece(new KnightPiece(board, ChessGame.Side.NORTH), 2, 3);
    board.addPiece(new KnightPiece(board, ChessGame.Side.NORTH), 2, 5);
    assertEquals("Test legal capture move failed", true, k.isLegalMove(3, 6)); 
    assertEquals("Test legal capture move failed", true, k.isLegalMove(5, 6)); 
    assertEquals("Test legal capture move failed", true, k.isLegalMove(6, 5)); 
    assertEquals("Test legal capture move failed", true, k.isLegalMove(6, 3)); 
    assertEquals("Test legal capture move failed", true, k.isLegalMove(5, 2)); 
    assertEquals("Test legal capture move failed", true, k.isLegalMove(3, 2)); 
    assertEquals("Test legal capture move failed", true, k.isLegalMove(2, 3)); 
    assertEquals("Test legal capture move failed", true, k.isLegalMove(2, 5)); 
    
    /* Test illegal capture moves*/
    board.addPiece(new KnightPiece(board, ChessGame.Side.SOUTH), 3, 6);
    board.addPiece(new KnightPiece(board, ChessGame.Side.SOUTH), 5, 6);
    board.addPiece(new KnightPiece(board, ChessGame.Side.SOUTH), 6, 5);
    board.addPiece(new KnightPiece(board, ChessGame.Side.SOUTH), 6, 3);
    board.addPiece(new KnightPiece(board, ChessGame.Side.SOUTH), 5, 2);
    board.addPiece(new KnightPiece(board, ChessGame.Side.SOUTH), 3, 2);
    board.addPiece(new KnightPiece(board, ChessGame.Side.SOUTH), 2, 3);
    board.addPiece(new KnightPiece(board, ChessGame.Side.SOUTH), 2, 5);
    assertEquals("Test illegal capture move failed", false, k.isLegalMove(3, 6)); 
    assertEquals("Test illegal capture move failed", false, k.isLegalMove(5, 6)); 
    assertEquals("Test illegal capture move failed", false, k.isLegalMove(6, 5)); 
    assertEquals("Test illegal capture move failed", false, k.isLegalMove(6, 3)); 
    assertEquals("Test illegal capture move failed", false, k.isLegalMove(5, 2)); 
    assertEquals("Test illegal capture move failed", false, k.isLegalMove(3, 2)); 
    assertEquals("Test illegal capture move failed", false, k.isLegalMove(2, 3)); 
    assertEquals("Test illegal capture move failed", false, k.isLegalMove(2, 5)); 
    
    /* Test on board */
    assertEquals("Test on board failed", true, k.isOnBoard(k.getChessBoard(), 5, 5));                                                              
    assertEquals("Test on board failed", false, k.isOnBoard(k.getChessBoard(), 9, 0));                                                            
  }
  
  /**
   * Tests all methods in class RookPiece and interface StraightPiece.
   */
  @Test
  public void testRookPieceAndStraightPiece() {
    /* Initial Setup */
    EuropeanChess e = new EuropeanChess();
    EuropeanChessDisplay ed = new EuropeanChessDisplay();
    e.setTurn(ChessGame.Side.SOUTH);
    ChessBoard board = new ChessBoard(8, 8, ed, e);
    RookPiece r = new RookPiece(board, ChessGame.Side.SOUTH);
    board.addPiece(r, 4, 4);
    board.addPiece(new RookPiece(board, ChessGame.Side.NORTH), 1, 4);
    board.addPiece(new RookPiece(board, ChessGame.Side.NORTH), 4, 1);
    board.addPiece(new RookPiece(board, ChessGame.Side.NORTH), 4, 6);
    board.addPiece(new RookPiece(board, ChessGame.Side.NORTH), 6, 4);
    
    /* Test label */
    assertEquals("Test label failed", "R", r.getLabel()); 
    
    /* Test legal and illegal non-capture moves. Test legal capture moves. */
    assertEquals("Test illegal non-capture move forwards failed", false, r.isLegalMove(0, 4));      //non-capture move forwards
    assertEquals("Test legal capture move forwards failed", true, r.isLegalMove(1, 4));                 //capture move forwards
    assertEquals("Test legal non-capture move forwards failed", true, r.isLegalMove(2, 4));        //non-capture move forwards
    assertEquals("Test legal non-capture move forwards failed", true, r.isLegalMove(3, 4));        //non-capture move forwards
    assertEquals("Test illegal non-capture move left failed", false, r.isLegalMove(4, 0));               //non-capture move left
    assertEquals("Test legal capture move left failed", true, r.isLegalMove(4, 1));                          //capture move left
    assertEquals("Test legal non-capture move left failed", true, r.isLegalMove(4, 2));                 //non-capture move left
    assertEquals("Test legal non-capture move left failed", true, r.isLegalMove(4, 3));                 //non-capture move left
    assertEquals("Test illegal non-capture move backwards failed", false, r.isLegalMove(7, 4));//non-capture move backwards
    assertEquals("Test legal capture move backwards failed", true, r.isLegalMove(6, 4));           //capture move backwards
    assertEquals("Test legal non-capture move backwards failed", true, r.isLegalMove(5, 4));  //non-capture move backwards
    assertEquals("Test illegal non-capture move right failed", false, r.isLegalMove(4, 7));          //non-capture move right
    assertEquals("Test legal capture move right failed", true, r.isLegalMove(4, 6));                     //capture move right
    assertEquals("Test legal non-capture move right failed", true, r.isLegalMove(4, 5));            //non-capture move right
    assertEquals("Test illegal non-capture move failed", false, r.isLegalMove(3, 3));                  //non-capture move diagonal
    
    /* Test non-legal capture moves */
    board.addPiece(new RookPiece(board, ChessGame.Side.SOUTH), 1, 4);
    board.addPiece(new RookPiece(board, ChessGame.Side.SOUTH), 4, 1);
    board.addPiece(new RookPiece(board, ChessGame.Side.SOUTH), 4, 6);
    board.addPiece(new RookPiece(board, ChessGame.Side.SOUTH), 6, 4);
    assertEquals("Test illegal capture move forwards failed", false, r.isLegalMove(1, 4));        //forwards
    assertEquals("Test illegal capture move left failed", false, r.isLegalMove(4, 1));                   //left
    assertEquals("Test illegal capture move backwards failed", false, r.isLegalMove(6, 4));     //backwards
    assertEquals("Test illegal capture move right failed", false, r.isLegalMove(4, 6));                //right
  }
  
  /**
   * Tests all methods in class BishopPiece and interface DiagonalPiece.
   */
  @Test
  public void testBishopPieceAndDiagonalPiece() {
    /* Initial Setup */
    EuropeanChess e = new EuropeanChess();
    EuropeanChessDisplay ed = new EuropeanChessDisplay();
    e.setTurn(ChessGame.Side.SOUTH);
    ChessBoard board = new ChessBoard(8, 8, ed, e);
    BishopPiece b = new BishopPiece(board, ChessGame.Side.SOUTH);
    board.addPiece(b, 4, 4);
    board.addPiece(new BishopPiece(board, ChessGame.Side.NORTH), 2, 6);
    board.addPiece(new BishopPiece(board, ChessGame.Side.NORTH), 2, 2);
    board.addPiece(new BishopPiece(board, ChessGame.Side.NORTH), 6, 2);
    board.addPiece(new BishopPiece(board, ChessGame.Side.NORTH), 6, 6);
    
    /* Test Label */
    assertEquals("Test label failed", "B", b.getLabel()); 
    
    /* Test legal and ilegal non-capture moves. Test legal capture moves. */
    assertEquals("Test illegal non-capture move failed", false, b.isLegalMove(1, 7));       //non-capture top right
    assertEquals("Test legal capture move failed", true, b.isLegalMove(2, 6));                  //capture top right
    assertEquals("Test legal non-capture move failed", true, b.isLegalMove(3, 5));         //non-capture top right
    assertEquals("Test illegal non-capture move failed", false, b.isLegalMove(0, 0));      //non-capture top left
    assertEquals("Test illegal non-capture move failed", false, b.isLegalMove(1, 1));      //non-capture top left
    assertEquals("Test legal capture move failed", true, b.isLegalMove(2, 2));                //capture top left
    assertEquals("Test legal non-capture move failed", true, b.isLegalMove(3, 3));      //non-capture top left
    assertEquals("Test illegal non-capture move failed", false, b.isLegalMove(7, 1));  //non-capture bottom left
    assertEquals("Test legal capture move failed", true, b.isLegalMove(6, 2));             //capture bottom left
    assertEquals("Test illegal non-capture move failed", false, b.isLegalMove(7, 7)); // non-capture bottom right
    assertEquals("Test legal capture move failed", true, b.isLegalMove(6, 6));            //capture bottom right
    assertEquals("Test legal non-capture move failed", true, b.isLegalMove(5, 5));   //non-capture bottom right
    assertEquals("Test illegal non-capture move failed", false, b.isLegalMove(4, 3));//non-capture left
    
    /* Test illegal capture moves. */
    board.addPiece(new BishopPiece(board, ChessGame.Side.SOUTH), 2, 6);
    board.addPiece(new BishopPiece(board, ChessGame.Side.SOUTH), 2, 2);
    board.addPiece(new BishopPiece(board, ChessGame.Side.SOUTH), 6, 2);
    board.addPiece(new BishopPiece(board, ChessGame.Side.SOUTH), 6, 6);
    assertEquals("Test illegal capture move failed", false, b.isLegalMove(2, 6)); //top right
    assertEquals("Test illegal capture move failed", false, b.isLegalMove(2, 2)); //top left
    assertEquals("Test illegal capture move failed", false, b.isLegalMove(6, 2)); //bottom left
    assertEquals("Test illegal capture move failed", false, b.isLegalMove(6, 6)); //bottom right
  }
  
  /**
   * Tests all methods in class QueenPiece.
   */
  @Test
  public void testQueenPiece() {
    /* Initial Setup */
    EuropeanChess e = new EuropeanChess();
    EuropeanChessDisplay ed = new EuropeanChessDisplay();
    e.setTurn(ChessGame.Side.SOUTH);
    ChessBoard board = new ChessBoard(8, 8, ed, e);
    QueenPiece q = new QueenPiece(board, ChessGame.Side.SOUTH);
    board.addPiece(q, 4, 4);
    board.addPiece(new QueenPiece(board, ChessGame.Side.NORTH), 1, 4);
    board.addPiece(new QueenPiece(board, ChessGame.Side.NORTH), 1, 1);
    board.addPiece(new QueenPiece(board, ChessGame.Side.NORTH), 4, 1);
    board.addPiece(new QueenPiece(board, ChessGame.Side.NORTH), 6, 2);
    board.addPiece(new QueenPiece(board, ChessGame.Side.NORTH), 6, 4);
    board.addPiece(new QueenPiece(board, ChessGame.Side.NORTH), 6, 6);
    board.addPiece(new QueenPiece(board, ChessGame.Side.NORTH), 4, 6);
    board.addPiece(new QueenPiece(board, ChessGame.Side.NORTH), 2, 6);
    
    /* Test Label */
    assertEquals("Test label failed", "Q", q.getLabel());
    
    /* Test legal and illegal non-capture moves. Test legal capture moves. */
    assertEquals("Test illegal non-capture move failed", false, q.isLegalMove(1, 7));          //non-capture top right
    assertEquals("Test legal capture move failed", true, q.isLegalMove(2, 6));                     //capture top right
    assertEquals("Test legal non-capture move failed", true, q.isLegalMove(3, 5));            //non-capture top right
    assertEquals("Test illegal non-capture move failed", false, q.isLegalMove(0, 4));         //non-capture forwards
    assertEquals("Test legal capture move failed", true, q.isLegalMove(1, 4));                    //capture forwards
    assertEquals("Test illegal non-capture move failed", true, q.isLegalMove(2, 4));         //non-capture forwards
    assertEquals("Test illegal non-capture move failed", true, q.isLegalMove(3, 4));        //non-capture forwards
    assertEquals("Test illegal non-capture move failed", false, q.isLegalMove(0, 0));       //non-capture top left
    assertEquals("Test legal capture move failed", true, q.isLegalMove(1, 1));                  //capture top left
    assertEquals("Test legal non-capture move failed", true, q.isLegalMove(2, 2));         //non-capture top left
    assertEquals("Test legal non-capture move failed", true, q.isLegalMove(3, 3));         //non-capture top left
    assertEquals("Test illegal non-capture move failed", false, q.isLegalMove(4, 0));      //non-capture left
    assertEquals("Test legal capture move failed", true, q.isLegalMove(4, 1));                 //capture left
    assertEquals("Test legal non-capture move failed", true, q.isLegalMove(4, 2));        //non-capture left
    assertEquals("Test legal non-capture move failed", true, q.isLegalMove(4, 3));       //non-capture left
    assertEquals("Test illegal non-capture move failed", false, q.isLegalMove(7, 1));    //non-capture bottom left
    assertEquals("Test legal capture move failed", true, q.isLegalMove(6, 2));               //capture bottom left
    assertEquals("Test legal non-capture move failed", true, q.isLegalMove(5, 3));      //non-capture bottom left
    assertEquals("Test illegal non-capture move failed", false, q.isLegalMove(7, 4));   //non-capture backwards
    assertEquals("Test legal capture move failed", true, q.isLegalMove(6, 4));              //capture backwards
    assertEquals("Test legal non-capture move failed", true, q.isLegalMove(5, 4));     //non-capture backwards
    assertEquals("Test illegal non-capture move failed", false, q.isLegalMove(7, 7));  //non-capture bottom right
    assertEquals("Test legal capture move failed", true, q.isLegalMove(6, 6));             //capture bottom right
    assertEquals("Test legal non-capture move failed", true, q.isLegalMove(5, 5));    //non-capture bottom right
    assertEquals("Test illegal non-capture move failed", false, q.isLegalMove(4, 7)); //non-capture right
    assertEquals("Test legal capture move failed", true, q.isLegalMove(4, 6));            //capture right
    assertEquals("Test legal non-capture move failed", true, q.isLegalMove(4, 5));  //non-capture right
    
    /* Test illegal capture moves. */
    board.addPiece(new QueenPiece(board, ChessGame.Side.SOUTH), 1, 4);
    board.addPiece(new QueenPiece(board, ChessGame.Side.SOUTH), 1, 1);
    board.addPiece(new QueenPiece(board, ChessGame.Side.SOUTH), 4, 1);
    board.addPiece(new QueenPiece(board, ChessGame.Side.SOUTH), 6, 2);
    board.addPiece(new QueenPiece(board, ChessGame.Side.SOUTH), 6, 4);
    board.addPiece(new QueenPiece(board, ChessGame.Side.SOUTH), 6, 6);
    board.addPiece(new QueenPiece(board, ChessGame.Side.SOUTH), 4, 6);
    board.addPiece(new QueenPiece(board, ChessGame.Side.SOUTH), 2, 6);
    assertEquals("Test illegal capture move failed", false, q.isLegalMove(2, 6)); //top right
    assertEquals("Test illegal capture move failed", false, q.isLegalMove(1, 4)); //forwards
    assertEquals("Test illegal capture move failed", false, q.isLegalMove(1, 1)); //top left
    assertEquals("Test illegal capture move failed", false, q.isLegalMove(4, 1)); //left
    assertEquals("Test illegal capture move failed", false, q.isLegalMove(6, 2)); //bottom left
    assertEquals("Test illegal capture move failed", false, q.isLegalMove(6, 4)); //backwards
    assertEquals("Test illegal capture move failed", false, q.isLegalMove(6, 6)); //bottom right
    assertEquals("Test illegal capture move failed", false, q.isLegalMove(4, 6)); //right
  }
  
  /**
   * Tests all methods in class KingPiece and some methods in interfaces AnyDirectionOnePiece and SpecialMovePiece.
   */
  @Test
  public void testKingPiece() {
    /* Initial Setup */
    EuropeanChess e = new EuropeanChess();
    EuropeanChessDisplay ed = new EuropeanChessDisplay();
    e.setTurn(ChessGame.Side.SOUTH);
    ChessBoard board = new ChessBoard(8, 8, ed, e);
    KingPiece k = new KingPiece(board, ChessGame.Side.SOUTH);
    board.addPiece(k, 4, 4);
    
    /* Test label */
    assertEquals("Test label failed", "K", k.getLabel());
    
    /* Test legal and illegal moves non-capture */
    assertEquals("Test legal move non-capture failed", true, k.isLegalMove(3, 3)); //top left
    assertEquals("Test legal move non-capture failed", true, k.isLegalMove(3, 4)); //forwards
    assertEquals("Test legal move non-capture failed", true, k.isLegalMove(3, 5)); //top right
    assertEquals("Test legal move non-capture failed", true, k.isLegalMove(4, 3)); //left
    assertEquals("Test legal move non-capture failed", true, k.isLegalMove(4, 5)); //right
    assertEquals("Test legal move non-capture failed", true, k.isLegalMove(5, 3)); //bottom left
    assertEquals("Test legal move non-capture failed", true, k.isLegalMove(5, 4)); //backwards
    assertEquals("Test legal move non-capture failed", true, k.isLegalMove(5, 5)); //bottom right
    assertEquals("Test legal move non-capture failed", false, k.isLegalMove(2, 2)); //top left
    assertEquals("Test legal move non-capture failed", false, k.isLegalMove(2, 4)); //forwards
    assertEquals("Test legal move non-capture failed", false, k.isLegalMove(2, 6)); //top right
    assertEquals("Test legal move non-capture failed", false, k.isLegalMove(4, 2)); //left
    assertEquals("Test legal move non-capture failed", false, k.isLegalMove(4, 6)); //right
    assertEquals("Test legal move non-capture failed", false, k.isLegalMove(6, 2)); //bottom left
    assertEquals("Test legal move non-capture failed", false, k.isLegalMove(6, 4)); //backwards
    assertEquals("Test legal move non-capture failed", false, k.isLegalMove(6, 6)); //bottom right
    
    /* Test legal moves capture */
    board.addPiece(new KingPiece(board, ChessGame.Side.NORTH), 3, 3);
    board.addPiece(new KingPiece(board, ChessGame.Side.NORTH), 3, 4);
    board.addPiece(new KingPiece(board, ChessGame.Side.NORTH), 3, 5);
    board.addPiece(new KingPiece(board, ChessGame.Side.NORTH), 4, 3);
    board.addPiece(new KingPiece(board, ChessGame.Side.NORTH), 4, 5);
    board.addPiece(new KingPiece(board, ChessGame.Side.NORTH), 5, 3);
    board.addPiece(new KingPiece(board, ChessGame.Side.NORTH), 5, 4);
    board.addPiece(new KingPiece(board, ChessGame.Side.NORTH), 5, 5);
    assertEquals("Test legal move capture failed", true, k.isLegalMove(3, 3)); //top left
    assertEquals("Test legal move capture failed", true, k.isLegalMove(3, 4)); //forwards
    assertEquals("Test legal move capture failed", true, k.isLegalMove(3, 5)); //top right
    assertEquals("Test legal move capture failed", true, k.isLegalMove(4, 3)); //left
    assertEquals("Test legal move capture failed", true, k.isLegalMove(4, 5)); //right
    assertEquals("Test legal move capture failed", true, k.isLegalMove(5, 3)); //bottom left
    assertEquals("Test legal move capture failed", true, k.isLegalMove(5, 4)); //backwards
    assertEquals("Test legal move capture failed", true, k.isLegalMove(5, 5)); //bottom right
    
    /* Test illegal moves capture */
    board.addPiece(new KingPiece(board, ChessGame.Side.SOUTH), 3, 3);
    board.addPiece(new KingPiece(board, ChessGame.Side.SOUTH), 3, 4);
    board.addPiece(new KingPiece(board, ChessGame.Side.SOUTH), 3, 5);
    board.addPiece(new KingPiece(board, ChessGame.Side.SOUTH), 4, 3);
    board.addPiece(new KingPiece(board, ChessGame.Side.SOUTH), 4, 5);
    board.addPiece(new KingPiece(board, ChessGame.Side.SOUTH), 5, 3);
    board.addPiece(new KingPiece(board, ChessGame.Side.SOUTH), 5, 4);
    board.addPiece(new KingPiece(board, ChessGame.Side.SOUTH), 5, 5);
    assertEquals("Test illegal move capture failed", false, k.isLegalMove(3, 3)); //top left
    assertEquals("Test illegal move capture failed", false, k.isLegalMove(3, 4)); //forwards
    assertEquals("Test illegal move capture failed", false, k.isLegalMove(3, 5)); //top right
    assertEquals("Test illegal move capture failed", false, k.isLegalMove(4, 3)); //left
    assertEquals("Test illegal move capture failed", false, k.isLegalMove(4, 5)); //right
    assertEquals("Test illegal move capture failed", false, k.isLegalMove(5, 3)); //bottom left
    assertEquals("Test illegal move capture failed", false, k.isLegalMove(5, 4)); //backwards
    assertEquals("Test illegal move capture failed", false, k.isLegalMove(5, 5)); //bottom right
    
    /** Test Castling **/
    board.removePiece(3, 3);
    board.removePiece(3, 4);
    board.removePiece(3, 5);
    board.removePiece(4, 3);
    board.removePiece(4, 4);
    board.removePiece(4, 5);
    board.removePiece(5, 3);
    board.removePiece(5, 4);
    board.removePiece(5, 5);
    KingPiece kSouth = new KingPiece(board, ChessGame.Side.SOUTH);
    KingPiece kNorth = new KingPiece(board, ChessGame.Side.NORTH);
    RookPiece rTopL = new RookPiece(board, ChessGame.Side.NORTH);
    RookPiece rTopR = new RookPiece(board, ChessGame.Side.NORTH);
    RookPiece rBotL = new RookPiece(board, ChessGame.Side.SOUTH);
    RookPiece rBotR = new RookPiece(board, ChessGame.Side.SOUTH);
    QueenPiece qSouthLeft = new QueenPiece(board, ChessGame.Side.SOUTH);
    QueenPiece qSouthRight = new QueenPiece(board, ChessGame.Side.SOUTH);
    QueenPiece qNorthLeft = new QueenPiece(board, ChessGame.Side.NORTH);
    QueenPiece qNorthRight = new QueenPiece(board, ChessGame.Side.NORTH);
    board.addPiece(kSouth, 7, 4);
    board.addPiece(kNorth, 0, 4);
    board.addPiece(rTopL, 0, 0);
    board.addPiece(rTopR, 0, 7);
    board.addPiece(rBotL, 7, 0);
    board.addPiece(rBotR, 7, 7);
    board.addPiece(qSouthLeft, 7, 3);
    board.addPiece(qSouthRight, 7, 5);
    board.addPiece(qNorthLeft, 0, 3);
    board.addPiece(qNorthRight, 0, 5);
    
    /* Piece in-between test */
    assertEquals("Piece in-between test failed", false, kSouth.isLegalMove(7, 2)); //castle south left
    assertEquals("Piece in-between test failed", false, kSouth.isLegalMove(7, 6)); //castle south right
    assertEquals("Piece in-between test failed", false, kNorth.isLegalMove(0, 2)); //castle north left
    assertEquals("Piece in-between test failed", false, kNorth.isLegalMove(0, 6)); //castle north right
    
    /* No piece in-between test */
    board.removePiece(7, 3);
    board.removePiece(7, 5);
    board.removePiece(0, 3);
    board.removePiece(0, 5);
    assertEquals("Piece in-between test failed", true, kSouth.isLegalMove(7, 2)); //castle south left
    assertEquals("Piece in-between test failed", true, kSouth.isLegalMove(7, 6)); //castle south right
    assertEquals("Piece in-between test failed", true, kNorth.isLegalMove(0, 2)); //castle north left
    assertEquals("Piece in-between test failed", true, kNorth.isLegalMove(0, 6)); //castle north right
    
    /* Test if king moves */
    e.makeMove(kSouth, 6, 4);
    e.makeMove(kNorth, 1, 4);
    assertEquals("Piece in-between test failed", false, kSouth.isLegalMove(7, 2)); //castle south left
    assertEquals("Piece in-between test failed", false, kSouth.isLegalMove(7, 6)); //castle south right
    assertEquals("Piece in-between test failed", false, kNorth.isLegalMove(0, 2)); //castle north left
    assertEquals("Piece in-between test failed", false, kNorth.isLegalMove(0, 6)); //castle north right
    
    /* Test if a rook moves */
    board.removePiece(6, 4);
    board.removePiece(1, 4);
    KingPiece newKSouth = new KingPiece(board, ChessGame.Side.SOUTH);
    KingPiece newKNorth = new KingPiece(board, ChessGame.Side.NORTH);
    board.addPiece(newKSouth, 7, 4);
    board.addPiece(newKNorth, 0, 4);
    e.makeMove(rTopL, 1, 0);
    e.makeMove(rTopR, 1, 7);
    e.makeMove(rBotL, 6, 0);
    e.makeMove(rBotR, 6, 7);
    assertEquals("Piece in-between test failed", false, newKSouth.isLegalMove(7, 2)); //castle south left
    assertEquals("Piece in-between test failed", false, newKSouth.isLegalMove(7, 6)); //castle south right
    assertEquals("Piece in-between test failed", false, newKNorth.isLegalMove(0, 2)); //castle north left
    assertEquals("Piece in-between test failed", false, newKNorth.isLegalMove(0, 6)); //castle north right
  }
  
  /**
   * Tests all methods in class PawnPiece and some methods in interfaces AnyDirectionOnePiece and SpecialMovePiece.
   */
  @Test
  public void testPawnPiece() {
    EuropeanChess e = new EuropeanChess();
    EuropeanChessDisplay ed = new EuropeanChessDisplay();
    e.setTurn(ChessGame.Side.SOUTH);
    ChessBoard board = new ChessBoard(8, 8, ed, e);
    PawnPiece pS = new PawnPiece(board, ChessGame.Side.SOUTH);
    PawnPiece pN = new PawnPiece(board, ChessGame.Side.NORTH);
    board.addPiece(pS, 6, 4);
    board.addPiece(pN, 1, 3);
    /* Test legal and illegal non-capture moves. Test special 2 forward move is one time use */
    assertEquals("Test legal non-capture move failed", true, pS.isLegalMove(5, 4)); //non-capture move
    assertEquals("Test legal non-capture move failed", true, pN.isLegalMove(2, 3)); //non-capture move
    assertEquals("Test illegal non-capture move failed", false, pS.isLegalMove(5, 3)); //non-capture move
    assertEquals("Test illegal non-capture move failed", false, pN.isLegalMove(2, 4)); //non-capture move
    assertEquals("Test special 2 forward move failed", true, pS.isLegalMove(4, 4)); //has not moved yet
    assertEquals("Test special 2 forward move failed", true, pN.isLegalMove(3, 3)); //has not moved yet
    e.makeMove(pS, 4, 4);
    e.makeMove(pN, 3, 3);
    assertEquals("Test special 2 forward move failed", false, pS.isLegalMove(2, 4)); //has already moved
    assertEquals("Test special 2 forward move failed", false, pN.isLegalMove(5, 3)); //has already moved
    /* Test legal capture moves */
    PawnPiece pS2 = new PawnPiece(board, ChessGame.Side.SOUTH);
    PawnPiece pN2 = new PawnPiece(board, ChessGame.Side.NORTH);
    board.addPiece(pS2, 4, 2);
    board.addPiece(pN2, 2, 4);
    assertEquals("Test legal capture move failed", true, pS.isLegalMove(3, 3)); //capture pawn to left
    assertEquals("Test legal capture move failed", true, pN.isLegalMove(4, 4)); //capture pawn to left
    e.makeMove(pS, 3, 3);
    assertEquals("Test legal capture move failed", true, pN2.isLegalMove(3, 3)); //capture pawn to right
    e.makeMove(pN2, 3, 3);
    assertEquals("Test legal capture move failed", true, pS2.isLegalMove(3, 3)); //capture pawn to right
    e.makeMove(pS2, 3, 3);
    board.addPiece(new PawnPiece(board, ChessGame.Side.SOUTH), 2, 2);
    board.addPiece(new PawnPiece(board, ChessGame.Side.SOUTH), 2, 4);
    board.addPiece(new PawnPiece(board, ChessGame.Side.NORTH), 2, 3);
    assertEquals("Test illegal capture move failed", false, pS2.isLegalMove(2, 2)); //can't capture same side piece
    assertEquals("Test illegal capture move failed", false, pS2.isLegalMove(2, 4)); //can't capture same side piece
    assertEquals("Test illegal capture move failed", false, pS2.isLegalMove(2, 3)); //can't capture forwards
  }
  
  /**
   * Tests most methods in class EuropeanChess.
   */
  @Test
  public void testEuropeanChess() {
    EuropeanChess e = new EuropeanChess();
    EuropeanChessDisplay ed = new EuropeanChessDisplay();
    e.setTurn(ChessGame.Side.SOUTH);
    ChessBoard board = new ChessBoard(8, 8, ed, e);
    KingPiece kSouth = new KingPiece(board, ChessGame.Side.SOUTH);
    KingPiece kNorth = new KingPiece(board, ChessGame.Side.NORTH);
    RookPiece rTopL = new RookPiece(board, ChessGame.Side.NORTH);
    RookPiece rTopR = new RookPiece(board, ChessGame.Side.NORTH);
    RookPiece rBotL = new RookPiece(board, ChessGame.Side.SOUTH);
    RookPiece rBotR = new RookPiece(board, ChessGame.Side.SOUTH);
    PawnPiece pSouth = new PawnPiece(board, ChessGame.Side.SOUTH);
    PawnPiece pNorth = new PawnPiece(board, ChessGame.Side.NORTH);
    board.addPiece(kSouth, 7, 4);
    board.addPiece(kNorth, 0, 4);
    board.addPiece(rTopL, 0, 0);
    board.addPiece(rTopR, 0, 7);
    board.addPiece(rBotL, 7, 0);
    board.addPiece(rBotR, 7, 7);
    board.addPiece(pSouth, 1, 1);
    board.addPiece(pNorth, 6, 2);
    
    /* Test everything dealing with turns */
    assertEquals("Get turn failed", ChessGame.Side.SOUTH, e.getTurn()); 
    e.setTurn(ChessGame.Side.EAST);
    assertEquals("Set turn failed", ChessGame.Side.EAST, e.getTurn()); 
    e.setTurn(ChessGame.Side.NORTH);
    e.switchTurn();
    assertEquals("Switch Turn failed", ChessGame.Side.SOUTH, e.getTurn());
    assertEquals("Legal piece to play failed", true, e.legalPieceToPlay(kSouth, 7, 4));
    assertEquals("Legal piece to play failed", false, e.legalPieceToPlay(kNorth, 0, 4));
  }
}