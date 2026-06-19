public class Main {
    public static void main(String[] args) {
Board board = new Board();

board.placePiece(
        new Position(6, 7),
        new Pawn(Color.BLACK));

board.placePiece(
        new Position(7, 4),
        new King(Color.WHITE));

board.placePiece(
        new Position(0, 4),
        new King(Color.BLACK));

Game game = new Game(board);

// Make it Black's turn
game.makeMove(
        new Move(
                new Position(7, 4),
                new Position(7, 3)
        )
);

System.out.println("Before:");
System.out.println(game);

System.out.println(
        game.makeMove(
                new Move(
                        new Position(6, 7),
                        new Position(7, 7)
                )
        )
);

System.out.println("After:");
System.out.println(game);

Position p = new Position(7, 7);

Piece piece = board.getPiece(p);

System.out.println(
        piece instanceof Queen
);
    }
}