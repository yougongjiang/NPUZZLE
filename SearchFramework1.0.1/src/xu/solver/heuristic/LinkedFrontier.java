package xu.solver.heuristic;

import core.problem.State;
import core.solver.Node;
import core.solver.heuristic.AbstractFrontier;
import s17020031029.problem.npuzzle.PuzzleState;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Collection;
/**
 * 封装了LinkedList数据结构的Frontier
 */
public class LinkedFrontier extends AbstractFrontier {
    //底层实现用的是LinkedList
    private final LinkedList<Node> nodeList = new LinkedList<>();

    public LinkedFrontier(Comparator<Node> evaluator) {
        super(evaluator);
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
        for (Node node: nodeList){
            if (node.getState().equals(s)){
                return node;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Object o) {
        /*Iterator<Node> it = nodeList.iterator();
        while(it.hasNext())
        {
            if(it.next().equals(o))
            {
                it.remove();
                return true;
            }
        }
        return false;*/
        return nodeList.remove(o);
    }

    /**
     * 将结点node插入到当前有序链表中
     * @param node  要插入的结点
     * @return      插入成功返回true
     */
    @Override
    public boolean add(Node node) {
        int j = 0;

        for (Node value : nodeList) {
            if (evaluator.compare(value, node) >= 0) {
                break;
            }
            j++;
        }
        nodeList.add(j, node);
        return true;
    }

    @Override
    public Iterator<Node> iterator() {
        return nodeList.iterator();
    }

    @Override
    public int size() {
        return nodeList.size();
    }

    @Override
    public boolean offer(Node node) {
        return this.add(node);
    }

    @Override
    public Node poll() {
        return nodeList.poll();
    }

    @Override
    public Node peek() {
        return nodeList.peek();
    }
}
