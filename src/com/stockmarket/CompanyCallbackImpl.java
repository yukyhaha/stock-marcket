package com.stockmarket;

/**********************************
 *   CompanyCallbackImpl
 *   Description: the StockExchange calls 
 *                back the specific company.
 *   Author: Zhichu Huang
 *   Date: 12/3/2011
 */
import java.rmi.*;
import java.rmi.server.*;
public class CompanyCallbackImpl extends  UnicastRemoteObject implements 
CompanyCallBackInterface {
	private Company thiscompany;
	  /** Constructor */

	  public CompanyCallbackImpl(Object company) throws RemoteException {

	    thiscompany = (Company)company;

	  }


	  
	  public void SellStock(int n,double bid){   //the company will sell the stock,n means the player wants to buy
		  int m=thiscompany.GetStock()-n;
		  thiscompany.SetStock(m);
		  double j=(bid*n+thiscompany.GetStock()*thiscompany.Getprice())/(thiscompany.GetStock()+n);
		  double k=(double)Math.round(j*100)/100;   
		  thiscompany.SetPrice(k);
		  thiscompany.update();
	  }

public void BuyStock(int n,double bid){      // the company will buy the stock; n means the player wants to sell
	   int m=thiscompany.GetStock()+n;
	   thiscompany.SetStock(m);
	   double j=(bid*n+thiscompany.GetStock()*thiscompany.Getprice())/(thiscompany.GetStock()+n);
	   double k=(double)Math.round(j*100)/100;   
	   thiscompany.SetPrice(k);
	   thiscompany.update();
}    //endBuyStock

public int getStock(){
	return thiscompany.stock;
}
	
}
