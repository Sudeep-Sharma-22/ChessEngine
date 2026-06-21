public enum PromotionType {
    QUEEN,
    ROOK,
    BISHOP,
    KNIGHT;

    public Piece createPiece(Color color){
        if(color == null){
            throw new IllegalArgumentException("Color cannot be null");
        }

        return switch(this){
            case QUEEN -> new Queen(color);
            case ROOK -> new Rook(color);
            case BISHOP -> new Bishop(color);
            case KNIGHT -> new Knight(color);
        };
    }
}

