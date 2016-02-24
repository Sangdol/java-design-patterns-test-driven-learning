import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * https://en.wikipedia.org/wiki/Factory_method_pattern
 *
 * Intent
 *   - Define an interface for creating an object, but let subclasses decide
 *     which class to instantiate. Factory method lets a class defer instantiation
 *     to subclasses.
 *
 * @author hugh
 */
public class FactoryMethodTest {

    abstract class BoardGame {
        public String play() {
            AbstractFactoryTest.Board board = createBoard();
            return String.format("&%s&", board.draw());
        }

        protected abstract AbstractFactoryTest.Board createBoard();
    }

    class StarBoardGame extends BoardGame {
        @Override
        protected AbstractFactoryTest.Board createBoard() {
            return new AbstractFactoryTest.StarBoard();
        }
    }
    
    class PlusBoardGame extends BoardGame {
        @Override
        protected AbstractFactoryTest.Board createBoard() {
            return new AbstractFactoryTest.PlusBoard();
        }
    }
    
    @Test
    public void factoryTest() throws Exception {
        BoardGame game = new StarBoardGame();
        assertThat(game.play(), is("&*&"));
        
        game = new PlusBoardGame();
        assertThat(game.play(), is("&+&"));
    }
}
