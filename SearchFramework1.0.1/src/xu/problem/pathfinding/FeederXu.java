package xu.problem.pathfinding;

import algs4.util.In;
import core.problem.Problem;
import core.problem.State;
import core.runner.EngineFeeder;
import core.runner.HeuristicType;
import core.solver.Node;
import core.solver.Searcher;
import core.solver.heuristic.AbstractFrontier;
import core.solver.heuristic.BestFirstSearch;
import core.solver.heuristic.EvaluationType;
import core.solver.heuristic.Predictor;
import xu.solver.heuristic.LinkedFrontier;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 寻路问题的EngineFeeder
 */
public class FeederXu extends EngineFeeder {
    /**
     * 从文件输入流中读入NPuzzle问题的实例
     *
     * @param io 输入流
     * @return 文件中所有NPuzzle实例
     */
    @Override
    public ArrayList<Problem> getProblems(In io) {

        ArrayList<Problem> problems = new ArrayList<>();
        //地图的大小
        int size = io.readInt();
        //读入地图
        GridType[][] grids = new GridType[size][];
        for (int i = 0; i < size; i++){
            grids[i] = new GridType[size];
            for (int j = 0; j < size; j++){
                int cellType = io.readInt();
                grids[i][j] = GridType.values()[cellType];
            }
        }
        while (io.hasNextLine()){
            //读入初始状态
            int row = io.readInt();
            int col = io.readInt();
            Position initialState = new Position(row, col);
            //读入目标状态
            row = io.readInt();
            col = io.readInt();
            Position goal = new Position(row, col);
            //生成寻路问题的实例，并设置其地图
            PathFinding problem = new PathFinding(initialState, goal, size);
            problem.setGrids(grids);
            //添加到问题列表
            problems.add(problem);
        }
        return problems;
    }

    /*

     */
    @Override
    public ArrayList<Problem> getProblems(Scanner scanner) {
        return null;
    }

    /**
     * 生成采取某种估值机制的Frontier
     *
     * @param type 结点评估器的类型
     * @return 使用该评估机制的一个Frontier实例
     */
    @Override
    public AbstractFrontier getFrontier(EvaluationType type) {
        return new LinkedFrontier(Node.evaluator(type));
    }

    /**
     * 获得对状态进行估值的Predictor
     *
     * @param type 估值函数的类型
     * @return  估值函数
     */
    @Override
    public Predictor getPredictor(HeuristicType type) {
        return Position.predictor(type);
    }

    /**
     * 生成IdaStar搜索的一个实例
     */
    @Override
    public Searcher getIdaStar() {
        return null;
    }

    /**
     * 用来提交头歌评分的Searcher
     *
     * @return 搜索引擎
     */
    @Override
    public Searcher getScoreSearcher() {
        return getAStar(HeuristicType.PF_GRID);
    }

}
