package com.stockmarket;

/**********************************
 *   Bankinterface
 *   Description: for the player to 
 *                create one account
 *   Author: Le Gao
 *   Date: 12/3/2011
 */
import java.rmi.Remote;
public interface Bankinterface extends Remote {	
	 public void CreateAccount(String m) throws java.rmi.RemoteException;
	 public void Deposit(String m,double amount) throws java.rmi.RemoteException;
	 public void Withdraw(String m,double amount) throws java.rmi.RemoteException;
	 public double CheckBalance(String name) throws java.rmi.RemoteException;
	}
