import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * http://butunclebob.com/ArticleS.UncleBob.IuseVisitor
 * - This solves SRP by moving the logic into a new object.
 *
 * http://c2.com/cgi/wiki?VisitorPattern
 * Intent
 *   - Represent an operation to be performed on the elements of an object structure.
 *   - Visitors lets you define a new operation without changing the classes of the elements
 *     on which it operates.
 *
 * @author hugh
 */
public class VisitorTest {
    interface Staff {
        String getPay();
        String accept(StaffVisitor visitor);
    }
    
    static class Ceo implements Staff {
        @Override
        public String getPay() {
            return "100";
        }

        @Override
        public String accept(StaffVisitor visitor) {
            return visitor.visit(this);
        }
    }

    static class Developer implements Staff {
        @Override
        public String getPay() {
            return "200";
        }

        @Override
        public String accept(StaffVisitor visitor) {
            return visitor.visit(this);
        }
    }

    interface StaffVisitor {
        String visit(Ceo ceo);
        String visit(Developer developer);
    }

    static class PrinterStaffVisitor implements StaffVisitor {
        @Override
        public String visit(Ceo ceo) {
            return "CEO: " + ceo.getPay();
        }

        @Override
        public String visit(Developer developer) {
            return "Developer: " + developer.getPay();
        }
    }

    @Test
    public void visitorTest() throws Exception {
        Staff ceo = new Ceo();
        Staff dev = new Developer();
        StaffVisitor visitor = new PrinterStaffVisitor();

        assertThat(ceo.accept(visitor), is("CEO: 100"));
        assertThat(dev.accept(visitor), is("Developer: 200"));
    }
}
