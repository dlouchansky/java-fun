import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class FunUtilsTest {
    ArrayList<Student> students;

    @Before
    public void setUp() {
        students = new ArrayList<Student>();
        students.add(new Student(3, "Three"));
        students.add(new Student(2, "Two"));
        students.add(new Student(1, "One"));
        students.add(new Student(4, "One"));
    }

    @Test
    public void testSort() {
        List<Student> result = FunUtils.sort(students, new Function2<Student, Boolean>() {
            public Boolean exec(Student a1, Student a2) {
                return a1.getName().compareTo(a2.getName()) > 0;
            }
        });

        assertEquals(result.get(0).getId(), new Integer(1));
    }

    @Test
    public void testFilter() {
        List<Student> filtered = FunUtils.filter(students, new Function<Student, Boolean>() {
            public Boolean exec(Student a) {
                return a.getName().equals("One") || a.getName().equals("Three");
            }
        });

        assertEquals(filtered.size(), 3);
    }

    @Test
    public void testMap() {
        List<Integer> ids = FunUtils.map(students, new Function<Student, Integer>() {
            public Integer exec(Student a) {
                return a.getId();
            }
        });

        assertEquals(ids.size(), 4);
        assertEquals(ids.get(0), new Integer(3));
    }

    @Test
    public void testGroup() {
        Map<String, List<Student>> result = FunUtils.group(students, new Function<Student, String>() {
            public String exec(Student a) {
                return a.getName();
            }
        });

        assertEquals(result.get("One").size(), 2);
    }

}
