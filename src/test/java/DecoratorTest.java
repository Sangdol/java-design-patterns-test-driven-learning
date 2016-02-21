import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * http://c2.com/cgi/wiki?DecoratorPattern
 * http://stackoverflow.com/questions/6366385/decorator-pattern-for-io
 * @author hugh
 */
public class DecoratorTest {

    interface Board {
        String draw();
    }

    static class StarBoard implements Board {
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
