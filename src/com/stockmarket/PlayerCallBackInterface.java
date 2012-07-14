package com.stockmarket;

/**********************************
 *   PlayerCallBackInterface
 *   Description: the StockExchange calls back the specific player
 *   Author: Yun Zhang
 *   Date: 12/3/2011
 */
import java.rmi.*;
public interface PlayerCallBackInterface extends java.rmi.Remote {
  public void SellStock(String StockName,int StockNumber,double averageprice)throws java.rmi.RemoteException;
  // StockNumber means a certain number
  public void BuyStock(String StockName,int StockNumber,double averageprice)throws java.rmi.RemoteException;
  // StockNumber means a certain number
  public void printInformation(String name1,String name2,int n,String name3)throws java.rmi.RemoteException;
  // name1 means the buyer, name2 means the seller, and n means the number of the stock, and name3 means the
  //name of the stock
  public double getBalance()throws java.rmi.RemoteException;
  public String getName()throws java.rmi.RemoteException;
  public void show1(String companyName,String StockName,double price,int StockNumber)
  throws java.rmi.RemoteException;
  public void show11()throws java.rmi.RemoteException;
  public void show2(String playerName,String StockName,double sellprice,int StockNumber)
  throws java.rmi.RemoteException;
  public void show22() throws java.rmi.RemoteException;
  public void show3(String playerName,String StockName,double buyprice,int StockNumber)
  throws java.rmi.RemoteException;  
  public void show33()throws java.rmi.RemoteException;
  public void SetMyturn(int m)throws java.rmi.RemoteException; 
  public void show4(String name) throws java.rmi.RemoteException;       // show who joins the game
  public void show44() throws java.rmi.RemoteException;   
  public void showResult(String name) throws java.rmi.RemoteException;   
  public double checkaccount(String name) throws java.rmi.RemoteException;   
  public int Check()throws java.rmi.RemoteException; // to check whether the player is crashed
  public void show55(String name)throws java.rmi.RemoteException; // to tell others who lost the game  
  public int getStock1()throws java.rmi.RemoteException;
  public int getStock2()throws java.rmi.RemoteException;
  public int getStock3()throws java.rmi.RemoteException;
  public int ReturnStock(String name)throws java.rmi.RemoteException;
  public void show66(int a)throws java.rmi.RemoteException;
}

