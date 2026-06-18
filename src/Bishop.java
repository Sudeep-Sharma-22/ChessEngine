public class Bishop extends Piece {
    public Bishop(Color color){
        super(color);
    }

    @Override
    public char getRepresentation(){
        return 'B';
    }

    @Override
    public boolean isValidMove(Move move, Board board){
        if(move == null || board == null){
            throw new IllegalArgumentException("Invalid Arguments passed");
        }

        Position from = move.getFrom();
        Position to = move.getTo();

        //* Validate movement pattern
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());
        if(rowDiff!=colDiff){
            return false;
        }

        int rowStep = Integer.signum(to.getRow() - from.getRow());
        int colStep = Integer.signum(to.getCol() - from.getCol());
        int row = from.getRow()+rowStep;
        int col = from.getCol()+colStep;
        
        while(row!=to.getRow() || col!=to.getCol()){
            Position cell = new Position(row,col);
            if(board.isOccupied(cell)) return false;
            row += rowStep;
            col += colStep;
        }

        Piece destinationPiece = board.getPiece(to);
        if(destinationPiece == null){
            return true;
        }
        return destinationPiece.getColor()!=this.getColor();
    }
}
