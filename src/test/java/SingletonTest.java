import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * https://en.wikipedia.org/wiki/Singleton_pattern
 * http://c2.com/cgi/wiki?SingletonPattern
 * http://stackoverflow.com/questions/137975/what-is-so-bad-about-singletons
 * - Hide dependencies
 * - Violation of SRP - they manage their life-cycle
 * - Tightly coupled - hard to test
 * - Carry state around for the lifetime of the application
 *
 * @author hugh
 */
public class SingletonTest {
    enum SingletonPlusDecorator {
        INSTANCE;

        public String execute(String arg) {
            return String.format("+%s+", arg);
        }
    }

    @Test
    public void singletonTest() throws Exception {
        assertThat(SingletonPlusDecorator.INSTANCE.execute("abc"), is("+abc+"));
    }
}
