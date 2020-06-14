package com.mcnz.jpa.examples;import javax.persistence.*;
@Entity
public class Child extends Parent {
  private String firstName;
  public String getFirstName() {return firstName;}
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
}
