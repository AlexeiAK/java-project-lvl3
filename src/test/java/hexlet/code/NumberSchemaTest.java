package hexlet.code;

import hexlet.code.schemas.NumberSchema;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NumberSchemaTest {
    private final int negativeNumber = -10;
    private final int positiveNumber = 10;

    @Test
    void testCrudeValid() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        boolean actual1 = schema.isValid(null);
        boolean expected1 = true;
        Assertions.assertEquals(expected1, actual1);
    }

    @Test
    void testRequired() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        schema.required();

        boolean actual1 = schema.isValid(null);
        boolean expected1 = false;
        Assertions.assertEquals(expected1, actual1);

        boolean actual2 = schema.isValid(positiveNumber);
        boolean expected2 = true;
        Assertions.assertEquals(expected2, actual2);

        boolean actual3 = schema.isValid("5");
        boolean expected3 = false;
        Assertions.assertEquals(expected3, actual3);
    }

    @Test
    void testPositive() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        boolean actual1 = schema.positive().isValid(positiveNumber);
        boolean expected1 = true;
        Assertions.assertEquals(expected1, actual1);

        boolean actual2 = schema.isValid(negativeNumber);
        boolean expected2 = false;
        Assertions.assertEquals(expected2, actual2);

        boolean actual3 = schema.isValid(null);
        boolean expected3 = true;
        Assertions.assertEquals(expected3, actual3);
    }

    @Test
    void testRange() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        final int minOfRange = 5;
        final int maxOfRange = 10;
        final int lessThenRange = 4;
        final int moreThenRange = 11;

        schema.range(minOfRange, maxOfRange);

        boolean actual1 = schema.isValid(minOfRange);
        boolean expected1 = true;
        Assertions.assertEquals(expected1, actual1);

        boolean actual2 = schema.isValid(maxOfRange);
        boolean expected2 = true;
        Assertions.assertEquals(expected2, actual2);

        boolean actual3 = schema.isValid(lessThenRange);
        boolean expected3 = false;
        Assertions.assertEquals(expected3, actual3);

        boolean actual4 = schema.isValid(moreThenRange);
        boolean expected4 = false;
        Assertions.assertEquals(expected4, actual4);
    }
}
