public abstract class Piece {
    private final Color color;
    private boolean hasMoved;

    protected Piece(Color color){
        this.color = color;
    }

    public Color getColor(){
        return this.color;
    }

    public boolean hasMoved(){
        return  hasMoved;
    }

    public void setMoved(boolean moved){
        this.hasMoved = moved;
    }

    public abstract char getRepresentation();

    public abstract boolean isValidMove(Move move, Board board);
}
