package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import hexlet.code.schemas.StringSchema;

class StringSchemaTest {
    private final int minLenthCheck = 3;

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
        boolean expected1 = true;
        Assertions.assertEquals(expected1, actual1);
    }

    @Test
    void testAll() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        schema.required();
        schema.isValid("what does the fox say"); // true
        schema.isValid("hexlet"); // true

        boolean actual2 = schema.isValid(null); // false
        boolean expected2 = false;
        Assertions.assertEquals(expected2, actual2);
    }
}
