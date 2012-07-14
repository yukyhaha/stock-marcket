package com.stockmarket;

/**********************************
 *   CompanyCallBackInterface
 *   Description: the StockExchange calls
 *               back the specific company.
 *   Author: Zhichu Huang
 *   Date: 12/3/2011
 */
import java.rmi.*;
public interface CompanyCallBackInterface extends java.rmi.Remote {	
	public void SellStock(int n,double bid)throws java.rmi.RemoteException;// sell the Stock to the client
	public void BuyStock(int n,double bid)throws java.rmi.RemoteException; //buy the Stock form the client
	public int getStock()throws java.rmi.RemoteException;
}
