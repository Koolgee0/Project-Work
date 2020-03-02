package meh;


public class ChessLocation {
    private int row;
    private int col;


    public ChessLocation(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof ChessLocation) {
            ChessLocation l = (ChessLocation) obj;
            return (row == l.getRow() &&
                    col == l.getCol());
        }
        return false;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
