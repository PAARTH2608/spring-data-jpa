package com.springjpa.spring.data.jpa.repository;

import com.springjpa.spring.data.jpa.entity.Guardian;
import com.springjpa.spring.data.jpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// we are using @SpringBootTest for testing the repository layer, but we shouldn't use that
// instead of that use @DataJpaTest
// what @DataJpaTest will do is it will flush the data ones testing is done

@SpringBootTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent() {
        Student student = Student.builder()
                .emailId("jain.paarth2608@gmail.com")
                .firstName("Paarth")
                .lastName("Jain")
//                .guardianName("Seema Jain")
//                .guardianEmail("jain.seema@gmail.com")
//                .guardianMobile("9999999999")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardianDetails() {
        Guardian guardian = Guardian.builder()
                .email("jain.seema@gmail.com")
                .name("Seema Jain")
                .mobile("9999999999")
                .build();
        Student student = Student.builder()
                .emailId("jain.paarth2608@gmail.com")
                .firstName("Paarth")
                .lastName("Jain")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void printAllStudents() {
        List<Student> studentList = studentRepository.findAll();
        System.out.println("Student List = " + studentList);
    }

    @Test
    public void printStudentByFirstName() {
        List<Student> students = studentRepository.findByFirstName("paarth");
        System.out.println(students);
    }

    @Test
    public void printStudentByFirstNameContaining() {
        List<Student> students = studentRepository.findByFirstNameContaining("paa");
        System.out.println(students);
    }

    @Test
    public void printStudentsBasedOnGuardianName() {
        List<Student> students = studentRepository.findByGuardianName("Seema Jain");
        System.out.println(students);
    }

    @Test
    public void printGetStudentByEmailAddressNativeNamedParam() {
        Student student = studentRepository.getStudentByEmailAddressNativeNamedParams(
                "jain.paarth2608@gmail.com"
        );
        System.out.println("Student = " + student);
    }

    // TILL NOW WE DEFINED METHODS TO READ THE DATA ONLY, NOW DEFINING METHODS TO UPDATE AND DELETE THE DATA
    @Test
    public void updateStudentNameByEmailIdTest() {
        studentRepository.updateStudentNameByEmailId(
                "Paarth Jain",
                "jain.paarth2608@gmail.com"
        );
    }
}