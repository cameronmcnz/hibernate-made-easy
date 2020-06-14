package com.mcnz.jpa.examples;
import javax.persistence.*;

@Entity
@Table(name = "exam_detail", schema = "hibernate_examples")
public class ExamDetail {
  @Id
  @GeneratedValue
  @Column(name = "id")
  private int id;
  @Basic private String fullName;
  @Basic private int numberOfQuestions;
  @Basic private int passingPercentage;  

  @OneToOne(cascade=CascadeType.ALL, mappedBy="detail")
  private Exam exam;
  
  public int getId() {return id;}
  private void setId(int id) {this.id = id;}

  
  public Exam getExam(){return exam;}
  public void setExam(Exam exam){this.exam=exam;}

  public String getFullName() {return fullName;}
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public int getNumberOfQuestions() {return numberOfQuestions;}
  public void setNumberOfQuestions(int numberOfQuestions) {
    this.numberOfQuestions = numberOfQuestions;
  }
  
  public int getPassingPercentage() {return passingPercentage;}
  public void setPassingPercentage(int passingPercentage) {
    this.passingPercentage = passingPercentage;
  }
  
}
