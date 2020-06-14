package com.mcnz.jpa.examples;
import java.util.*; import javax.persistence.*;
@Entity
public class LeftManyStudent {
  @Id
  @GeneratedValue
  long id;
  String studentName;

 @ManyToMany
 @JoinTable(name = "join_table", 
 joinColumns = { @JoinColumn(name = "lmstudent_id")},
 inverseJoinColumns={@JoinColumn(name="rmcourse_id")}   
 )
 List<RightManyCourse> courses = new ArrayList();

  public List<RightManyCourse> getCourses(){
    return courses;
  }
  public void setCourses(List<RightManyCourse> righties){
    this.courses = righties;
  }
  public long getId() { return id; }
  public void setId(long id) { this.id = id; }
  public String getStudentName() { return studentName; }
  public void setStudentName(String s){ studentName=s; }
}
