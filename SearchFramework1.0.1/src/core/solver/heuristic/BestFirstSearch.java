package core.solver.heuristic;

import java.util.*;


import algs4.util.Stopwatch;
import core.problem.Problem;
import core.problem.State;
import core.runner.HeuristicType;
import core.solver.*;
import s17020031029.problem.npuzzle.PuzzleAction;
import s17020031029.problem.npuzzle.PuzzleConfiguration;
import s17020031029.problem.npuzzle.PuzzleState;
/**
 * 不能被继承的类，final类
 */
public final class BestFirstSearch implements Searcher {

	//已经访问过的节点集合
	private final Set<State> explored;

	//还未扩展的节点队列
	private final AbstractFrontier frontier;

	private final Predictor predictor;
	/**
	 * 构造函数
	 * @param explored 具体的状态类的Set hashSet
	 * @param frontier Node对象的一个优先队列，可以确定一个状态所对应的结点是否在frontier中，
	 */
	public BestFirstSearch(Set<State> explored, AbstractFrontier frontier, Predictor predictor) {
		this.explored = explored;
		this.frontier = frontier;
		this.predictor = predictor;
	}

	@Override
	public Deque<Node> search(Problem problem)
	{
		//如果可直接判断问题是否可解，无解时直接返回解路径为null
//		if (!problem.solvable()){
//			return null;
//		}
//		frontier.clear();
//		explored.clear();
		//搜索树的根节点
//		Fringe fringe = new Fringe();
//		Explored explored = new Explored();
//		Node root = problem.root(predictor);
		//this.frontier.add(root);
//		fringe.insert(root);
		//fringe.insert(root);
		
//		while (true) {
//			
//			if (fringe.isEmpty()) {
//				System.out.println("empty");
//				return null;	//失败
//			}
//				
//
//			Node node = fringe.pop(); //choose the lowest-cost node in frontier
//			//如果已经到达目标状态，
//			if (problem.goal(node.getState())) {
//				return generatePath(node);    //找他的父节点
//			}
//			//将当前结点放入explored表中
//			explored.insert(node.getState());
//			//System.out.println("parent:");
//			//node.getState().draw();
//			for (Node child : problem.childNodes(node, predictor)) {
//				// 如果新扩展的节点，没有在Explored和Fringe中出现过。
//				// 因为两个node的状态相同，则视为二者相同（equals函数），
//				// 所以contains函数判断frontier中是否存在跟child的状态相同的结点
//				//System.out.println("child:");
//				//System.out.println(explored.contains(child.getState())+""+fringe.contains(child.getState()));
//				//child.getState().draw();
//				if (!explored.contains(child.getState()) && !fringe.contains(child.getState())) {
//					//System.out.println("insert");
//					fringe.insert(child);
//				}
//				else {
//					//child出现在Explored或Fringe中
//					//在启发函数满足单调条件的前提下，如果child是出现在Explored表里的节点，肯定不在Fringe中；
//					//而且到达这个节点的新路径肯定不会比旧路径更优
//					//System.out.println("delete");
//					Node revisited = fringe.revisited(child.getState());
//					if (revisited != null && revisited.evaluation() > child.evaluation()) {
//						fringe.replace(revisited, child);
//					}
//					//frontier.add(child);
//				}
//			}
//		}	
		//使用框架数据结构
		//如果可直接判断问题是否可解，无解时直接返回解路径为null
				if (!problem.solvable()){
					return null;
				}
				frontier.clear();
				explored.clear();
				//搜索树的根节点
				//System.out.println(frontier.isEmpty()+" "+explored.isEmpty());
				Node root = problem.root(predictor);
				this.frontier.add(root);
				//(frontier.getNode(root.getState())).getState().draw();;
				
		
				while (true) {
					
					if (frontier.isEmpty()) {
						System.out.println("empty");
						return null;	//失败
					}
						

					Node node = frontier.poll(); //choose the lowest-cost node in frontier
					//如果已经到达目标状态，
					if (problem.goal(node.getState())) {
						System.out.println(frontier.size()+" "+explored.size());
						return generatePath(node);    //找他的父节点
					}
					//将当前结点放入explored表中

					explored.add(node.getState());
					//System.out.println("parent:");
					//node.getState().draw();
					for (Node child : problem.childNodes(node, predictor)) {
						// 如果新扩展的节点，没有在Explored和Fringe中出现过。
						// 因为两个node的状态相同，则视为二者相同（equals函数），
						// 所以contains函数判断frontier中是否存在跟child的状态相同的结点
						//System.out.println("child:");
						//System.out.println(explored.contains(child.getState())+""+frontier.contains(child));
						//child.getState().draw();
						
						if (!explored.contains(child.getState()) && !frontier.contains(child)) {
							//System.out.println("insert");
							frontier.offer(child);
						}
						else {
							//child出现在Explored或Fringe中
							//在启发函数满足单调条件的前提下，如果child是出现在Explored表里的节点，肯定不在Fringe中；
							//而且到达这个节点的新路径肯定不会比旧路径更优
							//System.out.println("delete");
					
							//frontier.discardOrReplace(child);
							
							Node revisited = frontier.getNode(child.getState());
//							if(frontier.contains(child)) {
//								System.out.println("yes");
//								child.getState().draw();
//								System.out.println(child.getState().hashCode());
//								revisited.getState().draw();
//								System.out.println(revisited.getState().hashCode());
//							}
							if (revisited != null && revisited.evaluation() > child.evaluation()) {
								frontier.replace(revisited, child);//��child�ڵ����Fringe�е� revisited�ڵ�
							}
						}
					}
				}	
	}
	int [][] result;
	int newcut = 0;
	int flag = 0;
	int cutoff = 0;
	int num = 0;
	Node node;
	public static int[] u = {0, 0, 1, -1};
	public static int[] p = {1, -1, 0, 0};
	Stack<Node> s = new Stack<Node>();
	Stack<Node> s1 = new Stack<Node>();
	int side,sx,sy,Rstep;
	PuzzleState goal;
	PuzzleState initial;
	PuzzleAction[] actions = {new PuzzleAction("left"),new PuzzleAction("right"),new PuzzleAction("up"),new PuzzleAction("down")};
	HashMap<Integer,Node> map = new HashMap<Integer,Node>();
	int[][] State ,mat;
	int[] xx,yy,action;
	public Deque<Node> idaSearch(Problem problem){
		if (!problem.solvable()){
			return null;
		}
		flag = 0;
		Rstep=0;
		result = new int[200][20];
		action = new int[200];
		PuzzleState state = (PuzzleState) problem.getInitialState();
		initial = (PuzzleState) problem.getInitialState();
//		System.out.println();
		xx = new int[20];
		yy = new int[20];
		Node root = problem.root(predictor);
		node = root;
//		root.getState().draw();
		side = ((PuzzleState)root.getState()).getSize();
		goal = (PuzzleState)problem.getGoal();
		
		State = ((PuzzleState)node.getState()).getPuzzle();
		PuzzleConfiguration database = new PuzzleConfiguration();
		for(int i=0;i<side;i++)
			for(int j=0;j<side;j++) {
				State[i][j] = ((PuzzleState)node.getState()).getPuzzle()[i][j]; 
				xx[i*side+j+1] = i;
				yy[i*side+j+1] = j;
				if(State[i][j] == 0) {
					sx = i;
					sy = j;
				}
//				System.out.println(State[i][j]+","+xx[State[i][j]]+","+yy[State[i][j]]);
			}
		xx[0] = side-1;
		yy[0] = side-1;
//		mat = new int[side][side];
//		for (int i = 0; i < side; i++) 
//			for(int j = 0; j < side;j++) {
//				mat[i][j] = i*side + j+1;
////				System.out.print(mat[i][j]);
//			}
//		mat[side -1][side-1] = 0;
		
		Stopwatch time = new Stopwatch();
		for (cutoff = root.getHeuristic(); cutoff <= 100; cutoff = dfs(0, root.getHeuristic(),4)) {
//			for(int ni=0;ni<side*side;ni++)
//				System.out.print(mat[ni]+" ");
//			System.out.println("");
//			if(cutoff == 21) System.out.println(timer1.elapsedTime());
//			System.out.println(cutoff);
//			System.out.println(num);
			num = 0;
			if (flag == 1) {
//				System.out.println("find success"+ Rstep);
				break;
			}
		}
		System.out.println(time.elapsedTime());
		for(int i=0;i<Rstep;i++) {
			
			for(int j=0;j<side;j++) {
				for(int k=0;k<side;k++) {
					
				State[j][k] = result[i][j*side+k];
//				System.out.print(State[j][k]);
				}
			}
//			System.out.println("    "+action[i]);
			state = new PuzzleState(State,side);
//			if(i == Rstep-1) {
//				node = new Node(state,null,actions[action[i]],0,0);
//			}
			node = new Node(state,node,actions[action[i+1]],0,0);
		}
		//node = new Node(goal,node,actions[action[0]],0,0);
//		initial.draw();
//		cutoff = 52;
		
		//cutoff = 81;
		//System.out.println(cutoff);
		
//		explored.clear();
//		explored.add(root.getState());
//		Node n;
//		num = 0;
		
//		Stopwatch time = new Stopwatch();
//		while(true) {
//			//node = root;
////			explored.clear();
////			explored.add(node.getState());
////			map.clear();
//			newcut = 10000;
////			System.out.println(cutoff);
////			System.out.println(num);
//			//num = 0;
//			node = ida(node,problem);
////			if(cutoff==27) System.out.println(time.elapsedTime());
//			if (node != null && problem.goal(node.getState())) {
//				//	System.out.println(frontier.size()+" "+explored.size());
//					return generatePath(node);    //找他的父节点
//			}
//			else {
//				cutoff = newcut;
//			}
////			flag++;
////			num++;
//		}
//		
		return generatePath(node);
		
	}
public int dfs(int step, int h, int las) {
		
		//g(n)+h(n)=f(n) ����f(n) bound ����޶�  ���ù�ֵ����������f(n)����depth��·��
		if (step + h > cutoff) return step + h; //
//		if(layer[h]==-1)layer[h]=step;
//		if(step>layer[h]+15){
//			System.out.printf("enter\n");
//			return step+h;
//		}
		num++;
//		if(cutoff == 44 || cutoff == 21) {
//			for(int i = 0;i<side;i++)
//				for(int j = 0;j<side;j++){
//				
//				System.out.print(State[i][j]+" ");
//				if(j == side-1) System.out.println("");
//				
//			}
//			System.out.println(num+" "+(h+step));
//		}
		if (h == 0) { // ��������״̬ ���g(n)����
			//		System.out.println(step);
			flag = 1;
			Rstep = step;
			return step;
		}
		
		//int pos1 = pos;
		int ret = 127, x = sx, y = sy;
		int dx, dy, tar, ht, temp, i;
		for (i = 0; i < 4; i++) { //�ĸ�������չ
			dx = x + u[i];
			dy = y + p[i];
			if (dx < 0 || dy < 0 || dx > side - 1 || dy > side - 1 || !ok(i, las)) continue;
			
			//tar = (dx * side) + dy; //�������չ�����½ڵ��һά���� 2,2 2*4+2= 10
			State[x][y] = State[dx][dy]; // 0�����������չ�����ĵ������ a.mat[10]=11;
			State[dx][dy] = 0;//�൱��swap()
//			pos = tar;
			sx = dx;sy = dy;
			//map.put(mat.toString().hashCode(), mat);

			ht = getH(State);
			//ht = h - (Math.abs(xx[State[x][y]] - dx) + Math.abs(yy[State[x][y]]- dy))  + (Math.abs(xx[State[x][y]] - x) + Math.abs(yy[State[x][y]] - y));
			if (step + ht <= cutoff)
				for (int k = 0; k < side; k++)
					for(int l = 0; l < side;l++) {
						
						result[step][k*side+l] = State[k][l];
						action[step] = i;
					}
			temp = dfs(step + 1, ht, i);
			if (flag == 1)  return temp;
			if (ret > temp) ret = temp;
			State[dx][dy] = State[x][y];
			State[x][y] = 0;
//			pos = pos1;
			sx = x;sy = y;
			
		}
		//System.out.println(num);
		return ret;
	}
//	public Node ida(Node node,Problem p) {
//		
//		if (node.evaluation() > cutoff) return node;
//		
//		if(node.getHeuristic() == 0) {
//			return node;
//		}
//		//num++;
////		if(cutoff == 27) {
////			node.getState().draw();
////			System.out.println(num+" "+node.evaluation());
////		}
//		explored.add(node.getState());
//		map.put(node.getState().hashCode(), node);
//		//s.push(node);
//		//扩展子结点
////		for (Node child : p.childNodes(node, predictor)) {
//		PuzzleState state = ((PuzzleState)(node.getState()));
//		
//		int[][] state1 = new int[side][side];
//		for(int i=0;i<side;i++)
//			for(int j=0;j<side;j++) {
//				state1[i][j] = state.getPuzzle()[i][j];
//			}
//		int dx,dy,x,y,ht,temp,i,gx,gy;
//		x = state.getPos()[0][0];
//		y = state.getPos()[0][1];
//		PuzzleState s = new PuzzleState(state1,side);
////		System.out.println(x+" "+y+" "+side);
//		for (i = 0; i < 4; i++) { //生成子节点
//			
//			dx = x + u[i];
//			dy = y + this.p[i];
////			System.out.println(dx+" "+dy);
//			if (dx < 0 || dy < 0 || dx >side - 1 || dy > side - 1) continue;
//			
////			s = (PuzzleState)state.next(actions[i]);
//			gx = goal.getPos()[state1[dx][dy]][0];
//			gy = goal.getPos()[state1[dx][dy]][1];
//			state1[x][y] = state1[dx][dy];
//			state1[dx][dy] = 0;
//			ht = node.getHeuristic() + (Math.abs(x-gx) + Math.abs(y-gy)) - (Math.abs(dx-gx) + Math.abs(dy-gy));
////			ht = s.manhattan(goal);
//			s = (PuzzleState)state.next(actions[i]);
//			Node child = new Node(s, node, actions[i], node.getPathCost()+1, ht);
//			if( node.getParent() != null && node.getParent().getState().equals(child.getState()) ) {
//				continue;
//			}
//			//child.getState().draw();
//			//System.out.println(child.evaluation());
////			Node x1 = map.get(child.getState().hashCode());
//			state1[dx][dy] = state1[x][y];
//			state1[x][y] = 0;
////			if( x1 != null && x1.evaluation() <= child.evaluation() ) {
////				continue;
////			}
////			int flag1 = 0;
////			for(Node x:s) 
////				if(x.getState().equals(child.getState()))
////					flag1 = 1;
////			if( flag1 == 1) {
////				continue;
////			if( node.getParent() != null && node.getParent().getState().equals(child.getState()) ) {
////				continue;
////			}else {
//				//explored.add(child.getState());
////				if(child.evaluation() > cutoff) {
//					//s1.push(child);
////					System.out.println(child.evaluation());
////					child.getState().draw();
////					this.newcut = newcut < child.evaluation() ? this.newcut : child.evaluation();
////					continue;
//					
////				}else {
//					
////					if(child.getHeuristic() == 0)  {
////						return child;
////					}
////					
////					System.out.println(child.evaluation());
////					child.getState().draw();
//					Node n = ida(child,p);
//					if(n != null && n.getHeuristic() == 0) {
//						//n.getState().draw();
//						return n;
//					}
//					if(newcut > n.evaluation() ) newcut = n.evaluation();
//					//child = null;
//					//s.pop();
////				}
////				s = null;
//				
////				state = i%2==0 ? (PuzzleState)state.next(actions[i+1]) : (PuzzleState)state.next(actions[i-1]);
////			}
//			
//		}
//		return node;
//	}
	static final int[] tilePositions = {-1, 0, 0, 1, 2, 1, 2, 0, 1, 3, 4, 2, 3, 5, 4, 5};
	static final int[] tileSubsets = {-1, 1, 0, 0, 0, 1, 1, 2, 2, 1, 1, 2, 2, 1, 2, 2};
	public int getH(int[][] tmp) {
	
		int index0 = 0, index1 = 0, index2 = 0;
		for (int pos = 15; pos >= 0; --pos) {
			final int tile = tmp[pos/side][pos%side];
			if (tile != 0) {
				final int subsetNumber = tileSubsets[tile];
				switch (subsetNumber) {
					case 2:
						index2 |= pos << (tilePositions[tile] << 2);
						break;
					case 1:
						index1 |= pos << (tilePositions[tile] << 2);
						break;
					default:
						index0 |= pos << (tilePositions[tile] << 2);
						break;
				}
			}
		}
		return PuzzleConfiguration.costTable_15_puzzle_0[index0] +
				PuzzleConfiguration.costTable_15_puzzle_1[index1] +
				PuzzleConfiguration.costTable_15_puzzle_2[index2];
	}
	public boolean ok(int x,int y){
		if(x>y){
			int ff=x;
			x=y;
			y=ff;
		}
		if(x==0&y==1) return false;  //�����  1 1 Ϊ1
		if(x==2&&y==3) return false;
		return true;
	}
	public int expandedNode(){
		return explored.size();
	}
}