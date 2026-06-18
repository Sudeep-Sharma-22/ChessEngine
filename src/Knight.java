public class Knight extends Piece {
    public Knight(Color color){
        super(color);
    }

    @Override
    public char getRepresentation(){
        return 'N';
    }

    @Override
    public boolean isValidMove(Move move, Board board) {
        //* validate arguments
        if(move == null || board == null){
            throw new IllegalArgumentException("Invalid Arguments passed");
        }

        Position from = move.getFrom();
        Position to = move.getTo();

        int rowDiff = Math.abs(from.getRow() - to.getRow());
        int colDiff = Math.abs(from.getCol() - to.getCol());

        //* check if movement pattern is valid
        //if both of these conditions are false, then return false
        if( !((rowDiff == 2 && colDiff ==1) || (rowDiff == 1 && colDiff == 2)) ){
            return false;
        }

        //* check for destination piece
        Piece destinationPiece = board.getPiece(to);
        //if destination is empty
        if(destinationPiece == null){
            return true;
        }

        // return true if piece at destination is enemy 
        return  destinationPiece.getColor() != this.getColor();
    }
}
