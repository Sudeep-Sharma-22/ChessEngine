public class Game {
    private final Board board;
    private Color currentTurn;
    private static final int BOARD_SIZE = 8;
    private Move lastMove;

    public Game(){
        this.board = new Board();
        this.currentTurn = Color.WHITE;
        this.lastMove = null;

        setupBoard();
    }

    //* Constructor 2 made for testing
    public Game(Board board){
        if(board == null){
            throw new IllegalArgumentException(
                    "Invalid arguments"
            );
        }

        this.board = board;
        this.currentTurn = Color.WHITE;
        this.lastMove = null;
    }

    //* Constructor 3 made for testing
    public Game(Board board, Color currentTurn, Move lastMove){
        if(board == null || currentTurn == null){
            throw new IllegalArgumentException(
                    "Invalid arguments"
            );
        }

        this.board = board;
        this.currentTurn = currentTurn;
        this.lastMove = lastMove;
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
                            boolean originalHasMoved = piece.hasMoved();
                            // see if move can be made
                            if(board.makeMove(move)){
                                // get if move was legal or not
                                boolean legal = !isInCheck(color);
                                //undo the changes
                                board.undoMove(move, capturedPiece, piece, originalHasMoved);
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

    //* Promotes to queen by default when no choice passed,
    //* For choice implementation, see at overloaded version of it
    private void promotePawnIfNeeded(Position position){
        // validate arguments
        if(position == null){
        throw new IllegalArgumentException("Position cannot  be null");
        }

        Piece piece = board.getPiece(position);
        if(piece instanceof Pawn){
            int row = position.getRow();
            Color color = piece.getColor();
            boolean shouldPromote = (color == Color.BLACK && row == 7) || (color == Color.WHITE && row == 0);
            if(shouldPromote){
                board.replacePiece(position, new Queen(color));
            }
        }
    }

    //* Overloaded method of promotePawnIfNeeded(Position position)
    private void promotePawnIfNeeded(Position position, PromotionType promotionType){
        // validate arguments
        if(position == null){
        throw new IllegalArgumentException("Position cannot  be null");
        }

        Piece piece = board.getPiece(position);
        if(piece instanceof Pawn){
            int row = position.getRow();
            Color color = piece.getColor();
            boolean shouldPromote = (color == Color.BLACK && row == 7) || (color == Color.WHITE && row == 0);
            if(shouldPromote){
                board.replacePiece(position, promotionType.createPiece(color));
            }
        }
    }

    private boolean isSquareUnderAttack(Position position, Color victimColor){
        if(position == null || victimColor == null){
            throw new IllegalArgumentException("Invalid Arguments Passed");
        }

        Color enemyColor = victimColor.opposite();

        for(int row=0;row<BOARD_SIZE;row++){
            for(int col=0;col<BOARD_SIZE;col++){
                Position enemyPosition = new Position(row, col);
                if(enemyPosition.equals(position)){
                    continue;
                }
                Piece enemyPiece = board.getPiece(enemyPosition);
                if(enemyPiece != null && enemyPiece.getColor()==enemyColor){
                    Move move = new Move(enemyPosition,position);
                    if(enemyPiece.isValidMove(move, board)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isCastlingMove(Move move){
        if(move == null){
            throw new IllegalArgumentException("Move cannot be null");
        }

        Piece piece = board.getPiece(move.getFrom());

        if( !(piece instanceof King) ){
            return false;
        }

        Position from = move.getFrom();
        Position to = move.getTo();

        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());

        return rowDiff==0 && colDiff==2;
    }

    private boolean canCastle(Move move){
        //* Validate arguments
        if(move == null){
            throw new IllegalArgumentException("Move cannot be null");
        }

        //* Check if the move is actually a castling move
        if(!isCastlingMove(move)){
            return false;
        }

        Position from = move.getFrom();
        Position to = move.getTo();

        King king = (King)board.getPiece(from);
        Color color = king.getColor();
        int row = from.getRow();
        boolean kingside = to.getCol() > from.getCol();

        //* check if king has moved 
        if(king.hasMoved()){
            return false;
        }

        int rookCol = kingside ? 7 : 0;
        Position rookPosition = new Position(row, rookCol);
        Piece rook = board.getPiece(rookPosition);
        
        //* check if rook has moved 
        if( !(rook instanceof Rook) || (rook.getColor()!=color) || rook.hasMoved() ){
            return false;
        }

        //* check whether squares b/w king and rook are empty 
        int startCol = kingside ? 5 : 1;
        int endCol = kingside ? 6 : 3;

        for(int col = startCol; col<=endCol; col++){
            Position position = new Position(row, col);
            if(board.isOccupied(position)){
                return false;
            }
        }

        //* see whether king is in check 
        if(isInCheck(color)){
            return false;
        }

        //* check that king does not move through check and into check 
        int kingCol = kingside ? 5 : 3;
        int step = kingside ? 1 : -1;
        for(int col=kingCol; col!=to.getCol()+step; col+=step){
            Position position = new Position(row, col);
            if(isSquareUnderAttack(position, color)){
                return false;
            }
        }

        return true;
    }

    //* Note : This does not check if canCastle()==true, it assumes it is
    private void performCastling(Move move){
        if(move == null){
            throw new IllegalArgumentException("Move cannot be null");
        }

        Position from = move.getFrom();
        Position to = move.getTo();

        int row = from.getRow();

        boolean kingside = to.getCol() > from.getCol();

        int rookStartCol = kingside ? 7 : 0;
        int rookEndCol = kingside ? 5 : 3;

        Position rookStartPosition = new Position(row, rookStartCol);
        Position rookEndPosition = new Position(row, rookEndCol);
        Move rookMove = new Move(rookStartPosition,rookEndPosition);

        board.forceMove(move);
        board.forceMove(rookMove);
    }

    //* Checks whether it is an enPassantMove and also checks whether
    //* en passant is possible
    private boolean isEnPassantMove(Move move){
        //* Validate arguments
        if(move == null){
            throw new IllegalArgumentException("Move cannot be Null");
        }

        //* if first move, then no en passant possible
        if(lastMove == null){
            return false;
        }

        Piece movingPiece = board.getPiece(move.getFrom());
        //* check that the moving piece is a pawn
        if( !(movingPiece instanceof Pawn) ){
            return false;
        }

        Position from = move.getFrom();
        Position to = move.getTo();

        //* check if the destination is empty(just a safety check)
        if(board.isOccupied(to)){
            return false;
        }

        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());
        //* check that piece only moves exactly 1 row and 1 column
        if(rowDiff != 1 || colDiff != 1){
            return false;
        }

        //* another safety check based on pawn's moving direction
        int direction = movingPiece.getColor()==Color.WHITE?-1:1;
        if(to.getRow() != from.getRow() + direction){
            return false;
        }

        Piece lastMovedPiece = board.getPiece(lastMove.getTo());
        //* check that the last move was made by a pawn only
        if( !(lastMovedPiece instanceof Pawn) ){
            return false;
        }

        //* check that the last pawn which moved was of enemy
        if(lastMovedPiece.getColor() == movingPiece.getColor()){
            return false;
        }

        Position lastFrom= lastMove.getFrom();
        Position lastTo = lastMove.getTo();

        //* check that the pawn that moved in lastmove jumped 2 squares
        int lastRowDiff = Math.abs(lastTo.getRow() - lastFrom.getRow());
        if(lastRowDiff!=2){
            return false;
        }

        //* check whether our pawn is adjacent to the enemy pawn
        if(from.getRow() != lastTo.getRow()){
            return false;
        }
        if(to.getCol() != lastTo.getCol()){
            return false;
        }

        //* Above if's being false mean valid for en passant
        return true;
    }

    //* this does not check if enPassant is valid, it assumes it is
    private void performEnPassant(Move move){
        //* Validate arguments 
        if(move == null){
            throw new IllegalArgumentException("Move cannot be null");
        }

        //* move the pawn as we know move is valid
        board.forceMove(move);

        Position capturedPawnPosition = lastMove.getTo();

        //* remove the captured piece from the board
        board.removePiece(capturedPawnPosition);
    }

    //* assumes enPassant has occured
    private void undoEnPassant(Move move, Piece capturedPawn, Position capturedPawnPosition, boolean originalHasMoved){
        //* Validate arguments
        if(move == null){
            throw new IllegalArgumentException("Move cannot be null");
        }
        if( !(capturedPawn instanceof Pawn) ){
            throw new IllegalArgumentException("Captured piece is not a Pawn");
        }

        Position from = move.getFrom();
        Position to = move.getTo();

        Piece movingPiece = board.getPiece(to);
        if(movingPiece == null){
            throw new IllegalStateException("No Piece to undo");
        }

        // restore the capturing pawn
        board.removePiece(to);
        board.placePiece(from, movingPiece);
        movingPiece.setMoved(originalHasMoved);

        // restore the captured pawn
        //* NOTE: we dont use lastmove.getTo to get the capuredPawnPosition
        //* because if lastmove changes, it will break, so we want undo
        //* to be self sustained
        board.placePiece(capturedPawnPosition, capturedPawn);
        
    }

    private boolean isPromotionMove(Move move){
        if(move == null){
            throw new IllegalArgumentException("Move cannot be null");
        }

        Position from = move.getFrom();
        Position to = move.getTo();

        Piece piece = board.getPiece(from);

        if( !(piece instanceof Pawn) ){
            return false;
        }

        int destinationRow = to.getRow();
        Color color = piece.getColor();

        return (color == Color.WHITE && destinationRow == 0) || (color == Color.BLACK && destinationRow == 7);
    }

    private boolean makeMoveInternal(Move move, PromotionType promotionType){
        //* Validate arguments
        if(move == null){
            throw new IllegalArgumentException("Move cannot be null");
        }

        //* get the moving piece and captured piece(could be null if no capture)
        Piece movingPiece = board.getPiece(move.getFrom());
        //* Validate moving piece
        if(movingPiece == null){
            return false;
        }
        boolean originalHasMoved = movingPiece.hasMoved();
        Piece capturedPiece = board.getPiece(move.getTo());

        //* Check whether different piece
        if(movingPiece.getColor()!=currentTurn){
            return false;
        }

        //* Call Castling procedure and return if a castling move
        if(isCastlingMove(move)){
            if(canCastle(move)){
                performCastling(move);
                //* update last move and currentturn and return move successful
                lastMove = move;
                currentTurn = currentTurn.opposite();
                return true;
            }
            //* return false since its a castling move and canCastle()==false
            return false;
        }
        
        //* Call EnPassant procedure and return if enPassant move
        if(isEnPassantMove(move)){
            Position capturedPawnPosition = lastMove.getTo();
            Piece capturedPawn = board.getPiece(capturedPawnPosition);

            performEnPassant(move);

            if(isInCheck(currentTurn)){
                undoEnPassant(move, capturedPawn, capturedPawnPosition, originalHasMoved);
                return false;
            }

            // update last move and current turn and return move successful
            lastMove = move;
            currentTurn = currentTurn.opposite();
            return true;
        }

        //* make the move and return false if move unsuccesful
        if(!board.makeMove(move)){
            return false;
        }

        //* check if the above move leaves ur king in check
        if(isInCheck(currentTurn)){
            board.undoMove(move, capturedPiece, movingPiece, originalHasMoved);
            return false;
        }

        //* promote if possible(this function will take care of whether pawn/not pawn/possible/not possible)
        //* promote to queen by default if no choice given
        if(promotionType == null){
            promotePawnIfNeeded(move.getTo());
        }
        //* promote to the choice of piece if provided
        else{
            promotePawnIfNeeded(move.getTo(),promotionType);
        }

        //* update the last move
        lastMove = move;

        //* Since move is successful, change turns 
        currentTurn = currentTurn.opposite();

        //* return true to mark successful move completed
        return true;
    }

    //* Fully legal move
    public boolean makeMove(Move move){
        //* Validate arguments
        if(move == null){
            throw new IllegalArgumentException("Move cannot be null");
        }
        
        return makeMoveInternal(move, null);
    }

    //* Overloaded version of Game.makeMove(Move move) */
    public boolean makeMove(Move move, PromotionType promotionType){
        if(move == null){
            throw new IllegalArgumentException("Move cannot be null");
        }
        if(promotionType == null){
            throw new IllegalArgumentException("Promotion type cannot be null");
        }
        if(!isPromotionMove(move)){
            throw new IllegalArgumentException("Move is not a promotion move");
        }

        return makeMoveInternal(move, promotionType);
    }

    public boolean isInCheck(Color color){
        if(color == null){
            throw new IllegalArgumentException("Color cannot be null");
        }

        Position kingPosition = board.findKing(color);
        
        return isSquareUnderAttack(kingPosition, color);
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
