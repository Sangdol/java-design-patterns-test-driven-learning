import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * http://c2.com/cgi/wiki?DecoratorPattern
 * http://stackoverflow.com/questions/6366385/decorator-pattern-for-io
 *
 * Intent
 * - Attach additional responsibilities to an object dynamically.
 * - Decorators provide a flexible alternative to subclassing for extending functionality.
 * AKA wrapper
 * Motivation
 * - Sometimes we want to add responsibilities to individual objects, not to an entire class
 * - Inheritance is inflexible
 * - puts a border around every subclass instance
 *
 * @author hugh
 */
public class DecoratorTest {

    interface Board {
        String draw();
    }

    public static class StarBoard implements Board {
        @Override
        public String draw() {
            return "***";
        }
    }

    static class PlusDecorator implements Board {
        Board board;

        public PlusDecorator(Board board) {
            this.board = board;
        }

        @Override
        public String draw() {
            return String.format("+%s+", board.draw());
        }
    }

    @Test
    public void decoratorTest() throws Exception {
        Board starBoard = new StarBoard();
        assertThat(starBoard.draw(), is("***"));

        Board plusDecoratedBoard = new PlusDecorator(starBoard);
        assertThat(plusDecoratedBoard.draw(), is("+***+"));
    }
}
