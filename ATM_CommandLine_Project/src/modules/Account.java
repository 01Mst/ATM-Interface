package modules;

import java.util.ArrayList;

public class Account {
	
	private String name;
	
//	private double balance;
	
	private String uuid;
	
	private User holder;
	
	private ArrayList<Transaction> transactions;

	public Account(String name, User holder, Bank theBank) {
		
		this.name = name;
		this.holder = holder;
		
		this.uuid=theBank.getNewAccountUUID();
		
		this.transactions=new ArrayList<Transaction>();
		
		holder.addAccount(this);
		theBank.addAccount(this);
	}
	 public String getUUID() {
		 return this.uuid;
	 }
	 public String getSummaryLine() {
		 double balance=this.getBalance();
		
		 if(balance>=0) {
			 return String.format("%s:$%.02f:%s", this.uuid,balance,this.name);
		 }
		 else {
			 return String.format("%s:$(%.02f):%s", this.uuid,balance,this.name);
			 
		 }
	 }
	public double getBalance() {
		// TODO Auto-generated method stub
		double balance=0;
		for(Transaction t:this.transactions) {
			balance +=t.getAmount();
		}
		return balance;
	}
	public void printTransHistory() {
		// TODO Auto-generated method stub
		System.out.printf("\nTransaction history for account %s\n",this.uuid);
		for(int t=this.transactions.size()-1;t>=0;t--) {
			System.out.println(this.transactions.get(t).getSummaryLine());
		}
		System.out.println();
	}
	public void addTransaction(double amount, String memo) {
		// TODO Auto-generated method stub
		Transaction newTrans=new Transaction(amount,memo,this);
		this.transactions.add(newTrans);
		
	}
	
}
