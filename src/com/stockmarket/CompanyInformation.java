package com.stockmarket;

/**********************************
 *   CompanyInformation
 *   Description: for Stock Exchange to 
 *        Store the information of the company;
 *   Author: Zhichu huang
 *   Date: 12/3/2011
 */
class CompanyInformation{    
	String company;
	String StockName;    // record it as company name
	double price;    // current price of a stock in a company
	
	int StockNumber;
	CompanyCallBackInterface g;
CompanyInformation(String company,String StockName,double price,int StockNumber,CompanyCallBackInterface g){
		this.company=company;
	    this.StockName=StockName;
		this.price=price;
		this.StockNumber=StockNumber;
		this.g=g;
		}
public String getcompanyName(){
	return company;
}
	public String getName(){
		return StockName;
		}
	public double getprice(){
		return price;
	}
	public int getStockNumber(){
		return StockNumber;
	}
	public CompanyCallBackInterface Companycallback(){
		return g;
	}	
	public void Setprice(double price){
		this.price=price;
		}

	public void SetStockNumber(int number){
		this.StockNumber=number;
		}
}   // end CompanyInformation

