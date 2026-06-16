public class Square {
    private final Position position;
    private Piece piece;

    public Square(Position position, Piece piece){
        this(position);
        this.piece = piece;
    }

    public Square(Position position){
        if(position!=null){
            this.position = position;
            this.piece = null;
        }
        else{
            //throw exception
            throw new IllegalArgumentException("Invalid position");
        }
    }
    
    public Position getPosition(){
        return position;
    }
    
    public Piece getPiece(){
        return piece;
    }
    
    public boolean isOccupied(){
        return piece!=null;
    }
    
    public void placePiece(Piece piece){
        if(piece==null){
            throw new IllegalArgumentException("Invalid Piece");
        }
        else if(isOccupied()){
            throw new IllegalStateException("Square already Occupied");
        }
        else{
            //throw exception
            this.piece = piece;
        }
    }
    
    public void removePiece(){
        if(isOccupied()){
            this.piece = null;
        }
        else{
            //throw exception
            throw new IllegalStateException("Square already Empty");
        }
    }

}
