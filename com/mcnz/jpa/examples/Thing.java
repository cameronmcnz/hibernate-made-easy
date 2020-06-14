package com.mcnz.jpa.examples;
import javax.persistence.*;

@Entity
public class Thing {
  @Id
  @GeneratedValue
  private long id;
  private String name;
  @Embedded private ThingDetail thingDetail;

  public ThingDetail getThingDetail(){return thingDetail;}
  public void setThingDetail(ThingDetail thingDetail) {
    this.thingDetail = thingDetail;
  }
  public long getId() {return id;}
  public void setId(long id) {this.id = id;}
  public String getName() {return name;}
  public void setName(String name) {this.name = name;}
	
  public static void main(String args[]) {
  
    ThingDetail detail = new ThingDetail();
    detail.setAlias("Joey Shabidoo");
    detail.setCount(10);

    Thing thing = new Thing();
    thing.setName("Homer");
   thing.setThingDetail(detail);

    EntityManagerFactory emf =     
        Persistence.createEntityManagerFactory("jpa-tutorial");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
/*only the instance of Thing is explicitly saved*/
   em.persist(thing);
    em.getTransaction().commit();

  }
}
