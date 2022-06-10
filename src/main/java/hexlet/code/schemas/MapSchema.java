package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {

    public final MapSchema required() {
        Predicate<Object> resultPredicate = map -> map instanceof Map<?, ?>;
        addCheck(resultPredicate);

        return this;
    }

    public final MapSchema sizeof(int requiredQuantity) {
        Predicate<Object> resultPredicate = map ->
            map instanceof Map<?, ?> && ((Map<?, ?>) map).size() == requiredQuantity;
        addCheck(resultPredicate);

        return this;
    }

    public final void shape(Map<String, BaseSchema> schemas) {
        Predicate<Object> resultPredicate = map -> isValidAllValuesOfMap(schemas, (Map<String, Object>) map);

        addCheck(resultPredicate);
    }


    private boolean isValidAllValuesOfMap(Map<String, BaseSchema> schemas, Map<String, Object> map) {
        boolean result = false;

        for (Map.Entry<String, BaseSchema> pair: schemas.entrySet()) {
            String fieldName = pair.getKey();
            BaseSchema fieldCheck = pair.getValue();

            Object fieldValue = map.get(fieldName);

            result = fieldCheck.isValid(fieldValue);

            // If one of result is false, then the others will be false
            if (!result) {
                break;
            }
        }

        return result;
    }
}
