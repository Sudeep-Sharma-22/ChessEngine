public class Tests {
    public static void main(String[] args) {
// Test 1: Initial Board Setup
Game game = new Game();
System.out.println(game);

// Expected:

// r n b q k b n r
// p p p p p p p p
// - - - - - - - -
// - - - - - - - -
// - - - - - - - -
// - - - - - - - -
// P P P P P P P P
// R N B Q K B N R

// Current Turn: WHITE

// Test 2: Valid Pawn Single Move
// Game game = new Game();

// System.out.println(
//     game.makeMove(
//         new Move(
//             new Position(6,4),
//             new Position(5,4)
//         )
//     )
// );

// System.out.println(game);

// Expected:

// true

// Pawn moves to e3.

// Test 3: Valid Pawn Double Move
// Game game = new Game();

// System.out.println(
//     game.makeMove(
//         new Move(
//             new Position(6,4),
//             new Position(4,4)
//         )
//     )
// );

// Expected:

// true

// Test 4: Empty Source Square
// Game game = new Game();

// System.out.println(
//     game.makeMove(
//         new Move(
//             new Position(4,4),
//             new Position(3,4)
//         )
//     )
// );

// Expected:

// false

// Test 5: Move Opponent's Piece
// Game game = new Game();

// System.out.println(
//     game.makeMove(
//         new Move(
//             new Position(1,4),
//             new Position(2,4)
//         )
//     )
// );

// Expected:

// false

// Test 6: Move To Same Square
// new Move(
//     new Position(6,4),
//     new Position(6,4)
// );

// Expected:

// IllegalArgumentException

// Test 7: Knight Jump
// Game game = new Game();

// System.out.println(
//     game.makeMove(
//         new Move(
//             new Position(7,6),
//             new Position(5,5)
//         )
//     )
// );

// Expected:

// true

// Test 8: Bishop Blocked
// Game game = new Game();

// System.out.println(
//     game.makeMove(
//         new Move(
//             new Position(7,5),
//             new Position(4,2)
//         )
//     )
// );

// Expected:

// false

// Test 9: Rook Blocked
// Game game = new Game();

// System.out.println(
//     game.makeMove(
//         new Move(
//             new Position(7,0),
//             new Position(5,0)
//         )
//     )
// );

// Expected:

// false

// Test 10: Queen Blocked
// Game game = new Game();

// System.out.println(
//     game.makeMove(
//         new Move(
//             new Position(7,3),
//             new Position(5,3)
//         )
//     )
// );

// Expected:

// false

// Test 11: Cannot Capture Own Piece
// Game game = new Game();

// System.out.println(
//     game.makeMove(
//         new Move(
//             new Position(7,1),
//             new Position(6,3)
//         )
//     )
// );

// Expected:

// false

// Test 12: Turn Alternation
// Game game = new Game();

// System.out.println(
//     game.makeMove(
//         new Move(
//             new Position(6,4),
//             new Position(4,4)
//         )
//     )
// );

// System.out.println(
//     game.makeMove(
//         new Move(
//             new Position(6,3),
//             new Position(4,3)
//         )
//     )
// );

// Expected:

// true
// false

// Test 13: Pawn Diagonal Capture
// Game game = new Game();

// game.makeMove(
//     new Move(
//         new Position(6,4),
//         new Position(4,4)
//     )
// );

// game.makeMove(
//     new Move(
//         new Position(1,3),
//         new Position(3,3)
//     )
// );

// System.out.println(
//     game.makeMove(
//         new Move(
//             new Position(4,4),
//             new Position(3,3)
//         )
//     )
// );

// Expected:

// true

// Test 14: Move leaving own king in check
// Board board = new Board();

// board.placePiece(
//         new Position(0, 0),
//         new King(Color.BLACK)
// );

// board.placePiece(
//         new Position(0, 4),
//         new Rook(Color.BLACK)
// );

// board.placePiece(
//         new Position(7, 4),
//         new King(Color.WHITE)
// );

// board.placePiece(
//         new Position(7, 0),
//         new Rook(Color.WHITE)
// );

// Game game = new Game(board);

// System.out.println(game);

// System.out.println(
//         game.makeMove(
//                 new Move(
//                         new Position(7, 0),
//                         new Position(6, 0)
//                 )
//         )
// );

// System.out.println(game);

// Expected:

// false

// Test 14) King Moving into check:

// Board board = new Board();

// board.placePiece(
//         new Position(0, 0),
//         new King(Color.BLACK)
// );

// board.placePiece(
//         new Position(0, 4),
//         new Rook(Color.BLACK)
// );

// board.placePiece(
//         new Position(7, 3),
//         new King(Color.WHITE)
// );

// Game game = new Game(board);

// System.out.println(game);

// System.out.println(
//         game.makeMove(
//                 new Move(
//                         new Position(7, 3),
//                         new Position(7, 4)
//                 )
//         )
// );

// System.out.println(game);

// Expected:

// false

// Test 15: StaleMate Position:

// Board board = new Board();

// board.placePiece(
//         new Position(0, 0),
//         new King(Color.BLACK)
// );

// board.placePiece(
//         new Position(2, 2),
//         new King(Color.WHITE)
// );

// board.placePiece(
//         new Position(1, 2),
//         new Queen(Color.WHITE)
// );

// Game game = new Game(board);

// System.out.println(game);

// System.out.println(
//         game.isInCheck(Color.BLACK)
// );

// System.out.println(
//         game.isStalemate(Color.BLACK)
// );

// System.out.println(
//         game.isCheckmate(Color.BLACK)
// );

// Expected:

// false
// true
// false

// Additional Edge Cases :-
// Edge 1: Illegal Knight Move
// Game game = new Game();

// System.out.println(
//     game.makeMove(
//         new Move(
//             new Position(7,1),
//             new Position(6,1)
//         )
//     )
// );

// Expected:

// false

// Edge 2: Pawn Moving Backward
// Game game = new Game();

// game.makeMove(
//     new Move(
//         new Position(6,4),
//         new Position(4,4)
//     )
// );

// game.makeMove(
//     new Move(
//         new Position(1,0),
//         new Position(2,0)
//     )
// );

// System.out.println(
//     game.makeMove(
//         new Move(
//             new Position(4,4),
//             new Position(5,4)
//         )
//     )
// );

// Expected:

// false

// Edge 3: Pawn Double Move After Moving Once
// Game game = new Game();

// game.makeMove(
//     new Move(
//         new Position(6,4),
//         new Position(5,4)
//     )
// );

// game.makeMove(
//     new Move(
//         new Position(1,0),
//         new Position(2,0)
//     )
// );

// System.out.println(
//     game.makeMove(
//         new Move(
//             new Position(5,4),
//             new Position(3,4)
//         )
//     )
// );

// Expected:

// false


// //* 19) initial test */
// Game game = new Game();

// System.out.println(game.isCheckmate(Color.WHITE));
// System.out.println(game.isCheckmate(Color.BLACK));

// System.out.println(game.isStalemate(Color.WHITE));
// System.out.println(game.isStalemate(Color.BLACK));

// // expected :
// // false
// // false
// // false
// // false

// //* 20)Fool's mate */
// Game game = new Game();

// game.makeMove(
//     new Move(
//         new Position(6, 5),
//         new Position(5, 5)
//     )
// ); // f3

// game.makeMove(
//     new Move(
//         new Position(1, 4),
//         new Position(3, 4)
//     )
// ); // e5

// game.makeMove(
//     new Move(
//         new Position(6, 6),
//         new Position(4, 6)
//     )
// ); // g4

// game.makeMove(
//     new Move(
//         new Position(0, 3),
//         new Position(4, 7)
//     )
// ); // Qh4#

// System.out.println(game.isInCheck(Color.WHITE));
// System.out.println(game.isCheckmate(Color.WHITE));
// System.out.println(game.isStalemate(Color.WHITE));

// // Expected :
// // true
// // true
// // false

// //* 21) White Promotion */
// Board board = new Board();

// board.placePiece(
//     new Position(1, 0),
//     new Pawn(Color.WHITE)
// );

// board.placePiece(
//     new Position(7, 4),
//     new King(Color.WHITE)
// );

// board.placePiece(
//     new Position(0, 4),
//     new King(Color.BLACK)
// );

// Game game = new Game(board);

// System.out.println("Before:");
// System.out.println(game);

// System.out.println(
//     game.makeMove(
//         new Move(
//             new Position(1, 0),
//             new Position(0, 0)
//         )
//     )
// );

// System.out.println("After:");
// System.out.println(game);

// Piece piece =
//         board.getPiece(
//             new Position(0, 0)
//         );

// System.out.println(
//         piece instanceof Queen
// );

// // Expected:
// // true
// // true
// // Q - - - k - - -

// //* 22)Black Promotion */
// Board board = new Board();

// board.placePiece(
//     new Position(6, 7),
//     new Pawn(Color.BLACK)
// );

// board.placePiece(
//     new Position(7, 4),
//     new King(Color.WHITE)
// );

// board.placePiece(
//     new Position(0, 4),
//     new King(Color.BLACK)
// );

// Game game = new Game(board);

// // Make it Black's turn
// game.makeMove(
//     new Move(
//         new Position(7, 4),
//         new Position(7, 3)
//     )
// );

// System.out.println(
//     game.makeMove(
//         new Move(
//             new Position(6, 7),
//             new Position(7, 7)
//         )
//     )
// );

// System.out.println(game);

// Piece piece =
//         board.getPiece(
//             new Position(7, 7)
//         );

// System.out.println(
//         piece instanceof Queen
// );

// // Expected:
// // true
// // true
// // - - - K - - - q

// //* 23) Promotion + Undo */
// Board board = new Board();

// board.placePiece(
//     new Position(1, 0),
//     new Pawn(Color.WHITE)
// );

// board.placePiece(
//     new Position(7, 4),
//     new King(Color.WHITE)
// );

// board.placePiece(
//     new Position(0, 4),
//     new King(Color.BLACK)
// );

// Move move = new Move(
//     new Position(1, 0),
//     new Position(0, 0)
// );

// Piece movingPiece =
//         board.getPiece(move.getFrom());

// Piece capturedPiece =
//         board.getPiece(move.getTo());

// boolean originalHasMoved =
//         movingPiece.hasMoved();

// System.out.println("Before:");
// System.out.println(board);

// // Make move
// board.makeMove(move);

// // Simulate promotion
// board.replacePiece(
//     move.getTo(),
//     new Queen(Color.WHITE)
// );

// System.out.println("After promotion:");
// System.out.println(board);

// // Undo
// board.undoMove(
//     move,
//     capturedPiece,
//     movingPiece,
//     originalHasMoved
// );

// System.out.println("After undo:");
// System.out.println(board);

// Piece piece =board.getPiece(
//             new Position(1, 0)
//         );

// System.out.println(
//         piece instanceof Pawn
// );

// System.out.println(
//         board.getPiece(
//             new Position(0, 0)
//         ) == null
// );

// System.out.println(!piece.hasMoved());

// // Expected:
// // Before:
// // - - - - k - - -
// // P - - - - - - -
// // ...
// // - - - - K - - -

// // After promotion:
// // Q - - - k - - -
// // ...
// // - - - - K - - -

// // After undo:
// // - - - - k - - -
// // P - - - - - - -
// // ...
// // - - - - K - - -

// // true
// // true
// // true

// //* 24) Valid White Kingside Castling */ 
// Game game = new Game();

// game.makeMove(new Move(
//         new Position(6,4),
//         new Position(4,4)
// ));

// game.makeMove(new Move(
//         new Position(1,4),
//         new Position(3,4)
// ));

// game.makeMove(new Move(
//         new Position(7,6),
//         new Position(5,5)
// ));

// game.makeMove(new Move(
//         new Position(0,1),
//         new Position(2,2)
// ));

// game.makeMove(new Move(
//         new Position(7,5),
//         new Position(4,2)
// ));

// game.makeMove(new Move(
//         new Position(0,6),
//         new Position(2,5)
// ));

// System.out.println(
//         game.makeMove(
//                 new Move(
//                         new Position(7,4),
//                         new Position(7,6)
//                 )
//         )
// );

// System.out.println(game);
// //* 25) Valid black kingside castling(continue from test 6) */
// game.makeMove(new Move(
//         new Position(1,6),
//         new Position(2,6)
// ));

// game.makeMove(new Move(
//         new Position(6,0),
//         new Position(5,0)
// ));

// game.makeMove(new Move(
//         new Position(0,5),
//         new Position(1,6)
// ));

// game.makeMove(new Move(
//         new Position(6,1),
//         new Position(5,1)
// ));

// System.out.println(
//         game.makeMove(
//                 new Move(
//                         new Position(0,4),
//                         new Position(0,6)
//                 )
//         )
// );

// System.out.println(game);
// // Expected:
// // true

// //* 26) Piece between king and rook */
// Game game = new Game();

// System.out.println(
//         game.makeMove(
//                 new Move(
//                         new Position(7,4),
//                         new Position(7,6)
//                 )
//         )
// );

// // Expected:
// // false

// //* 27) king already moved */
// Game game = new Game();

// game.makeMove(new Move(
//         new Position(6,4),
//         new Position(4,4)
// ));

// game.makeMove(new Move(
//         new Position(1,4),
//         new Position(3,4)
// ));

// game.makeMove(new Move(
//         new Position(7,6),
//         new Position(5,5)
// ));

// game.makeMove(new Move(
//         new Position(0,1),
//         new Position(2,2)
// ));

// game.makeMove(new Move(
//         new Position(7,4),
//         new Position(6,4)
// ));

// game.makeMove(new Move(
//         new Position(0,6),
//         new Position(2,5)
// ));

// game.makeMove(new Move(
//         new Position(6,4),
//         new Position(7,4)
// ));

// game.makeMove(new Move(
//         new Position(1,0),
//         new Position(2,0)
// ));

// game.makeMove(new Move(
//         new Position(7,5),
//         new Position(4,2)
// ));

// game.makeMove(new Move(
//         new Position(1,1),
//         new Position(2,1)
// ));

// System.out.println(
//         game.makeMove(
//                 new Move(
//                         new Position(7,4),
//                         new Position(7,6)
//                 )
//         )
// );

// // Expected :
// // false

// //* 28) Rook already moved */
// Game game = new Game();

// game.makeMove(new Move(
//         new Position(6,7),
//         new Position(5,7)
// ));

// game.makeMove(new Move(
//         new Position(1,0),
//         new Position(2,0)
// ));

// game.makeMove(new Move(
//         new Position(7,6),
//         new Position(5,5)
// ));

// game.makeMove(new Move(
//         new Position(1,1),
//         new Position(2,1)
// ));

// game.makeMove(new Move(
//         new Position(7,7),
//         new Position(6,7)
// ));

// game.makeMove(new Move(
//         new Position(1,2),
//         new Position(2,2)
// ));

// game.makeMove(new Move(
//         new Position(6,7),
//         new Position(7,7)
// ));

// game.makeMove(new Move(
//         new Position(1,3),
//         new Position(2,3)
// ));

// game.makeMove(new Move(
//         new Position(6,4),
//         new Position(4,4)
// ));

// game.makeMove(new Move(
//         new Position(1,4),
//         new Position(2,4)
// ));

// game.makeMove(new Move(
//         new Position(7,5),
//         new Position(4,2)
// ));

// game.makeMove(new Move(
//         new Position(1,5),
//         new Position(2,5)
// ));

// System.out.println(
//         game.makeMove(
//                 new Move(
//                         new Position(7,4),
//                         new Position(7,6)
//                 )
//         )
// );

// // Expected :
// // false

// //* 29) Castling while in check */
// Board board = new Board();

// board.placePiece(
//         new Position(7,4),
//         new King(Color.WHITE)
// );

// board.placePiece(
//         new Position(7,7),
//         new Rook(Color.WHITE)
// );

// board.placePiece(
//         new Position(0,4),
//         new King(Color.BLACK)
// );

// board.placePiece(
//         new Position(0,6),
//         new Rook(Color.BLACK)
// );

// Game game = new Game(board);

// System.out.println(
//         game.makeMove(
//                 new Move(
//                         new Position(7,4),
//                         new Position(7,6)
//                 )
//         )
// );

// // Expected :
// // false

// //* 30) Passing through check */
// Board board = new Board();

// board.placePiece(
//         new Position(7,4),
//         new King(Color.WHITE)
// );

// board.placePiece(
//         new Position(7,7),
//         new Rook(Color.WHITE)
// );

// board.placePiece(
//         new Position(0,4),
//         new King(Color.BLACK)
// );

// board.placePiece(
//         new Position(0,5),
//         new Rook(Color.BLACK)
// );

// Game game = new Game(board);

// System.out.println(
//         game.makeMove(
//                 new Move(
//                         new Position(7,4),
//                         new Position(7,6)
//                 )
//         )
// );

// // Expected :
// // false

// //* 31) destination in check */
// Board board = new Board();

// board.placePiece(
//         new Position(7,4),
//         new King(Color.WHITE)
// );

// board.placePiece(
//         new Position(7,7),
//         new Rook(Color.WHITE)
// );

// board.placePiece(
//         new Position(0,4),
//         new King(Color.BLACK)
// );

// board.placePiece(
//         new Position(0,6),
//         new Rook(Color.BLACK)
// );

// Game game = new Game(board);

// System.out.println(
//         game.makeMove(
//                 new Move(
//                         new Position(7,4),
//                         new Position(7,6)
//                 )
//         )
// );

// // Expected :
// // false
    }
}
