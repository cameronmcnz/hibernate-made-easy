package com.mcnz.jpa.examples;
import org.hibernate.Session;
public class HibernateCrudExample {
  public static void main(String[] args) {
    HibernateCrudExample.createRecord();
    HibernateCrudExample.retrieveRecord();
    HibernateCrudExample.updateRecord();
    HibernateCrudExample.deleteRecord();
  }
  public static void createRecord(){
   Session sess = HibernateUtil.beginTransaction();
   Player p = new Player(); p.setPassword("hib123");
   sess.persist(p);
   HibernateUtil.commitTransaction(sess);
  }
  public static void retrieveRecord(){
   Session sess = HibernateUtil.beginTransaction();
   Player p=sess.find(Player.class, Long.valueOf(2));
   HibernateUtil.commitTransaction(sess);
  }
 public static void updateRecord(){
   Session sess = HibernateUtil.beginTransaction();
   Player p = sess.find(Player.class, Long.valueOf(2));
   p.setPassword("kowabunga");
   HibernateUtil.commitTransaction(sess);
 }
 public static void deleteRecord(){
  Session sess = HibernateUtil.beginTransaction();
  Player p = sess.find(Player.class, Long.valueOf(2));
  sess.remove(p);  //record is deleted
  HibernateUtil.commitTransaction(sess);
 }
}  
