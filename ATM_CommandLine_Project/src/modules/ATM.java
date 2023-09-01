package modules;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class ATM {
  public static void main(String[] args) throws NoSuchAlgorithmException {
	  
	  Scanner sc=new Scanner(System.in);
	  
	  Bank theBank=new Bank("Indian Overseas Bank");
	  
	  User aUser=theBank.addUser("Thulasimani", "S", "3216");
	 // User bUser=theBank.addUser("Thulasimani", "S", "3216");
	  
	  
	  Account newAccount=new Account("Checking",aUser,theBank);
	  aUser.addAccount(newAccount);
	  theBank.addAccount(newAccount);
	  
	  User curUser;
	  while(true) {
		  curUser=ATM.mainMenuPrompt(theBank,sc);
		ATM.printUserMenu(curUser,sc);
	  }
  }

private static void printUserMenu(User theUser, Scanner sc) {
	// TODO Auto-generated method stub
	theUser.printAccountsSummary();
	
	int choice;
	do {
		System.out.printf("Welcome %s, What would you like to do?",theUser.getFirstName());
		System.out.println(" 1-> Show account transaction history");
	    System.out.println(" 2-> Withdrawl");
	    System.out.println(" 3-> Deposit");
	    System.out.println(" 4-> Transfer");
	    System.out.println(" 5-> Quit");
	    System.out.println();
	    System.out.println("Enter Choice: ");
	    choice=sc.nextInt();
	    if(choice<1||choice>5) {
	    	System.out.println("Invalid choice. Please choose 1 to 5");
	    }
	}while(choice<1||choice>5);
	
	switch(choice) {
	case 1:
		ATM.showTransactionHistory(theUser, sc);
		break;
	case 2:
		ATM.WithdrawlFunds(theUser,sc);
		break;
	case 3:
		ATM.depositFunds(theUser,sc);
		break;
	case 4:
		ATM.transferFunds(theUser,sc);
		break;
	}
	if(choice!=5) {
		ATM.printUserMenu(theUser, sc);
	}
	
	
	
}

private static void WithdrawlFunds(User theUser, Scanner sc) {

	// TODO Auto-generated method stub
	
	int fromAcct;
	 double amount;
	 double acctBal;
	 String memo;
	 
	 do {
		 System.out.printf("Enter the number (1-%d) of the account\n "+"to withdraw from: ",theUser.numAccounts());
		 fromAcct=sc.nextInt();
		 if(fromAcct<0||fromAcct>=theUser.numAccounts()) {
			System.out.println("Invalid account. Please try again."); 
		 }
	 }while(fromAcct<0||fromAcct>=theUser.numAccounts());
	 acctBal=theUser.getAccBalance(fromAcct);
	 do {
		 System.out.printf("Enter the amount to transfer (max $%.02f):$",acctBal);
		amount=sc.nextDouble();
		if(amount<0) {
			System.out.println("Amount must be greater than zero.");
			
		}else if(amount>acctBal) {
			System.out.printf("Amount must be greater than\n"+"balance of $%.02f.\n",acctBal);
			
		}
	 }while(amount<0||amount>acctBal);
	 
	 sc.nextLine();
	 System.out.println("Enter a memo: ");
	 memo=sc.nextLine();
	 
	 theUser.addAcctTransaction(fromAcct, -1*amount, memo);
	
}

private static void transferFunds(User theUser, Scanner sc) {
	// TODO Auto-generated method stub
	 int fromAcct;
	 int toAcct;
	 double amount;
	 double acctBal;
	 
	 do {
		 System.out.printf("Enter the number (1-%d) of the account\n "+"to transfer from: ",theUser.numAccounts());
		 fromAcct=sc.nextInt();
		 if(fromAcct<0||fromAcct>=theUser.numAccounts()) {
			System.out.println("Invalid account. Please try again."); 
		 }
	 }while(fromAcct<0||fromAcct>=theUser.numAccounts());
	 acctBal=theUser.getAccBalance(fromAcct);
	 
	 do {
		 System.out.printf("Enter the number (1-%d) of the account\n "+"to transfer to: ");
		 toAcct=sc.nextInt();
		 if(toAcct<0||toAcct>=theUser.numAccounts()) {
			System.out.println("Invalid account. Please try again."); 
		 
	 }
	 }while(toAcct<0||toAcct>=theUser.numAccounts());
	 
	 do {
		 System.out.printf("Enter the amount to transfer (max $%.02f):$",acctBal);
		amount=sc.nextDouble();
		if(amount<0) {
			System.out.println("Amount must be greater than zero.");
			
		}else if(amount>acctBal) {
			System.out.printf("Amount must be greater than\n"+"balance of $%.02f.\n",acctBal);
			
		}
	 }while(amount<0||amount>acctBal);
	 
	 theUser.addAcctTransaction(fromAcct,-1*amount,String.format("Transfer to amount %s",theUser.getAcctUUID(toAcct)));
	 theUser.addAcctTransaction(toAcct,amount,String.format("Transfer to amount %s",theUser.getAcctUUID(fromAcct)));
}

private static void depositFunds(User theUser, Scanner sc) {
	// TODO Auto-generated method stub
	int toAcct;
	 double amount;
	 double acctBal;
	 String memo;
	 
	 do {
		 System.out.printf("Enter the number (1-%d) of the account\n "+"to deposit in: ",theUser.numAccounts());
		 toAcct=sc.nextInt();
		 if(toAcct<0||toAcct>=theUser.numAccounts()) {
			System.out.println("Invalid account. Please try again."); 
		 }
	 }while(toAcct<0||toAcct>=theUser.numAccounts());
	 acctBal=theUser.getAccBalance(toAcct);
	 do {
		 System.out.printf("Enter the amount to transfer (max $%.02f):$",acctBal);
		amount=sc.nextDouble();
		if(amount<0) {
			System.out.println("Amount must be greater than zero.");
			
		}
	 }while(amount<0);
	 
	 sc.nextLine();
	 System.out.println("Enter a memo: ");
	 memo=sc.nextLine();
	 
	 theUser.addAcctTransaction(toAcct, amount, memo);
	
	
}

private static void withdrawlFunds(User theUser, Scanner sc) {
	// TODO Auto-generated method stub
	int theAcct;
	
	do {
		System.out.printf("Enter the number (1-%d) of the "
				+ "account\n whose the transaction you want to see: ",theUser.numAccounts());
	theAcct=sc.nextInt()-1;
	if(theAcct<0||theAcct>=theUser.numAccounts()) {
		System.out.println("Invalid account. Please try again.");
	}
	}while(theAcct<0||theAcct>=theUser.numAccounts());
	
	theUser.printAccTransHistory(theAcct);
	
	
	
}

private static void showTransactionHistory(User theUser, Scanner sc) {
	// TODO Auto-generated method stub
	int theAcct;
	do {
		System.out.printf("Enter the number (1-%d) of the account\n"
				+"whose transaction you want to see:",theUser.numAccounts());
		theAcct=sc.nextInt()-1;
		if(theAcct<0||theAcct>=theUser.numAccounts()) {
			System.out.println("Invalid account. Please try again.");
		}
	}while(theAcct<0||theAcct>=theUser.numAccounts());
	theUser.printAccTransHistory(theAcct);
}


private static User mainMenuPrompt(Bank theBank, Scanner sc) throws NoSuchAlgorithmException {
	// TODO Auto-generated method stub
	
	String userID;
	String pin;
	User outUser;
	
	do {
		System.out.printf("\n\n Welcome to %s\n\n",theBank.getName());
		System.out.print("Enter User ID: ");
		userID=sc.nextLine();
		System.out.println("Enter PIN: ");
		pin=sc.nextLine();
		
		outUser=theBank.userLogin(userID,pin);
		if(outUser==null) {
			System.out.println("Incorrect user ID/pin combination. "+"Please try again.");
			
		}
		
	}while(outUser==null);
	
	return outUser;
}
}
