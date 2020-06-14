package com.mcnz.jpa.examples;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class JpaCrudExample {

	public static void main(String[] args) throws Exception {

		 ///MyDatabaseWizard.main(null);
		JpaCrudExample.createRecord();
		//JpaCrudExample.retrieveRecord();
		//JpaCrudExample.updateRecord();
		//JpaCrudExample.deleteRecord();
		
		//JpaCrudExample.criteriaQuery();
		//criteriaVerifiedUsersQuery();
		//criteriaFuzzyEmail();
		//criteriaBetweenDates();
		//criteriaLoginNameAndPassword();
		//criteriaFindByCommonPassword();
		//criteriaCountQuery();
		//findByFuzzyNamedQuery();
		//persistMergeRecord();
	} 
	
	public static void createRecord() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-example");
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		Player p = new Player();
		p.setPassword("jpa345");
		entityManager.persist(p);
		entityManager.getTransaction().commit();

	} 
	
	public static void persistMergeRecord() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-example");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Player player1 = new Player();  player1.setPassword("aaaaaa");
		em.persist(player1); em.flush();
		em.detach(player1);
		em.flush();
		System.out.println(player1.getId());
		Player player2 = em.find(Player.class, player1.getId());
		player1.setVerified(true);
		player2.setVerified(false);
		System.out.print("The instances are the same? ");
		System.out.println(player1.equals(player2));
		try {
			em.persist(player1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			em.refresh(player1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			em.merge(player1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		em.getTransaction().commit();



	}


 
	public static void retrieveRecord() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-tutorial");
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();

		Long key = Long.valueOf(1);
		Player p = entityManager.find(Player.class, key);
		System.out.println(p.getPassword());

		entityManager.getTransaction().commit();
	}

	public static void updateRecord() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-tutorial");
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin(); 

		Long key = Long.valueOf(1);
		Player p = entityManager.find(Player.class, Long.valueOf(1));
		p.setPassword("kowabunga");

		entityManager.getTransaction().commit();
	}

	public static void deleteRecord() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-tutorial");
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();

		Long key = Long.valueOf(1);
		Player p = entityManager.find(Player.class, key);
		entityManager.remove(p); // record is deleted

		entityManager.getTransaction().commit();
	}

	public static void queryRecords() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-tutorial");
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();

		Query query = entityManager.createQuery("select distinct p from Player as p", Player.class);
		

		List<Player> players = query.getResultList();

		for (Player playa : players) {
			System.out.println("The password is :" + playa.getPassword());
		}

		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			System.out.println(player.getLoginName());
		}
		entityManager.flush();
		//entityManager.detach(arg0);h();
		entityManager.getTransaction().commit();
	}
	
	public static void findByNamedQuery() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-tutorial");
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();

		Query query=  entityManager.createNamedQuery("player.findByEmail");
		query.setParameter("email", "mj@mcnz.com");
		List<Player> players = query.getResultList();

		for (Player playa : players) {
			System.out.println(playa);
		}

		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			System.out.println(player.getLoginName());
		}
		
		entityManager.getTransaction().commit();
	}
	
	public static void findByFuzzyNamedQuery() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-tutorial");
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();

		Query query=  entityManager.createNamedQuery("player.findByFuzzyEmail");
		query.setParameter("email", "%scja%");
		List<Player> players = query.getResultList();

		for (Player playa : players) {
			System.out.println(playa);
		}

		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			System.out.println(player.getLoginName());
		}
		entityManager.getTransaction().commit();
	}
	

	
	public static void criteriaQuery() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-tutorial");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Player> cQuery = builder.createQuery(Player.class);
		
		
		Root<Player> from = cQuery.from(Player.class);  
		cQuery.orderBy(builder.asc(from.get("loginName")));
		
		TypedQuery<Player> query = em.createQuery(cQuery);
		List<Player> players = query.getResultList();
		players.forEach(player -> System.out.println(player));
		
		em.getTransaction().commit();
	}
	
	public static void criteriaCountQuery() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-tutorial");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> cQuery = builder.createQuery(Long.class);
		cQuery.select(builder.count(cQuery.from(Player.class)));
		
		TypedQuery<Long> query = em.createQuery(cQuery);
		System.out.println(query.getSingleResult());
		em.getTransaction().commit();
	}
	
	public static void criteriaVerifiedUsersQuery() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-tutorial");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Player> cQuery = builder.createQuery(Player.class);
		
		Root<Player> root = cQuery.from(Player.class);     
		
		ParameterExpression<Boolean> flag = builder.parameter(Boolean.class);
		Predicate expression = builder.equal(root.get("verified"), flag);
		
		cQuery.select(root).where(expression);
		
		TypedQuery<Player> query = em.createQuery(cQuery);
		query.setParameter(flag, true);
		List<Player> players = query.getResultList();
		players.forEach(player -> System.out.println(player.getVerified()));
		
		em.getTransaction().commit();
	}
	
	
	
	public static void criteriaLoginNameAndPassword() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-tutorial");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Player> cQuery = builder.createQuery(Player.class);
		
		Root<Player> root = cQuery.from(Player.class);     
		
		ParameterExpression<String> password = builder.parameter(String.class);
		Expression<Boolean> passwordExpression = builder.equal(root.get("password"), password);
		
		ParameterExpression<String> loginName = builder.parameter(String.class);
		Expression<Boolean> loginExpression = builder.equal(root.get("loginName"), loginName);
		
		
		Predicate loginAndPassword = builder.and(loginExpression, passwordExpression);
		
		cQuery.select(root).where(loginAndPassword);
		
		TypedQuery<Player> query = em.createQuery(cQuery);
		query.setParameter(loginName, "astra");
		query.setParameter(password, "pwd");
		Player player = query.getSingleResult();
		System.out.println(player);
		
		em.getTransaction().commit();
	}
	
	public static void criteriaFindByCommonPassword() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-tutorial");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Player> cQuery = builder.createQuery(Player.class);
		
		Root<Player> root = cQuery.from(Player.class);     
		
		ParameterExpression<String> password = builder.parameter(String.class);
		Expression<Boolean> passwordExpression = builder.equal(root.get("password"), password);
		cQuery.select(root).where(passwordExpression);
		
		TypedQuery<Player> query = em.createQuery(cQuery);
		query.setParameter(password, "pwd");
		List<Player> players = query.getResultList();
		players.forEach(player -> System.out.println(player.getLoginName()));
		
		em.getTransaction().commit();
	}
	
	public static void criteriaFuzzyEmail() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-tutorial");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Player> cQuery = builder.createQuery(Player.class);
		
		Root<Player> root = cQuery.from(Player.class);     
		Predicate predicate = builder.like(root.get("emailAddress"), "%scja%");
		
		cQuery.select(root).where(predicate);
		
		TypedQuery<Player> query = em.createQuery(cQuery);
		//query.setParameter(email, true);
		List<Player> players = query.getResultList();
		players.forEach(player -> System.out.println(player.getEmailAddress()));
		
		em.getTransaction().commit();
	}
	
	
	public static void criteriaBetweenDates() throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-tutorial");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Player> cQuery = builder.createQuery(Player.class);
		
		Root<Player> root = cQuery.from(Player.class);     
		
		ParameterExpression<String> email = builder.parameter(String.class);
		//Predicate predicate = builder.like(root.get("emailAddress"), "%scja%");
		java.text.SimpleDateFormat textFormat = 
			    new java.text.SimpleDateFormat("yyyy-MM-dd");
		Date startDate = new Date();
		startDate = textFormat.parse("1998-1-1");
		
		Date endDate = new Date();
		startDate = textFormat.parse("2018-6-1");
		
		Predicate predicate =  builder.between(root.get("lastAccessTime"), startDate, endDate);
		    //predicate.add(date);

		cQuery.select(root).where(predicate);
		
		TypedQuery<Player> query = em.createQuery(cQuery);
		//query.setParameter(email, true);
		List<Player> players = query.getResultList();
		players.forEach(player -> System.out.println(player.getLastAccessTime())); 
		
		List<Player> users = new java.util.ArrayList();
		//users.add(player);
		
		em.getTransaction().commit();
	}

}
