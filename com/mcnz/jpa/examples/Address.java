package com.mcnz.jpa.examples;
import javax.persistence.*;
@Entity
@Table(name = "address", schema = "hibernate_examples")
public class Address {
  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @JoinColumn(name="client_id")
  private Client client;

  @Column(name = "addr1", nullable=true)
  private String addressLine1;

  @Column(name = "addr2", nullable=true)
  private String addressLine2;

  private String city, state, country, code;

  public Long getId() {return id;}
  public void setId(Long id) {this.id = id;}
  public Client getClient() {return client;}
  public void setClient(Client c) {this.client = c;}
  public String getAddressLine1() {return addressLine1;}
  public void setAddressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
  }
  public String getAddressLine2() {return addressLine2;}
  public void setAddressLine2(String addressLine2) {
    this.addressLine2 = addressLine2;
  }
  public String getCity() {return city;}
  public void setCity(String city) {this.city = city;}
  public String getCountry() {return country;}
  public void setCountry(String c) {this.country = c;}
  public String getCode() {return code;}
  public void setCode(String code) {this.code = code;}
  public String getState() {return state;}
  public void setState(String state) {this.state = state;}
}
