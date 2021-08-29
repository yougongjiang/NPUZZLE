package xu.problem.pathfinding;

import algs4.util.In;
import core.problem.Problem;
import core.runner.EngineFeeder;
import org.junit.jupiter.api.Test;



import java.util.ArrayList;

class PathFeederTest {

    @Test
    void getProblems() {
        //生成一个具体的EngineFeeder：FeederXu，引擎饲养员徐老师:)
        EngineFeeder feeder = new FeederXu();

        //从文件中读入问题的实例，NPuzzle问题
        In problemInput = new In("resources/pathfinding.txt");
        //feeder从文件获取所有问题实例
        ArrayList<Problem> problems = feeder.getProblems(problemInput);

        for (Problem problem : problems){
            problem.draw();
        }

    }
}