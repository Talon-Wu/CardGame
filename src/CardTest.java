import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

/*
 *@ Talon
 *11.21
 *2023/11/21
 */public class CardTest {

    public Card card;
    @Before
    public void setUp() throws Exception {
        card = new Card(5);
    }

    @After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void testToString() {
        String st =  card.toString();
        assertEquals("5",st);
    }

    @org.junit.Test
    public void getValue() {
        assertEquals(5,card.getValue());
    }

    @org.junit.Test
    public void setValue() {
        // test 10 and -10
        card.setValue(10);
        assertEquals(10,card.getValue());
        boolean testSet = card.setValue(-10);
        assertEquals(false,testSet);
    }
}