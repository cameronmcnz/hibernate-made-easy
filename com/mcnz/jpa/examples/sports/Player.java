package com.mcnz.jpa.examples.sports; 
import javax.persistence.*;
@Entity(name="playa")
public class Player { 
 @Id
 @GeneratedValue
 private long id;
 private String nickName;
 @ManyToOne
 @JoinColumn(name="team_id")      
 private Team team;

 public Team getTeam() {return team;}
 public void setTeam(Team t) {this.team = t;}
 public long getId() {return id;}
 public void setId(long id) {this.id = id;}
 public String getNickName() {return nickName;}
 public void setNickName(String n) {nickName = n;}
}
