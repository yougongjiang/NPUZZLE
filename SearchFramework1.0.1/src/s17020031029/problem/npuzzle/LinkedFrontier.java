package s17020031029.problem.npuzzle;

import core.problem.State;

import core.solver.Node;
import core.solver.heuristic.AbstractFrontier;
import s17020031029.problem.npuzzle.PuzzleState;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Collection;
/**
 * 封装了LinkedList数据结构的Frontier
 */
public class LinkedFrontier extends AbstractFrontier{
	
	//重写比较器
    private Comparator<Node> comparator = new Comparator<Node>(){
        @Override
        public int compare(Node s1, Node s2) {
            return s1.compareTo(s2);
        }
    };
    //底层实现用的是LinkedList
   // private final LinkedList<Node> nodeList = new LinkedList<>();

    PriorityQueue<Node> nodes = new PriorityQueue<Node>(comparator);
    private final HashMap<Integer,Node> map = new HashMap<Integer,Node>();
    //private final Set<State> explored = new HashSet<>();

    public LinkedFrontier(Comparator<Node> type) {
    	super(type);
    }
    
  
    /**
     * 获取 Frontier 中，状态为 s 的节点
     *
     * @param s 状态
     * @return 存在：  相应的状态为 s 的节点；
     * 不存在：null
     */
    @Override
    protected Node getNode(State s) {
//        for (Node node: nodeList){
//            if (node.getState().equals(s)){
//                return node;
//            }
//        }
    	
        return map.get(s.hashCode());
    }

//    public boolean remove(Node from) {
//        /*Iterator<Node> it = nodeList.iterator();
//        while(it.hasNext())
//        {
//            if(it.next().equals(o))
//            {
//                it.remove();
//                return true;
//            }
//        }
//        return false;*/
//
//        return nodes.remove(from);
//    }

    /**
     * 将结点node插入到当前有序链表中
     * @param node  要插入的结点
     * @return      插入成功返回true
     */
    public boolean add(Node node) {
        //int j = 0;

//        for (Node value : nodeList) {
//            if (evaluator.compare(value, node) > 0) {
//                break;
//            }
//            if(evaluator.compare(value, node) == 0 && value.getHeuristic() >= node.getHeuristic()) {
//            	break;
//            }
//            j++;
//        }
        nodes.add(node);
        map.put(node.getState().hashCode(), node);
        //explored.add(node.getState());
        return true;
    }

    public Iterator<Node> iterator() {
        return nodes.iterator();
    }

    public int size() {
        return nodes.size();
    }

    public boolean offer(Node node) {
        return this.add(node);
    }

    public Node poll() {
    	
    	Node ans = nodes.poll();
    	map.remove(ans.getState().hashCode());
    	//explored.remove(ans.getState().hashCode());
        return ans;
    }

    public Node peek() {
        return nodes.peek();
    }
    @Override
    public boolean contains(Object n) {
    //	return map.containsValue(((Node)n).getState());
//    	return map.get(((Node)n).getState().hashCode())!= null;
  //  	return map.get(((Node)n).hashCode())!=null;
    	return map.containsKey(((Node)n).getState().hashCode());
    	//return nodes.contains(n);
    	
//    	return map.get(((Node)n).getState().hashCode()) != null;
    }
  //Fringe是否为空
    public boolean isEmpty() {
        return nodes.isEmpty(); //Fix me
    }
  //用节点to代替Fringe中的from节点
    public void replace(Node from, Node to) {
        map.put(from.getState().hashCode(),to);
        
        //nodes.remove(from);
        nodes.add(to);
        //Fix me
    }
    
    public void clear() {
    	
    	nodes.clear();
    	map.clear();
    }
}
