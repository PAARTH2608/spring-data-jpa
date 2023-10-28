package com.springjpa.spring.data.jpa.repository;

import com.springjpa.spring.data.jpa.entity.Course;
import com.springjpa.spring.data.jpa.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CourseMaterialRepositoryTest {
    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Test
    public void saveCourseMaterial() {
        // this will give error, because we are trying to save the course material without saving course in the database
        // for that to not happen we will use cascading
        Course course = Course.builder()
                .title("DSA")
                .credit(6)
                .build();
        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("www.google.com")
                .course(course)
                .build();
        courseMaterialRepository.save(courseMaterial);
    }

    @Test
    public void printAllCourses() {
        List<CourseMaterial> courseMaterialList = courseMaterialRepository.findAll();
        System.out.println("course materials = " + courseMaterialList);
    }
}