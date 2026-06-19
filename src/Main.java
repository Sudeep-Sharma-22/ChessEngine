public class Main {
    public static void main(String[] args) {
Game game = new Game();

game.makeMove(new Move(
        new Position(6,5),
        new Position(5,5))); // f3

game.makeMove(new Move(
        new Position(1,4),
        new Position(3,4))); // e5

game.makeMove(new Move(
        new Position(6,6),
        new Position(4,6))); // g4

game.makeMove(new Move(
        new Position(0,3),
        new Position(4,7))); // Qh4#

System.out.println(game.isInCheck(Color.WHITE));
System.out.println(game.isCheckmate(Color.WHITE));
System.out.println(game.isStalemate(Color.WHITE));
    }
}