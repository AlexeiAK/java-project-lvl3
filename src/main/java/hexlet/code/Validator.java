package hexlet.code;

import hexlet.code.schemas.StringSchema;

public class Validator {
    private StringSchema schema = new StringSchema();

    public final StringSchema string() {
        return schema;
    }
}
