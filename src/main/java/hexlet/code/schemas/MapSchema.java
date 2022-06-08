package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {

    public final MapSchema required() {
        Predicate<Object> resultPredicate = map -> map instanceof Map<?, ?>;
        addPredicate(resultPredicate);

        return this;
    }

    public final MapSchema sizeof(int requiredQuantity) {
        Predicate<Map<?, ?>> resultPredicate = map -> map.size() == requiredQuantity;
        addPredicate(resultPredicate);

        return this;
    }

    public final void shape(Map<String, BaseSchema> schemas) {
        Predicate<Map<String, Object>> resultPredicate = map -> isValidAllValuesOfMap(schemas, map);

        addPredicate(resultPredicate);
    }

    private boolean isValidAllValuesOfMap(Map<String, BaseSchema> schemas, Map<String, Object> map) {
        boolean result = false;

        for (Map.Entry<String, BaseSchema> pair: schemas.entrySet()) {
            String fieldCheck = pair.getKey();
            BaseSchema schemasBaseSchema = pair.getValue();

            if (map.containsKey(fieldCheck)) {
                Object fieldValue = map.get(fieldCheck);

                result = schemasBaseSchema.isValid(fieldValue);

                // If one of result is false, then the others will be false
                if (!result) {
                    break;
                }
            } else {
                break; // if one of mapKey missing, then false
            }
        }

        return result;
    }
}
