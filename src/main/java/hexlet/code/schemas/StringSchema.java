package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema {

    public final StringSchema required() {
        Predicate<String> resultPredicate = s -> s != null && s.length() > 0;
        addPredicate(resultPredicate);

        return this;
    }

    public final StringSchema contains(String testedString) {
        Predicate<String> resultPredicate = s -> s.contains(testedString);
        addPredicate(resultPredicate);

        return this;
    }

    public final StringSchema minLength(int length) {
        Predicate<String> resultPredicate = s -> s.length() >= length;
        addPredicate(resultPredicate);

        return this;
    }
}
