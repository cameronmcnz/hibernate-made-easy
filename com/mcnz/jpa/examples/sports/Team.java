package com.mcnz.jpa.examples.sports;
import java.util.*; import javax.persistence.*;
@Entity
public class Team {
 @Id
 @GeneratedValue
 private long id; 
 private String name;
 @OneToMany(mappedBy="team",  
              targetEntity=Player.class, 
    fetch=FetchType.EAGER, cascade = CascadeType.ALL)
 private List<Player> players; 

 public List<Player> getPlayers() {return players;}
 public void setPlayers(List<Player> p){players=p;}
 public long getId() {return id;}
 public void setId(long id) {this.id = id;}
 public String getName() {return name;}
 public void setName(String name) {this.name = name;}

 public static void main(String args[]){
    EntityManagerFactory emf =     
        Persistence.createEntityManagerFactory("jpa-tutorial");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();

    Team team = new Team();
    Player p1 = new Player();
    Player p2 = new Player();

    em.persist(team);
    em.persist(p1);
    em.persist(p2);

    team.setName("Pickering Atoms");
    p1.setNickName("Lefty");
    p1.setTeam(team);
    p2.setNickName("Blinky");
    p2.setTeam(team);
    em.getTransaction().commit();
  }
}
