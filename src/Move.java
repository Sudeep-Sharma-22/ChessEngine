import java.util.Objects;

public class Move {
    private final Position from;
    private final Position to;

    public Move(Position from, Position to){
        if(from == null || to == null){
            throw new IllegalArgumentException("Position can't be null");
        }
        if(from.equals(to)){
            throw new IllegalArgumentException("Invalid Move");
        }

        this.from = from;
        this.to = to;
    }

    public Position getFrom(){
        return this.from;
    }

    public Position getTo(){
        return this.to;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        else if(!(obj instanceof Move)) return false;
        
        Move move = (Move)obj;
        return (move.from.equals(this.from) && move.to.equals(this.to));
    }

    @Override
    public int hashCode() {
        return Objects.hash(from,to);
    }

    @Override
    public String toString() {
        return from + "->" + to;
    }
}
