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

    //todo merge sort
    public static <P> List<P> sort(List<P> students, Function2<P, Boolean> comparator) {
        int j;
        boolean flag = true;
        P temp;

        while (flag)
        {
            flag = false;
            for(j = 0; j < students.size() - 1; j++)
            {
                if (comparator.exec(students.get(j), students.get(j + 1))) {
                    temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                    flag = true;
                }
            }
        }
        return students;
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
