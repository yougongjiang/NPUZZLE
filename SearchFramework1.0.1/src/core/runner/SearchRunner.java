package core.runner;

import core.problem.Problem;
import core.solver.Node;
import algs4.util.In;
import core.solver.heuristic.BestFirstSearch;
import xu.problem.pathfinding.FeederXu;

import java.util.ArrayList;
import java.util.Deque;

public class SearchRunner {

    public static void main(String[] args) {

        //从文件中读入问题的实例，寻路问题
        In problemInput = new In("resources/pathfinding.txt");

        //生成一个具体的EngineFeeder：FeederXu，引擎饲养员徐老师:)
        EngineFeeder feeder = new FeederXu();

        //feeder从文件获取所有问题实例
        ArrayList<Problem> problems = feeder.getProblems(problemInput);

        //从Feeder获取所使用的搜索引擎 AStar
        BestFirstSearch astar = (BestFirstSearch) feeder.getScoreSearcher();

        for (Problem problem : problems){
            //使用AStar引擎求解问题
            Deque<Node> path = astar.search(problem);
            //解的可视化
            problem.showSolution(path);
            //仅打印路径
            problem.printPath(path);
            System.out.println(astar.expandedNode());
            System.out.println();
        }

        System.out.println("==============================================================");

        //从Feeder获取所使用的搜索引擎 Dijkstra
        BestFirstSearch dijkstra = (BestFirstSearch) feeder.getDijkstra();

        for (Problem problem : problems){
            //使用AStar引擎求解问题
            Deque<Node> path = dijkstra.search(problem);
            //解的可视化
            problem.showSolution(path);
            //仅打印路径
            problem.printPath(path);
            System.out.println(dijkstra.expandedNode());
            System.out.println();
        }
    }
}