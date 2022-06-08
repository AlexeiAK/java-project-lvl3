package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.NumberSchema;

public class Validator {
    private final BaseSchema stringSchema = new StringSchema();
    private final BaseSchema numberSchema = new NumberSchema();
    private final BaseSchema mapSchema = new MapSchema();

    public final StringSchema string() {
        return (StringSchema) stringSchema;
    }

    public final NumberSchema number() {
        return (NumberSchema) numberSchema;
    }

    public final MapSchema map() {
        return (MapSchema) mapSchema;
    }
}
