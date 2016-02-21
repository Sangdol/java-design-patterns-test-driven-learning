import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * http://c2.com/cgi/wiki?TemplateMethodPattern
 *
 * - Define the skeleton of an algorithm in an operation, deferring some steps to subclasses.
 * - TemplateMethod lets subclasses redefine certain steps of an algorithm without changing the algorithm's structure.
 *
 * @author hugh
 */
public class TemplateMethodTest {
    static abstract class Staff {
        protected int experience;

        abstract int getWorkHour();
        abstract int getHourlyPay();

        public int getPay() {
            return this.getHourlyPay() * this.getWorkHour();
        }
    }

    static class Ceo extends Staff {
        public Ceo(int experience) {
            this.experience = experience;
        }

        @Override
        int getWorkHour() {
            return 40;
        }

        @Override
        int getHourlyPay() {
            return experience * 2;
        }
    }

    static class Developer extends Staff {
        public Developer(int experience) {
            this.experience = experience;
        }

        @Override
        int getWorkHour() {
            return 50;
        }

        @Override
        int getHourlyPay() {
            return experience;
        }
    }

    @Test
    public void payTest() throws Exception {
        Ceo ceo = new Ceo(10);
        Developer developer = new Developer(15);

        assertThat(ceo.getPay(), is(800));
        assertThat(developer.getPay(), is(750));
    }
}
