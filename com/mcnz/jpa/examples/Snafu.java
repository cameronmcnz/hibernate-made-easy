package com.mcnz.jpa.examples;

import javax.persistence.*;



@Entity
public class Snafu {

  long id;
  String situation;

  @Id
  @GeneratedValue
  public long getId() {return id;}
  public void setId(long id) {this.id = id;}

  public String getSituation(){
    return situation;
  }
  public void setSituation(String situation) {
    this.situation = situation;
  }

/* main not required – just for testing */
  public static void main(String args[]) {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-tutorial");
	EntityManager em = emf.createEntityManager();
	em.getTransaction().begin();
    Snafu snafu = new Snafu();
    snafu.setSituation("normal");
    em.persist(snafu);
    em.getTransaction().commit();
  }
}
