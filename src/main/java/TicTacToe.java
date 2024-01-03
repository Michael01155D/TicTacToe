import java.lang.reflect.Array;
import java.util.*;
public class TicTacToe {
    private String [][] board;
    private int gridSize;
    private ArrayList<String> validCells;
    private String mark;
    private Scanner scanner;

    public TicTacToe(int gridSize){
        this.gridSize = gridSize;
        validCells = new ArrayList<>();
        board = new String[gridSize][gridSize];
        mark = "x";
        scanner = new Scanner(System.in);
        makeNewBoard();
    }

    public void display() {
        String border = "";
        for (int i = 0; i < gridSize; i++){
            border += "-------";
        }
        border+= "----";
        System.out.println(border);

        for (int i = 0; i < gridSize; i++) {
            System.out.print("| ");
            for (int j = 0; j < gridSize; j++) {
                printCell(board[i][j].length(), i, j); //adjust spacing btwn num and brackets depending on # digits
            }
            System.out.println(" |");
        }
        System.out.println(border);
    }

    public void printCell(int digits, int i, int j){
        if (digits == 3){
            System.out.print("[ " +board[i][j]+" ]");
        }
        if (digits == 1) {
            System.out.print("[  "+ board[i][j] + "  ]");
        }
        if (digits == 2){
            System.out.print("[  " + board[i][j] + " ]");
        }
    }


    public void makeNewBoard(){
        board = new String[gridSize][gridSize];
        validCells.clear();
        int cellNum = 1;
        for (int i = 0; i < gridSize; i++){
            for (int j = 0; j < gridSize; j++){
                board[i][j] = ""+cellNum;
                validCells.add(""+cellNum);
                cellNum++;
            }
        }
    }

    public void play(){
        System.out.println();
        if (gridSize > 31 || gridSize < 3){
            System.out.println("Invalid board size, please choose between 3 and 31");
            return;
        }
        while (true){
            String input = getInput();
            int index = Integer.parseInt(input) -1;
            int row = findRow(index);
            int column = findCol(index);
            board[row][column] = mark;
            boolean isGameOver = gameOverCheck(row, column);
            if (isGameOver){
                String player = mark.equals("x") ? "1" : "2";
                display();
                System.out.println("Game over! Player " + player +" wins!");
                break;
            }
            boolean isATie = tieCheck();
            if (isATie){
                display();
                System.out.println("Game over! It's a tie. :(");
                break;
            }
            changePlayer();
        }
    }

    public String getInput(){
        String currentPlayer = mark.equals("x") ? "1" : "2";
        String input = "";
        while (!(validCells.contains(input))) {
            display();
            System.out.println("Player " + currentPlayer +", enter a valid cell number to mark with " + mark);
            input = scanner.nextLine().trim();
        }
        validCells.remove(input);
        return input;
    }

    public int findRow(int index){
        /*Leaving this in to remind myself of process I took to figure it out:
        find the pattern
            1 2 3 4
            5 6 7 8
            9 10 11 12
           13 14 15 16
        exs: index = 3, then cell is @ [0][3]
             index = 12, then cell is @ [3][0]  i = index / n, j = index % n  if i >= n
             index = 5, then cell is @ [1][1], i = index /n; j = index % n if i >= n
             */
     return index >= gridSize ? index / gridSize : 0;
    }

    public int findCol(int index){
        return index >= gridSize ? index % gridSize : index;
    }

    public boolean gameOverCheck(int row, int col){
        // If valid, check: above 2 cells
        if(row != 0 && row != 1){
            if (checkUp(row,col)){
                return true;
            }
        }
        //below 2 cells
        if(row != gridSize -1 && row != gridSize -2){
            if (checkDown(row, col)){
                return true;
            }
        }
        //left 2 cells
        if (col != 0 && col != 1){
            if (checkLeft(row, col)){
                return true;
            }
        }
        //right 2 cells
        if (col != gridSize -1 && col != gridSize -2){
            if (checkRight(row, col)){
                return true;
            }
        }
        //left and right cells
        if (col != gridSize -1 && col != 0){
            if (checkLeftRight(row, col)){
                return true;
            }
        }
        //above and below cells
        if (row != gridSize -1 && row != 0){
            if (checkUpDown(row, col)){
                return true;
            }
        }
        //if none of the above, check for diagonal win
        return checkDiagonals(row, col);
    }

