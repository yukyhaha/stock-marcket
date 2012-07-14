package com.stockmarket;

/**********************************
 *   Bank
 *   Description: for the player to 
 *                create one account 
 *   Author: Le Gao
 *   Date: 12/3/2011
 */
import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.rmi.registry.*;
public class Bank extends UnicastRemoteObject implements Bankinterface{
	static ArrayList<Account> Record = null;

      public Bank() throws RemoteException{
    	  super();
      Record=new ArrayList<Account>();    // to record the information of the accounts for each players.   	
	}
	public void CreateAccount(String name){
		Record.add(new Account(name));
	
	}//end CreateAccount
    public void Deposit(String name,double amount){
       for(Account s:Record){
           if(s.getName().equals(name)){
           s.deposit(amount);
                               }
       }   
    }//end Deposit
    

    public double CheckBalance(String name){
    double mm=0;
    	for(Account s:Record){
    	if(s.getName().equals(name))
    		mm=s.getBalance();    	    		
    	}
    	return mm; 
    }//end CheckBalance
    
    public void Withdraw(String name,double amount){
    	for(Account s:Record){
            if(s.getName().equals(name)){
            s.withdraw(amount);
                       }          
    }
    }//end Withdraw
 private static void startRegistry(int RMIPortNum)throws RemoteException{
     try {
 	 Registry registry= LocateRegistry.getRegistry(RMIPortNum);
 	 registry.list( );  
 	
 }
 catch (RemoteException ex) {
   // No valid registry at that port.
       System.out.println(
       "RMI registry cannot be located at port " + RMIPortNum);
       Registry registry= LocateRegistry.createRegistry(RMIPortNum);
       System.out.println( "RMI registry created at port " + RMIPortNum);
 }
 } // end startRegistry 	
 
 public static void main(String args[]){
	    try{
	       Bankinterface obj = new Bank();
	          startRegistry(1200);
	          Registry registry = LocateRegistry.getRegistry(1200);
	          registry.rebind("Bank", obj);
	          System.out.println("Bank " + obj + " registered");
	          Timer timer=new Timer();
	          timer.scheduleAtFixedRate(new TimerTask(){
	        		public void run() {
	        	for(int i=0;i<Record.size();i++){
	        		Record.get(i).setBalance((double)Math.round(Record.get(i).getBalance()*1.01*100)/100);   // the interest rate is 0.03
	        		 
	        	}
	        		
	        			
	        		}
	        	}, new Date(),
	        		120000);
	          
	    }
	    catch(Exception ex){
	    	 ex.printStackTrace();
	    }
	    } // end main    	
    	    	
}



//An inner class for account
 class Account {

  // Create a new lock
  private  Lock lock = new ReentrantLock();
  // Create a condition
  private  Condition newDeposit = lock.newCondition();
  public   double balance = 0;
  private String name;
  
  Account(String name){
       this.name=name;
  }
	  
 public String getName(){
       return name; 
 }
 public void setBalance(double a){
	 balance=a;
 }
  public double getBalance() {
    return balance;

  }

  public void withdraw(double amount) {

    lock.lock(); // Acquire the lock
    try {
      while (balance < amount)

        newDeposit.await();

   
    
    balance=(double)Math.round((balance-amount)*100)/100; 
    System.out.println("\t\t\tWithdraw " + amount +

      "\t\t" + getBalance());

    }

    catch (InterruptedException ex) {

      ex.printStackTrace();

    }

    finally {

      lock.unlock(); // Release the lock
    }

  }

  public void deposit(double amount) {

    lock.lock(); // Acquire the lock
    try {

 
    balance=(double)Math.round((balance+amount)*100)/100; 
      System.out.println("Deposit " + amount +

        "\t\t\t\t\t" + getBalance());



      // Signal thread waiting on the condition
      newDeposit.signalAll();

    }

    finally {

      lock.unlock(); // Release the lock
    }

  }

}



