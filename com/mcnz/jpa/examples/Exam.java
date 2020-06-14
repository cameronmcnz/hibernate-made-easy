package com.mcnz.jpa.examples;
import javax.persistence.*;

@Entity
@Table(name = "exam", schema = "hibernate_examples")
public class Exam {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private int id;

  @Basic private String shortName;

  @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
  @JoinColumn(name="detail_id")
  private ExamDetail detail;


  public int getId() { return id; }
  private void setId(int id) {this.id = id;}

  public ExamDetail getDetail() {return detail; }
  public void setDetail(ExamDetail detail) {
    this.detail = detail;
  }	
  
  public String getShortName() { return shortName;}
  public void setShortName(String shortName) {
    this.shortName = shortName;
  }
  
  public static void main(String args[]){
      EntityManagerFactory emf =     
        Persistence.createEntityManagerFactory("jpa-tutorial");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();

  Exam exam = new Exam();
  exam.setShortName("SCJA");

  ExamDetail detail = new ExamDetail();
  detail.setFullName("Sun Certified Java Associate");
  detail.setPassingPercentage(62);
  detail.setNumberOfQuestions(55);
  exam.setDetail(detail);

  //possible due to CascadeType.ALL
  em.persist(exam);
  em.getTransaction().commit();
}

}
