package meh;

public class Rook extends ChessPiece {


    public Rook(String owner, ChessLocation initialLocation, ChessGame game) {
        super(owner, initialLocation, game);
        if (owner.equalsIgnoreCase("player1")) {
            id = '\u265C';
        } else if (owner.equalsIgnoreCase("player2")) {
            id = '\u2656';
        }
    }


    @Override
    public boolean moveTo(ChessLocation location) {
        if ((chessLocation.getRow() == location.getRow()) !=
                (chessLocation.getCol() == location.getCol())) {

            return checkLineOfSight(chessLocation, location) && super.moveTo(location);
        }
        return false;
    }


    @Override
    protected void updateThreateningLocation() {
        threateningLocations.clear();

        super.updateVertical(1);
        super.updateVertical(-1);
        super.updateHorizontal(1);
        super.updateHorizontal(-1);
    }
}
