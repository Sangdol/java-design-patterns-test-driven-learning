import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * https://en.wikipedia.org/wiki/Composite_pattern
 * http://c2.com/cgi/wiki?CompositePattern
 *
 * Intent
 *   - Compose objects into tree structure that represent whole-part hierarchies.
 *   - Composite lets clients treat individual objects and compositions of objects uniformly.
 *   - A leaf has the same interface as a node.
 *
 * @author hugh
 */
public class CompositeTest {
    static class CompositeBoard implements DecoratorTest.Board {
        List<DecoratorTest.Board> boards = new ArrayList<>();

        @Override
        public String draw() {
            return boards.stream()
                    .map(DecoratorTest.Board::draw)
                    .collect(Collectors.joining());
        }

        public void add(DecoratorTest.Board board) {
            boards.add(board);
        }
    }

    @Test
    public void compositeTest() throws Exception {
        DecoratorTest.Board starBoard = new DecoratorTest.StarBoard();
        DecoratorTest.Board plusBoard = new DecoratorTest.PlusDecorator(starBoard);
        CompositeBoard compositeBoard = new CompositeBoard();
        compositeBoard.add(starBoard);
        compositeBoard.add(plusBoard);

        assertThat(compositeBoard.draw(), is("***+***+"));
    }
}
