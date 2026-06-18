public class Pawn extends Piece {
    public Pawn(Color color){
        super(color);
    }

    @Override
    public char getRepresentation(){
        return 'P';
    }

    @Override
    public boolean isValidMove(Move move, Board board) {
        //* validate arguments
        if(move == null || board == null){
            throw new IllegalArgumentException("Invalid Arguments passed");
        }

        //* Get Piece information
        Position from = move.getFrom();
        Position to = move.getTo();
        int row = from.getRow();
        int col = from.getCol();

        int rowDelta = to.getRow() - from.getRow();
        int colDelta = to.getCol() - from.getCol();
        
        Color color = this.getColor();

        int forward = color==Color.WHITE?-1:1;
        
        Piece destinationPiece = board.getPiece(to);

        //Straight moves
        if(colDelta == 0){
            //check if destination is occupied(color does not matter)
            if(destinationPiece != null){
                return false;
            }
            //2 square move
            if(rowDelta == 2*forward){
                //* check if not on the starting piece
                boolean onStartingRow = (color==Color.WHITE && row==6) || (color==Color.BLACK && row==1);
                if(!onStartingRow){
                    return false;
                }

                //* check if intermediate piece is occupied(color dont matter)
                if(board.isOccupied(new Position(row+forward, col))){
                    return false;
                }

                return true;
            }
            //one square move
            else if(rowDelta == forward){
                return true;
            }
        }

        //Captures
        else if(rowDelta==forward && Math.abs(colDelta)==1){
            //check if destination is not occupied by enemy
            if(destinationPiece == null || destinationPiece.getColor() == color){
                return false;
            }
            return true;
        }

        //* return false if anything other than straight or captures  
        return false;
    }
}
