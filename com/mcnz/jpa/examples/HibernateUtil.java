package com.mcnz.jpa.examples;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static EntityManagerFactory factory;

	public static Session getSession() {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory("jpa-tutorial");
		}
		EntityManager entityManager = factory.createEntityManager();
		Session session = entityManager.unwrap(org.hibernate.Session.class);

		SessionFactory factory = session.getSessionFactory();

		return session;
	}

	public static Session beginTransaction() {
		Session hibernateSession = getCurrentSession();
		hibernateSession.beginTransaction();
		return hibernateSession;
	}

	public static void commitTransaction(Session s) {
		s.getTransaction().commit();
	}

	public static void rollbackTransaction(Session s) {
		s.getTransaction().rollback();
	}

	public static void closeSession(Session s) {
		s.close();
	}

	public static Session getCurrentSession() {
		Map<String, String> settings = new HashMap<>();
		settings.put("connection.driver_class", "com.mysql.jdbc.Driver");
		settings.put("dialect", "org.hibernate.dialect.MySQL8Dialect");
		settings.put("hibernate.connection.url", "jdbc:mysql://localhost/hibernate_examples");
		settings.put("hibernate.connection.username", "root");
		settings.put("hibernate.connection.password", "password");
		settings.put("hibernate.current_session_context_class", "thread");
		settings.put("hibernate.show_sql", "true");
		settings.put("hibernate.format_sql", "true");

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(settings).build();

		MetadataSources metadataSources = new MetadataSources(serviceRegistry);
		metadataSources.addAnnotatedClass(Player.class);

		Metadata metadata = metadataSources.buildMetadata();
		SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
		Session session = sessionFactory.getCurrentSession();
		return session;
	}

	public static Session getCurrentSessionFromConfig() {
		Configuration config = new Configuration();
		config.configure();
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		return session;
	}

	public static SessionFactory getCurrentSessionFromJPA() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-tutorial");
		EntityManager entityManager = emf.createEntityManager();
		Session session = entityManager.unwrap(org.hibernate.Session.class);
		SessionFactory factory = session.getSessionFactory();
		return factory;
	}

}
