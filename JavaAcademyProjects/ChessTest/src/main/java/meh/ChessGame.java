package meh;


public class ChessGame {

    private ChessBoard chessBoard;
    private King player1King;
    private King player2King;


    public ChessGame() {
        chessBoard = new ChessBoard();
        setupTeam(0, "player1");
        setupTeam(7, "player2");
    }


    private void setupTeam(int side, String player) {
        int one = (side > 0) ? -1 : 1;
        int colIncerment = 0;


        ChessPiece r1 = new Rook(player, new ChessLocation(side, colIncerment), this);
        ChessPiece r2 = new Rook(player, new ChessLocation(side, 7 - colIncerment), this);
        colIncerment += 1;


        ChessPiece n1 = new Knight(player, new ChessLocation(side, colIncerment), this);
        ChessPiece n2 = new Knight(player, new ChessLocation(side, 7 - colIncerment), this);
        colIncerment += 1;


        ChessPiece b1 = new Bishop(player, new ChessLocation(side, colIncerment), this);
        ChessPiece b2 = new Bishop(player, new ChessLocation(side, 7 - colIncerment), this);
        colIncerment += 1;


        if (player.equalsIgnoreCase("player1")) {
            player1King = new King(player, new ChessLocation(side, colIncerment), this);
        } else {
            player2King = new King(player, new ChessLocation(side, colIncerment), this);
        }

        ChessPiece q = new Queen(player, new ChessLocation(side, 7 - colIncerment), this);

        // Pawns
        for (int i = 0; i < 8; i++) {
            ChessPiece p = new Pawn(player, new ChessLocation(side + one, i), this);
        }
    }


    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public King getPlayer1King() {
        return player1King;
    }

    public King getPlayer2King() {
        return player2King;
    }
}
