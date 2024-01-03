import java.lang.reflect.Array;
import java.util.*;
public class TicTacToe {
    private String [][] board;
    private int gridSize;
    private ArrayList<String> validCells;
    private String mark;
    private Scanner scanner;

    public TicTacToe(){
        gridSize = 31;
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

        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board[0].length; j++) {
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
        while (true){
            display();
            String input = getInput();
            int index = Integer.parseInt(input) -1;
            int row = findRow(index);
            int column = findCol(index);
            board[row][column] = mark;
            display();
            boolean isGameOver = gameOverCheck(row, column);
            if (isGameOver){
                String player = mark.equals("x") ? "1" : "2";
                System.out.println("Game over! Player " + player +" wins!");
                break;
            }
            boolean isATie = tieCheck();
            if (isATie){
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
            System.out.println("Player " + currentPlayer +", enter a cell number to mark with " + mark);
            input = scanner.nextLine().trim();
        }
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
        //TODO check neighbours of index. note it cant just be any neighbours, has to form line of 3
        //make methods: checkRight(), checkLeft(), checkUp(), checkDown(), checkDiagonals()
        //if row index is 0 or 1, disregard checkUp, if row is n-1 n-2 disregard checkDown, likewise for left/right for col index
        // real question is how to handle diagonals w/ edge cases.
        if(row != 0 && row != 1){
            boolean upWinner = checkUp(row, col);
        }
        if(row != gridSize -1 && row != gridSize -2){
            boolean downWinner = checkDown(row, col);
        }
        return false;
    }

    public boolean checkUp(int row, int col){
        return board[row][col].equals(board[row - 1][col]) && board[row][col].equals(board[row - 2][col]);
    };

    public boolean checkDown(int row, int col){
        return board[row][col].equals(board[row + 1][col]) && board[row][col].equals(board[row+2][col]);
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

