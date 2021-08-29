package core.problem;

import core.solver.Node;

import core.solver.heuristic.Predictor;
import s17020031029.problem.npuzzle.PuzzleState;

import java.util.ArrayList;
import java.util.Deque;

/**
 * 所有问题的抽象超类
 * initialState
 * goal
 */
public abstract class Problem {
    //成员变量
    protected State initialState;
    protected State goal;
    protected int size;     //问题的规模：15-puzzle为4；寻路问题为Grid的边长；野人传教士为野人与传教士的人数

    public Problem(State initialState, State goal) {
        this.initialState = initialState;
        this.goal = goal;
    }

    public Problem(State initialState, State goal, int size) {
        this(initialState, goal);
        this.size = size;
    }

    public State getInitialState() {
        return initialState;
    }

    public State getGoal() {
        return goal;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setInitialState(State initialState) {
        this.initialState = (PuzzleState)initialState;
    }

    public void setGoal(State goal) {
        this.goal = (PuzzleState)goal;
    }

    /**
     * 当前问题是否有解
     * @return 有解，true; 无解，false
     *
     */
    public abstract boolean solvable();

    /**
     * 从初始状态产生搜索树的根节点
     * @param predictor 启发函数
     * @return 当前问题的根结点
     */
    public final Node root(Predictor predictor) {

        return new Node(initialState, null, null,
                0, predictor.heuristics(initialState, goal));
    }

    /**
     * 生成node的所有合法的后继结点
     * @param parent      父结点
     * @param predictor   启发函数
     *
     * @return  parent结点的所有子结点
     */
    public final Iterable<? extends Node> childNodes(Node parent, Predictor predictor) {
        ArrayList<Node> nodes = new ArrayList<>();
        //父结点的状态
        PuzzleState parentState = (PuzzleState)parent.getState();
        //对于parentState上所有可能的action，但有的不可行
        for (Action action : parentState.actions()){
            //如果父结点状态下的动作是可行的
            if (applicable(parentState, action)){
                //得到后继状态
                PuzzleState state = (PuzzleState)parentState.next(action);
                //计算路径长度 = 父结点路径长度 + 进入后继状态所采取的动作的代价
                int pathCost = parent.getPathCost() + stepCost(state, action);
                //使用predictor对state与goal的距离进行估值
                int heuristics = predictor.heuristics(state, goal);
//                int dx = parentState.getPos()[0][0],dy = parentState.getPos()[0][1] ;
//                int x = state.getPos()[0][0],y = state.getPos()[0][1];
//                int gx = ((PuzzleState)goal).getPos()[state.getPuzzle()[dx][dy]][0], gy= ((PuzzleState)goal).getPos()[state.getPuzzle()[dx][dy]][1];
//                int heuristics = parent.getHeuristic() - (Math.abs(x-gx) + Math.abs(y-gy)) + Math.abs(dx-gx)+Math.abs(dy-gy);
                //生成子结点
                Node child = new Node(state, parent, action, pathCost, heuristics);
                nodes.add(child);
            }
        }
        return nodes;
    }

    /**
     *
     * @param state     当前状态
     * @param action    进入当前状态所采取的Action
     * @return          进入当前状态的代价
     */
    public abstract int stepCost(State state, Action action);

    /**
     * 在状态state上的action是否可用？
     * @param state     当前状态
     * @param action    当前状态下所采用的动作
     * @return  true：可用；false：不可用
     */
    protected abstract boolean applicable(State state, Action action);

    /**
     * 判断某个状态state是否到达目标状态，多数情况下是判断跟目标状态是否相等。
     *
     * @param state 要判断的状态
     * @return  true：要判断的状态已经是目标状态；否则，false
     */
    public boolean goal(State state){
        return state.equals(goal);
    }

    /**
     * 解路径的可视化
     * @param path 解路径
     */
    public abstract void showSolution(Deque<Node> path);

    /**
     * 打印当前问题实例
     */
    public abstract void draw();

    /**
     * 打印解路径
     * @param path 解路径
     */
    public abstract void printPath(Deque<Node> path);
}
