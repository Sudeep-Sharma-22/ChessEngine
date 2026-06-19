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

    public boolean makeMove(Move move){
        //* Validate arguments
        if(move == null){
            throw new IllegalArgumentException("Move cannot be null");
        }

        Piece piece = board.getPiece(move.getFrom());
        //* Validate piece
        if(piece == null){
            return false;
        }
        //* Check whether different piece
        if(piece.getColor()!=currentTurn){
            return false;
        }
        
        //* make the move and return false if move unsuccesful
        if(!board.makeMove(move)){
            return false;
        }

        //* Since move is successful, change turns 
        currentTurn = currentTurn.opposite();

        //* return true to mark successful move completed
        return true;
    }

    @Override
    public String toString() {
        return board + "\nCurrent Turn: " + currentTurn;
    }

}
