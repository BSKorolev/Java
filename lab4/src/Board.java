import Figures.Bishop;
import Figures.Figure;
import Figures.King;
import Figures.Knight;
import Figures.Pawn;
import Figures.Queen;
import Figures.Rook;

import java.util.ArrayList;

public class Board {

    private Figure fields[][] = new Figure[8][8];
    private ArrayList<String> takeWhite = new ArrayList<>(16);
    private ArrayList<String> takeBlack = new ArrayList<>(16);
    private char colorGaming;

    public Board() {
        init();
    }

    public void init() {
        this.fields[0] = new Figure[]{
                new Rook("R", 'w'), new Knight("N", 'w'),
                new Bishop("B", 'w'), new Queen("Q", 'w'),
                new King("K", 'w'), new Bishop("B", 'w'),
                new Knight("N", 'w'), new Rook("R", 'w')
        };
        this.fields[1] = new Figure[]{
                new Pawn("P", 'w'), new Pawn("P", 'w'),
                new Pawn("P", 'w'), new Pawn("P", 'w'),
                new Pawn("P", 'w'), new Pawn("P", 'w'),
                new Pawn("P", 'w'), new Pawn("P", 'w'),
        };

        this.fields[7] = new Figure[]{
                new Rook("R", 'b'), new Knight("N", 'b'),
                new Bishop("B", 'b'), new Queen("Q", 'b'),
                new King("K", 'b'), new Bishop("B", 'b'),
                new Knight("N", 'b'), new Rook("R", 'b')
        };
        this.fields[6] = new Figure[]{
                new Pawn("P", 'b'), new Pawn("P", 'b'),
                new Pawn("P", 'b'), new Pawn("P", 'b'),
                new Pawn("P", 'b'), new Pawn("P", 'b'),
                new Pawn("P", 'b'), new Pawn("P", 'b'),
        };
    }

    public String getCell(int row, int col) {
        Figure figure = this.fields[row][col];
        if (figure == null) {
            return "    ";
        }
        return " " + figure.getColor() + figure.getName() + " ";
    }

    public ArrayList<String> getTakeWhite() {
        return takeWhite;
    }

    public ArrayList<String> getTakeBlack() {
        return takeBlack;
    }

    public boolean move_figure(int row1, int col1, int row2, int col2) {

        Figure figure = this.fields[row1][col1];

        if (figure.canMove(row1, col1, row2, col2) && this.fields[row2][col2] == null && isWayFree(row1, col1)) {
            System.out.println("move");
            this.fields[row2][col2] = figure;
            this.fields[row1][col1] = null;
            return true;
        } else if (figure.canAttack(row1, col1, row2, col2) && this.fields[row2][col2] != null && this.fields[row2][col2].getColor() != this.fields[row1][col1].getColor() && isWayFree(row1, col1)) {
            System.out.println("attack");
            switch (this.fields[row2][col2].getColor()) {
                case 'w':
                    this.takeWhite.add(this.fields[row2][col2].getColor() + this.fields[row2][col2].getName());
                    break;
                case 'b':
                    this.takeBlack.add(this.fields[row2][col2].getColor() + this.fields[row2][col2].getName());
                    break;
            }
            this.fields[row2][col2] = figure;
            this.fields[row1][col1] = null;
            return true;
        }
        return false;
    }

    public boolean isWayFree(int row, int col) {
        Figure figure = fields[row][col];
        if (figure != null) {
            if (!isWayFreeForFigure(figure, row, col)) {
                return false;
            }
        }
        return true;
    }

    private boolean isWayFreeForFigure(Figure figure, int row, int col) {
        if (figure instanceof Pawn) {
            return isPawnPathFree(figure, row, col);
        }
        if (figure instanceof Bishop) {
            return isDiagonalPathFree(figure, row, col);
        }
        if (figure instanceof Rook) {
            return isHorizontalPathFree(figure, row, col) && isVerticalPathFree(figure, row, col);
        } else if (figure instanceof Queen) {
            return isHorizontalPathFree(figure, row, col) && isVerticalPathFree(figure, row, col) && isDiagonalPathFree(figure, row, col);
        }
        return true;
    }

    private boolean isPawnPathFree(Figure figure, int row, int col) {
        switch (figure.getColor()) {
            case 'w':
                if (fields[row + 1][col] != null) {
                    return false;
                }
                break;
            case 'b':
                if (fields[row - 1][col] != null) {
                    return false;
                }
                break;
        }
        return true;
    }

    private boolean isDiagonalPathFree(Figure figure, int row, int col) {
        for (int i = 0; row + i < 8 && col + i < 8; i++) {
            if (fields[row + i][col + i] != null) {
                return false;
            }
        }
        for (int i = 0; row + i < 8 && col - i >= 0; i++) {
            if (fields[row + i][col - i] != null) {
                return false;
            }
        }
        for (int i = 0; row - i >= 0 && col + i < 8; i++) {
            if (fields[row - i][col + i] != null) {
                return false;
            }
        }
        for (int i = 0; row - i >= 0 && col - i >= 0; i++) {
            if (fields[row - i][col - i] != null) {
                return false;
            }
        }
        return true;
    }

    private boolean isHorizontalPathFree(Figure figure, int row, int col) {
        for (int i = col - 1; i >= 0; i--) {
            if (fields[row][i] != null) {
                return false;
            }
        }
        for (int i = col + 1; i < 8; i++) {
            if (fields[row][i] != null) {
                return false;
            }
        }
        return true;
    }

    private boolean isVerticalPathFree(Figure figure, int row, int col) {
        for (int i = row - 1; i >= 0; i--) {
            if (fields[i][col] != null) {
                return false;
            }
        }
        for (int i = row + 1; i < 8; i++) {
            if (fields[i][col] != null) {
                return false;
            }
        }
        return true;
    }

    public boolean isCheck() {
        int kingRow = -1;
        int kingCol = -1;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Figure figure = fields[row][col];
                if (figure instanceof King && figure.getColor() == colorGaming) {
                    kingRow = row;
                    kingCol = col;
                    break;
                }
            }
            if (kingRow != -1 && kingCol != -1) {
                break;
            }
        }

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Figure figure = fields[row][col];
                if (figure != null && figure.getColor() != colorGaming && figure.canAttack(row, col, kingRow, kingCol)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isCheckmate() {
        if (!isCheck()) {
            return false;
        }

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Figure figure = fields[row][col];
                if (figure != null && figure.getColor() == colorGaming) {
                    for (int destRow = 0; destRow < 8; destRow++) {
                        for (int destCol = 0; destCol < 8; destCol++) {
                            if (move_figure(row, col, destRow, destCol)) {
                                boolean isStillCheck = isCheck();
                                move_figure(destRow, destCol, row, col);
                                if (!isStillCheck) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
    public void print_board(){
        System.out.println(" +----+----+----+----+----+----+----+----+");
        for(int row = 7; row > -1; row--){
            System.out.print(row+1);
            for(int col = 0; col< 8; col++){
                System.out.print("|"+getCell(row, col));
            }
            System.out.println("|");
            System.out.println(" +----+----+----+----+----+----+----+----+");
        }

        System.out.print("    ");
        for(int col = 0; col < 8; col++){
            char column = (char) ('a' + col);
            System.out.print(column + "    ");
        }

    }
}
