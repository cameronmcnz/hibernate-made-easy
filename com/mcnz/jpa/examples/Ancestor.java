package com.mcnz.jpa.examples; import javax.persistence.*;
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Ancestor {
  @Id
  @GeneratedValue
  private Long id;
  private String nationality;
  public Long getId() {return id;}
  public void setId(Long id) {this.id = id;}
  public String getNationality() {return nationality;}
  public void setNationality(String nationality) {
    this.nationality = nationality;
  }
  
  public static void main(String args[]) {
	  Ancestor a = new Ancestor();
	  a.setNationality("Korean");
	  Parent p = new Parent();
	  p.setNationality("Jewish");    
	  p.setLastName("Steinberg");
	  Child c = new Child();
	  c.setNationality("Irish"); 
	  c.setLastName("McKenzie");
	  c.setFirstName("Cameron");
	  EntityManagerFactory emf =     
	    Persistence.createEntityManagerFactory("jpa-tutorial");
	  EntityManager em = emf.createEntityManager();
	  em.getTransaction().begin();  

	  em.persist(a); em.persist(p); em.persist(c);

	  em.getTransaction().commit();
	}

}
