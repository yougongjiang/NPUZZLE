package s17020031029.problem.npuzzle;

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

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
* N-puzzle Problem
* */
public class FeederStu extends EngineFeeder {
	
	private static int[][] Zobrist;  //棋盘每个位置对应的随机数
	
	public static int[][] getZobrist() {
        return Zobrist;
    }

    public static void setZobrist(int[][] zobrist) {
        Zobrist = zobrist;
    }
    /**
     * 从文件输入流中读入NPuzzle问题的实例
     *
     * @param io 输入流
     * @return 文件中所有NPuzzle实例
     */
    @Override
    public ArrayList<Problem> getProblems(In io) {
        ArrayList<Problem> problems = new ArrayList<>();
        while (io.hasNextLine()){
            int size = io.readInt();
            int[][] copy = new int[size][size];
            //initstate
            PuzzleState initialState = new PuzzleState();
            initialState.setSize(size);
            for(int i=0;i<size;i++)
                for(int j=0;j<size;j++)
                    copy[i][j] = io.readInt();
            initialState.setPuzzle(copy);
            //goal
            PuzzleState goal = new PuzzleState();
            goal.setSize(size);
            for(int i=0;i<size;i++)
                for(int j=0;j<size;j++)
                    copy[i][j] = io.readInt();
            goal.setPuzzle(copy);
            Npuzzle problem = new Npuzzle(initialState,goal);
            problems.add(problem);
            //break;
        }
        SecureRandom rand = new SecureRandom();
        Zobrist = new int[16][];
        for(int i=0;i<16;i++){
            Zobrist[i]= new int[16];
            for(int j=0;j<16;j++){
                Zobrist[i][j]=rand.nextInt();
            }
        }
        return problems;
    }

    @Override
    public ArrayList<Problem> getProblems(Scanner scanner) {
        return null;
    }
    @Override
    public AbstractFrontier getFrontier(EvaluationType type) {
        return new LinkedFrontier(Node.evaluator(type));
    }

    @Override
    public Predictor getPredictor(HeuristicType type) {
        return PuzzleState.predictor(type);
    }

    @Override
    public Searcher getIdaStar() {
        return null;
    }

    @Override
    public Searcher getScoreSearcher() {
        return null;
    }

    public Searcher getSearcher(HeuristicType type){
        //获取Frontier，其Node以g(n)+h(n)的升序排列
        AbstractFrontier frontier = getFrontier(EvaluationType.FULL);
        //以HashSet作为Explored表
        Set<State> explored = new HashSet<>();
        //根据explored和frontier生成AStar引擎，并使用类型为type的启发函数
        return new BestFirstSearch(explored, frontier, PuzzleState.predictor(type));
    }
}
