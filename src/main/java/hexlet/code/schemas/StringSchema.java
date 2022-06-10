package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema {

    public final StringSchema required() {
        Predicate<Object> resultPredicate = s ->
            s instanceof String && !((String) s).isEmpty();
        addPredicate(resultPredicate);

        return this;
    }

    public final StringSchema contains(String testedString) {
        Predicate<Object> resultPredicate = s ->
            s instanceof String && ((String) s).contains(testedString);
        addPredicate(resultPredicate);

        return this;
    }

    public final StringSchema minLength(int length) {
        Predicate<Object> resultPredicate = s ->
            s instanceof String && ((String) s).length() >= length;
        addPredicate(resultPredicate);

        return this;
    }
}
