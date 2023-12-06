package com.supraja.cruddemo;

import com.supraja.cruddemo.dao.AppDAO;
import com.supraja.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	//This will run after all beans are
	@Bean
	public CommandLineRunner commandLineRunner (AppDAO appDAO) {
		return runner -> {
//			deleteInstructorById(appDAO);
//			createInstructor(appDAO);
//			findInstructorById(appDAO);
//			findInstructorDetailsById(appDAO);
//			deleteInstructionDetailsById(appDAO);
//			createInstructorWithCourse(appDAO);
//			findInstructorWithCourses(appDAO);
//			findCoursesByInstructorId(appDAO);
//			findInstructorWithCoursesJoinFetch(appDAO);
//			updateInstructor(appDAO);
//			updateCourse(appDAO);
//			deleteInstructorandNotCourseById(appDAO);
//			deleteCourseById(appDAO);
//			createCourseandReviews(appDAO);
//			findCourseAndReviewsById(appDAO);
//			deleteCourseAndReviewsById(appDAO);
//			createCourseandStudents(appDAO);
//			findCourseandStudentsByCourseId(appDAO);
//			findCourseandStudentByStudentId(appDAO);
//			addMoreCoursesForStudent(appDAO);
			deleteStudent(appDAO);

		};
	}

	private void deleteStudent(AppDAO appDAO) {
		int id = 2;
		System.out.println("Deleting student with id " + id);
		appDAO.deleteStudent(id);
		System.out.println("Done!");
	}

	private void addMoreCoursesForStudent(AppDAO appDAO) {
		int theId = 2;
		Student student = appDAO.findCourseandStudentByStudentId(theId);

		// Create more courses
		Course tempCourse1 = new Course("Learn to code from webDev");
		Course tempCourse2 = new Course("Learn ML from webDev");

		//Add courses to the student
		student.addCourse(tempCourse1);
		student.addCourse(tempCourse2);

		System.out.println("Saving student");
		appDAO.updateStudents(student);

		System.out.println("Student -- " + student);
		System.out.println("Courses --- " + student.getCourses());
		System.out.println("Done!");

	}

	private void findCourseandStudentByStudentId(AppDAO appDAO) {
		int id =  3;
		Student student = appDAO.findCourseandStudentByStudentId(id);
		System.out.println("Student -------- "+ student);
		System.out.println("Course -------- "+ student.getCourses());

	}

	private void findCourseandStudentsByCourseId(AppDAO appDAO) {
		int theId = 11;
		Course tempCourse = appDAO.findCourseandStudentsByCourseId(theId);
		System.out.println("Course --- " + tempCourse);
		System.out.println("Students --- " + tempCourse.getStudents());

	}

	private void createCourseandStudents(AppDAO appDAO) {
		// create a course
		Course course = new Course("CodeWithHarry ML");
//		 create students
		Student student = new Student("Supraja", "Srikanth", "ssupraja872@gmail.com");
		Student student1 = new Student("Indu", "Yadav", "indu@gmail.com");

		Student student2 = new Student("Harry", "Bhai", "harry@gmail.com");

//		add students to the course
		course.addStudent(student);



		// save the course and assosiated students
		System.out.println("Saving course " + course );
		System.out.println("Associated students " + course.getStudents());
		appDAO.saveCourse(course);
		System.out.println("Done!");
	}

	private void deleteCourseAndReviewsById(AppDAO appDAO) {

		int id = 10;

		appDAO.deleteCourseAndReviewById(id);

		System.out.println("Done");
	}

	private void findCourseAndReviewsById(AppDAO appDAO) {
		int id = 10;
		Course course = appDAO.findCourseAndReviewsById(id);
		System.out.println("Course -------- " + course);
		System.out.println("Reviews -------- " + course.getReviews());

	}

	private void createCourseandReviews(AppDAO appDAO) {
		// create course
		Course newCourse = new Course("My first Course with review!");
		//add reviews
		Review tempReview = new Review("First review");
		newCourse.addReview(tempReview);
		tempReview = new Review("second review");
		newCourse.addReview(tempReview);
		tempReview = new Review("First review");
		newCourse.addReview(tempReview);
		//save course
		//This will also save the review
		appDAO.saveCourse(newCourse);


	}

	private void deleteCourseById(AppDAO appDAO) {
		int id = 11;
		System.out.println("Deleting course with id " + id);
		appDAO.deleteCourseById(id);
		System.out.println("Done");
	}

	private void deleteInstructorandNotCourseById(AppDAO appDAO) {
		int id = 1;
		appDAO.deleteInstructorandNotCourseById(id);
		System.out.println("Done !");
	}

	private void updateCourse(AppDAO appDAO) {
		int id =10;
		Course  course = appDAO.findCourseById(id);
		course.setTitle("Sleep, eat and Code");
		appDAO.updateCourse(course);
		id =1;
		System.out.println("Finding instructor with courses with join fetch with  id = " + id);
		Instructor instructor = appDAO.findInstructorWithCoursesJoinFetch(id);
		System.out.println("Instructor --- " + instructor);
		System.out.println("Courses --- " + instructor.getCourses());
	}

	private void updateInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding instructor with  id = " + id);

		Instructor tempInstructor = appDAO.findInstructorById(id);

		System.out.println("Instructor " + tempInstructor);

		//updating last name
		tempInstructor.setLastName("Tester");

		appDAO.updateInstructor(tempInstructor);

		Instructor updatedInstructor = appDAO.findInstructorById(id);

		System.out.println("Printing upadted instructor" + updatedInstructor);
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding instructor with courses with join fetch with  id = " + id);
		Instructor instructor = appDAO.findInstructorWithCoursesJoinFetch(id);
		System.out.println("Instructor --- " + instructor);
		System.out.println("Courses --- " + instructor.getCourses());
	}

	private void findCoursesByInstructorId(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding instructor with  id = " + id);

		Instructor tempInstructor = appDAO.findInstructorById(id);

		System.out.println("Instructor " + tempInstructor);

		List<Course> courses = appDAO.findCoursesByInstructorId(id);

		tempInstructor.setCourses(courses);

		System.out.println("Courses " + tempInstructor.getCourses());

	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding courses with  id = " + id);

		Instructor tempInstructor = appDAO.findInstructorById(id);

		System.out.println("Instructor " + tempInstructor);
		System.out.println("Courses " + tempInstructor.getCourses());
	}

	private void createInstructorWithCourse(AppDAO appDAO) {
		Instructor instructor = new Instructor("Harry", "Bhai", "harry@gmail.com");
		InstructorDetail instructorDetail = new InstructorDetail("CodewithHarry", "coding");

		instructor.setInstructorDetail(instructorDetail);

		// create some courses

		Course tempCourse = new Course("Javascript");
		instructor.add(tempCourse);
		Course tempCourse2 = new Course("React");
		instructor.add(tempCourse2);
		Course tempCourse3 = new Course("ML");
		instructor.add(tempCourse3);

		// saving instructor
		// NOTE : This will also save the course because of CASCADE.PERSIST
		System.out.println("Instructor being saved == " + instructor);
		System.out.println("Course are  == " + instructor.getCourses());
		appDAO.saveInstructor(instructor);
		System.out.println("Done!");

	}

	private void deleteInstructionDetailsById(AppDAO appDAO) {
		int id = 5;
		appDAO.deleteInstructorDetailById(id);
	}

	private void findInstructorDetailsById(AppDAO appDAO) {
		int id = 5;
		InstructorDetail instructorDetail = appDAO.findInstructorDetailsById(id);
		System.out.println(instructorDetail);
		Instructor instructor = instructorDetail.getInstructor();
		System.out.println("Instructor ----- " + instructor);
	}

	private void deleteInstructorById(AppDAO appDAO) {
		// NOTE- This will also delete the details in instructorDetails due to CASCADE.ALL
		int id = 0;
		System.out.println("Instructor with id" + id + " is deleted");
		appDAO.deleteInstructorById(id);
		System.out.println("Instructor deleted");
	}

	private void findInstrucorById(AppDAO appDAO) {
		// Note : This will also return the Instructor Details object due to cascade
		// and eager loading
		int id = 1;
		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println("Instructor " + instructor);
	}

	private void createInstructor(AppDAO appDAO) {
		// create the instructor
		System.out.println("Creating instructor ----");
//		Instructor instructor = new Instructor("supraja", "srikanth", "ssupraja@gmail.com");
		Instructor instructor = new Instructor("Indu", "Yadav", "indu@gmail.com");
		System.out.println("Instructor - " + instructor);
		InstructorDetail instructorDetail = new InstructorDetail("Codewithfun", "coding");

		instructor.setInstructorDetail(instructorDetail);

		System.out.println("Instructor 2 " + instructor);

		// save the instructor

		// NOTE- This will also save the details in instructorDetails due to CASCADE.ALL


		appDAO.saveInstructor(instructor);

	}
}
