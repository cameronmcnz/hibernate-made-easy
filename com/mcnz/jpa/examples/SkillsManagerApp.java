package com.mcnz.jpa.examples;
import java.util.*; import javax.persistence.*;
public class SkillsManagerApp {
 public static void main(String arss[]) {
 Client client = new Client();
 client.setUsername("me");
 client.setPassword("passw0rd");
 ClientDetail detail = new ClientDetail();
 detail.setEmailAddress("mail@scja.com");
 detail.setFirstName("Cameron");
 detail.setLastName("McKenzie");
 client.setClientDetail(detail);
 Address address = new Address();
 address.setAddressLine1("390 Queens Quay");
 address.setAddressLine2("apt 2301");
 address.setCity("Toronto");
 address.setCountry("Canada");
 address.setClient(client);
 client.getAddresses().add(address);
 Skill basting = new Skill();
 basting.setName("turkey basting");
 client.getSkills().add(basting);
 Skill kicking = new Skill();
 kicking.setName("tire kicking");
 /* kicking not added as a skill */
 Skill polishing = new Skill();
 polishing.setName("shoe polishing");
 client.getSkills().add(polishing);
 EntityManagerFactory emf =
  Persistence.createEntityManagerFactory("jpa-tutorial");
 EntityManager em = emf.createEntityManager();
 em.getTransaction().begin();
 em.persist(client);
 em.getTransaction().commit();
 }
}
