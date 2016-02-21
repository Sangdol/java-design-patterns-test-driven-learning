import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * http://c2.com/cgi/wiki?ObserverPattern
 * Intent
 *   - Define a one-to-many dependency between objects so that when one object changes state,
 *     all its dependents are notified and updated automatically.
 * Alias
 *   - Subject Observer, Publish Subscribe, Callback
 *
 * http://c2.com/cgi/wiki?DeprecatingTheObserverPattern
 * - Deprecating Observer Pattern in favor of reactive programming
 *   - as it's hard and error-prone
 *
 * http://www.tutorialspoint.com/design_pattern/observer_pattern.htm
 * - Implementation reference
 *
 * @author hugh
 */
public class ObserverTest {

    static class Subject {
        private List<Observer> observers = new ArrayList<>();
        private int currentHour;

        public void setCurrentHour(int hour) {
            this.currentHour = hour;
        }

        public void attach(Observer observer) {
            observers.add(observer);
        }

        public void notifyAllObservers() {
            observers.forEach(observer -> observer.update(currentHour));
        }
    }

    interface Observer {
        void update(int currentHour);
    }

    @SuppressWarnings("unused")
    interface Staff {
        String getState();
    }

    static class Developer implements Observer, Staff {
        String state = "Sleeping";

        @Override
        public void update(int currentHour) {
            if (currentHour < 10 || currentHour > 19) {
                state = "At Home";
            } else {
                state = "At Office";
            }
        }

        @Override
        public String getState() {
            return state;
        }
    }


    @Test
    public void observerTest() throws Exception {
        Subject subject = new Subject();
        Developer dev1 = new Developer();
        Developer dev2 = new Developer();

        assertThat(dev1.getState(), is("Sleeping"));

        subject.attach(dev1);
        subject.attach(dev2);

        subject.setCurrentHour(9);
        subject.notifyAllObservers();
        assertThat(dev1.getState(), is("At Home"));
        assertThat(dev2.getState(), is("At Home"));
    }
}
