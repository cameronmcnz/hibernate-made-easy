package com.mcnz.jpa.examples;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


@Entity 
@Table(name="player", schema="hibernate_examples")
@NamedQuery(name="Player.findByEmail", query="from Player p WHERE p.emailAddress = :email")
@NamedQuery(name="Player.findByFuzzyEmail", query="from Player p WHERE p.emailAddress like :email")
public class Player {
	

	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="handle", unique=true)
	@Basic private String loginName;
	
	@Basic private String emailAddress;
	
	@Basic private Boolean verified; 
	
	@Column(nullable=false)
	private String password;
	
	@Transient
	private String encryptedPassword;
	
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date lastAccessTime;
	
	@Temporal(TemporalType.DATE)
	@Column(updatable=false)
	private java.util.Calendar registrationDate;
	
	public Player() { }
	
	public Player(String n, String p) {
		loginName = n;
		password = p;
	}
	
	public Player(String n, String p, String e) {
		this(n, p);
		emailAddress = e;
		verified = false;
		lastAccessTime = new java.util.Date();
		registrationDate = java.util.Calendar.getInstance();
	}
	
	@Override
	public String toString() {
		return "Player [id=" + id + ", "
				+ "name=" + loginName 
				+ ", emailAddress=" + emailAddress 
				+ ", verified=" + verified
				+ ", password=" + password 
				+ ", lastAccessTime=" + lastAccessTime 
				+ ", registrationDate=" + registrationDate + "]";
	}
	


	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public Boolean getVerified() {
		return verified;
	}
	public void setVerified(Boolean verified) {
		this.verified = verified;
	}
	public java.util.Date getLastAccessTime() {
		return lastAccessTime;
	}
	public void setLastAccessTime(java.util.Date lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}
	public java.util.Calendar getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(java.util.Calendar registrationDate) {
		this.registrationDate = registrationDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String name) {
		this.loginName = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
	  public static void withOut(){
		    EntityManagerFactory emf = 
		     Persistence.createEntityManagerFactory("jpa-tutorial");
		    EntityManager entityManager = 
		       emf.createEntityManager();
		    entityManager.getTransaction().begin();
		    Player p = new Player();
		    p.setPassword("abc123");
		    entityManager.persist(p);
		    entityManager.getTransaction().commit();
		  }
	  
	  public static void with(){
		  	EntityManager entityManager = PersistenceManager.beginTransaction();
		    Player p = new Player();
		    p.setPassword("abc123");
		    entityManager.persist(p);
		    entityManager.getTransaction().commit();
		  }



	public static void main(String args[]) throws Exception {
		
		//MyDatabaseWizard.main(null);
		
 		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-tutorial");
		EntityManager entityManager = emf.createEntityManager();
		

		entityManager.getTransaction().begin();
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Player> criteriaQuery = criteriaBuilder.createQuery(Player.class);
		Root<Player> itemRoot = criteriaQuery.from(Player.class);
		
		Predicate predicateForName
		  = criteriaBuilder.equal(itemRoot.get("loginName"), "astra");
		
		Predicate predicateForPassword
		  = criteriaBuilder.equal(itemRoot.get("password"), "pwd");
		
		Predicate combinedPredicate
		  = criteriaBuilder.and(predicateForName, predicateForPassword);
		

		
		//criteriaQuery.where(combinedPredicate);
		List<Player> players = entityManager.createQuery(criteriaQuery).getResultList();
		
		for (Player player : players) {
			System.out.println(player);
		}
		
		
		entityManager.getTransaction().commit();
		}		

	
			




}

