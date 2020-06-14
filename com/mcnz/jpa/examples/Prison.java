package com.mcnz.jpa.examples;
import javax.persistence.*;

@Entity
public class Prison {
  private String city;
  @EmbeddedId  private CompoundKey id;

  public String getCity() {return city;}
  public void setCity(String city) {this.city=city;}
  public CompoundKey getId() {return id;}
  public void setId(CompoundKey id) {this.id = id;}
	
  public static void main(String args[]) {
    Prison jail = new Prison();
    jail.setCity("Milhaven");
    Long wayne = new Long(99);
    Long eric = new Long(88);
    CompoundKey key = new CompoundKey(wayne, eric);
    jail.setId(key);

    EntityManagerFactory emf =     
    Persistence.createEntityManagerFactory("jpa-tutorial");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();

    em.persist(jail);

    em.getTransaction().commit();
  }
}
