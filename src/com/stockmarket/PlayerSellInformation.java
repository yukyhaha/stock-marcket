package com.stockmarket;

/**********************************
 *   PlayerSellInformation
 *   Description: record the information of 
 *                the selling of the player
 *   Author: Yun Zhang
 *   Date: 12/3/2011
 */
class PlayerSellInformation{    
	String player;
	String stock;
	double sellprice;
	int StockNumber;
	PlayerCallBackInterface k;
	PlayerSellInformation(String player,String stock,double sellprice,int StockNumber,PlayerCallBackInterface g){
		this.player=player;
		this.stock=stock;
		this.sellprice=sellprice;
		this.StockNumber=StockNumber;
		this.k=g;	
	}
	public String getplayerName(){
		return player;
	}
	public double getsellprice(){
		return sellprice;
	}
		public String getstockName(){
			return stock;
			}
		public int getStockNumber(){
			return StockNumber;
		}
		public PlayerCallBackInterface PlayerCallback(){
			return k;
		}
		public void Setsellprice(double sellprice){
			this.sellprice=sellprice;
			}
		public void SetStockNumber(int number){
			this.StockNumber=number;
			}
}      //end PlayerSellInformation