/*

The persistent fields or properties of an entity may be of the following types: Java primitive types,
java.lang.String, other Java serializable types (including wrappers of the primitive types,
java.math.BigInteger, java.math.BigDecimal, java.util.Date,
java.util.Calendar[5], java.sql.Date, java.sql.Time, java.sql.Timestamp,
byte[], Byte[], char[], Character[], java.time.LocalDate, java.time.Local-
Time, java.time.LocalDateTime, java.time.OffsetTime, java.time.OffsetDateTime,
and user-defined types that implement the Serializable interface); enums; entity types;
collections of entity types; embeddable classes (see Section 2.5); collections of basic and embeddable
types (see Section 2.6).


The Temporal annotation must be specified for persistent fields or properties of type
java.util.Date and java.util.Calendar unless a converter is being applied. It may only be
specified for fields or properties of these types.

The Basic annotation is the simplest type of mapping to a database column. The Basic annotation
can be applied to a persistent property or instance variable of any of the following types: Java primitive
types, wrappers of the primitive types, java.lang.String, java.math.BigInteger,
java.math.BigDecimal, java.util.Date, java.util.Calendar, java.sql.Date,
java.sql.Time, java.sql.Timestamp, java.time.LocalDate, java.time.Local-
Time, java.time.LocalDateTime, java.time.OffsetTime, java.time.OffsetDateTime,
byte[], Byte[], char[], Character[], enums, and any other type that implements
Serializable.[102] As described in Section 2.8, the use of the Basic annotation is optional for persistent
fields and properties of these types. If the Basic annotation is not specified for such a field or
property, the default values of the Basic annotation will apply.
 
 
 
 		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-tutorial");
		EntityManager entityManager = emf.createEntityManager();

		entityManager.getTransaction().begin();

		java.text.SimpleDateFormat textFormat = 
		    new java.text.SimpleDateFormat("yyyy-MM-dd");
		{
		Player p = new Player();
		p.setLoginName("mj"); p.setPassword("abc123");
		p.setEmailAddress("mj@mcnz.com");
		p.setVerified(false);
		p.setLastAccessTime(new java.util.Date());
		p.setRegistrationDate(new java.util.GregorianCalendar(2020,01,01));
		entityManager.persist(p);
		}{
		Player p = new Player();
		p.setLoginName("mario"); p.setPassword("pass");
		p.setEmailAddress("mario@scja.ca");
		p.setVerified(true);
		p.setLastAccessTime(textFormat.parse("2018-1-1"));
		p.setRegistrationDate(new java.util.GregorianCalendar(2017,01,01));
		entityManager.persist(p);
		}{
		Player p = new Player(); p.setLoginName("sk8trgrl");
		p.setPassword("password");
		p.setEmailAddress("avril@scja.com");
		p.setVerified(false);
		p.setLastAccessTime(new java.util.Date());
		p.setRegistrationDate(new java.util.GregorianCalendar(2018,01,01));
		entityManager.persist(p);
		}
		{
		Player p = new Player();
		p.setLoginName("ridley"); p.setPassword("mypassword");
		p.setEmailAddress("getbent@scja.ca");
		p.setVerified(true);
		p.setLastAccessTime(new java.util.Date());
		p.setLastAccessTime(textFormat.parse("2020-10-5"));
		p.setRegistrationDate(new java.util.GregorianCalendar(2020,5,11));
		entityManager.persist(p);
		}{
		Player p = new Player();
		p.setLoginName("kerri"); p.setPassword("pwd");
		p.setEmailAddress("sheehan@princessjava.com");
		p.setVerified(false);
		p.setLastAccessTime(textFormat.parse("2018-2-25"));
		p.setRegistrationDate(new java.util.GregorianCalendar(2017,12,12));
		entityManager.persist(p);
		}{
		Player p = new Player();
		p.setLoginName("astra"); p.setPassword("pwd");
		p.setEmailAddress("rabbit@princessjava.com");
		p.setVerified(false);
		p.setLastAccessTime(new java.util.Date());
		p.setRegistrationDate(new java.util.GregorianCalendar());
		entityManager.persist(p);
		}{
		Player p = new Player();
		p.setLoginName("cameron"); p.setPassword("90210");
		p.setEmailAddress("me@scwcd.com");
		p.setVerified(true);
		p.setLastAccessTime(textFormat.parse("2018-9-15"));
		p.setRegistrationDate(new java.util.GregorianCalendar(2018,02,15));
		entityManager.persist(p);
		}{
		Player p = new Player();
		p.setLoginName("stephen"); p.setPassword("low");
		p.setEmailAddress("stanley@pulpjava.com");
		p.setVerified(false);
		p.setLastAccessTime(textFormat.parse("2018-2-25"));
		p.setRegistrationDate(new java.util.GregorianCalendar(2018,02,15));
		entityManager.persist(p);
		}{
		Player p = new Player();
		p.setLoginName("che"); p.setPassword("password");
		p.setEmailAddress("ernesto@pulpjava.com");
		p.setVerified(true);
		p.setLastAccessTime(textFormat.parse("1999-7-26"));
		p.setRegistrationDate(new java.util.GregorianCalendar(1999,3,9));
		entityManager.persist(p);
		}{
		Player p = new Player();
		p.setLoginName("remy"); p.setPassword("password");
		p.setEmailAddress("rabbit@scja.com");
		p.setVerified(false);
		p.setLastAccessTime(new java.util.Date());
		p.setRegistrationDate(new java.util.GregorianCalendar(2017,05,21));
		entityManager.persist(p);
		}
		
		
		
 */
