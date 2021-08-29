package s17020031029.problem.npuzzle;

import core.problem.Action;
import core.problem.State;
import core.runner.HeuristicType;
import core.solver.heuristic.Predictor;
import java.util.ArrayList;
import java.util.EnumMap;

public class PuzzleState extends State {
    private int[][] puzzle;
    private int[][] pos;
    private int size;
    //private String str;
    public PuzzleState(int[][] state,int size){
    	this.puzzle = new int[size][size];
        this.pos = new int[size*size][2];
        this.size = size;
        
        for(int i=0;i<size;i++) {
            //System.arraycopy(puzzle[i],0,this.puzzle[i],0,getSize());
            for(int j=0;j<size;j++)
            {
                int no = state[i][j];
                this.puzzle[i][j] = no;
                this.pos[no][0] = i;
                this.pos[no][1] = j;    //标记位置
                //str += no;
            }
        }
    }
    public PuzzleState(){
    	
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int[][] getPuzzle() {
        return puzzle;
    }

    public int getSize() {
        return size;
    }

    public int[][] getPos() {
        return pos;
    }

    public void setPuzzle(int[][] puzzle) {
        this.puzzle = new int[this.getSize()][this.getSize()];
        this.pos = new int[getSize()*getSize()][2];
       // str = "";
        for(int i=0;i<this.getSize();i++) {
            //System.arraycopy(puzzle[i],0,this.puzzle[i],0,getSize());
            for(int j=0;j<this.getSize();j++)
            {
                int no = puzzle[i][j];
                this.puzzle[i][j] = no;
                this.pos[no][0] = i;
                this.pos[no][1] = j;    //标记位置
                //str += no;
            }
        }
    }

    @Override
    public void draw() {
        for (int i = 0; i < this.getSize(); i++)
            System.out.print("+---");
        System.out.println("+");
        for (int i = 0; i < this.getSize(); i++) {
            for (int j = 0; j < this.getSize(); j++) {
                if (this.puzzle[i][j] != 0 && this.puzzle[i][j] < 10)
                    System.out.print("| " + this.puzzle[i][j] + " ");
                else if(this.puzzle[i][j] == 0)
                    System.out.print("| # ");
                else
                    System.out.print("|" + this.puzzle[i][j] + " ");
            }
            System.out.println("|");
            for (int j = 0; j < this.getSize(); j++)
                System.out.print("+---");
            System.out.println("+");
        }
    }

    @Override
    public State next(Action action) {

        int[][] state = new int[size][size];
        for(int i=0;i<size;i++)
        	for(int j=0;j<size;j++) {
        		state[i][j] = puzzle[i][j];
        	}
        String dire = ((PuzzleAction)action).getDire();
        int i = this.getPos()[0][0],j = this.getPos()[0][1];
        if (dire.equals("up")) {
            state[i][j] = state[i - 1][j];
            state[i - 1][j] = 0;
        } else if (dire.equals("down")) {
            state[i][j] = state[i + 1][j];
            state[i + 1][j] = 0;
        } else if (dire.equals("left")) {
            state[i][j] = state[i][j - 1];
            state[i][j - 1] = 0;
        } else if (dire.equals("right")) {
            state[i][j] = state[i][j + 1];
            state[i][j + 1] = 0;
        }
        return new PuzzleState(state,size);
    }

    @Override
    public Iterable<? extends Action> actions() {
        ArrayList<PuzzleAction> actions = new ArrayList<>();
        String[] list = {"right","left","down","up"};
        for(String str : list)
        {
            PuzzleAction action = new PuzzleAction();
            action.setDire(str);
            actions.add(action);
        }
        return actions;
    }

    @Override
    public boolean equals(Object obj) {
//        if (obj == this) return true;
//
//       if (obj instanceof PuzzleState || obj instanceof State) {
//            PuzzleState another = (PuzzleState) obj;
//            //两个Node对象的状态相同，则认为是相同的
//            for(int i=0;i<getSize();i++)
//                for(int j=0;j<getSize();j++) {
//                    if (this.getPuzzle()[i][j] != another.getPuzzle()[i][j])
//                        return false;
//                }
//            return true;
//        }
//       return false;
       return this.hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode()
    {
//        int num=17;
//        for(int i=0;i<getSize();i++)
//            for(int j=0;j<getSize();j++)
//                num = 17 + 31*str.hashCode();
    	int num=0;
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++) {
                //System.out.println(j);
               // System.out.println(board[i][j]);
                if (puzzle[i][j] != 0) {
                    num ^= FeederStu.getZobrist()[i * size + j][puzzle[i][j]];
                }
            }
        }
        return num;
    }

    //枚举映射，存放不同类型的启发函数
    private static final EnumMap<HeuristicType, Predictor> predictors = new EnumMap<>(HeuristicType.class);
    static{
        predictors.put(HeuristicType.MISPLACED,
                (state, goal) -> ((PuzzleState)state).misplaced((PuzzleState)goal));
        predictors.put(HeuristicType.MANHATTAN,
                (state, goal) -> ((PuzzleState)state).manhattan((PuzzleState)goal));
        predictors.put(HeuristicType.DISJOINT_PATTERN,
                (state, goal) -> ((PuzzleState)state).disjoint_pattern((PuzzleState)goal));
    }
    public static Predictor predictor(HeuristicType type){
        Predictor predictor= predictors.get(type);    //map方法获取键为type的值
        return predictor;
    }
    //3个启发函数
    private int misplaced(PuzzleState goal){
        int num = 0;
        int[][] st = getPuzzle(), go = goal.getPuzzle();
        for (int i = 0; i < getSize(); i++)
            for (int j = 0; j < getSize(); j++) {
                if (st[i][j] != go[i][j] && go[i][j] != 0)
                    num++;
            }
        return num;
    }

    public int manhattan(PuzzleState goal){
        int num=0;
        int[][] st = getPos(), go = goal.getPos();
        for(int i=1;i<getSize()*getSize();i++)
        {
            int ix = st[i][0],iy = st[i][1],gx = go[i][0],gy = go[i][1];
            num += Math.abs(ix - gx) + Math.abs(iy - gy);
        }
        return num;
    }

    private int disjoint_pattern(PuzzleState goal){return 0;}
}
