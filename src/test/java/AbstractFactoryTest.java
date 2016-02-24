import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * https://en.wikipedia.org/wiki/Abstract_factory_pattern
 * http://c2.com/cgi/wiki?AbstractFactoryPattern
 *
 * Intent
 *   - Provide an interface for creating families of related or dependent objects
 *     without specifying their concrete classes.
 *
 * @author hugh
 */
public class AbstractFactoryTest {
    interface Board {
        String draw();
    }
    
    interface BoardFactory {
        Board createBoard();
    }
    
    public static class StarBoard implements Board {
        @Override
        public String draw() {
            return "*";
        }
    }
    
    public static class PlusBoard implements Board {
        @Override
        public String draw() {
            return "+";
        }
    }

    class StarBoardFactory implements BoardFactory {
        @Override
        public Board createBoard() {
            return new StarBoard();
        }
    }

    class PlusBoardFactory implements BoardFactory {
        @Override
        public Board createBoard() {
            return new PlusBoard();
        }
    }
    
    @Test
    public void factoryTest() throws Exception {
        Board board = createBoard("star");
        assertThat(board.draw(), is("*"));

        board = createBoard("plus");
        assertThat(board.draw(), is("+"));
    }

    private Board createBoard(String type) {
        BoardFactory factory;
        switch (type) {
            case "star":
                factory = new StarBoardFactory();
                break;
            case "plus":
                factory = new PlusBoardFactory();
                break;
            default:
                throw new IllegalArgumentException();
        }
        return factory.createBoard();
    }
}
