package com.springjpa.spring.data.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(
        exclude = "course"
)
public class CourseMaterial {
    @Id
    @SequenceGenerator(
            name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_material_sequence"
    )
    private Long courseMaterialId;
    private String url;

    // course material cannot exist without course, so we define one-to-one mapping
    // this course material table will have extra column course_id referencing to courseId of the course table
    @OneToOne(
            cascade = CascadeType.ALL,
            // LAZY is used when we only need the current data i.e. course material and not the course data
            // EAGER is used when we need current data along with the parent data, and it should fetch from the another table as well
            fetch = FetchType.LAZY,
            // now we don't want that a course can be saved without course material, so we can use optional keyword for this
            optional = false // so in order to save the course, course material is requried
    )
    @JoinColumn(
            name = "course_id",
            referencedColumnName = "courseId"
    )
    private Course course;
}
