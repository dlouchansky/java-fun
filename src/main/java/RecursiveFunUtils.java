import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecursiveFunUtils {

    public static <P, R> List<R> map(List<P> list, Function<P, R> function) {
        return map(list, function, new ArrayList<R>());
    }

    public static <P, R> List<R> map(List<P> list, Function<P, R> function, List<R> result) {
        if (list.size() == 0) {
            return result;
        }

        R obj = function.exec(list.remove(0));
        result.add(obj);
        return map(list, function, result);
    }

    public static <P> List<P> filter(List<P> list, Function<P, Boolean> comparator) {
        return filter(list, comparator, new ArrayList<P>());
    }

    public static <P> List<P> filter(List<P> list, Function<P, Boolean> comparator, List<P> result) {
        if (list.size() == 0) {
            return result;
        }

        P obj = list.remove(0);
        if (comparator.exec(obj))
            result.add(0, obj);
        return filter(list, comparator, result);
    }

    public static <P> List<P> sort(List<P> persons, Function2<P, Boolean> comparator) {
        List<P> result = new ArrayList<P>();
        if (persons.size() == 1) {
            result.add(persons.get(0));
            return result;
        } else if (persons.size() == 0) {
            return result;
        }

        List<P> left = sort(persons.subList(0, persons.size() / 2), comparator);
        List<P> right = sort(persons.subList(persons.size() / 2, persons.size()), comparator);

        result = merge(left, right);
        return result;
    }

    public static <P> List<P> merge(List<P> left, List<P> right) {
        ArrayList<P> result = new ArrayList<P>();
        // todo
        return result;
    }

    public static <P, R> Map<R, List<P>> group(List<P> list, final Function<P, R> function) {
        return group(list, function, new HashMap<R, List<P>>());
    }

    public static <P, R> Map<R, List<P>> group(List<P> list, final Function<P, R> function, Map<R, List<P>> result) {
        if (list.size() == 0) {
            return result;
        }

        P obj = list.remove(0);
        R field = function.exec(obj);

        if (result.get(field) == null) {
            result.put(field, new ArrayList<P>());
        }
        result.get(field).add(obj);

        return group(list, function, result);
    }

    public static <P> void forEach(List<P> list, Function<P, Void> function) {
        if (list.size() != 0) {
            P obj = list.remove(0);
            function.exec(obj);
            forEach(list, function);
        }
    }
}
