package com.mcnz.jpa.examples;
import javax.persistence.*;
@Entity
public class Interest {
  @Id
  private CompoundKey id;
  private double rate;

  public CompoundKey getId() {return id;}
  public void setId(CompoundKey id) {this.id=id;}
  public double getRate() {return rate;}
  public void setRate(double rate) {this.rate=rate;}

  public static void main(String args[]) {
    Interest rate = new Interest();
    rate.setRate(18.5);

    Long wayne=new Long(99); Long mario=new Long(66);
    CompoundKey key = new CompoundKey(wayne, mario);
    rate.setId(key);

    EntityManagerFactory emf =     
        Persistence.createEntityManagerFactory("jpa-tutorial");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    em.persist(rate);
    em.getTransaction().commit();
  }
}
