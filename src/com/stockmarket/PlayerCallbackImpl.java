package com.stockmarket;

/**********************************
 *   PlayerCallbackImpl
 *   Description: the StockExchange 
 *     calls back the specific player
 *   Author: Yun Zhang
 *   Date: 12/3/2011
 */
import java.rmi.*;
import java.rmi.server.*;
public class PlayerCallbackImpl extends  UnicastRemoteObject implements PlayerCallBackInterface {            
	private Player thisplayer;
	  /** Constructor */
	  public PlayerCallbackImpl(Object player) throws RemoteException {
	    thisplayer = (Player)player;
	  }
	 
	  public String getName(){
		  return thisplayer.name;
	  }
	  
	  public int getStock1(){
		  return thisplayer.stock1;
	  }
	  public int getStock2(){
		  return thisplayer.stock2;
	  }
	  public int getStock3(){
		  return thisplayer.stock3;
	  }
	  public void SellStock(String StockName,int StockNumber,double averageprice){    
		  if(StockName.equals("ibm")){
			  thisplayer.stock1-=StockNumber;
			//  System.out.print("1");
		  }
		  else if(StockName.equals("acer")){
			  thisplayer.stock2-=StockNumber;
		  }	  
		  else if(StockName.equals("dell")){
			  thisplayer.stock3-=StockNumber;
		  }		  
		
		  thisplayer.balance+=(double)Math.round((averageprice*StockNumber*100)/100);		  
	  }
	  
	  public void SetMyturn(int m){    // to give the token
		  thisplayer.myturn=m;
	  }
	  
	  public void printInformation(String name1,String name2,int n,String name3){
		  thisplayer.jtextarea2.append(name1+" "+"buys"+" "+n+" "+name3+" "+"from"+" "+name2+'\n');
	  }
	  public void BuyStock(String StockName,int StockNumber,double averageprice){    
		  if(StockName.equals("ibm")){
			  thisplayer.stock1+=StockNumber;
		  }
		  else if(StockName.equals("acer")){
			  thisplayer.stock2+=StockNumber;
		  }	  
		  else if(StockName.equals("dell")){
			  thisplayer.stock3+=StockNumber;
		  }		  
		  thisplayer.balance-=(double)Math.round((averageprice*StockNumber*100)/100);		  
	  }
	  public void show4(String name){
		   
		   thisplayer.jtextarea2.append(name+" "+"joins"+" "+"the game"+'\n');
	  }
	  public double getBalance(){
		  return thisplayer.balance;
	  }
	public void show44(){
		thisplayer.jtextarea1.append(""+'\n');
	}
	public void show11(){
		thisplayer.jtextarea1.setText("");
		thisplayer.jtextarea1.append("Company"+"      "+"Stock"+"    "+"StockNumber"+"       "+"price"+'\n');
	}
	public void show1(String companyName,String StockName,double price,int StockNumber){
		// show the information of the company
		 thisplayer.jtextarea1.append(companyName+"    "+StockName+"      "+StockNumber+"                   "+price+'\n');
	}
	public void show22(){
		thisplayer.jtextarea1.setText("");
		thisplayer.jtextarea1.append("SellInformation below"+'\n');
		thisplayer.jtextarea1.append("PlayerName"+"   "+"StockName"+"   "+"StockNumber"+"   "+"Sellprice"+'\n');
	}
	public void show2(String playerName,String StockName,double sellprice,int StockNumber){
		thisplayer.jtextarea1.append(playerName+"                "+StockName+"               "+StockNumber
				+"                     "+sellprice+'\n');
	}
	public void show33(){
		thisplayer.jtextarea1.append("BuyInformation below"+'\n');
		thisplayer.jtextarea1.append("PlayerName"+"   "+"StockName"+"   "+"StockNumber"+"    "+"buyprice"+'\n');
	}
	  public void show3(String playerName,String StockName,double buyprice,int StockNumber){
		  thisplayer.jtextarea1.append(playerName+"                     "+StockName+"               "+StockNumber
				  +"                  "+buyprice+'\n');
	  }
	  public void show55(String name){
		  thisplayer.jtextarea2.append(name+" "+"lost the connection with this game"+'\n');
	  }
	  public void showResult(String name1){
		  thisplayer.jtextarea2.append("time is out"+","+" "+name1+" "+"wins this game"+'\n');
	  }
	  
	  public double checkaccount(String name){
		return thisplayer.checkaccount(name);
		  
	  }
	  public int Check(){
		  return thisplayer.checkpoint;
	  }
	  public int ReturnStock(String name){
		  int stock=0;
		  if(name.equals("ibm")){
			  stock=thisplayer.stock1;
		  }
		  else if(name.equals("acer")){
			  stock=thisplayer.stock2;
		  }
		  else if (name.equals("dell")){
			  stock=thisplayer.stock3;
		  }
		  return stock;
	  }
	public void show66(int a){
		thisplayer.jtextarea2.append("the game has processed"+" "+a+" "+"minutes"+'\n');
	}
}
