package com.mcnz.jpa.examples;
import javax.persistence.*;

@Entity
@Table(name = "BlobClobBoBob", 
    schema = "hibernate_examples")
public class BlobClobber {
  Long id;
  String beans; 
  char[] ming;
  Character[] witness;
  java.sql.Clob sqlClob;

  java.sql.Blob sqlBlob;
  byte[] me; 
  
  public void setId(Long id) {
	this.id = id;
}
public void setBeans(String beans) {
	this.beans = beans;
}
public void setMing(char[] ming) {
	this.ming = ming;
}
public void setWitness(Character[] witness) {
	this.witness = witness;
}
public void setSqlClob(java.sql.Clob sqlClob) {
	this.sqlClob = sqlClob;
}
public void setSqlBlob(java.sql.Blob sqlBlob) {
	this.sqlBlob = sqlBlob;
}
public void setMe(byte[] me) {
	this.me = me;
}
@Id
  @GeneratedValue
  @Column(name = "id")
  public Long getId() {return id;}
  @Lob
  public String getBeans() {return beans;}
  @Lob
  public byte[] getMe() {return me;}
  @Lob
  public char[] getMing() {return ming;}
  @Lob
  public java.sql.Blob getSqlBlob() {return sqlBlob;}
  @Lob
  public java.sql.Clob getSqlClob() {return sqlClob;}
  @Lob
  public Character[] getWitness() {return witness;}
}  
