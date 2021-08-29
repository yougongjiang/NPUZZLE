package xu.problem.pathfinding;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void next() {
        Position position = new Position(5, 6);
        assertEquals(position.next(new Move(Direction.S)), new Position(6, 6));

        Position next = (Position) position.next(new Move(Direction.E)); // (5,7)
        assertEquals(next, new Position(5, 7));
        assertEquals(next.next(new Move(Direction.N)), new Position(4, 7));
        assertEquals(next.next(new Move(Direction.W)), new Position(5, 6));

    }
}