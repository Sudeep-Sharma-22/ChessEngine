public class Main {
    public static void main(String[] args) {
Game game = new Game();

game.makeMove(
    new Move(
        new Position(6,3),
        new Position(4,3)
    )
);

game.makeMove(
    new Move(
        new Position(1,0),
        new Position(2,0)
    )
);

System.out.println(
    game.makeMove(
        new Move(
            new Position(7,2),
            new Position(5,4)
        )
    )
);

System.out.println(game);
    }
}