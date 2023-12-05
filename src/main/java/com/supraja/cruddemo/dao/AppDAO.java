package com.supraja.cruddemo.dao;

import com.supraja.cruddemo.entity.Course;
import com.supraja.cruddemo.entity.Instructor;
import com.supraja.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;

import java.util.List;

public interface AppDAO {

    void saveInstructor(Instructor instructor);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailsById(int id);
    void deleteInstructorDetailById(int id);
    List<Course> findCoursesByInstructorId (int id);

    Instructor findInstructorWithCoursesJoinFetch(int id);

    void updateInstructor(Instructor tempInstructor);

    void updateCourse(Course tempCourse);

    Course findCourseById (int id);

    void deleteInstructorandNotCourseById (int id);

    void deleteCourseById (int id);

    void saveCourse (Course course);

    Course findCourseAndReviewsById (int id);

    void deleteCourseAndReviewById (int id);
}
