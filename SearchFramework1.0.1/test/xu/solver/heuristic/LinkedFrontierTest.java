package xu.solver.heuristic;

import core.problem.State;
import core.solver.Node;
import core.solver.heuristic.EvaluationType;
import xu.problem.pathfinding.Direction;
import xu.problem.pathfinding.Move;
import xu.problem.pathfinding.Point;
import xu.problem.pathfinding.Position;

import static org.junit.jupiter.api.Assertions.*;

class LinkedFrontierTest {

    @org.junit.jupiter.api.Test
    void offer() {
        LinkedFrontier frontier = new LinkedFrontier(Node.evaluator(EvaluationType.FULL));
        State position = new Position(4, 4);
        Node parent = new Node(position, null, null, 0, 15);

        position = new Position(5, 5);
        Node node = new Node(position, parent, new Move(Direction.SE), 0, 15);
        frontier.offer(node);

        position = new Position(5, 6);
        node = new Node(position, node, new Move(Direction.S), 1, 13);
        frontier.offer(node);
        position = new Position(6, 6);
        node = new Node(position, node, new Move(Direction.E), 2, 13);
        frontier.offer(node);
        position = new Position(7, 6);
        node = new Node(position, node, new Move(Direction.E), 3, 9);
        frontier.offer(node);

        for (Node node1: frontier){
            System.out.println(node1);
        }
        System.out.println();

        position = new Position(9, 6);
        node = new Node(position, node, new Move(Direction.E), 2, 13);

        System.out.println(frontier.getNode(node.getState()));

        position = new Position(6, 6);
        node = new Node(position, node, new Move(Direction.W), 22, 13);

        System.out.println(frontier.contains(node));

        System.out.println();

        position = new Position(5, 6);
        node = new Node(position, node, new Move(Direction.S), 3, 10);
        frontier.discardOrReplace(node);

        for (Node node1: frontier){
            System.out.println(node1);
        }

        System.out.println();
        while (!frontier.isEmpty()){
            frontier.poll().draw();
        }
    }
}