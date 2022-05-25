package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {
    public final NumberSchema required() {
        Predicate<Object> resultPredicate = i -> i instanceof Integer;
        getAppliedMethods().add(resultPredicate);

        return this;
    }

    public final NumberSchema positive() {
        Predicate<Integer> resultPredicate = i -> (Integer) i > 0;
        getAppliedMethods().add(resultPredicate);

        return this;
    }

    public final NumberSchema range(int min, int max) {
        Predicate<Integer> resultPredicate = i -> i >= min && i <= max;
        getAppliedMethods().add(resultPredicate);

        return this;
    }
}
