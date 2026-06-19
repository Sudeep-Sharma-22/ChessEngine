public class Board {
    private static final int BOARD_SIZE = 8;
    private final Square[][] squares;

    public Board(){
        squares = new Square[BOARD_SIZE][BOARD_SIZE];
        for(int row=0;row<BOARD_SIZE;row++){
            for(int col=0;col<BOARD_SIZE;col++){
                Position position = new Position(row, col);
                Square square = new Square(position);
                squares[row][col]=square;
            }
        }
    }

    public Square getSquare(Position position){
        if(position==null){
            throw new IllegalArgumentException("Invalid Position passed");
        }
        int row = position.getRow();
        int col = position.getCol();
        return squares[row][col];
    }

    public boolean isOccupied(Position position){
        return getSquare(position).isOccupied();
    }

    public Piece getPiece(Position position){
        return getSquare(position).getPiece();
    }

    public void placePiece(Position position, Piece piece){
        getSquare(position).placePiece(piece);
    }

    public void removePiece(Position position){
        getSquare(position).removePiece();
    }

    public void replacePiece(Position position, Piece piece){
        if(position == null || piece == null){
            throw new IllegalArgumentException("Invalid Arguments Passed");
        }

        removePiece(position);
        placePiece(position, piece);
    }

    public Position getPiecePosition(Piece piece){
        if(piece==null){
            throw new IllegalArgumentException("Invalid Piece passed");
        }
        Square square;
        for(int row=0;row<BOARD_SIZE;row++){
            for(int col=0;col<BOARD_SIZE;col++){
                square = squares[row][col];
                if(square.isOccupied() && square.getPiece()==piece){
                    return square.getPosition();
                }
            }
        }
        return null;
    }

    public boolean isValidMove(Move move) {
        if (move == null) {
            throw new IllegalArgumentException(
                "Invalid move");
        }

        // check if a piece is in the position from which we want to move it
        Piece piece = getPiece(move.getFrom());
        if (piece == null) {
            return false;
        }

        return piece.isValidMove(move, this);
    }

    public boolean makeMove(Move move){
        if(move == null){
            throw new IllegalArgumentException("Invalid Move");
        }

        if(!isValidMove(move)){
            return false;
        }

        Position start = move.getFrom();
        Position destination = move.getTo();
        Piece piece = getPiece(start);
        //* remove the piece from start
        removePiece(start);
        //*remove the piece at destination in case of a capture,
        //*coz placePiece will only place at an empty square
        if(isOccupied(destination)){
            removePiece(destination);
        }
        //* place the piece on destination
        placePiece(destination, piece);
        return true;
    }

    public void undoMove(Move move, Piece capturedPiece){
        if(move==null){
            throw new IllegalArgumentException("Move cannot be null");
        }

        Position from = move.getFrom();
        Position to = move.getTo();

        Piece movingPiece = getPiece(to);
        if(movingPiece==null){
            throw new IllegalStateException("No piece to undo");
        }


        //* restore moving piece back to from
        removePiece(to);
        placePiece(from, movingPiece);

        //* restore capturedpiece if capture happened
        if(capturedPiece!=null){
            placePiece(to, capturedPiece);
        }
    }

    public Position findKing(Color color){
        if(color == null){
            throw new IllegalArgumentException("Color cannot be null");
        }
        
        for(int row=0;row<BOARD_SIZE;row++){
            for(int col=0;col<BOARD_SIZE;col++){
                Square square = squares[row][col];
                Piece piece = square.getPiece();
                if(piece instanceof King && piece.getColor()==color){
                    return new Position(row, col);
                }
            }
        }
        throw new IllegalStateException("King not found");
    }

    @Override
    public String toString(){
        StringBuilder board = new StringBuilder();
        for(int row=0;row<BOARD_SIZE;row++){
            for(int col=0;col<BOARD_SIZE;col++){
                Square square = squares[row][col];
                if(!square.isOccupied()){
                    board.append('-');
                    board.append(' ');
                }
                else{
                    Piece piece = square.getPiece();
                    char pieceRepresentation = piece.getRepresentation();
                    Color pieceColor = piece.getColor();
                    if(pieceColor == Color.BLACK){
                        pieceRepresentation = Character.toLowerCase(pieceRepresentation);
                    }
                    board.append(pieceRepresentation);
                    board.append(' ');
                }
            }
            board.append('\n');
        }
        return board.toString();
    }
}
