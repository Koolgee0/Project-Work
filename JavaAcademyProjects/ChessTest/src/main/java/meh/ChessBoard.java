package meh;


public class ChessBoard {
    private ChessPiece[][] board;


    public ChessBoard() {
        board = new ChessPiece[8][8];
    }


    public boolean isPieceAt(int row, int col) {
        return board[row][col] != null;
    }


    public void placePieceAt(ChessPiece piece, ChessLocation location) {
        if (isPieceAt(location.getRow(), location.getCol())) {
            removePieceAt(location);
        }
        if (piece.getChessLocation() != null) {
            removePieceAt(piece.getChessLocation());
        }
        board[location.getRow()][location.getCol()] = piece;
        piece.setChessLocation(location);
    }


    private void removePieceAt(ChessLocation location) {
        board[location.getRow()][location.getCol()] = null;
    }


    public static boolean locationInBounds(ChessLocation location) {
        return location.getRow() >= 0 &&
                location.getRow() < 8 &&
                location.getCol() >= 0 &&
                location.getCol() < 8;
    }


    public ChessPiece getPieceAt(ChessLocation location) {
        return board[location.getRow()][location.getCol()];
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("  0  1  2  3  4  5  6  7\n");
        for (int row = 0; row < 8; row++) {
            s.append(row);
            for (int col = 0; col < 8; col++) {
                if (board[row][col] != null) {
                    s.append(" ").append(board[row][col].getId());
                } else {
                    s.append(" - ");
                }
            }
            s.append("\n");
        }
        return s.toString();
    }
}
