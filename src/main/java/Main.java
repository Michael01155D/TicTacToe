
public class Main {
    public static void main(String[] args) {

        if (args.length == 0){
            TicTacToe game = new TicTacToe(3);
            game.play();
        }

        if (args.length > 0){
            try {
                int gridSize = Integer.parseInt(args[0]);
                TicTacToe game = new TicTacToe(gridSize);
                game.play();
            } catch (Exception e){
                System.err.println("Argument" + args[0] + " must be an integer between 3 and 31.");
                System.exit(1);
            }
        }
    }


}