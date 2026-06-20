public class Main {
    public static void main(String[] args) {
Board board = new Board();

board.placePiece(
    new Position(1, 0),
    new Pawn(Color.WHITE)
);

board.placePiece(
    new Position(0, 1),
    new Rook(Color.BLACK)
);

board.placePiece(
    new Position(7, 4),
    new King(Color.WHITE)
);

board.placePiece(
    new Position(0, 4),
    new King(Color.BLACK)
);

Move move =
        new Move(
            new Position(1, 0),
            new Position(0, 1)
        );

Piece movingPiece =
        board.getPiece(move.getFrom());

Piece capturedPiece =
        board.getPiece(move.getTo());

board.makeMove(move);

// Promote
board.replacePiece(
    move.getTo(),
    new Queen(Color.WHITE)
);

System.out.println("After promotion capture:");
System.out.println(board);

// Undo
board.undoMove(
    move,
    capturedPiece,
    movingPiece
);

System.out.println("After undo:");
System.out.println(board);

System.out.println(
        board.getPiece(
            new Position(1, 0)
        ) instanceof Pawn
);

System.out.println(
        board.getPiece(
            new Position(0, 1)
        ) instanceof Rook
);
    }
}