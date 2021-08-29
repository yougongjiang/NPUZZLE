package xu.problem.pathfinding;

import core.problem.Action;
import core.problem.Problem;
import core.problem.State;
import core.solver.Node;
import java.util.Deque;

/**
 * 寻路问题
 */
public class PathFinding extends Problem {

    //地图信息
    GridType[][] grids;

    public PathFinding(State initialState, State goal) {
        super(initialState, goal);
    }

    public PathFinding(State initialState, State goal, int size) {
        super(initialState, goal, size);
        grids = new GridType[size][size];
    }

    public GridType[][] getGrids() {
        return grids;
    }

    public void setGrids(GridType[][] grids) {
        for (int i = 0; i < size; i++){
            System.arraycopy(grids[i], 0, this.grids[i], 0, size);
        }
    }

    /**
     * 当前问题是否有解
     * 因为只有通过搜索来判断，所以先默认为true
     * @return 有解，true; 无解，false
     */
    @Override
    public boolean solvable() {
        return true;
    }

    @Override
    public int stepCost(State state, Action action) {
        Position position = (Position) state ;
        GridType type = grids[position.getRow() - 1][position.getCol() - 1];
        if (type == GridType.EMPTY)
            return action.stepCost();
        if (type == GridType.GRASS)
            return action.stepCost() * 5;
        return Integer.MIN_VALUE;
    }

    @Override
    protected boolean applicable(State state, Action action) {
        int[] offsets = Direction.offset(((Move)action).getDirection());
        int row = ((Position)state).getRow() + offsets[1];
        int col = ((Position)state).getCol() + offsets[0];
        return row > 0 && row <= size &&
                col > 0 && col <= size &&
                grids[row - 1][col - 1] != GridType.WALL;
    }

    @Override
    public void showSolution(Deque<Node> path) {
        //将地图转换为字符数组
        char[][] grids = new char[size][];
        for (int i = 0; i < size; i++){
            grids[i] = new char[size];
            for (int j = 0; j < size; j++) {
                grids[i][j] = this.grids[i][j].symbol();
            }
        }
        //标记起点
        int row = ((Position)initialState).getRow();
        int col = ((Position)initialState).getCol();
        grids[row - 1][col - 1] = '@';
        //和终点
        row = ((Position)goal).getRow();
        col = ((Position)goal).getCol();
        grids[row - 1][col - 1] = '&';

        //打印寻路问题。
        System.out.println(initialState + "→" + goal);
        //将解路径中的动作符号写入字符数组grids
        for (Node node : path) {
            Position p = (Position) node.getState();
            Move move = (Move) node.getAction();
            Direction d = move.getDirection();
            grids[p.getRow() - 1][p.getCol() - 1] = d.symbol();
        }

        //打印字符数组
        drawGrid(grids);
    }

    private void drawGrid(char[][] grids) {
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++) {
                System.out.print(grids[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 打印当前问题的起点→终点
     * 和 地图
     */
    @Override
    public void draw() {
        System.out.println(initialState + "→" + goal);
        for (GridType[] rows : grids){
            for (GridType type : rows){
                System.out.print(type.symbol() + " ");
            }
            System.out.println();
        }
    }

    /**
     * 打印解路径所采取的动作序列
     * @param path 解路径
     */
    @Override
    public void printPath(Deque<Node> path) {
        if (path == null){
            System.out.println("No Solution.");
            return;
        }
        for (Node node : path) {
            Move move = (Move) node.getAction();
            System.out.print(move.getDirection().name() + " ");
        }
        System.out.println();

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
