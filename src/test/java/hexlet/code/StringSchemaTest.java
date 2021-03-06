package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import hexlet.code.schemas.StringSchema;

class StringSchemaTest {
    private final int minLenthCheck = 50;

    @Test
    void testNoString() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        final int noStringForContains = 5;
        final int noStringForMinLength = 777;

        schema.contains("5");
        boolean actual1 = schema.isValid(noStringForContains);
        boolean expected1 = false;
        Assertions.assertEquals(expected1, actual1);

        schema.minLength(minLenthCheck);
        boolean actual2 = schema.isValid(noStringForMinLength);
        boolean expected2 = false;
        Assertions.assertEquals(expected2, actual2);
    }

    @Test
    void testCrudeValid() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        boolean actual1 = schema.isValid("");
        boolean expected1 = true;
        Assertions.assertEquals(expected1, actual1);

        boolean actual2 = schema.isValid(null);
        boolean expected2 = true;
        Assertions.assertEquals(expected2, actual2);
    }

    @Test
    void testRequired() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        schema.required();

        boolean actual1 = schema.isValid("what does the fox say");
        boolean expected1 = true;
        Assertions.assertEquals(expected1, actual1);

        boolean actual2 = schema.isValid(null);
        boolean expected2 = false;
        Assertions.assertEquals(expected2, actual2);

        boolean actual3 = schema.isValid("");
        boolean expected3 = false;
        Assertions.assertEquals(expected3, actual3);
    }

    @Test
    void testContains() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        boolean actual1 = schema.contains("what").isValid("what does the fox say");
        boolean expected1 = true;
        Assertions.assertEquals(expected1, actual1);

        schema.contains("does");

        boolean actual2 = schema.isValid("what does the fox say");
        boolean expected2 = true;
        Assertions.assertEquals(expected2, actual2);
    }

    @Test
    void testMinLength() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        boolean actual1 = schema.minLength(minLenthCheck).isValid("abc");
        boolean expected1 = false;
        Assertions.assertEquals(expected1, actual1);
    }

    @Test
    void testWithDiffChain() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        schema.contains("what");

        boolean actual1 = schema.isValid("what does the fox say");
        boolean expected1 = true;
        Assertions.assertEquals(expected1, actual1);

        schema.contains("whatthe");

        boolean actual2 = schema.isValid("what does the fox say");
        boolean expected2 = false;
        Assertions.assertEquals(expected2, actual2);

        schema.contains("does");

        boolean actual3 = schema.isValid("what does the fox say");
        boolean expected3 = false; // because already been false
        Assertions.assertEquals(expected3, actual3);
    }
}
