package xu.problem.pathfinding;

import core.problem.Action;

public class Move extends Action {

    private final Direction direction;

    public Move(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public void draw() {
        System.out.println(toString());
    }

    @Override
    public int stepCost() {
        return Direction.cost(direction);
    }

    @Override
    public String toString() {
        return direction.symbol() + "";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;

        if (obj instanceof Move) {
            Move another = (Move) obj;
            //两个Node对象的状态相同，则认为是相同的
            return this.direction.equals(another.direction);
        }
        return false;
    }
}
