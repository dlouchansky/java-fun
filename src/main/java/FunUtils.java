import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FunUtils {

    public static <P, R> List<R> map(List<P> list, final Function<P, R> function) {
        final List<R> processed = new ArrayList<R>();

        forEach(list, new Function<P, Void>() {
           public Void exec(P s) {
                processed.add(function.exec(s));
                return (null);
           }
        });

        return processed;
    }

    public static <P, R> List<R> recursiveMap(List<P> list, Function<P, R> function) {
        if (list.size() == 0) {
            return new ArrayList<R>();
        }

        if (list.size() == 1) {
            List<R> processed = new ArrayList<R>();
            processed.add(function.exec(list.get(0)));
            return processed;
        }

        R obj = function.exec(list.remove(0));
        List<R> processed = recursiveMap(list, function);
        processed.add(0, obj);
        return processed;
    }

    public static <P> List<P> filter(List<P> list, final Function<P, Boolean> comparator) {
        final List<P> result = new ArrayList<P>();

        forEach(list, new Function<P, Void>() {
            public Void exec(P s) {
                if(comparator.exec(s)){
                    result.add(s);
                }
                return (null);
            }
        });

        return result;
    }

    public static <P> List<P> recursiveFilter(List<P> list, Function<P, Boolean> comparator) {
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
        List<P> processed = recursiveFilter(list, comparator);
        if (comparator.exec(obj))
            processed.add(0, obj);

        return processed;
    }

    // bubble sort
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
        final Map<R, List<P>> result = new HashMap<R, List<P>>();

        forEach(list, new Function<P, Void>() {
            public Void exec(P s) {
                R field = function.exec(s);

                if (result.get(field) == null) {
                    result.put(field, new ArrayList<P>());
                }

                result.get(field).add(s);
                return (null);
            }
        });

        return result;
    }

    public static <P> void forEach(List<P> list, Function<P, Void> function) {
        for (P item : list) {
            function.exec(item);
        }
    }

}





