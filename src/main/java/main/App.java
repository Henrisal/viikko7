package main;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Gifu newGifu = new Gifu();
        System.out.println( "Tervetuloa Gifu-järjestelmään" );
        System.out.println("Mille yliopistolle haluat ottaa järjestelmän käyttöön?");
        Scanner sc = new Scanner(System.in);
        String university = sc.nextLine();
        boolean exit = false;
        while(!exit){
            System.out.println("1) Luo uusi kurssi, 2) Luo uusi opiskelija, 3) Listaa kurssit, 4) Listaa opiskelijat, 5) Lisää opiskelija kurssille, 6) Anna kurssiarvosanat, 7) Listaa kurssilla olevat opiskelijat, 8) Listaa opiskelijan arvosanat, 9) Listaa kaikkien kurssien kaikkien opiskelijoiden arvosanat, 0) Lopeta ohjelma");
            if(sc.hasNext()) {
                int i = 0;
                String stringInput = sc.nextLine();
                i = Integer.parseInt(stringInput);
            
                switch(i){
                    case 1:
                    System.out.println("Anna kurssin nimi:");
                    String name = sc.nextLine();
                    System.out.println("Anna kurssin ID:");
                    String id = sc.nextLine();
                    System.out.println("Anna kurssin maksimi opiskelijamäärä:");
                    stringInput = sc.nextLine();
                    int maxStudents = Integer.parseInt(stringInput);
                    Course newCourse = new Course(name, id, maxStudents);
                    newGifu.addCourse(newCourse);
                    break;

                    case 2:
                    System.out.println("Anna opiskelijan nimi:");
                    name = sc.nextLine();
                    System.out.println("Anna opiskelijan opiskelijanumero:");
                    id = sc.nextLine();
                    Student newStudent = new Student(name, id);
                    newGifu.addStudent(newStudent);

                    break;
                    case 3:
                    newGifu.listCourses();
                    break;
                    case 4:
                    newGifu.listStudents();
                    break;
                    case 5:
                    newGifu.listCourses();
                    System.out.println("Mille kurssille haluat lisätä opiskelijan? Syötä kurssin numero:");
                    stringInput = sc.nextLine();
                    int number = Integer.parseInt(stringInput);
                    newCourse = newGifu.getCourse(number);
                    newGifu.listStudents();
                    System.out.println("Minkä opiskelijan haluat lisätä kurssille? Syötä opiskelijan numero:");
                    stringInput = sc.nextLine();
                    number = Integer.parseInt(stringInput);
                    newStudent = newGifu.getStudent(number);
                    newGifu.enrollStudent(newStudent, newCourse);
                    break;

                    case 6:
                    newGifu.listCourses();
                    System.out.println("Minkä kurssin haluat arvostella? Syötä kurssin numero:");
                    stringInput = sc.nextLine();
                    number = Integer.parseInt(stringInput);
                    newCourse = newGifu.getCourse(number);
                    ArrayList<Enrollment> enrollments = newGifu.getEnrollments(newCourse);
                    for (Enrollment enrollment : enrollments) {
                    System.out.println("Anna arvosana opiskelijalle " + enrollment.getStudent().getInformation());
                    stringInput = sc.nextLine();
                    int grade = Integer.parseInt(stringInput);
                    enrollment.gradeCourse(grade);
                    }
                    break;
                    

                    case 7:
                    newGifu.listCourses();
                    System.out.println("Minkä kurssin opiskelijat haluat listata? Syötä kurssin numero:");
                    stringInput = sc.nextLine();
                    number = Integer.parseInt(stringInput);
                    newCourse = newGifu.getCourse(number);
                    enrollments = newGifu.getEnrollments(newCourse);
                    for (Enrollment enrollment : enrollments) {
                        System.out.println(enrollment.getStudent().getInformation()+", arvosana: "+enrollment.getGrade());
                    }
                    break;

                    case 8:
                    newGifu.listStudents();
                    System.out.println("Minkä opiskelijan arvosanat haluat listata? Syötä opiskelijan numero:");
                    stringInput = sc.nextLine();
                    number = Integer.parseInt(stringInput);
                    newStudent = newGifu.getStudent(number);
                    enrollments = newGifu.getEnrollments(newStudent);
                    System.out.println("Opiskelijan "+newGifu.getStudent(number)+" arvosanat:");
                    for (Enrollment enrollment : enrollments) {
                        System.out.println(enrollment.getCourse().getInformation()+", arvosana: "+enrollment.getGrade());
                    }
                    break;

                    case 9:
                    ArrayList<Course> courses = newGifu.getCourses();
                    for (Course course : courses) {
                        System.out.println(course.getInformation());
                        enrollments = newGifu.getEnrollments(course);
                        for (Enrollment enrollment : enrollments) {
                            System.out.println(enrollment.getStudent().getInformation() + ", arvosana: " + enrollment.getGrade());
                         }
                    }
                    break;




                    case 0:
                    System.out.println("Kiitos ohjelman käytöstä.");
                        exit = true;
                        break;
                    default:
                        System.out.println("Syöte oli väärä");
                        break;


                }


        }

        

    }
    sc.close();
}
}