public class Student {
    private String name;
    private Integer id;

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Student(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
