package com.mcnz.jpa.examples;
import javax.persistence.*;

public class PersistenceManager {

  private static EntityManagerFactory factory;
	
  public static EntityManager getEntityManager () {
    if (factory == null) {
      EntityManagerFactory factory = 
          Persistence.createEntityManagerFactory("jpa-tutorial");
    }
    EntityManager entityManager = factory.createEntityManager();
    return entityManager;
  }
  
  
  public static EntityManager beginTransaction() {
	  
	  EntityManager entityManager = getEntityManager();
	  entityManager.getTransaction().begin();
	  return entityManager;
	  
  }
  
  public static void commitTransaction(EntityManager em) {
	  em.close();
  }

}


