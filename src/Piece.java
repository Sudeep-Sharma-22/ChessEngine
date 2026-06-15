public abstract class Piece {
    private final Color color;
    protected Piece(Color color){
        this.color = color;
    }
    public Color getColor(){
        return this.color;
    }
}
