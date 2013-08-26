import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FunUtils {
    public static <P, R> List<R> map(List<P> list, Function<P, R> function) {
        List<R> ids = new ArrayList<R>();
        for (P item : list) {
            ids.add(function.exec(item));
        }
        return ids;
    }

    public static <P> List<P> filter(List<P> students, Function<P, Boolean> comparator) {
        List<P> result = new ArrayList<P>();
        for (P student : students) {
            if(comparator.exec(student)){
                result.add(student);
            }
        }
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

    public static <P, R> Map<R, List<P>> group(List<P> list, Function<P, R> function) {
        Map<R, List<P>> result = new HashMap<R, List<P>>();

        for (P item : list) {
            R field = function.exec(item);

            if (result.get(field) == null) {
               result.put(field, new ArrayList<P>());
            }

            result.get(field).add(item);
        }

        List<R> ids = new ArrayList<R>();

        return result;
    }
}





