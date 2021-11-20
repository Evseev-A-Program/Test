import java.util.*;

public class StudentMain {
    public static void main(String[] args) {

    Student<String> student = new Student("A", new ArrayList(), x->"yes".equals(x) || "no".equals(x));
    student.addMarks("yes");
    student.addMarks("mb");
    student.saveStudents();
    student.addMarks("no");
    student.saveStudents();
    student.addMarks("yes");
    student.addMarks("no");
    student.addMarks("no");
    System.out.println(student);
    student.uploadStudent(1);
    System.out.println(student);
    }
}


enum Marks{
    TWO(2), THREE(3), FOUR(4), FIVE(5);

    int value;

    Marks(int value) {
        this.value = value;
    }
}

class Student<T> {

    private String name;
    private List<T> marks;
    private Check<T> check;
    private Deque<Action> action = new ArrayDeque<>();
    private SaveStudent saveStudent;

    public Student(String name, List<T> marks, Check<T> check) {
        this.marks = new ArrayList<>();
        this.check = check;
        this.name = name;
        for (T t: marks){
            if(check.checkValue(t)) marks.add(t);
        }
    }

    static class SaveStudent<T>{
        private  String name;
        private List<T> marks;
        private Deque<Action> action = new ArrayDeque<>();
        private static List<SaveStudent> saveStudents = new ArrayList<>();

        public SaveStudent(String name, List<T> marks, Deque<Action> action) {
            this.name = name;
            this.marks = marks;
            this.action = action;
        }
    }

    public Student (SaveStudent student){
        this.name = student.name;
        this.marks = new ArrayList<>(student.marks);
    }

    public void addMarks(T mark){
        if(check.checkValue(mark)) marks.add(mark);
        action.push(() -> marks.remove(marks.size()-1));
    }

    public  void deleteMarks(int i){
        if (i > 0 & i < marks.size()) {
            T t = marks.get(i);
            action.push(() -> marks.add(i, marks.get(i)));
            marks.remove(i);
        }

    }

    public void editName(String name){
        if (name != null) {
            String currentname = name;
            action.push(()->this.name=currentname);
            this.name = name;
        }

    }

    public String getName() {
        return name;
    }

    public List<T> getMarks() {
        return new ArrayList<>(this.marks);
    }

    public void undo(){
        action.pop().backAction();
    }

    public void saveStudents(){
       SaveStudent.saveStudents.add(new SaveStudent(name, marks, action));
    }

    public void uploadStudent(int i){
        if (i > 0 && i < SaveStudent.saveStudents.size()){
            this.name = SaveStudent.saveStudents.get(i).name;
            this.marks = SaveStudent.saveStudents.get(i).marks;
            this.action = SaveStudent.saveStudents.get(i).action;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return  false;
        if(this == obj) return true;
        if(!Student.class.equals(obj.getClass())) return false;
        Student student = (Student) obj;
        return name.equals(student.name) && marks.equals(student.marks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, marks);
    }

    @Override
    public String toString() {
        return "Name: " + name + " Marks: " + marks;
    }
}