    public boolean checkUp(int row, int col){
        return board[row][col].equals(board[row - 1][col]) && board[row][col].equals(board[row - 2][col]);
    };

    public boolean checkDown(int row, int col){
        return board[row][col].equals(board[row + 1][col]) && board[row][col].equals(board[row+2][col]);
    }

    public boolean checkLeft(int row, int col){
        return board[row][col].equals(board[row][col -1]) && board[row][col].equals(board[row][col - 2]);
    }

    public boolean checkRight(int row, int col){
        return board[row][col].equals(board[row][col +1]) && board[row][col].equals(board[row][col +2]);
    }

    public boolean checkLeftRight(int row, int col){
        return board[row][col].equals(board[row][col -1]) && board[row][col].equals(board[row][col+1]);
    }

    public boolean checkUpDown(int row, int col){
        return board[row][col].equals(board[row -1][col]) && board[row][col].equals(board[row +1][col]);
    }

    public boolean checkDiagonals(int row, int col){
        int last = gridSize - 1;

        //cell can be bottom of diagonal going up-right if: it isnt on top row, 2nd top row, rightmost col, 2nd rightmost col
        boolean topRightPossible = row != 0 && row != 1 && col != last && col != last -1;

        //cell can be bottom of a diagonal going up-left  if: it isnt top row, 2nd top, leftmost col, 2nd leftmost col
        boolean topLeftPossible = row != 0 && row != 1 && col != 0 && col != 1;

        //cell can be the top of a diagonal going down-right if: it isnt bot row, 2nd bot row, rightmost col, 2nd rightmost col
        boolean botRightPossible = row != last && row != last -1 && col != last && col != last -1;

        //cell can be the top of a diagonal going down-left if: it isnt bot, 2nd bot row, leftmost col, 2nd leftmost col
        boolean botLeftPossible = row != last && row != last - 1 && col != 0 && col != 1;

        //cell could be middle of a diagonal if: it isn't on top row, bottom row, leftmost col, or rightmost col
        boolean middleDiagonalPossible = row != 0 && row != last && col != 0 && col != last;

        /*  possible diagonals:
        a) 2 upper-left,
        b) 2 upper-right,
        c) 2 lower-left,
        d) 2 lower-right,
        e) 1 upper-left 1 lower-right,
        f) 1 upper-right 1 lower-left

        //if top row, a b e f cant be checked
        //if 2nd top row, a b cant be checked
        // if bot row, c d e f cant be checked
        // if 2nd from bot row, c d cant be checked
        // if leftmost col, a c e f  cant be checked
        // if 2nd leftmost col, a c cant be checked
        // if rightmost col, b d e f cant be checked
        // if 2nd rightmost col, b d cant be checked

        ^ef are redundant, can combine to one variable: middleDiagonal
        */


        if (topRightPossible && board[row][col].equals(board[row - 1][col+1]) && board[row][col].equals(board[row -2][col +2])){
            return true;
        }
        if (topLeftPossible && board[row][col].equals(board[row -1][col -1]) && board[row][col].equals(board[row-2][col-2]) ){
            return true;
        }

        if (botRightPossible && board[row][col].equals(board[row+1][col +1]) && board[row][col].equals(board[row+2][col+2])){
            return true;
        }

        if (botLeftPossible && board[row][col].equals(board[row+1][col-1]) && board[row][col].equals(board[row+2][col-2])){
            return true;
        }

        if (middleDiagonalPossible){

            //above left & below right  are same
            if (board[row][col].equals(board[row+1][col+1]) && board[row][col].equals(board[row -1][col-1])){
                return true;
            }
            // if above right & below left are same
            if (board[row][col].equals(board[row-1][col+1]) && board[row][col].equals(board[row+1][col-1])){
                return true;
            }
        }

        return false;
    }

    public boolean tieCheck(){
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                if(!(board[i][j].equals("x") || board[i][j].equals("o"))){
                    return false;
                }
            }
        }
        return true;
    }

    public void changePlayer(){
        mark = mark.equals("x") ? "o" : "x";
    }


}

