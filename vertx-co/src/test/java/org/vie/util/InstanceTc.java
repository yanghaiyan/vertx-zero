package org.vie.util;

import org.junit.Test;
import org.vie.util.log.Annal;
import top.test.UnitBase;

public class InstanceTc extends UnitBase {

    @Test
    public void testInteger() {
        final User user = new User();
        Instance.invoke(user, "invoke", 22);
    }

    @Test
    public void testString() {
        final User user = new User();
        Instance.invoke(user, "invoke", "String");
    }

    @Test
    public void testNoArg() {
        final User user = new User();
        Instance.invoke(user, "invoke");
    }

    @Test
    public void testWrapperInteger() {
        final Email email = new Email();
        Instance.invoke(email, "invoke", new Integer(22));
    }

    @Test
    public void testUnboxInteger() {
        final Email email = new Email();
        Instance.invoke(email, "invoke", 22);
    }
}

class Email {

    private static final Annal LOGGER = Annal.get(Email.class);

    public void invoke(final Integer integer) {
        LOGGER.info("Email: invoke(Integer) " + integer);
    }

    public void invoke(final int integer) {

        LOGGER.info("Email: invoke(int)" + integer);
    }
}

class User {
    private static final Annal LOGGER = Annal.get(User.class);

    public void invoke() {
        LOGGER.info("User: invoke. ");
    }

    public void invoke(final String name) {
        LOGGER.info("User: invoke(String)" + name);
    }

    public void invoke(final int integer) {
        LOGGER.info("User: invoke(Integer)" + integer);
    }
}