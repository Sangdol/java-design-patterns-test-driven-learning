import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * https://en.wikipedia.org/wiki/Builder_pattern
 * @author hugh
 */
public class BuilderTest {
    static class Person {
        private int age;
        private String name;

        static class Builder {
            private int age;
            private String name;
            public Builder() {}

            public Builder age(int age) {
                this.age = age;
                return this;
            }

            public Builder name(String name) {
                this.name = name;
                return this;
            }

            public Person build() {
                return new Person(this);
            }
        }

        private Person(Builder builder) {
            this.age = builder.age;
            this.name = builder.name;
        }
    }

    @Test
    public void builderTest() throws Exception {
        Person person = new Person.Builder().age(34).name("SH").build();
        assertThat(person.age, is(34));
        assertThat(person.name, is("SH"));
    }
}
