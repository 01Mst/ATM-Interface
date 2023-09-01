package modules;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {
	
  private String firstName;
  
  private String lastName;
  
  private String uuid;
  
  private byte pinHash[];
  
  private ArrayList<Account> accounts;

public User(String firstName, String lastName, String pin, Bank theBank) throws NoSuchAlgorithmException {
	
	this.firstName = firstName;
	this.lastName = lastName;
	
	MessageDigest md=MessageDigest.getInstance("MD5");
	
	this.pinHash=md.digest(pin.getBytes());
	
	this.uuid=theBank.getNewUserUUID();
	
	this.accounts=new ArrayList<Account>();
	
	System.out.printf("New user %s,%s with ID %s created.\n",lastName,firstName,this.uuid);
}

public void addAccount(Account account) {
	// TODO Auto-generated method stub
	this.accounts.add(account);
	
}
  public String getUUID() {
	  return this.uuid;
  }

public boolean validatePin(String pin) throws NoSuchAlgorithmException {
	// TODO Auto-generated method stub
	
	MessageDigest md=MessageDigest.getInstance("MD5");
	return MessageDigest.isEqual(md.digest(pin.getBytes()), this.pinHash);
}

public Object getFirstName() {
	// TODO Auto-generated method stub
	return this.firstName;
}

public void printAccountsSummary() {
	// TODO Auto-generated method stub
	System.out.printf("\n\n%s's account summary ",this.firstName);
	for(int a=0;a<this.accounts.size();a++) {
		System.out.printf("%d)%s\n",a+1,this.accounts.get(a).getSummaryLine());
	}
	System.out.println();
	
}

public int numAccounts() {
	// TODO Auto-generated method stub
	return this.accounts.size();
}

public void printAccTransHistory(int acctIdx) {
	// TODO Auto-generated method stub
	this.accounts.get(acctIdx).printTransHistory();
	
}

public double getAccBalance(int acctIdx) {
	// TODO Auto-generated method stub
	return this.accounts.get(acctIdx).getBalance();
}

public String getAcctUUID(int acctIdx) {
	// TODO Auto-generated method stub
	return this.accounts.get(acctIdx).getUUID();
}

public void addAcctTransaction(int acctIdx, double amount, String memo) {
	// TODO Auto-generated method stub
	this.accounts.get(acctIdx).addTransaction(amount,memo);
	
}
  
}
