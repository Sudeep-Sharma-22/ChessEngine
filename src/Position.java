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
}
