package xu.problem.pathfinding;

public enum GridType {
    EMPTY('0'),  // 空地
    GRASS('#'),  // 草地，通过的代价高，普通代价的2倍
    //MUDDY,  // 泥地，通过代价为3倍
    WALL('*');   // 石墙，无法通过

    private final char symbol;

    GridType(char symbol){
        this.symbol = symbol;
    }

    public char symbol(){
        return symbol;
    }

    @Override
    public String toString() {
        return symbol + "";
    }
}
