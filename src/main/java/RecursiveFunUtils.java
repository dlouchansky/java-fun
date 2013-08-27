import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecursiveFunUtils {

    public static <P, R> List<R> map(List<P> list, Function<P, R> function) {
        if (list.size() == 0) {
            return new ArrayList<R>();
        }

        if (list.size() == 1) {
            List<R> processed = new ArrayList<R>();
            processed.add(function.exec(list.get(0)));
            return processed;
        }

        R obj = function.exec(list.remove(0));
        List<R> processed = map(list, function);
        processed.add(0, obj);
        return processed;
    }

    public static <P> List<P> filter(List<P> list, Function<P, Boolean> comparator) {
        if (list.size() == 0) {
            return new ArrayList<P>();
        }

        if (list.size() == 1) {
            List<P> processed = new ArrayList<P>();
            if(comparator.exec(list.get(0))){
                processed.add(list.get(0));
            }
            return processed;
        }

        P obj = list.remove(0);
        List<P> processed = filter(list, comparator);
        if (comparator.exec(obj))
            processed.add(0, obj);

        return processed;
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
        if (list.size() == 0) {
            return new HashMap<R, List<P>>();
        }

        if (list.size() == 1) {
            Map<R, List<P>> result = new HashMap<R, List<P>>();
            P obj = list.get(0);
            R field = function.exec(obj);
            result.put(field, new ArrayList<P>());
            result.get(field).add(obj);
            return result;
        }

        P obj = list.remove(0);
        R field = function.exec(obj);
        Map<R, List<P>> result = group(list, function);

        if (result.get(field) == null) {
            result.put(field, new ArrayList<P>());
        }
        result.get(field).add(obj);
        return result;
    }

    public static <P> void forEach(List<P> list, Function<P, Void> function) {
        if (list.size() != 0) {
            P obj = list.remove(0);
            function.exec(obj);
            forEach(list, function);
        }
    }
}
