public interface ForwardOnePiece {
  public default boolean defaultLegalMove(int toRow, int toColumn) {
    return false;
  }
}