package hexlet.code;

import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class MapSchemaTest {

    @Test
    void testCrudeValid() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        boolean actual1 = schema.isValid(null);
        boolean expected1 = true;
        Assertions.assertEquals(expected1, actual1);
    }

    @Test
    void testRequired() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        schema.required();

        boolean actual1 = schema.isValid(null);
        boolean expected1 = false;
        Assertions.assertEquals(expected1, actual1);

        boolean actual2 = schema.isValid(new HashMap());
        boolean expected2 = true;
        Assertions.assertEquals(expected2, actual2);

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        boolean actual3 = schema.isValid(data);
        boolean expected3 = true;
        Assertions.assertEquals(expected3, actual3);
    }

    @Test
    void testSizeof() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        final int requiredMapSize = 2;
        schema.sizeof(requiredMapSize);

        boolean actual1 = schema.isValid(data);
        boolean expected1 = false;
        Assertions.assertEquals(expected1, actual1);

        data.put("key2", "value2");
        boolean actual2 = schema.isValid(data);
        boolean expected2 = true;
        Assertions.assertEquals(expected2, actual2);
    }
}
