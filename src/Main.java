public class Main {
    public static void main(String[] args) {
        Board board = new Board();

        board.placePiece(
                new Position(0, 0),
                new Rook(Color.BLACK));

        board.placePiece(
                new Position(7, 4),
                new King(Color.WHITE));

        board.placePiece(
                new Position(6, 3),
                new Pawn(Color.WHITE));

        System.out.println(board);
    }
}