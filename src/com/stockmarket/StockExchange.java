package com.stockmarket;

/**********************************
 *   StockExchange
 *   Description: This is the stock exchange server, 
 *   response to every request the play send to the server
 *   Author: Ou Mi
 *   Date: 12/3/2011
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.rmi.registry.*;
public class StockExchange extends UnicastRemoteObject implements StockExchangeInterface{
	static ArrayList<CompanyInformation> company = null;  // records the information of the company 
	static ArrayList<PlayerSellInformation> playersell = null;   // records the information of the player sell
	static ArrayList<PlayerBuyInformation> playerbuy = null;  //records the information of the player buy
	private static int endstatus=2;     
	public static int getEndstatus() {
		return endstatus;
	}


	public static ArrayList<CompanyInformation> getCompany() {
		return company;
	}


	public static void setCompany(ArrayList<CompanyInformation> company) {
		StockExchange.company = company;
	}


	public static ArrayList<PlayerSellInformation> getPlayersell() {
		return playersell;
	}


	public static void setPlayersell(ArrayList<PlayerSellInformation> playersell) {
		StockExchange.playersell = playersell;
	}


	public static ArrayList<PlayerBuyInformation> getPlayerbuy() {
		return playerbuy;
	}


	public static void setPlayerbuy(ArrayList<PlayerBuyInformation> playerbuy) {
		StockExchange.playerbuy = playerbuy;
	}


	public static Vector getCallbackObjects() {
		return callbackObjects;
	}


	public static void setCallbackObjects(Vector callbackObjects) {
		StockExchange.callbackObjects = callbackObjects;
	}


	public static int getToken() {
		return token;
	}


	public static void setToken(int token) {
		StockExchange.token = token;
	}


	public static int getTime() {
		return time;
	}


	public static void setTime(int time) {
		StockExchange.time = time;
	}


	public static int getTimes() {
		return times;
	}


	public static void setTimes(int times) {
		StockExchange.times = times;
	}


	public static Vector getPlayername() {
		return playername;
	}


	public static void setPlayername(Vector playername) {
		StockExchange.playername = playername;
	}


	public static Vector getQueue() {
		return queue;
	}


	public static void setQueue(Vector queue) {
		StockExchange.queue = queue;
	}


	public static void setEndstatus(int endstatus) {
		StockExchange.endstatus = endstatus;
	}

	private static Vector callbackObjects;
	private static int token=1;
	private static int time=-3;
	private static int times=4;
    private static Vector playername;    // when the lost connection happens, then this will be 
    // useful to tell other players.
	private static Vector queue;
	StockExchange()throws RemoteException{
		super();
		 playername=new Vector();
		 callbackObjects = new Vector();
		 company=new ArrayList<CompanyInformation>();  
		 playersell=new ArrayList<PlayerSellInformation>();
		 playerbuy=new ArrayList<PlayerBuyInformation>();
		 queue=new Vector();
	} // constructor
	
	// for client to request the token
	public void requesttoken(PlayerCallBackInterface g){          	
		try{
			if(this.token==1){
			g.SetMyturn(this.token);			
			this.token=0;
			}
			else{
				queue.addElement(g);
				System.out.println("in"+" "+g.getName());
		        
		}
		}
		catch(Exception e){			
		}
		}
	
	// release token
	public void releasetoken(){
		this.token=1;
		try{
		PlayerCallBackInterface g=(PlayerCallBackInterface)queue.firstElement();
		System.out.println("out"+" "+g.getName());
		queue.removeElementAt(0);		
		g.SetMyturn(this.token);
		}
		
		catch(Exception e){			
		}
	}
	
    private static void callback(String name1,String name2,int n,String name3) {
        // the server calls the clients in the list when the data is updated  	
        
         for (int i = 0; i < callbackObjects.size(); i++)  {
           // convert the vector object to a callback object
           PlayerCallBackInterface player = (PlayerCallBackInterface) callbackObjects.elementAt(i);
            try{
                 player.printInformation(name1, name2, n, name3);
      
           }
           catch(Exception ex){
           }
         }
    }//end callback
    
    // show current company situation
    public void show(PlayerCallBackInterface g)      
    {  
    	try{
    	g.show11();}
    	catch(Exception e){    		
    	}
    	for(int i=0;i<company.size();i++){
    	try{
    		g.show1(company.get(i).getcompanyName(),company.get(i).getName(),company.get(i).getprice(),
    				company.get(i).getStockNumber());    		
    	}
    	catch(Exception e){
    		}
    	}}
    
    // show current player information
    public void show2(PlayerCallBackInterface g){       
    	try{
    		
    	     g.show22();
    	}
    	catch(Exception e){
    		
    	}
    	for(int i=0;i<playersell.size();i++){
    		try{
    		g.show2(playersell.get(i).getplayerName(),playersell.get(i).getstockName(),
    				playersell.get(i).getsellprice(),playersell.get(i).getStockNumber());}
    		catch(Exception e){
    			   		}
    		
    	}
    	try{
    		g.show44();
    		g.show33();
    	}
    	catch(Exception e){
    		
    	}
    	for(int i=0;i<playerbuy.size();i++){
    		try{
    		g.show3(playerbuy.get(i).getplayerName(),playerbuy.get(i).getstockName(),
    				playerbuy.get(i).getbuyprice(),playerbuy.get(i).getStockNumber());}
    		catch(Exception e){
    			   		}
    	}
    }
    
    //regist the server
	private static void startRegistry(int RMIPortNum)throws RemoteException{
	     try {
	 	 Registry registry= LocateRegistry.getRegistry(RMIPortNum);
	 	 registry.list( );  
	 	
	 }
	 catch (RemoteException ex) {
	   // No valid registry at that port.
	       System.out.println(
	       "RMI registry cannot be located at port " + RMIPortNum);
	       Registry registry= LocateRegistry.createRegistry(RMIPortNum);
	       System.out.println( "RMI registry created at port " + RMIPortNum);
	 }
	 } // end startRegistry 
	
	// the function is to give each transcation to the all players
	public void addCallback(String name,PlayerCallBackInterface CallbackObject){    // to record the address of the player;
		
		callbackObjects.addElement (CallbackObject);
		playername.addElement(name);		
		try{
		for(int i = 0; i < callbackObjects.size(); i++){
			PlayerCallBackInterface player = (PlayerCallBackInterface) callbackObjects.elementAt(i);
			player.show4(name);
		}}
		catch(Exception e){
			
		}
			
	}
	
	//add company information in the stock exchange server
	public void InitializeCompany(String companyName,String StockName,double price,int StockNumber,CompanyCallBackInterface g) {
		      company.add(new CompanyInformation(companyName,StockName,price,StockNumber,g));
		      System.out.println(companyName+""+StockName);
		
	} 

	//put the sell information on the StockExchange
	public void PlayerSell(String player,String StockName,double sellprice,int StockNumber,PlayerCallBackInterface g){
		try{   
			find1(player,StockName,sellprice,StockNumber,g);
		}
		catch(Exception e){		
	    }
	}    


	public void find1(String player,String StockName,double sellprice,int StockNumber,PlayerCallBackInterface g){    //to find the hightest buyprice 
		int n;    // for company
		int m;// for player
		double n1;  // for company
		double m1;   // for player
		double averageprice=0;   // finaly price
		while(StockNumber>0){
			 int ll=(int)(Math.random()*2); // 0 means the company rejects the bid while 1 means accepting		 
			 System.out.println("ll"+" "+ll);
			 n=0;    // for company
		     m=0;// for player
			 n1=0;  // for company
			 m1=0;   // for player
			 for(int i=0;i<company.size();i++){			 
				    if(company.get(i).getName().equals(StockName)){
				    	n=i;
				    	n1=company.get(i).getprice();
				    }
			 }
			
			 for(int i=0;i<playerbuy.size();i++){
				    if(playerbuy.get(i).getstockName().equals(StockName)&&playerbuy.get(i).getbuyprice()>m1&&!(playerbuy.get(i).PlayerCallback()==g)){
				    	m=i;
				    	m1=playerbuy.get(i).getbuyprice();
				    }
		     }
			
		    if(sellprice<=n1&&n1>=m1&&ll==1){        // to choose the company
				CompanyInformation targetcom=company.get(n);
				CompanyCallBackInterface targetcompany = (CompanyCallBackInterface)targetcom.Companycallback();		
				averageprice=(sellprice*StockNumber+targetcom.getprice()*targetcom.getStockNumber())/(targetcom.getStockNumber()+StockNumber);
				averageprice=(double)Math.round(averageprice*100)/100;   
				try{
					targetcompany.BuyStock(StockNumber,sellprice);
					g.SellStock(StockName, StockNumber,averageprice);
					callback(targetcom.getcompanyName(),player,StockNumber,StockName);
				}
				catch(Exception e){
				}   // end try
		
			finally{
				StockNumber=0;
			}
			
		    }
		    else if((sellprice>m1&&sellprice>n1)||(sellprice<=n1&&m1==0&&ll==0)){
			   playersell.add(new PlayerSellInformation(player,StockName,sellprice,StockNumber,g));
			   break;
		   }
		    else if((sellprice<=m1&&m1>n1)||(sellprice<=n1&&n1>=m1&&ll==0)){          // to choose the player 
			   PlayerBuyInformation targetpla=playerbuy.get(m);
			   PlayerCallBackInterface targetplayer=(PlayerCallBackInterface)targetpla.PlayerCallback();
			   averageprice=(sellprice*StockNumber+targetpla.getbuyprice()*targetpla.getStockNumber())/(targetpla.getStockNumber()+StockNumber);
			   averageprice=(double)Math.round(averageprice*100)/100;   
			   int finallyStockNumber;
			   try{			  
				   if(StockNumber>=targetpla.getStockNumber()){ 
					   // it is impossible to self-sell, since it is a trick 
					   finallyStockNumber=targetpla.getStockNumber();
					   if(targetplayer.getBalance()>=(finallyStockNumber*averageprice)){
						   g.SellStock(StockName,finallyStockNumber,averageprice);
						   targetplayer.BuyStock(StockName,finallyStockNumber,averageprice);
						   callback(targetpla.getplayerName(),player,finallyStockNumber,StockName);
						   playerbuy.remove(m);
						   StockNumber-=targetpla.getStockNumber();
						  
						   continue;
					   }
					   else {
						   playerbuy.remove(m);
						 
						   continue;
					   }
				
				   }
				   else if(StockNumber<targetpla.getStockNumber()){
				
					   if(targetplayer.getBalance()>=(StockNumber*averageprice)){
					   targetpla.SetStockNumber((targetpla.getStockNumber()-StockNumber));	
					   g.SellStock(StockName,StockNumber,averageprice);
					   targetplayer.BuyStock(StockName,StockNumber,averageprice);
					   callback(targetpla.getplayerName(),player,StockNumber,StockName);
					   StockNumber=0;
				   }
					   else{
						   playerbuy.remove(m);
						  
						   continue;  
					   }
				   }
			   }
			   catch(Exception e){
				   }
		   }
		}	
	}// end find1

public void PlayerBuy(String player,String StockName,double buyprice,int StockNumber,PlayerCallBackInterface g){ 
	// to find the lowest sellprice
	try{

	find2(player,StockName,buyprice,StockNumber,g);}
	catch(Exception ex){		
	}
	

	
}

	public void find2(String player,String StockName,double buyprice,int StockNumber,PlayerCallBackInterface g){
		// to find the lowest sellprice
		int n;    // for company
		int m;// for player
		double n1;  // for company
		double m1;   // for player
		double averageprice=0;   // finally price
		while(StockNumber>0){
			 int mm=(int)(Math.random()*2);    // 1 meanas the company will accept while 0 means the compnay will reject
			 System.out.println("mm"+""+mm);
			 n=0;    // for company
		     m=0;// for player
			 n1=100000000;  // for company
			 m1=100000000;   // for player
			 for(int i=0;i<company.size();i++){			 
				    if(company.get(i).getName().equals(StockName)){
				    n=i;
				 
				    n1=company.get(i).getprice();
				    }
				    }
			
			 for(int i=0;i<playersell.size();i++){
				    if(playersell.get(i).getstockName().equals(StockName)&&playersell.get(i).getsellprice()<m1&&!(playersell.get(i).PlayerCallback()==g)){
				    m=i;
				    m1=playersell.get(i).getsellprice();
				    }
				    }
			
			 if((buyprice<n1&&buyprice<m1)||(n1<=m1&&m1==100000000&&mm==0)){
				 playerbuy.add(new PlayerBuyInformation(player,StockName,buyprice,StockNumber,g));
				  break;	 
			 }
		if(n1<=m1&&buyprice>n1&&mm==1){    // to choose the company
			CompanyInformation targetcom=company.get(n);
		
			CompanyCallBackInterface targetcompany = (CompanyCallBackInterface)targetcom.Companycallback();		
			averageprice=(buyprice*StockNumber+targetcom.getprice()*targetcom.getStockNumber())/(targetcom.getStockNumber()+StockNumber);
			averageprice=(double)Math.round(averageprice*100)/100;   
			if(StockNumber<=targetcom.getStockNumber()){			
			try{
				
			targetcompany.SellStock(StockNumber,buyprice);
			g.BuyStock(StockName, StockNumber,averageprice);
			callback(player,targetcom.getcompanyName(),StockNumber,StockName);
			 
		
			}
			catch(Exception e){
				}   // end try
			
			finally{
				StockNumber=0;
			}
		}
			else{
				try{
			    StockNumber-=targetcom.getStockNumber();
			    targetcompany.SellStock(targetcom.getStockNumber(),buyprice);
			    g.BuyStock(StockName,targetcom.getStockNumber(), averageprice);
			    callback(player,targetcom.getcompanyName(),targetcom.getStockNumber(),StockName);
			    continue;
							}
		        catch(Exception e){
		        			        }
		}
		}
		else if((m1<n1&&buyprice>m1)||(n1<=m1&&buyprice>n1&&mm==0)){    // to choose the player
		   PlayerSellInformation targetpla=playersell.get(m);
		   PlayerCallBackInterface targetplayer=targetpla.PlayerCallback();
		   averageprice=(buyprice*StockNumber+targetpla.getsellprice()*targetpla.getStockNumber())/(targetpla.getStockNumber()+StockNumber);
		   averageprice=(double)Math.round(averageprice*100)/100;   
		   if(StockNumber<targetpla.getStockNumber()){
			   try{
				   if(StockNumber<=targetplayer.ReturnStock(StockName)){
				   g.BuyStock(StockName, StockNumber, averageprice);
				   targetplayer.SellStock(StockName, StockNumber, averageprice);
				   callback(player,targetpla.getplayerName(),StockNumber,StockName);
				   targetpla.SetStockNumber(targetpla.getStockNumber()-StockNumber);
				   StockNumber=0;
			   }
				   else{
					   playersell.remove(m);
						continue;   
					   
				   }
			   }
			   catch(Exception e){
				   			   }
			   finally{
				 
			   }
		   }
		   else if(StockNumber>=targetpla.getStockNumber()){    // there maybe a case that in t1, the player post
			   // 10 shares to sell, but in t2, he may not have 10 shares to sell while t1<t2.
			   try{
				   if(targetpla.getStockNumber()<=targetplayer.ReturnStock(StockName)){
				   StockNumber-=targetpla.getStockNumber();
				   g.BuyStock(StockName,targetpla.getStockNumber() ,averageprice);				   
				   targetplayer.SellStock(StockName,targetpla.getStockNumber(),averageprice);
				   callback(player,targetpla.getplayerName(),targetpla.getStockNumber(),StockName);
				   playersell.remove(m);
				   continue;
			   }
				   else{
					   playersell.remove(m);
						continue;   
				
				   }
			   }
			   catch(Exception e){
				   
			   }
		   }
		}
		}
	}//end find2
	
public void UpdateCompanyInformation(String companyName,double price,int StockNumber){
	for(CompanyInformation s:company){
		if(s.getcompanyName().equals(companyName)){
			s.Setprice(price);
			s.SetStockNumber(StockNumber);
		}
	}
}// end UpdateCompanyInformation 

//start the stock exchange 
public static void main(String args[]){
		 try{    
			
		          StockExchange obj = new StockExchange();		 
		          startRegistry(1099);
		          Registry registry = LocateRegistry.getRegistry();
		          registry.rebind("Stock Exchange", obj); 
		          System.out.println("Stock Exchange " + obj + " registered");		        		        					 							          
		          Timer timer=new Timer();	        // the game time is 10 minutes, the server will find who wins.
		          timer.scheduleAtFixedRate(new TimerTask(){
		          	    public void run() {
		          	    	if(endstatus!=0){
		          	    		double m=0;
		          	    		double n=0;
		          	    		String name=null;
		          	    		try{	
		          	    			for(int i=0;i<callbackObjects.size();i++){
		          	    				endstatus--;
		          	    				PlayerCallBackInterface player=(PlayerCallBackInterface)callbackObjects.elementAt(i);
		          	    				double h=(player.getStock1()*company.get(0).getprice()+player.getStock2()*company.get(1).getprice()+
		          	    						player.getStock3()*company.get(2).getprice());    // the value of the stock of the player
		          	    				if((player.getBalance()+h+player.checkaccount(player.getName()))>=m){
		          	    					m=(player.getBalance()+h+player.checkaccount(player.getName()));
		          	    					n=i;	
		          	    					name=player.getName();
		          	    				}
		          	    			}
		          	    	
		          	    			for(int i=0;i<callbackObjects.size();i++){
		          	    				PlayerCallBackInterface player=(PlayerCallBackInterface)callbackObjects.elementAt(i);
		          	    				player.showResult(name);
		          	    			}
		          	    		}
		          	    		catch(Exception e){
		          	    	
		          	    		}
		          	    	}
		        		}  
		        	}, new Date(),600000);                  // 10 minutes
		    }
		    catch(Exception ex){
		    	 ex.printStackTrace();
		    }                          // end timer
		      
		    
		     Timer timer2=new Timer();	        // for every 3 minutes, the server will tell the players the time
	         timer2.scheduleAtFixedRate(new TimerTask(){
	          	    public void run() {
	          	    	if(times!=0){
	          	    		time+=3;
	          	    		times--;
	          	    		try{	
	          	   
	          	    			for(int i=0;i<callbackObjects.size();i++){
	          	    		
	          	    				PlayerCallBackInterface player=(PlayerCallBackInterface)callbackObjects.elementAt(i);
	          	    				player.show66(time);
	          	    			}
	          	    		}	          	    
	          	    		catch(Exception e){
	          	    		}
	          	    	}
	          	    }   
	        	}, new Date(),180000);                  // 3 minutes and end time2
	    
	   
		    Timer timer1=new Timer();      // to check whether some players has been crashed,if so, remove it,
		   // and tells others  
			
	        timer1.scheduleAtFixedRate(new TimerTask(){
	        	    public void run() {
	        	             for(int i=0;i<callbackObjects.size();i++){
	        	            	 try{
	        	            		 PlayerCallBackInterface player=(PlayerCallBackInterface)callbackObjects.elementAt(i);	        	     
	        	            		 if(player.Check()==1){
	        	            			 System.out.println("connection"+" "+player.getName());	        	    
	        	            			 continue;
	        	            		 }
	        	            	 }	        	  
	        	            	 catch(Exception e){
	        	            		 String playname=(String)playername.elementAt(i);
	        	            		 for(int k=0;k<playersell.size();k++){    // to remove the information of this player
	        	            			 if(playersell.get(k).getplayerName().equals(playname)){
	        	            				 playersell.remove(k);
	        	            				 k--;
	        	            				 continue;
	        	            			 }
	        	            			 else continue;
	        	            		 }
	        	            		 for(int k=0;k<playerbuy.size();k++){    // to remove the information of this player
	        	            			 if(playerbuy.get(k).getplayerName().equals(playname)){
	        	            				 playerbuy.remove(k);
	        	            				 k--;
	        	            				 continue;
	        	            			 }
	        	            			 else continue;
	        	            		 }
	        	            		 playername.removeElementAt(i);
	        	            		 callbackObjects.removeElementAt(i);
	        	            		 for(int j=0;j<callbackObjects.size();j++){
	        	            			 try{
	        	            				 PlayerCallBackInterface play=(PlayerCallBackInterface)callbackObjects.elementAt(j);
	        	            				 play.show55(playname);}
	        	            			 catch(Exception ex){
		        	    		
	        	            			 }
	        	            		 }
	        	            		 continue;
	        	            	 }}
	        	    	}
	      	}, new Date(),60000);         // the period is one minute and end timer1
		    } // end main
		        
}   //end StockExchange

