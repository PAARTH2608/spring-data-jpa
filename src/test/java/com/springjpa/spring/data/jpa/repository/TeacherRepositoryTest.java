package com.springjpa.spring.data.jpa.repository;

import com.springjpa.spring.data.jpa.entity.Course;
import com.springjpa.spring.data.jpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {
    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher() {
        Course courseDBA = Course.builder()
                .title("DBA")
                .credit(5)
                .build();
        Course courseJava = Course.builder()
                .title("Java")
                .credit(5)
                .build();
        Teacher teacher = Teacher.builder()
                .firstName("Arvind")
                .lastName("Jain")
//                .courses(List.of(courseDBA, courseJava))
                .build();
        teacherRepository.save(teacher);
    }
}