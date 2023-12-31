package com.supraja.cruddemo.entity;

import jakarta.persistence.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {
    //define fields

    //define constructors

    // define setters and getters

    // define toString

    // annotate


    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course(String title) {
        this.title = title;
    }

    public Course() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    // bi-directional
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    // This is for uni-directional
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="course_id")
    private List<Review> reviews;

    // add method for reviews
    public void addReview (Review review) {
        if (reviews == null) {
            reviews = new ArrayList<>();
        }
        reviews.add(review);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    // Adding students ManyToMany
    @ManyToMany(cascade =  {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinTable(
            name="course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name="student_id")
    )
    private List<Student> students;

    //Add method to saveStudent
    public void addStudent (Student student) {
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
    }
}
