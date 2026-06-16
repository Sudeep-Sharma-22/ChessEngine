public class Pawn extends Piece {
    public Pawn(Color color){
        super(color);
    }

    @Override
    public char getRepresentation(){
        return 'P';
    }
}
