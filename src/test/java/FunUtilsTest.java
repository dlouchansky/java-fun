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
    }

    @Test
    public void testSortEmpty() {
        List<Student> result = FunUtils.sort(students, new Function2<Student, Boolean>() {
            public Boolean exec(Student a1, Student a2) {
                return a1.getName().compareTo(a2.getName()) > 0;
            }
        });

        assertEquals(0, result.size());
    }

    @Test
    public void testSortOneElemByName() {
        students.add(new Student(3, "Three"));

        List<Student> result = FunUtils.sort(students, new Function2<Student, Boolean>() {
            public Boolean exec(Student a1, Student a2) {
                return a1.getName().compareTo(a2.getName()) > 0;
            }
        });

        assertEquals(1, result.size());
    }

    @Test
    public void testSortThreeElemsByName() {
        students.add(new Student(3, "Three"));
        students.add(new Student(2, "Two"));
        students.add(new Student(1, "One"));

        List<Student> result = FunUtils.sort(students, new Function2<Student, Boolean>() {
            public Boolean exec(Student a1, Student a2) {
                return a1.getName().compareTo(a2.getName()) > 0;
            }
        });

        assertEquals(result.size(), 3);
        assertEquals(new Integer(1), result.get(0).getId());
        assertEquals(new Integer(2), result.get(2).getId());
    }

    @Test
    public void testSortThreeElemsWhereTwoAreEqual() {
        students.add(new Student(2, "One"));
        students.add(new Student(1, "Two"));
        students.add(new Student(4, "One"));

        List<Student> result = FunUtils.sort(students, new Function2<Student, Boolean>() {
            public Boolean exec(Student a1, Student a2) {
                return a1.getName().compareTo(a2.getName()) > 0;
            }
        });

        assertEquals(3, result.size());
        assertEquals(new Integer(1), result.get(2).getId());
    }

    @Test
    public void testFilterEmpty() {
        List<Student> filtered = FunUtils.filter(students, new Function<Student, Boolean>() {
            public Boolean exec(Student a) {
                return a.getName().equals("One") || a.getName().equals("Three");
            }
        });

        assertEquals(0, filtered.size());
    }

    @Test
    public void testFilterOneElemIfMatches() {
        students.add(new Student(3, "Three"));

        List<Student> filtered = FunUtils.filter(students, new Function<Student, Boolean>() {
            public Boolean exec(Student a) {
                return a.getName().equals("One") || a.getName().equals("Three");
            }
        });

        assertEquals(1, filtered.size());
    }

    @Test
    public void testFilterOneElemIfNotMatches() {
        students.add(new Student(3, "Three"));

        List<Student> filtered = FunUtils.filter(students, new Function<Student, Boolean>() {
            public Boolean exec(Student a) {
                return a.getName().equals("One");
            }
        });

        assertEquals(0, filtered.size());
    }

    @Test
    public void testFilterThreeElementsWhereTwoMatch() {
        students.add(new Student(3, "Three"));
        students.add(new Student(2, "Two"));
        students.add(new Student(1, "One"));

        List<Student> filtered = FunUtils.filter(students, new Function<Student, Boolean>() {
            public Boolean exec(Student a) {
                return a.getName().equals("One") || a.getName().equals("Three");
            }
        });

        assertEquals(2, filtered.size());
    }

    @Test
    public void testMapGetIdList() {
        students.add(new Student(2, "One"));
        students.add(new Student(1, "Two"));
        students.add(new Student(1, "One"));

        List<Integer> ids = FunUtils.map(students, new Function<Student, Integer>() {
            public Integer exec(Student a) {
                return a.getId();
            }
        });

        assertEquals(3, ids.size());
        assertEquals(new Integer(2), ids.get(0));
    }

    @Test
    public void testMapGetIdListFromEmpty() {
        List<Integer> ids = FunUtils.map(students, new Function<Student, Integer>() {
            public Integer exec(Student a) {
                return a.getId();
            }
        });

        assertEquals(0, ids.size());
    }

    @Test
    public void testGroupById() {
        students.add(new Student(2, "One"));
        students.add(new Student(1, "Two"));
        students.add(new Student(1, "One"));

        Map<String, List<Student>> result = FunUtils.group(students, new Function<Student, String>() {
            public String exec(Student a) {
                return a.getName();
            }
        });
        assertEquals(2, result.get("One").size());
        assertEquals(2, result.size());
    }

    @Test
    public void testGroupByIdIfEmpty() {
        Map<String, List<Student>> result = FunUtils.group(students, new Function<Student, String>() {
            public String exec(Student a) {
                return a.getName();
            }
        });

        assertEquals(0, result.size());
    }

    @Test
    public void testForEachIfEmpty() {
        final ArrayList<Integer> testList = new ArrayList<Integer>();
        FunUtils.forEach(students, new Function<Student, Void>() {
            public Void exec(Student a) {
                testList.add(1);
                return (null);
            }
        });
        assertEquals(0, testList.size());
    }

    @Test
    public void testForEach() {
        students.add(new Student(2, "One"));
        students.add(new Student(1, "Two"));
        students.add(new Student(1, "One"));

        final ArrayList<Integer> testList = new ArrayList<Integer>();
        FunUtils.forEach(students, new Function<Student, Void>() {
            public Void exec(Student a) {
                testList.add(1);
                return (null);
            }
        });
        assertEquals(3, testList.size());
    }
}
