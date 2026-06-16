public class Board {
    private static final int BOARD_SIZE = 8;
    private final Square[][] squares;

    public Board(){
        squares = new Square[BOARD_SIZE][BOARD_SIZE];
        for(int row=0;row<BOARD_SIZE;row++){
            for(int col=0;col<BOARD_SIZE;col++){
                Position position = new Position(row, col);
                Square square = new Square(position);
                squares[row][col]=square;
            }
        }
    }

    public Square getSquare(Position position){
        if(position==null){
            throw new IllegalArgumentException("Invalid Position passed");
        }
        int row = position.getRow();
        int col = position.getCol();
        return squares[row][col];
    }

}
