package com.mcnz.jpa.examples;

import javax.persistence.*;

@Entity
@IdClass(com.mcnz.jpa.examples.CompoundKey.class)
public class Fracture {
  Long bankId; Long userId; String bone;

  @Id
  public Long getBankId() {return bankId;}

  @Id
  public Long getPlayerId() {return userId;}
  public void setBankId(Long bankId){this.bankId = bankId;}
  public void setPlayerId(Long userId){this.userId = userId;}
  public String getBone() {return bone;}
  public void setBone(String bone) {this.bone = bone;}

  public static void main(String args[]) {
    Fracture bone = new Fracture();
    bone.setBone("arm");
    bone.setBankId( new Long(99));
    bone.setPlayerId(new Long(88));

    EntityManagerFactory emf =     
        Persistence.createEntityManagerFactory("jpa-tutorial");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();

    em.persist(bone);

    em.getTransaction().commit();
  }

}
