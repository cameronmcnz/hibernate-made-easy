package com.mcnz.jpa.examples;
import java.util.*;import javax.persistence.*;
@Entity
@Table(name = "client", schema = "hibernate_examples")
public class Client {
 @OneToMany(mappedBy="client", targetEntity=Address.class,   
         fetch=FetchType.EAGER, cascade = CascadeType.ALL)
 private List<Address> addresses=new ArrayList<Address>();
 @ManyToMany(cascade = CascadeType.ALL)
 @JoinTable(name = "client_skill", 
  joinColumns = { @JoinColumn(name = "client_id") }, 
   inverseJoinColumns = { @JoinColumn(name = "skill_id") })
 private List<Skill> skills = new ArrayList<Skill>();
 @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
 @JoinColumn(name="detail_id")
 private ClientDetail clientDetail;
 @Id
 @GeneratedValue
 @Column(name = "id")
 private Long id;
 private String username;
 private String password;private Boolean verified;
 public Long getId() {return id;}
 public void setId(Long id) {this.id = id;}
 public List<Skill> getSkills() {return skills;}
 public void setSkills(List<Skill> skills){this.skills=skills;}	
 public List<Address> getAddresses() {return addresses;}
 public void setAddresses(List<Address> addresses) {
  this.addresses = addresses;
 }
 public ClientDetail getClientDetail(){return clientDetail;}
 public void setClientDetail(ClientDetail clientDetail) {
  this.clientDetail = clientDetail;
 }
 public String getPassword() {return password;}
 public void setPassword(String password){this.password = password;}


public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public Boolean getVerified() {return verified;}
 public void setVerified(Boolean verified){this.verified=verified;}
}
