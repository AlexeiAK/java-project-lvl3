package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class StringSchema {
    private final List<Predicate> appliedMethods = new ArrayList<>();


    public final boolean isValid(String testedString) {
        Predicate<String> resultPredicate = null;

        if (appliedMethods.size() == 0) {
            resultPredicate = s -> s == null || s.equals("");

        } else {
            for (Predicate predicate : appliedMethods) {
                resultPredicate = predicate;
            }
        }

        return resultPredicate.test(testedString);
    }

    public final StringSchema required() {
        Predicate<String> resultPredicate = s -> s != null && s.length() > 0;
        appliedMethods.add(resultPredicate);

        return this;
    }

    public final StringSchema contains(String testedString) {
        Predicate<String> resultPredicate = s -> s.contains(testedString);
        appliedMethods.add(resultPredicate);

        return this;
    }

    public final StringSchema minLength(int length) {
        Predicate<String> resultPredicate = s -> s.length() >= length;
        appliedMethods.add(resultPredicate);

        return this;
    }
}
