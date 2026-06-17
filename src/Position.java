import java.util.Objects;

public class Position {
    private final int row;
    private final int col;
    private static final int MAX_INDEX = 7;
    private static final int MIN_INDEX = 0;
    public Position(int row,int col){
        if(row>=MIN_INDEX &&  row<=MAX_INDEX && col>=MIN_INDEX && col<=MAX_INDEX){
            this.row = row;
            this.col = col;
        }
        else{
            throw new IllegalArgumentException("Invalid Position values");        }
    }

    public int getRow(){
        return row;
    }
    
    public int getCol(){
        return col;
    }

    @Override
    public String toString(){
        char file = (char) ('a' + this.col);
        char rank = (char)('0' + (MAX_INDEX + 1 - this.row));
        return ""+ file + rank;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;// same object compared, so reference comparison also works
        if(!(obj instanceof Position)) return false;
        Position position = (Position)obj;

        //* since we are in the position class only, we can access the private variables of 
        //* other position objects, so we did not use getrow and getcol below. it reduced unnecessary 
        //* method calls
        return (position.row==this.row && position.col==this.col);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row,col);
    }
}
