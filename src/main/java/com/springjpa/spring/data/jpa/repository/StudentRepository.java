package com.springjpa.spring.data.jpa.repository;

import com.springjpa.spring.data.jpa.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    public List<Student> findByFirstName(String firstName);
    // for functions like which returns the List of students which contains some letter of the string you provide,
    // use "findByFirstNameContaining(String name)"
    public List<Student> findByFirstNameContaining(String firstName);

    // to create method which find list of student based on guardian name
    public List<Student> findByGuardianName(String guardianName);

    // NATIVE NAMED QUERY
    @Query(
            value = "SELECT * from tbl_student s where s.email_address = :emailId",
            nativeQuery = true
    )
    public Student getStudentByEmailAddressNativeNamedParams(
            @Param("emailId") String emailId
    );

    // UPDATING METHODS
    // 1. UPDATING THE FIRST NAME OF PERSON BASED ON GIVEN EMAIL ID
    @Modifying // used for modifying the values in database and there has to be txn for that
    @Transactional // after the particular update operation is performed, that txn will be committed to the database
    // transaction will be added to the service layer and we can call multiple repository layer from that service layer to make entire flow as txn
    @Query(
            value = "UPDATE tbl_student set first_name = ?1 WHERE email_address = ?2",
            nativeQuery = true
    )
    int updateStudentNameByEmailId(String firstName, String emailId);
}
