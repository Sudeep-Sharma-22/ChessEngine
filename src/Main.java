import java.util.Scanner;
public class Main {
    private static Position parsePosition(String square){
        if(square == null){
            throw new IllegalArgumentException("Square cannot be null");
        }

        square = square.trim().toLowerCase();

        if(square.length() != 2){
            throw new IllegalArgumentException("Invalid Square");
        }
        
        char file = square.charAt(0);
        char rank = square.charAt(1);

        if(file < 'a' || file > 'h'){
            throw new IllegalArgumentException("Invalid File");
        }
        if(rank < '1' || rank > '8'){
            throw new IllegalArgumentException("Invalid Rank");
        }

        int col = file - 'a';
        int row = 8 - (rank - '0');

        return new Position(row, col);
    }

    public static void main(String[] args) {
        Game game = new Game();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println(game);
            System.out.print("Enter move: ");

            String input = scanner.nextLine();

            String[] parts = input.trim().split("\\s+");
            if(parts.length != 2){
                System.out.println("Invalid input. Give input as: e2 e4");
                System.out.println();
                continue;
            }

            try{
                Position from = parsePosition(parts[0]);
                Position to = parsePosition(parts[1]);
    
                Move move = new Move(from, to);

                boolean success = game.makeMove(move);
                if(!success){
                    System.out.println("Illegal move");
                    System.out.println();
                }
            }
            //* We are only catching IllegalArgument exception and not illegal
            //* state exception because former means that user did something 
            //* wrong, so program should not break and should ask him to fix.
            //* but the latter means that the engine is wrong and programmer made a mistake
            //* ,so we want it to crash so we can fix it.
            catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }

        }
    }
}