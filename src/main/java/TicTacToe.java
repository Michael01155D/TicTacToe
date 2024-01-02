import java.lang.reflect.Array;
import java.util.*;
public class TicTacToe {
    private String [][] board;
    private int gridSize;
    private int cellNum;
    private ArrayList<String> validCells;
    private String mark;
    private Scanner scanner;

    public TicTacToe(){
        gridSize = 4;
        validCells = new ArrayList<>();
        board = new String[gridSize][gridSize];
        mark = "x";
        scanner = new Scanner(System.in);
        makeNewBoard();
    }
        //
    public void display() {
        String border = "";
        for (int i = 0; i < gridSize; i++){
            border += "--------";
        }
        border+= "--";
        System.out.println(border);

        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board[0].length; j++) {
                printCell(board[i][j].length(), i, j);
            }
            System.out.println(" |");
        }
        System.out.println(border);
    }
    public void makeNewBoard(){
        board = new String[gridSize][gridSize];
        validCells.clear();
        cellNum = 1;
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
            //find the pattern
            ///    1 2 3 4
            //     5 6 7 8
            //     9 10 11 12
            //     13 14 15 16

            //exs: index = 3, then cell is @ [0][3]
            //     index = 12, then cell is @ [3][0]  i = index / n, j = index % n ? if i > n
            //     index = 5, then cell is @ [1][1], i = index /n; j = index % n ?
            mark = mark.equals("x") ? "o" : "x";

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
     return index >= gridSize ? index / gridSize : 0;
    }

    public int findCol(int index){
        return index >= gridSize ? index % gridSize : index;
    }

    public boolean checkWinner(String chosenTile){
        int index = Integer.parseInt(chosenTile) - 1;
        //check neighbours of index. if 2 neighbours have same mark, gg.
        return false;
    }

    public void printCell(int digits, int i, int j){
        if (digits == 1) {
            System.out.print("[  "+ board[i][j] + "  ]");
        } else {
            System.out.print("[  " + board[i][j] + " ]");
        }
    }
}

