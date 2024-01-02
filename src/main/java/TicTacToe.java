import java.lang.reflect.Array;
import java.util.*;
public class TicTacToe {
    private String [][] board;
    private int rows;
    private int cols;
    private int cellNum;
    private ArrayList<String> validCells;
    private String mark;
    private Scanner scanner;

    public TicTacToe(){
        rows = 3;
        cols = 3;
        validCells = new ArrayList<>();
        board = new String[rows][cols];
        mark = "X";
        scanner = new Scanner(System.in);
        makeNewBoard();
    }
        //
    public void display() {
        String top = "--";
        for (int i = 0; i < rows; i++){
            top += "---";
        }
        top+= "--";
        System.out.println(top);

        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board[0].length; j++) {
                System.out.print("["+board[i][j]+"]");
            }
            System.out.println(" |");
        }
        System.out.println(top);
    }
    public void makeNewBoard(){
        board = new String[rows][cols];
        validCells.clear();
        cellNum = 1;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                board[i][j] = ""+cellNum;
                validCells.add(""+cellNum);
                cellNum++;
            }
        }
    }

    public String turn(){
        String markToApply = mark;
        mark = mark.equals("X") ? "O" : "X";
        return markToApply;
    }

    public void play(){
        while (true){
            display();
            String currentPlayer = mark.equals("X") ? "1" : "2";
            String input = "";
            while (!(validCells.contains(input))) {
                System.out.println("Player " + currentPlayer +", enter a cell number to mark with " + mark);
                input = scanner.nextLine().trim();
            }
        }
    }
}

