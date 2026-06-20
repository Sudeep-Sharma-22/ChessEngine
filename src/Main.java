public class Main {
    public static void main(String[] args) {
        Game game = new Game();

game.makeMove(new Move(
        new Position(6,4),
        new Position(4,4)
));

game.makeMove(new Move(
        new Position(1,4),
        new Position(3,4)
));

game.makeMove(new Move(
        new Position(7,6),
        new Position(5,5)
));

game.makeMove(new Move(
        new Position(0,1),
        new Position(2,2)
));

game.makeMove(new Move(
        new Position(7,5),
        new Position(4,2)
));

game.makeMove(new Move(
        new Position(0,6),
        new Position(2,5)
));

System.out.println(
        game.makeMove(
                new Move(
                        new Position(7,4),
                        new Position(7,6)
                )
        )
);

System.out.println(game);

game.makeMove(new Move(
        new Position(1,6),
        new Position(2,6)
));

game.makeMove(new Move(
        new Position(6,0),
        new Position(5,0)
));

game.makeMove(new Move(
        new Position(0,5),
        new Position(1,6)
));

game.makeMove(new Move(
        new Position(6,1),
        new Position(5,1)
));

System.out.println(
        game.makeMove(
                new Move(
                        new Position(0,4),
                        new Position(0,6)
                )
        )
);

System.out.println(game);
    }
}