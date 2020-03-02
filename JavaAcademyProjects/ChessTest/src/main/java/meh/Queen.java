package meh;

public class Queen extends ChessPiece {


    public Queen(String owner, ChessLocation initialLocation, ChessGame game) {
        super(owner, initialLocation, game);
        if (owner.equalsIgnoreCase("player1")) {
            id = '\u265B';
        } else if (owner.equalsIgnoreCase("player2")) {
            id = '\u2655';
        }
    }


    @Override
    public boolean moveTo(ChessLocation location) {
        return checkLineOfSight(chessLocation, location) && super.moveTo(location);
    }


    @Override
    protected void updateThreateningLocation() {
        threateningLocations.clear();

        super.updateVertical(1);
        super.updateVertical(-1);

        super.updateHorizontal(1);
        super.updateHorizontal(-1);

        super.updateDiagonal(1, 1);
        super.updateDiagonal(-1, 1);
        super.updateDiagonal(1, -1);
        super.updateDiagonal(-1, -1);
    }
}
