
public class Main {
    public static void main(String[] args) {
    
        if (args.length == 0){
            TicTacToe game = new TicTacToe(3);
            game.play();
        }

        else if (args.length == 1){
            try {
                int gridSize = Integer.parseInt(args[0]);
                TicTacToe game = new TicTacToe(gridSize);
                game.play();
            } catch (Exception e){
                System.err.println("Argument" + args[0] + " must be an integer between 3 and 31.");
                System.exit(1);
            }
        }
        else if (args.length == 2 && args[1].trim().equalsIgnoreCase("ai")){
            try {
                int gridSize = Integer.parseInt(args[0]);
                TicTacToe game = new TicTacToe(gridSize, true);
                game.play();
            } catch (Exception e){
                System.err.println("Argument" + args[0] + " must be an integer between 3 and 31.");
                System.exit(1);
            }
        }
    }


}