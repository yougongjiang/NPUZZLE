package s17020031029.runner;

import algs4.util.In;
import core.problem.Problem;
import core.runner.EngineFeeder;
import core.runner.HeuristicType;
import core.solver.Node;
import core.solver.heuristic.BestFirstSearch;
import s17020031029.problem.npuzzle.FeederStu;

import java.util.ArrayList;
import java.util.Deque;

public class PuzzleRunner {
    public static void main(String[] args) {
        long start,now;
        //从文件中读入问题的实例
        In problemInput = new In("../resources/9-puzzle.txt");

        EngineFeeder feeder = new FeederStu();

        //feeder从文件获取所有问题实例
        ArrayList<Problem> problems = feeder.getProblems(problemInput);

        
/*
        for (Problem problem : problems){
            //使用AStar引擎求解问题
            start = System.currentTimeMillis();
            Deque<Node> path = astar.search(problem);
            //解的可视化
            problem.getInitialState().draw();
            problem.showSolution(path);
            now = System.currentTimeMillis();
            System.out.println((now-start)/1000.0+"s");
            //仅打印路径
            //problem.printPath(path);
            System.out.println("最少操作次数："+path.size());
            System.out.println(astar.expandedNode());
            System.out.println();
        }

        System.out.println("==============================================================");
 */
//      从Feeder获取所使用的搜索引擎 AStar
//        BestFirstSearch astar = (BestFirstSearch) ((FeederStu) feeder).getSearcher(HeuristicType.MISPLACED);
//        astar = (BestFirstSearch) ((FeederStu) feeder).getSearcher(HeuristicType.MANHATTAN);
//        for (Problem problem : problems){
//            //使用AStar引擎求解问题
//            start = System.currentTimeMillis();
//            Deque<Node> path = astar.search(problem);
//            //解的可视化
//            //problem.getInitialState().draw();
//            problem.showSolution(path);
//            now = System.currentTimeMillis();
//            System.out.println((now-start)/1000.0+"s");
//            //仅打印路径
//            problem.printPath(path);
//            System.out.println("最少操作次数："+path.size());
//            System.out.println(astar.expandedNode());
//            System.out.println();
//        }
            
//          //从Feeder获取所使用的搜索引擎 AStar
//            //BestFirstSearch astar = (BestFirstSearch) ((FeederStu) feeder).getSearcher(HeuristicType.MISPLACED);
            BestFirstSearch astar = (BestFirstSearch) ((FeederStu) feeder).getSearcher(HeuristicType.MANHATTAN);
            for (Problem problem : problems){
                //使用AStar引擎求解问题
                start = System.currentTimeMillis();
                Deque<Node> path = astar.idaSearch(problem);
                //解的可视化
                //problem.getInitialState().draw();
                problem.showSolution(path);
                now = System.currentTimeMillis();
                System.out.println((now-start)/1000.0+"s");
                //仅打印路径
                problem.printPath(path);
                System.out.println("最少操作次数："+path.size());
//              System.out.println(astar.expandedNode());
                System.out.println();


        }
   }
}
