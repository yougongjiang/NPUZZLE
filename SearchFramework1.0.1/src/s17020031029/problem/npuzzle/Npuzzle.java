package s17020031029.problem.npuzzle;

import core.problem.Action;
import core.problem.Problem;
import core.problem.State;
import core.solver.Node;

import java.util.Deque;

public class Npuzzle extends Problem {

    public Npuzzle(State initialState, State goal) {
        super(initialState, goal);
    }

    @Override
    public boolean solvable() {
        return true;
    }

    @Override
    public int stepCost(State state, Action action) {
        return 1;
    }

    @Override
    protected boolean applicable(State state, Action action) {
        int size = ((PuzzleState)state).getSize();
        int i = ((PuzzleState)state).getPos()[0][0];
        int j = ((PuzzleState)state).getPos()[0][1];
        String dire = ((PuzzleAction)action).getDire();
        if(dire.equals("up") && i != 0)
            return true;
        else if(dire.equals("down") && i != size-1)
            return true;
        else if(dire.equals("left") && j != 0)
            return true;
        else if(dire.equals("right") && j != size-1)
            return true;
        return false;
    }

    @Override
    public void showSolution(Deque<Node> path) {
        if (path == null){
            System.out.println("No Solution.");
            return;
        }
        for(Node node : path)
        {
            if(node.getAction() != null)
                node.getAction().draw();
            node.getState().draw();
        }
    }

    @Override
    public void draw() {
        getInitialState().draw();
    }

    @Override
    public void printPath(Deque<Node> path) {

    }
}
