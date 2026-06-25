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

    private static PromotionType parsePromotionType(String piece){
        if(piece == null){
            throw new IllegalArgumentException("Promotion piece cannot be null");
        }

        piece = piece.trim().toLowerCase();

        switch (piece) {
            case "q":
                return PromotionType.QUEEN;
            case "r":
                return PromotionType.ROOK;
            case "b":
                return PromotionType.BISHOP;
            case "n": 
                return PromotionType.KNIGHT;
            default:
                throw new IllegalArgumentException("Invalid promotion piece. Use q, r, b or n");
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            boolean flip = (game.getCurrentTurn() == Color.BLACK);
            System.out.println(game.getBoardRepresentation(flip));
            System.out.println("Current Turn: "+ game.getCurrentTurn()+ "\n");
            System.out.print(game.getCurrentTurn()+" to move: ");
            
            String input = scanner.nextLine();
            System.out.println();
            System.out.println("----------------------");
            System.out.println();

            if(input.equalsIgnoreCase("quit")){
                break;
            }

            String[] parts = input.trim().split("\\s+");
            if(parts.length != 2 && parts.length != 3){
                System.out.println("Invalid input. Give input as:");
                System.out.println("e2 e4");
                System.out.println("or");
                System.out.println("e7 e8 q");
                System.out.println();
                continue;
            }

            try{
                Position from = parsePosition(parts[0]);
                Position to = parsePosition(parts[1]);
    
                Move move = new Move(from, to);

                boolean success;

                if(parts.length == 2){
                    success = game.makeMove(move);
                }
                else{
                    PromotionType promotionType =
                            parsePromotionType(parts[2]);

                    success = game.makeMove(
                            move,
                            promotionType
                    );
                }

                if(!success){
                    System.out.println("Illegal move");
                    System.out.println();
                }
                else{
                    //move has been made successfully and turns switched
                    Color currentTurn = game.getCurrentTurn();
                    boolean afterMoveChangeFlip = currentTurn == Color.BLACK;
                    Color enemy = currentTurn.opposite();
                    if(game.isCheckmate(currentTurn)){
                        System.out.println(game.getBoardRepresentation(afterMoveChangeFlip));
                        System.out.println("Checkmate! " + enemy + " wins!");
                        System.out.println();
                        break;
                    }
                    else if(game.isStalemate(currentTurn)){
                        System.out.println(game.getBoardRepresentation(afterMoveChangeFlip));
                        System.out.println("Stalemate! It's a Draw!");
                        System.out.println();
                        break;
                    }
                    else if(game.isInCheck(currentTurn)){
                        System.out.println(currentTurn + " King in Check!");
                        System.out.println();
                    }
                }
            }
            //* We are only catching IllegalArgument exception and not illegal
            //* state exception because former means that user did something 
            //* wrong, so program should not break and should ask him to fix.
            //* but the latter means that the engine is wrong and programmer made a mistake
            //* ,so we want it to crash so we can fix it.
            catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
                System.out.println();
            }

        }

        scanner.close();
    }
}