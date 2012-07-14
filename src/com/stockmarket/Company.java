package com.stockmarket;

/**********************************
 *   Company
 *   Description: company class, deal 
 *    with all the operation for company 
 *   Author: Zhichu Huang
 *   Date: 12/3/2011
 */
import javax.swing.*;
import java.awt.*;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.awt.event.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
public class Company {
	private String name;
	private String stockName;
	private double price=30;  //the price of the current stock
	public int stock=1000;
	private StockExchangeInterface w;
	CompanyCallbackImpl g =null;
	Company(String name,String stockName){
      this.name=name;
      this.stockName=stockName;
      initializeRMI1(); 
     
try{
	  g= new CompanyCallbackImpl(this);       // put the information into the server
      w.InitializeCompany(this.name,this.stockName,this.price,this.stock,(CompanyCallBackInterface)g);
      adjustprice();
}
catch(Exception e){
	
}
   
	}
   public int GetStock(){
	   return this.stock;
   }
	public void SetPrice(double price){
		this.price=price;
	}// end SetPrice
	
	public void adjustprice(){
		Timer timer=new Timer();
	
        timer.scheduleAtFixedRate(new TimerTask(){
        	    public void run() {
        	    int ll=(int)(Math.random()*2);
      		
      	     if(ll==0){                            //the price will be up;
      	    price=(double)Math.round((price*=(1+((Math.random()*2+1))/100))*100)/100; //0.01---0.03
      	   
      	    update();
      	}
      	     else if(ll==1){
      			
      			price=(double)Math.round((price*=(1-((Math.random()*2+1)/100)))*100)/100;//0.01---0.03
      			update();
      		}
      		}
      	}, new Date(),
      		60000); 
	}
	
	public void SetStock(int stock){
		this.stock=stock;
	}// end SetStock
	
	public double Getprice(){
		return price;
	}
	
	public void update(){       // if there is some changes of the company, then update to the StockExchange
		try{
			w.UpdateCompanyInformation(name,price,stock);
		}
		catch(Exception e){			
		}
	}
	
	
	protected void initializeRMI1() {   //for Stock Exchange
	    String host = "";
	    try {	    	  
	      Registry registry = LocateRegistry.getRegistry("localhost");
	      w = (StockExchangeInterface)registry.lookup("Stock Exchange");
	      System.out.println("Stock Exchange object " + w + " found");
	    }
	    catch(Exception ex) {
	      System.out.println(ex);
	    }
	    
	 }//end initializeRMI
	public static void main(String args[]){
		Company company1=new Company("company1","ibm");
		Company company2=new Company("company2","acer");
		Company company3=new Company("company3","dell");
	}
}


