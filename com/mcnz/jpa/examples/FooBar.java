package com.mcnz.jpa.examples;

import javax.persistence.*;

@Entity
@Table(name="bar")
@SecondaryTable(name="foo")
public class FooBar {
  @Id
  @GeneratedValue
  int id;
  @Column(table="foo")
  String fooName;
  public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getFooName() {
	return fooName;
}

public void setFooName(String fooName) {
	this.fooName = fooName;
}

public String getBarCode() {
	return barCode;
}

public void setBarCode(String barCode) {
	this.barCode = barCode;
}

String barCode;   
  
  public static void main(String args[]) {
	  /* HibernateUtil needs FooBar.class in AnnotationConfiguration*/
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-tutorial");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		FooBar fb = new FooBar();
		fb.setBarCode("90210");
		fb.setFooName("ManChu");
	    em.persist(fb);
	    em.getTransaction().commit();

  }

}

