import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * https://en.wikipedia.org/wiki/Command_pattern
 * http://c2.com/cgi/wiki?CommandPattern
 *
 * Encapsulate a request as an object, thereby letting you parameterize clients with different requests,
 * queue or log requests, and support undoable operations. AKA, Action, Transaction
 *
 * @author hugh
 */
public class CommandTest {
    interface Command {
        String execute();
    }

    static class CommandQueue {
        private Queue<Command> queue = new LinkedList<>();

        public void add(Command command) {
            queue.add(command);
        }

        public String pollExecute() {
            Command command = queue.poll();
            if (command != null) {
                return command.execute();
            }
            return null;
        }
    }

    static class StarBoardCommand implements Command {
        private DecoratorTest.Board starBoard = new DecoratorTest.StarBoard();

        @Override
        public String execute() {
            return starBoard.draw();
        }
    }

    static class PlusBoardCommand implements Command {
        private DecoratorTest.Board plusBoard =
                new DecoratorTest.PlusDecorator(new DecoratorTest.StarBoard());

        @Override
        public String execute() {
            return plusBoard.draw();
        }
    }

    @Test
    public void commandTest() throws Exception {
        CommandQueue queue = new CommandQueue();
        queue.add(new StarBoardCommand());
        queue.add(new PlusBoardCommand());

        assertThat(queue.pollExecute(), is("***"));
        assertThat(queue.pollExecute(), is("+***+"));
    }
}
