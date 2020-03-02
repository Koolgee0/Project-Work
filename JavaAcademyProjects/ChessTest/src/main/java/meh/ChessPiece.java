package meh;

import java.util.ArrayList;


public abstract class ChessPiece implements ChessPieceInterface {

    protected ChessGame chessGame;
    protected String owner;
    protected ChessLocation chessLocation;
    protected char id;
    protected ArrayList<ChessLocation> threateningLocations;

    protected abstract void updateThreateningLocation();


    public ChessPiece(String owner, ChessLocation initialLocation, ChessGame game) {
        this.owner = owner;
        chessLocation = null;
        chessGame = game;
        threateningLocations = new ArrayList<>();
        chessGame.getChessBoard().placePieceAt(this, initialLocation);
    }


    protected boolean checkLineOfSight(ChessLocation start, ChessLocation end) {

        if (start.getCol() == end.getCol()) {
            int one = (start.getRow() - end.getRow() < 0) ? 1 : -1;
            for (int row = start.getRow() + one; row < end.getRow(); row += one) {
                if (chessGame.getChessBoard().isPieceAt(row, start.getCol())) {
                    return false;
                }
            }
            return true;
        }


        if (start.getRow() == end.getRow()) {
            int one = (start.getCol() - end.getCol() < 0) ? 1 : -1;
            for (int col = start.getCol() + one; col < end.getCol(); col += one) {
                if (chessGame.getChessBoard().isPieceAt(start.getRow(), col)) {
                    return false;
                }
            }
            return true;
        }


        if (start.getCol() - end.getCol() ==
                start.getRow() - end.getRow()) {

            int one = (start.getRow() - end.getRow() < 0) ? 1 : -1;
            for (int inc = one; Math.abs(inc) < Math.abs(start.getRow() - end.getRow()); inc += one) {
                if (chessGame.getChessBoard().isPieceAt(start.getRow() + inc, start.getCol() + inc)) {
                    return false;
                }
            }
            return true;
        } else if (start.getCol() - end.getCol() * -1 ==
                start.getRow() - end.getCol()) {

            int one = (start.getRow() - end.getRow() < 0) ? 1 : -1;
            int negOne = one * -1;
            for (int inc = one; Math.abs(inc) < Math.abs(start.getRow() - end.getRow()); inc += one) {
                if (chessGame.getChessBoard().isPieceAt(start.getRow() + inc, start.getCol() + (inc * negOne))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }


    protected void updateVertical(int one) {
        ChessLocation location = new ChessLocation(chessLocation.getRow() + one, chessLocation.getCol());
        while (ChessBoard.locationInBounds(location)) {
            ChessPiece piece = chessGame.getChessBoard().getPieceAt(location);
            if (piece != null) {
                if (!piece.getOwner().equalsIgnoreCase(owner)) {
                    threateningLocations.add(location);
                    return;
                } else if (!chessLocation.equals(location)) {
                    threateningLocations.add(new ChessLocation(location.getRow() - one, location.getCol()));
                    return;
                }
            } else {
                location = new ChessLocation(location.getRow() + one, location.getCol());
            }
        }
    }


    protected void updateHorizontal(int one) {
        ChessLocation location = new ChessLocation(chessLocation.getRow(), chessLocation.getCol() + one);
        while (ChessBoard.locationInBounds(location)) {
            ChessPiece piece = chessGame.getChessBoard().getPieceAt(location);
            if (piece != null) {
                if (!piece.getOwner().equalsIgnoreCase(owner)) {
                    threateningLocations.add(location);
                    return;
                } else if (!chessLocation.equals(location)) {
                    threateningLocations.add(new ChessLocation(location.getRow(), location.getCol() - one));
                    return;
                }
            } else {
                location = new ChessLocation(location.getRow(), location.getCol() + one);
            }
        }
    }


    protected void updateDiagonal(int rowOne, int colOne) {
        ChessLocation location = new ChessLocation(chessLocation.getRow() + rowOne, chessLocation.getCol() + colOne);
        while (ChessBoard.locationInBounds(location)) {
            ChessPiece piece = chessGame.getChessBoard().getPieceAt(location);
            if (piece != null) {
                if (!piece.getOwner().equalsIgnoreCase(owner)) {
                    threateningLocations.add(location);
                    return;
                } else if (!chessLocation.equals(location)) {
                    threateningLocations.add(new ChessLocation(location.getRow() - rowOne, location.getCol() - colOne));
                    return;
                }
            } else {
                location = new ChessLocation(location.getRow() + rowOne, location.getCol() + colOne);
            }
        }
    }


    public boolean moveTo(ChessLocation newLocation) {
        ChessBoard board = chessGame.getChessBoard();
        ChessPiece oldPiece = board.getPieceAt(newLocation);

        if (oldPiece == null ||
                !oldPiece.getOwner().equals(owner)) {

            board.placePieceAt(this, newLocation);
            return true;
        }
        return false;
    }


    public ChessLocation getChessLocation() {
        return chessLocation;
    }


    public void setChessLocation(ChessLocation location) {
        chessLocation = location;
    }


    public String getOwner() {
        return owner;
    }


    public char getId() {
        return id;
    }


    public ArrayList<ChessLocation> getThreateningLocations() {
        return threateningLocations;
    }
}
