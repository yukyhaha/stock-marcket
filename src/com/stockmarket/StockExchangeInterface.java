package com.stockmarket;

/**********************************
 *   StockExchangeInterface
 *   Description: This is the interface of 
 *   stock exchange server. 
 *   Author: Ou Mi
 *   Date: 12/3/2011
 */
import java.rmi.Remote;
public interface StockExchangeInterface extends Remote {
	public void InitializeCompany(String company,String StockName,double price,int StockNumber,CompanyCallBackInterface g)
	throws java.rmi.RemoteException;
	public void UpdateCompanyInformation(String companyname,double price,int StockNumber)
	throws java.rmi.RemoteException;
	public void PlayerBuy(String player,String StockName,double buyprice,int StockNumber,PlayerCallBackInterface g)
	throws java.rmi.RemoteException;
	public void PlayerSell(String player,String StockName,double sellprice,int StockNumber,PlayerCallBackInterface g)
	throws java.rmi.RemoteException;
	public void addCallback(String name,PlayerCallBackInterface CallbackObject)throws java.rmi.RemoteException;
	public void show(PlayerCallBackInterface g)throws java.rmi.RemoteException;
	public void show2(PlayerCallBackInterface g)throws java.rmi.RemoteException;
	public void requesttoken(PlayerCallBackInterface g)throws java.rmi.RemoteException;
	public void releasetoken()throws java.rmi.RemoteException;

}
