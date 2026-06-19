public class Game {
    private final Board board;
    private Color currentTurn;
    private static final int BOARD_SIZE = 8;

    public Game(){
        board = new Board();
        currentTurn = Color.WHITE;
        
        setupBoard();
    }

    private void setupBoard(){
        // set pawns
        for(int col=0;col<BOARD_SIZE;col++){
            //white pawns
            board.placePiece(new Position(6,col), new Pawn(Color.WHITE));
            
            //black pawns
            board.placePiece(new Position(1, col), new Pawn(Color.BLACK));
        }
        
        //create the rest of the while pieces
        Piece[] whitePieces = {
            new Rook(Color.WHITE),
            new Knight(Color.WHITE),
            new Bishop(Color.WHITE),
            new Queen(Color.WHITE),
            new King(Color.WHITE),
            new Bishop(Color.WHITE),
            new Knight(Color.WHITE),
            new Rook(Color.WHITE)
        };

        //create the rest of the black pieces
        Piece[] blackPieces = {
            new Rook(Color.BLACK),
            new Knight(Color.BLACK),
            new Bishop(Color.BLACK),
            new Queen(Color.BLACK),
            new King(Color.BLACK),
            new Bishop(Color.BLACK),
            new Knight(Color.BLACK),
            new Rook(Color.BLACK)
        };

        //place the rest of the pieces on the board
        for(int col=0;col<BOARD_SIZE;col++){
            //black pieces
            board.placePiece(new Position(0, col), blackPieces[col]);

            //white pieces
            board.placePiece(new Position(7, col), whitePieces[col]);
        }
    }

    private boolean hasAnyLegalMove(Color color){
        //* validate arguments
        if(color == null){
            throw new IllegalArgumentException("Color cannot be null");
        }

        //* check whole board for our pieces
        for(int row=0;row<BOARD_SIZE;row++){
            for(int col=0;col<BOARD_SIZE;col++){
                Position start = new Position(row,col);
                Piece piece = board.getPiece(start);
                // operate only if our piece found
                if(piece!=null && piece.getColor() == color){
                    //* try a move to all squares of board with that piece
                    for(int possibleRow = 0;possibleRow<BOARD_SIZE;possibleRow++){
                        for(int possibleCol=0;possibleCol<BOARD_SIZE;possibleCol++){
                            Position destination = new Position(possibleRow, possibleCol);
                            //* skip moving to ur own position
                            if(start.equals(destination))continue;
                            Piece capturedPiece = board.getPiece(destination);
                            Move move = new Move(start, destination);
                            // see if move can be made
                            if(board.makeMove(move)){
                                // get if move was legal or not
                                boolean legal = !isInCheck(color);
                                //undo the changes
                                board.undoMove(move, capturedPiece);
                                // if legal move, then return true
                                if(legal){
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        // return false since no legal move found
        return false;
    }

    public boolean makeMove(Move move){
        //* Validate arguments
        if(move == null){
            throw new IllegalArgumentException("Move cannot be null");
        }

        //* get the moving piece and captured piece(could be null if no capture)
        Piece movingPiece = board.getPiece(move.getFrom());
        Piece capturedPiece = board.getPiece(move.getTo());

        //* Validate moving piece
        if(movingPiece == null){
            return false;
        }
        //* Check whether different piece
        if(movingPiece.getColor()!=currentTurn){
            return false;
        }
        
        //* make the move and return false if move unsuccesful
        if(!board.makeMove(move)){
            return false;
        }

        //* check if the above move leaves ur king in check
        if(isInCheck(currentTurn)){
            board.undoMove(move, capturedPiece);
            return false;
        }

        //* Since move is successful, change turns 
        currentTurn = currentTurn.opposite();

        //* return true to mark successful move completed
        return true;
    }

    public boolean isInCheck(Color color){
        if(color == null){
            throw new IllegalArgumentException("Color cannot be null");
        }

        Position kingPosition = board.findKing(color);
        Color enemyColor = color.opposite();
        for(int row = 0;row<BOARD_SIZE;row++){
            for(int col = 0;col<BOARD_SIZE;col++){
                Position position = new Position(row, col);
                Piece piece = board.getPiece(position);
                if(piece!=null && piece.getColor()==enemyColor){
                    Move move = new Move(position,kingPosition);
                    if(piece.isValidMove(move,board)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isCheckmate(Color color){
        if(color == null){
            throw new IllegalArgumentException("Color cannot be null");
        }

        return isInCheck(color) && !hasAnyLegalMove(color);
    }

    public boolean isStalemate(Color color){
        if(color == null){
            throw new IllegalArgumentException("Color cannot be null");
        }

        return !isInCheck(color) && !hasAnyLegalMove(color);
    }

    @Override
    public String toString() {
        return board + "\nCurrent Turn: " + currentTurn +"\n";
    }

}
