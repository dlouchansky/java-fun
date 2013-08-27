import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RecursiveFunUtilsTest {
    ArrayList<Student> students;
    @Before
    public void setUp() {
        students = new ArrayList<Student>();
    }

    @Test
    public void testRecursiveSortEmpty() {
        List<Student> result = RecursiveFunUtils.sort(students, new Function2<Student, Boolean>() {
            public Boolean exec(Student a1, Student a2) {
                return a1.getName().compareTo(a2.getName()) > 0;
            }
        });

        assertEquals(result.size(), 0);
    }

    @Test
    public void testRecursiveSortOneElemByName() {
        students.add(new Student(3, "Three"));

        List<Student> result = RecursiveFunUtils.sort(students, new Function2<Student, Boolean>() {
            public Boolean exec(Student a1, Student a2) {
                return a1.getName().compareTo(a2.getName()) > 0;
            }
        });

        assertEquals(result.size(), 1);
    }

    @Test
    public void testRecursiveSortThreeElemsByName() {
        students.add(new Student(3, "Three"));
        students.add(new Student(2, "Two"));
        students.add(new Student(1, "One"));

        List<Student> result = RecursiveFunUtils.sort(students, new Function2<Student, Boolean>() {
            public Boolean exec(Student a1, Student a2) {
                return a1.getName().compareTo(a2.getName()) > 0;
            }
        });

        assertEquals(result.size(), 3);
        assertEquals(result.get(0).getId(), new Integer(1));
        assertEquals(result.get(2).getId(), new Integer(2));
    }

    @Test
    public void testRecursiveSortThreeElemsWhereTwoAreEqual() {
        students.add(new Student(2, "One"));
        students.add(new Student(1, "Two"));
        students.add(new Student(4, "One"));

        List<Student> result = RecursiveFunUtils.sort(students, new Function2<Student, Boolean>() {
            public Boolean exec(Student a1, Student a2) {
                return a1.getName().compareTo(a2.getName()) > 0;
            }
        });

        assertEquals(result.size(), 3);
        assertEquals(result.get(2).getId(), new Integer(1));
    }

    @Test
    public void testRecursiveFilterEmpty() {
        List<Student> filtered = RecursiveFunUtils.filter(students, new Function<Student, Boolean>() {
            public Boolean exec(Student a) {
                return a.getName().equals("One") || a.getName().equals("Three");
            }
        });

        assertEquals(filtered.size(), 0);
    }

    @Test
    public void testRecursiveFilterOneElemIfMatches() {
        students.add(new Student(3, "Three"));

        List<Student> filtered = RecursiveFunUtils.filter(students, new Function<Student, Boolean>() {
            public Boolean exec(Student a) {
                return a.getName().equals("One") || a.getName().equals("Three");
            }
        });

        assertEquals(filtered.size(), 1);
    }

    @Test
    public void testRecursiveFilterOneElemIfNotMatches() {
        students.add(new Student(3, "Three"));

        List<Student> filtered = RecursiveFunUtils.filter(students, new Function<Student, Boolean>() {
            public Boolean exec(Student a) {
                return a.getName().equals("One");
            }
        });

        assertEquals(filtered.size(), 0);
    }

    @Test
    public void testRecursiveFilterThreeElementsWhereTwoMatch() {
        students.add(new Student(3, "Three"));
        students.add(new Student(2, "Two"));
        students.add(new Student(1, "One"));

        List<Student> filtered = RecursiveFunUtils.filter(students, new Function<Student, Boolean>() {
            public Boolean exec(Student a) {
                return a.getName().equals("One") || a.getName().equals("Three");
            }
        });

        assertEquals(filtered.size(), 2);
    }

    @Test
    public void testRecursiveMapGetIdList() {
        students.add(new Student(2, "One"));
        students.add(new Student(1, "Two"));
        students.add(new Student(1, "One"));

        List<Integer> ids = RecursiveFunUtils.map(students, new Function<Student, Integer>() {
            public Integer exec(Student a) {
                return a.getId();
            }
        });

        assertEquals(ids.size(), 3);
        assertEquals(ids.get(0), new Integer(2));
    }

    @Test
    public void testRecursiveMapGetIdListFromEmpty() {
        List<Integer> ids = RecursiveFunUtils.map(students, new Function<Student, Integer>() {
            public Integer exec(Student a) {
                return a.getId();
            }
        });

        assertEquals(ids.size(), 0);
    }

    @Test
    public void testRecursiveGroupById() {
        students.add(new Student(2, "One"));
        students.add(new Student(1, "Two"));
        students.add(new Student(1, "One"));

        Map<String, List<Student>> result = RecursiveFunUtils.group(students, new Function<Student, String>() {
            public String exec(Student a) {
                return a.getName();
            }
        });
        assertEquals(result.get("One").size(), 2);
        assertEquals(result.size(), 2);
    }

    @Test
    public void testRecursiveGroupByIdIfEmpty() {
        Map<String, List<Student>> result = RecursiveFunUtils.group(students, new Function<Student, String>() {
            public String exec(Student a) {
                return a.getName();
            }
        });

        assertEquals(result.size(), 0);
    }

    @Test
    public void testRecursiveForEachIfEmpty() {
        final ArrayList<Integer> testList = new ArrayList<Integer>();
        RecursiveFunUtils.forEach(students, new Function<Student, Void>() {
            public Void exec(Student a) {
                testList.add(1);
                return (null);
            }
        });
        assertEquals(testList.size(), 0);
    }

    @Test
    public void testRecursiveForEach() {
        students.add(new Student(2, "One"));
        students.add(new Student(1, "Two"));
        students.add(new Student(1, "One"));

        final ArrayList<Integer> testList = new ArrayList<Integer>();
        RecursiveFunUtils.forEach(students, new Function<Student, Void>() {
            public Void exec(Student a) {
                testList.add(1);
                return (null);
            }
        });
        assertEquals(testList.size(), 3);
    }
}
