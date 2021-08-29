package xu.problem.pathfinding;

import java.util.EnumMap;

public enum Direction {
    N('↑'),  //北
    NE('↗'), //东北
    E('→'),  //东
    SE('↘'), //东南
    S('↓'),  //南
    SW('↙'), //西南
    W('←'),  //西
    NW('↖');  //西北

    Direction(char symbol){
        this.symbol = symbol;
    }
    private final char symbol;
    public char symbol(){
        return symbol;
    }

    //各个方向移动的代价，直线为10，斜线为14
    private static final EnumMap<Direction, Integer> DIRECTION_COST = new EnumMap<>(Direction.class);
    static{
        int scale = Position.SCALE;
        int diagonal = (int) (scale * Position.ROOT2);

        DIRECTION_COST.put(N, scale);
        DIRECTION_COST.put(NE, diagonal);
        DIRECTION_COST.put(E, scale);
        DIRECTION_COST.put(SE, diagonal);
        DIRECTION_COST.put(S, scale);
        DIRECTION_COST.put(SW, diagonal);
        DIRECTION_COST.put(W, scale);
        DIRECTION_COST.put(NW, diagonal);
    }

    //各个方向移动的坐标位移量
    private static final EnumMap<Direction, int[]> DIRECTION_OFFSET = new EnumMap<>(Direction.class);
    static{
        //列号（或横坐标）增加量；行号（或纵坐标）增加量
        DIRECTION_OFFSET.put(N, new int[]{0, -1});
        DIRECTION_OFFSET.put(NE, new int[]{1, -1});
        DIRECTION_OFFSET.put(E, new int[]{1, 0});
        DIRECTION_OFFSET.put(SE, new int[]{1, 1});
        DIRECTION_OFFSET.put(S, new int[]{0, 1});
        DIRECTION_OFFSET.put(SW, new int[]{-1, 1});
        DIRECTION_OFFSET.put(W, new int[]{-1, 0});
        DIRECTION_OFFSET.put(NW, new int[]{-1, -1});
    }

    public static int[] offset(Direction dir){
        return DIRECTION_OFFSET.get(dir);
    }

    public static int cost(Direction dir){
        return DIRECTION_COST.get(dir);
    }
}
