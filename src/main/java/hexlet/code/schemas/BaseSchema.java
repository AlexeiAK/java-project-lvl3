package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema {
    private final List<Predicate<Object>> checks = new ArrayList<>();

    public final boolean isValid(Object testedObject) {
        for (Predicate predicate : checks) {
            boolean resultOfPredicate = predicate.test(testedObject);

            if (!resultOfPredicate) {
                return false;
            }
        }

        return true;
    }

    protected final void addCheck(Predicate predicate) {
        checks.add(predicate);
    }
}
