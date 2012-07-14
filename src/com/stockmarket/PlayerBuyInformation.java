package com.stockmarket;

/**********************************
 *   PlayerBuyInformation
 *   Description: Record the information of the buying of the player 
 *   Author: Yun Zhang
 *   Date: 12/3/2011
 */
class PlayerBuyInformation{          
	String player;
	String stock;
	double buyprice; 
	int StockNumber;
	PlayerCallBackInterface k;
	PlayerBuyInformation(String player,String stock,double buyprice,int StockNumber,PlayerCallBackInterface g){
		this.player=player;
		this.stock=stock;
		this.buyprice=buyprice; 
		this.StockNumber=StockNumber;
		this.k=g;	
	}
	public String getplayerName(){
		return player;
	}
	public double getbuyprice(){
		return buyprice;
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
		public void Setbuyprice(double buyprice){
			this.buyprice=buyprice;
			}
		public void SetStockNumber(int number){
			this.StockNumber=number;
			}
}     // end PlayerbuyInformation