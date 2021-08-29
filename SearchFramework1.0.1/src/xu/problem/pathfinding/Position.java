package xu.problem.pathfinding;

import core.problem.Action;
import core.problem.State;
import core.runner.HeuristicType;
import core.solver.Node;
import core.solver.heuristic.Predictor;
import java.util.ArrayList;
import java.util.EnumMap;

/**
 * PathFinding问题的状态
 * 位置状态，表示寻路机器人在什么位置
 */
public class Position extends State {
    public static final int SCALE = 10;       //单元格的边长
    public static final double ROOT2 = 1.4;   //2的平方根

    //可移动的方向：四个方向或者八个方向
    private static final int MOVETYPE = 1;   //八个方向移动
    private static final Direction[][] directions = new Direction[2][];
    static{
        //四个方向移动
        directions[0] = new Direction[]{Direction.N, Direction.E, Direction.S, Direction.W};
        //八个方向移动
        directions[1] = Direction.values();
    }

    //机器人在场地中的位置
    private final Point point;

    public Position(int row, int col) {
        this.point = new Point(row, col);
    }

    @Override
    public void draw() {
        System.out.println(this);
    }

    /**
     * 当前状态采用action而进入的下一个状态
     *
     * @param action 当前状态下，一个可行的action
     * @return 下一个状态
     */
    @Override
    public State next(Action action) {
        //当前Action所带来的位移量
        Direction dir = ((Move)action).getDirection();
        int[] offsets = Direction.offset(dir);
        //生成新状态所在的点
        int col = getCol() + offsets[0];
        int row = getRow() + offsets[1];

        return new Position(row, col);
    }

    @Override
    public Iterable<? extends Action> actions() {
        ArrayList<Move> moves = new ArrayList<>();
        for (Direction d : directions[MOVETYPE])
            moves.add(new Move(d));
        return moves;
    }

    //枚举映射，存放不同类型的启发函数
    private static final EnumMap<HeuristicType, Predictor> predictors = new EnumMap<>(HeuristicType.class);
    static{
        predictors.put(HeuristicType.PF_EUCLID,
                (state, goal) -> ((Position)state).euclid((Position)goal));
        predictors.put(HeuristicType.PF_MANHATTAN,
                (state, goal) -> ((Position)state).manhattan((Position)goal));
        predictors.put(HeuristicType.PF_GRID,
                (state, goal) -> ((Position)state).gridDistance((Position)goal));
    }
    public static Predictor predictor(HeuristicType type){
        Predictor predictor= predictors.get(type);    //map方法获取键为type的值
        return predictor;
    }

    //两个点之间的Grid距离，尽量走对角线
    private int gridDistance(Position goal) {
        int width = Math.abs(this.getCol() - goal.getCol());
        int height = Math.abs(this.getRow() - goal.getRow());
        if (width > height) {
            return (width - height) * SCALE + height * (int) (SCALE * ROOT2);
        }
        else{
            return (height - width) * SCALE + width * (int) (SCALE * ROOT2);
        }
    }

    //两个点之间的曼哈顿距离乘以SCALE
    private int manhattan(Position goal) {
        return this.point.manhattan(goal.point) * SCALE;
    }

    //两个点之间的欧几里德距离
    private int euclid(Position goal) {
        return (int) (this.point.euclid(goal.point) * SCALE);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;

        if (obj instanceof Position ) {
            Position another = (Position) obj;
            //两个Node对象的状态相同，则认为是相同的
            return this.point.equals(another.point);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return point.hashCode();
    }

    @Override
    public String toString() {
        return point.toString();
    }

    public int getRow() {
        return point.getX();
    }

    public int getCol() {
        return point.getY();
    }
}
