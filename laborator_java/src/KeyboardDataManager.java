import java.util.ArrayList;
import java.util.Scanner;

public class KeyboardDataManager implements IDataLoader {
    @Override
    public Student[] createStudentsData() {
        ArrayList<Student> studenti=new ArrayList<Student>();
       // Scanner scan= new Scanner(System.in);
//        boolean introduceStudents = true;
//        while(introduceStudents) {
//            System.out.println("If you want to stop introducing students type exit, if not enter the last name of the new student: ");
//            Scanner scan = new Scanner(System.in);
//            String firstAnswer = scan.next();
//            if(firstAnswer == "exit")
//            introduceStudents=false;
//            else {
//                String lastName = firstAnswer;
//                System.out.println("Enter the first name of the new student: ");
//                String firstName = scan.next();
//                System.out.println("Enter the group of the new student: ");
//                int  group = scan.nextInt();
//            }
//            }

        return new Student[0];
    }

    @Override
    public Profesor[] createProfesorData() {
        return new Profesor[0];
    }

    @Override
    public Curs[] createCoursesData() {
        return new Curs[0];
    }
}
