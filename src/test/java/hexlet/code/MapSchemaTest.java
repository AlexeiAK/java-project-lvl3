package hexlet.code;

import hexlet.code.schemas.BaseSchema;
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

    @Test
    void testShape() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        final int positiveNumber = 5;
        final int negativeNumber = -5;
        final int humanAge = 100;

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", humanAge);

        boolean actual1 = schema.isValid(human1);
        boolean expected1 = true;
        Assertions.assertEquals(expected1, actual1);


        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);

        boolean actual2 = schema.isValid(human2);
        boolean expected2 = true;
        Assertions.assertEquals(expected2, actual2);


        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", ""); // false
        human3.put("age", null); // true

        boolean actual3 = schema.isValid(human3);
        boolean expected3 = false; // because ["name", ""] is false
        Assertions.assertEquals(expected3, actual3);


        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", negativeNumber);

        boolean actual4 = schema.isValid(human4);
        boolean expected4 = false;
        Assertions.assertEquals(expected4, actual4);


        Map<String, Object> human5 = new HashMap<>();
        human5.put("NOname", "Valya");
        human5.put("age", positiveNumber);

        boolean actual5 = schema.isValid(human5);
        boolean expected5 = false;
        Assertions.assertEquals(expected5, actual5);
    }
}
