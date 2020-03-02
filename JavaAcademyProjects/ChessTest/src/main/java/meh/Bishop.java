package meh;

public class Bishop extends ChessPiece {


    public Bishop(String owner, ChessLocation initialLocation, ChessGame game) {
        super(owner, initialLocation, game);
        if (owner.equalsIgnoreCase("player1")) {
            id = '\u265D';
        } else if (owner.equalsIgnoreCase("player2")) {
            id = '\u2657';
        }
    }


    @Override
    public boolean moveTo(ChessLocation location) {
        if (Math.abs(chessLocation.getRow() - location.getRow()) ==
                Math.abs(chessLocation.getCol() - location.getCol())) {

            return checkLineOfSight(chessLocation, location) && super.moveTo(location);
        }
        return false;
    }


    @Override
    protected void updateThreateningLocation() {
        threateningLocations.clear();
        super.updateDiagonal(1, 1);
        super.updateDiagonal(-1, 1);
        super.updateDiagonal(1, -1);
        super.updateDiagonal(-1, -1);
    }
}
