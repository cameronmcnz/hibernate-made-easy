package com.mcnz.jpa.examples; import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "client_detail", 
        schema = "hibernate_examples")

public class ClientDetail {

	@Id
	 @GeneratedValue
	 @Column(name = "id")
	 private Long id;	
	 
	 @OneToOne(cascade=CascadeType.ALL, 
	                 mappedBy="clientDetail")
	  public Client client;

  private String firstName, middleName, lastName; 
  private String emailAddress, passwordHint;
  private Date registrationDate, verificationDate;

  public Client getClient(){return client;}
  public void setClient(Client client){this.client=client;}

  public Long getId() {return id;}
  public void setId(Long id) {this.id = id;}

  public String getEmailAddress() {return emailAddress;}
  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }
  public String getFirstName() {return firstName;}
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public String getLastName() {return lastName;}
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public String getMiddleName() {return middleName;}
  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }
  public String getPasswordHint() {return passwordHint;}
  public void setPasswordHint(String passwordHint) {
    this.passwordHint = passwordHint;
  }
  public Date getRegistrationDate() {return registrationDate;}
  public void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }
  public Date getVerificationDate() {return verificationDate;}
  public void setVerificationDate(Date verificationDate) {
    this.verificationDate = verificationDate;
  }
}
