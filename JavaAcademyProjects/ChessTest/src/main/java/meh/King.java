package meh;

public class King extends ChessPiece {

    public King(String owner, ChessLocation initialLocation, ChessGame game) {
        super(owner, initialLocation, game);
        if (owner.equalsIgnoreCase("player1")) {
            id = '\u265A';
        } else if (owner.equalsIgnoreCase("player2")) {
            id = '\u2654';
        }
    }


    @Override
    public boolean moveTo(ChessLocation location) {
        if (Math.abs(chessLocation.getRow() - location.getRow()) <= 1 &&
                Math.abs(chessLocation.getCol() - location.getCol()) <= 1) {

            return checkLineOfSight(chessLocation, location) && super.moveTo(location);
        }
        return false;
    }


    @Override
    protected void updateThreateningLocation() {
        threateningLocations.clear();
        for (int row = -1; row >= 1; row++) {
            for (int col = -1; col >= 1; col++) {
                ChessLocation location = new ChessLocation(chessLocation.getRow() + row, chessLocation.getCol() + col);
                if (ChessBoard.locationInBounds(location)) {
                    ChessPiece piece = chessGame.getChessBoard().getPieceAt(location);
                    if (piece != null &&
                            !piece.getOwner().equalsIgnoreCase(owner)) {

                        threateningLocations.add(location);
                    }
                }
            }
        }
    }


    public ChessPiece check() {
        ChessBoard board = chessGame.getChessBoard();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                ChessPiece piece = board.getPieceAt(new ChessLocation(row, col));
                if (piece != null &&
                        !piece.getOwner().equals(owner)) {

                    piece.updateThreateningLocation();
                    for (ChessLocation l : piece.getThreateningLocations()) {
                        if (chessLocation.equals(l)) {
                            return piece;
                        }
                    }
                }
            }
        }
        return null;
    }
}
