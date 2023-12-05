package com.supraja.cruddemo.dao;

import com.supraja.cruddemo.entity.Course;
import com.supraja.cruddemo.entity.Instructor;
import com.supraja.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDAOImpl implements  AppDAO{

    // define field for entity manager
    private EntityManager entityManager;
    //Inject it using constructor
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //Adding transactional annotation since we are performing an update on db
    @Override
    @Transactional
    public void saveInstructor(Instructor instructor) {
            // this will also save the info in instructor detail because of cascade
            entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructorToDelete = entityManager.find(Instructor.class, id);
        entityManager.remove(instructorToDelete);
    }

    @Override
    public InstructorDetail findInstructorDetailsById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    // This code is before course was added
    public void deleteInstructorDetailById(int id) {
        InstructorDetail instructorDetailsToDelete = entityManager.find(InstructorDetail.class, id);
        entityManager.remove(instructorDetailsToDelete);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);
        query.setParameter("data", id);

        return query.getResultList();
    }

    @Override
    public Instructor findInstructorWithCoursesJoinFetch(int id) {
        TypedQuery<Instructor> query = entityManager.createQuery("select i from Instructor i JOIN FETCH i.courses where i.id =:data", Instructor.class);
        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void updateInstructor(Instructor tempInstructor) {
        entityManager.merge(tempInstructor);
    }

    @Override
    @Transactional
    public void updateCourse(Course tempCourse) {
        entityManager.merge(tempCourse);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorandNotCourseById(int id) {
        Instructor instructorToDelete = entityManager.find(Instructor.class, id);

        List<Course> tempCourse = instructorToDelete.getCourses();

        // We need to update the instructor in the course to null
        // If we skip this step we will get foreign key exception
        for (Course course : tempCourse) {
            course.setInstructor(null);
        }

        // this will not delete course due to cascade types
        entityManager.remove(instructorToDelete);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        Course tempCourse = entityManager.find(Course.class, id);
        entityManager.remove(tempCourse);
    }

    @Override
    @Transactional
    public void saveCourse(Course course) {
            entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsById(int id) {
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c JOIN FETCH c.reviews where c.id =:data", Course.class);
        query.setParameter("data", id);
        return query.getSingleResult();

    }

    @Override
    @Transactional
    public void deleteCourseAndReviewById(int id) {
        Course courseToDelete = entityManager.find(Course.class, id);
        entityManager.remove(courseToDelete);
    }
}
