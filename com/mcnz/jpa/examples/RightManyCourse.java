package com.mcnz.jpa.examples;
import java.util.*;
import javax.persistence.*;

@Entity
public class RightManyCourse {

  @Id
  @GeneratedValue
  long id;
  String courseCode;

@ManyToMany
@JoinTable(name = "join_table", 
 joinColumns={@JoinColumn(name="rmcourse_id")}, 
 inverseJoinColumns={@JoinColumn(name="lmstudent_id")}
)
List<LeftManyStudent> students = new Vector();

  public List<LeftManyStudent> getStudents() {
    return students;
  }
  public void setStudents(List<LeftManyStudent> lefties){
    this.students = lefties;
  }
  public long getId() {
    return id;
  }
  public void setId(long id) {
    this.id = id;
  }

  public String getCourseCode() {
    return courseCode;
  }
  public void setCourseCode(String courseCode) {
    this.courseCode = courseCode;
  }
  
  public static void main (String args[]) {
	  LeftManyStudent student01 = new LeftManyStudent();
	  student01.setStudentName("Jim Jump");
	  LeftManyStudent student02 = new LeftManyStudent();
	  student02.setStudentName("Julie Camp");
	  LeftManyStudent student03 = new LeftManyStudent();
	  student03.setStudentName("Cam Johnson");
	  LeftManyStudent student04 = new LeftManyStudent();
	  student04.setStudentName("Marcus McKenzie");
	  RightManyCourse java101 = new RightManyCourse();
	  java101.setCourseCode("Java-101");
	  RightManyCourse cplus101 = new RightManyCourse();
	  cplus101.setCourseCode("C++ - 101");
	  RightManyCourse math101 = new RightManyCourse();
	  math101.setCourseCode("Math - 101");
	  java101.getStudents().add(student01);
	  java101.getStudents().add(student02);
	  java101.getStudents().add(student03);
	  cplus101.getStudents().add(student02);
	  cplus101.getStudents().add(student03);
	  math101.getStudents().add(student04);

	  EntityManagerFactory emf =     
	     Persistence.createEntityManagerFactory("jpa-tutorial");
	  EntityManager em = emf.createEntityManager();
	  em.getTransaction().begin();
	  em.persist(student01);
	  em.persist(student02);
	  em.persist(student03);
	  em.persist(student04);
	  em.persist(java101);
	  em.persist(cplus101);
	  em.persist(math101);
	  em.getTransaction().commit();
	}

}
