public class Queen extends Piece {
    public Queen(Color color){
        super(color);
    }

    @Override
    public char getRepresentation(){
        return 'Q';
    }

    @Override
    public boolean isValidMove(Move move, Board board) {
        if(move == null || board == null){
            throw new IllegalArgumentException("Invalid Arguments passed");
        }

        //* after validating input arguments, use rook and bishop's
        //* isvalidmove functions as queen is basically rook+bishop.
        Rook tempRook = new Rook(this.getColor());
        Bishop tempBishop = new Bishop(this.getColor());

        return tempRook.isValidMove(move, board) || tempBishop.isValidMove(move, board);
    }
}
