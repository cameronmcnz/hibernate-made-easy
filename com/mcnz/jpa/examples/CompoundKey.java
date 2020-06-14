package com.mcnz.jpa.examples;import javax.persistence.Embeddable;
/* Final Iteration of the CompoundKey Class */
@Embeddable
public class CompoundKey implements java.io.Serializable{
  private Long userId;  private Long bankId;
  public CompoundKey() { }
  public CompoundKey(Long user, Long bank) {
    userId = user;  bankId = bank;
  }
  public Long getBankId() {return bankId;}
  public void setBankId(Long bankId) {
    this.bankId = bankId;
  }
  public Long getPlayerId() {return userId;}
  public void setPlayerId(Long userId) {
    this.userId = userId;
  }

  public boolean equals(Object key) {
   boolean result = true;
   if (!(key instanceof CompoundKey)) {return false;}
    Long otherPlayerId = ((CompoundKey)key).getPlayerId();
    Long otherBankId = ((CompoundKey)key).getBankId();
    if (bankId == null || otherBankId == null) {
      result = false;
    }else {
      result = bankId.equals(otherBankId);
    }
    if (userId == null || otherPlayerId == null) {
      result = false;
    }else {
      result = userId.equals(otherPlayerId);
    }
   return result;
  }

  public int hashCode() {
    int code = 0;
    if (userId!=null) {code +=userId;}
    if (bankId!=null) {code +=bankId;}
    return code;
  }
}
