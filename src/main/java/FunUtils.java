import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FunUtils {

    public static <P, R> List<R> map(List<P> list, final Function<P, R> function) {
        final List<R> ids = new ArrayList<R>();

        forEach(list, new Function<P, Void>() {
           public Void exec(P s) {
                ids.add(function.exec(s));
                return (null);
           }
        });

        return ids;
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





