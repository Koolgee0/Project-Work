package meh;

public class Knight extends ChessPiece {


    public Knight(String owner, ChessLocation initialLocation, ChessGame game) {
        super(owner, initialLocation, game);
        if (owner.equalsIgnoreCase("player1")) {
            id = '\u265E';
        } else if (owner.equalsIgnoreCase("player2")) {
            id = '\u2658';
        }
    }


    @Override
    public boolean moveTo(ChessLocation location) {
        if (Math.abs(chessLocation.getRow() - location.getRow()) == 2 &&
                Math.abs(chessLocation.getCol() - location.getCol()) == 1) {

            return super.moveTo(location);
        } else if (Math.abs(chessLocation.getRow() - location.getRow()) == 1 &&
                Math.abs(chessLocation.getCol() - location.getCol()) == 2) {

            return super.moveTo(location);
        }
        return false;
    }


    @Override
    protected void updateThreateningLocation() {
        int[] rowMoves = {-2, -1, 1, 2, -2, -1, 1, 2};
        int[] colMoves = {1, 2, 2, 1, -1, -2, -2, -1};

        threateningLocations.clear();
        for (int i = 0; i < 8; i++) {
            ChessLocation location = new ChessLocation(rowMoves[i], colMoves[i]);
            if (ChessBoard.locationInBounds(location)) {
                ChessPiece piece = chessGame.getChessBoard().getPieceAt(location);

                if (piece != null &&
                        !piece.getOwner().equals(owner)) {

                    threateningLocations.add(location);
                }
            }
        }
    }
}
