package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {

    public final MapSchema required() {
        Predicate<Object> resultPredicate = o -> o instanceof Map<?, ?>;
        getAppliedMethods().add(resultPredicate);

        return this;
    }

    public final MapSchema sizeof(int requiredQuantity) {
        Predicate<Map<?, ?>> resultPredicate = o -> o.size() == requiredQuantity;
        getAppliedMethods().add(resultPredicate);

        return this;
    }
}
