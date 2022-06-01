package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {
    public final NumberSchema required() {
        Predicate<Object> resultPredicate = i -> i instanceof Integer;
        addPredicate(resultPredicate);

        return this;
    }

    public final NumberSchema positive() {
        Predicate<Integer> resultPredicate = i -> i == null || i > 0;
        addPredicate(resultPredicate);

        return this;
    }

    public final NumberSchema range(int min, int max) {
        Predicate<Integer> resultPredicate = i -> i >= min && i <= max;
        addPredicate(resultPredicate);

        return this;
    }
}
