package s17020031029.problem.npuzzle;

import core.problem.Action;

public class PuzzleAction extends Action {
    private String dire;

    public PuzzleAction(){
    	
    }
    public PuzzleAction(String a){
    	dire = a;
    }
    public void setDire(String dire) {
        this.dire = dire;
    }

    public String getDire() {
        return dire;
    }

    @Override
    public void draw() {
        System.out.println("->"+getDire());
    }

    @Override
    public int stepCost() {
        return 1;
    }
}
