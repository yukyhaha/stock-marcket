package com.stockmarket;

/**********************************
 *   Player
 *   Description: the User interface of the game. 
 *                entry of the application
 *   Author: Yun Zhang
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
public class Player extends JApplet{
	//public String stockname;  // for the use of the button
	public int checkpoint=1;
	private int turn=1;
	public String name;
	public double balance=2000;
    private Bankinterface h;
    
    private StockExchangeInterface w;
    private int account=1;  //each one only has at most one account
    PlayerCallbackImpl g = null;
    public int stock1=0;      // Stock1
    public int stock2=0;      //Stock2
    public int stock3=0;      //Stock3
    public  int myturn=0;
    JFrame frame1 = new JFrame();                      // the interface at the beginning of the game
    JFrame frame2 = new JFrame();                      // the interface at the stockexchange
    JFrame frame3 = new JFrame();                      // for the bank interface
    public JTextField jtextfield5=new JTextField();    // At the begining to input the name
    public Label Input=new Label("Enter player name: ");
    public JButton next=new JButton("Login");
    public JTextArea jtextarea=new JTextArea("Welcome to the bank, you need to create a bank account at first.");// check account
    public JTextField jtextfield=new JTextField();  // input the money     
    private JButton Bankbutton1=new JButton("Click Here to Create Account");
	private JButton Bankbutton2=new JButton("Withdraw");                
	private JButton Bankbutton3=new JButton("Deposit");     
	private JButton Bankbutton6=new JButton("Enter stock Market");
	private Label query=new Label();  
	private JButton Bankbutton5=new JButton("Check Account");
	private Label Banklabel1=new Label(" Input The Amount");           
	private Label Banklabel3=new Label("        Bank Transaction Information");  //for bank     
	private Label Banklabel2=new Label("           Financial information");  //for bank     
	//private Label ff=new Label("");
	//private Label gg=new Label("");
    private JButton buy=new JButton("Buy"); 
    private JButton sell=new JButton("Sell"); 
    private JButton request=new JButton("Company Information");// request the current company's sell and buy information
    private JButton request1=new JButton("Play Log information");// request the current player's sell and buy information
    private JButton show=new JButton("User Account Information"); // shows the player's information like the stocks
    private JButton startgame=new JButton("Click Here to Start Game");
    private JButton enterbank=new JButton("Enter the Bank");
    public JTextArea jtextarea1;  // show the player's information
    public JTextArea jtextarea2;  // display the every transcation
    public JScrollPane j1;
    public JScrollPane j2;
    public JScrollPane j3;
    private Label stock=new Label("Input the name of the stock");
    private Label stockNameLable =new Label(" Stock Name ");
    public JTextField jtextfield1=new JTextField();   // for the name of the stock 
    private Label bid=new Label(" Bid ");
   // private Label errorMsg = new Label();
    public JTextField jtextfield2=new JTextField();     // input the bid
    private Label number=new Label(" Number ");
    public JTextField jtextfield3=new JTextField();      // input the number
   
    public Player(){
	    Graphic2();   // the graphic of the input
		Graphic1();   // for the market   
		Graphic();     // for bank
		try{
			  g= new PlayerCallbackImpl(this);
		     }
		catch(Exception e){			
		}
		
	}
   
   //call the bank remote method to check the account information
   public double checkaccount(String name){
	   double nn=0;
	  try{
	  nn=h.CheckBalance(name);}
	  catch(Exception e){
		  
	  }
	  return nn;
   }
   
   public void Graphic(){
	    frame3.setLayout(new BorderLayout(5,10));
	    frame3.setSize(350,500);
	    frame3.setBounds(300,200,350,500);
	    
	    JPanel p40=new JPanel();          
	    p40.setLayout(new GridLayout(2,1));
	    p40.add(Banklabel1);
	    p40.add(jtextfield);
	    JPanel p41=new JPanel();          
	    p41.setLayout(new GridLayout(1,2));
	    p41.add(p40);
	    
	    p41.setBackground(Color.GRAY);
	  
	    JPanel p42=new JPanel();
	    p42.setLayout(new GridLayout(3,1,5,5));
	    p42.setBackground(Color.GRAY);
	    p42.add(Bankbutton5);
	    p42.add(Bankbutton3);   
	    p42.add(Bankbutton2);
	    
	    JPanel p30=new JPanel();          
	    p30.setLayout(new GridLayout(1,2,5,5));
	    p30.add(p41);
	    p30.add(p42);
	    p30.setBackground(Color.GRAY);
	    
	    Banklabel1.setBackground(Color.GRAY);
	   // JPanel p31=new JPanel();          
	  //  p31.setLayout(new GridLayout(1,2));
	   // p31.add(p30);
	   // p31.add(gg);
	   // p31.setBackground(Color.GRAY);
	    JPanel p35=new JPanel();          
	    p35.setLayout(new GridLayout(2,1));
	    p35.add(p30);
	    JPanel p33=new JPanel();          
	    p33.setLayout(new GridLayout(2,1,5,5));
	    p33.add(Bankbutton1);	 
	    p33.add(Bankbutton6);
	   // JPanel p36=new JPanel();          
	   // p36.setLayout(new GridLayout(1,2));   
	   // p36.add(ff);
	   // p36.add(p33);
	   // ff.setBackground(Color.GRAY);
	    //p36.setBackground(Color.GRAY);
	    p35.add(p33);
	    j3=new JScrollPane(jtextarea,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    jtextarea.setLineWrap(true);
	    jtextarea.setForeground(Color.red);
	    jtextarea.setWrapStyleWord(true);
	    jtextarea.setEditable(false);
	   frame3.getContentPane().setBackground(Color.GRAY);
	   frame3.getContentPane().add(j3,BorderLayout.CENTER);
	   frame3.getContentPane().add(p35,BorderLayout.SOUTH);
      
       Bankbutton6.addActionListener(new ActionListener() { // go to the market
		    public void actionPerformed(ActionEvent evt) {
	          frame3.setVisible(false);
	          frame2.setVisible(true);
		   		      }
		    });
       
		Bankbutton2.addActionListener(new ActionListener() { // withdraw
		    public void actionPerformed(ActionEvent evt) {
		    try{
		    	if(account==0){
		    	
		    	double i=Double.parseDouble(jtextfield.getText().trim());
		    	if(i<=h.CheckBalance(name)){
		        jtextarea.setText("");
		        jtextarea.append("Withdraw succeeds"+'\n');
		    	h.Withdraw(name,i);
		    	jtextfield.setText("");
		    	balance=(double)Math.round((balance+i)*100)/100; 
		    	}
		    	else{
		    		jtextarea.setText("");
		    		jtextarea.append("Withdraw fails"+'\n');}
		    	 
		    }}
		    catch(Exception e){	
		    }
		   		      }
		    });
		
		Bankbutton3.addActionListener(new ActionListener() { // Deposit
		    public void actionPerformed(ActionEvent evt) {
		    try{
		    	if(account==0){
		    	
		    	double i=Double.parseDouble(jtextfield.getText().trim()); 	
		    	if(i<=balance){
		    	h.Deposit(name, i);
		    	jtextfield.setText("");
		    	jtextarea.setText("");
		    	jtextarea.append("Deposit succeeds"+'\n');
		    	balance=(double)Math.round((balance-i)*100)/100; 
		    	}
		    	else{
		    		jtextarea.setText("");
		    		jtextarea.append("Deposit fails"+'\n');
		    	}
		    }}
		    catch(Exception e){	
		    }
		   		      }
		    });
		
		Bankbutton1.addActionListener(new ActionListener() { // CreateAccount
		    public void actionPerformed(ActionEvent evt) {
		    try{
		    	if(account==1){
		        
		    	h.CreateAccount(name);
		    	account--;}
		    	
		    }
		    catch(Exception e){	
		    }
		   		      }
		    });
		
		Bankbutton5.addActionListener(new ActionListener() { // Check Account's balance 
		    public void actionPerformed(ActionEvent evt) {
		    try{
		    	if(account==0){
		    	jtextarea.setText("");
		    	jtextarea.append("Account's balance is :"+" "+h.CheckBalance(name)+'\n');	
		    	}
		    		    }
		    catch(Exception e){	
		    }
		   		      }
		    });
		
   }  // end Graphic
   
   //game entry. The initialize user interface for the player
   public void Graphic2(){  
	   
	   frame1.setTitle("StockMarket");
	   Input.setBackground(Color.cyan);
	   frame1.setSize(200,100);
	   frame1.setBounds(500, 400,200,100);
        
	   frame1.setLayout(new GridLayout(3,1));
	   frame1.getContentPane().add(Input);
	   frame1.getContentPane().add(jtextfield5);
	   frame1.getContentPane().add(next);
	   frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   frame1.setVisible(true);
	   
	   //now the frame2 and frame3 isn't visible until the game is begin
	   frame2.setVisible(false);
	   frame3.setVisible(false);
	   
	   next.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent evt) {
		    	if(!jtextfield5.getText().trim().equals("")){
		    		  // get the player name
				      name=jtextfield5.getText().trim();
				      //set the initialize frame as unvisiable
				      frame1.setVisible(false);	
				      //set the main game as visiable
				      frame2.setTitle("Stock Market"+"---"+name);
				      frame2.setVisible(true);	
				      //set bank frame as false
				      frame3.setTitle("Bank"+"---"+name);
				      frame3.setVisible(false);	
				}      
		    }
	   });    
	   
   } 
   
   //the stock exchange frame
   public void Graphic1(){     
		
		frame2.setLayout(new BorderLayout(5,10));
	    frame2.setSize(800,500);
	    frame2.setBounds(100,200,650,500);
	    //the two information display area for the player to check the data in game
		j1=new JScrollPane(jtextarea1=new JTextArea(),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		j2=new JScrollPane(jtextarea2=new JTextArea("Welcome to the game! At first you need to start the game to initialize your personal information."),
				 JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    jtextarea1.setLineWrap(true);
	    jtextarea1.setWrapStyleWord(true);
	    jtextarea2.setLineWrap(true);
	    jtextarea2.setWrapStyleWord(true);
	    jtextarea2.setForeground(Color.red);
	    
	    //
		JPanel p3=new JPanel();
		p3.setLayout(new BorderLayout());
		p3.add(j2,BorderLayout.CENTER);
		p3.add(Banklabel3,BorderLayout.NORTH);
		Banklabel3.setBackground(Color.cyan);
		
		
		JPanel p4=new JPanel();     
		p4.setLayout(new BorderLayout());
		   
		p4.add(j1,BorderLayout.CENTER);
		p4.add(Banklabel2,BorderLayout.NORTH);
		Banklabel2.setBackground(Color.CYAN);
                
		
		// the operation button area
		  JPanel p2=new JPanel();
		  p2.setLayout(new GridLayout(1,1,5,5));
		  p2.add(p3, BorderLayout.EAST);
                  p2.add(p4, BorderLayout.WEST);
		
		JPanel p20=new JPanel();
		p20.setLayout(new GridLayout(4,2));
		p20.setBackground(Color.GRAY);
		p20.add(stockNameLable);
		p20.add(jtextfield1);
		p20.add(bid);
		p20.add(jtextfield2);
		p20.add(number);
        p20.add(jtextfield3);
        p20.add(buy);
        p20.add(sell);
		
		JPanel p24=new JPanel();
		p24.setLayout(new GridLayout(3,1,5,5));
		p24.setBackground(Color.GRAY);
		p24.add(request1);
		p24.add(request);
		p24.add(show);
		

		JPanel p21=new JPanel();
        p21.setLayout(new GridLayout(2,1,5,5));   
        p21.setBackground(Color.GRAY);
        p21.add(p20);
        p21.add(p24);

        JPanel p25=new JPanel();
        p25.setLayout(new GridLayout(2,1,10,5));     
		p25.setBackground(Color.GRAY);
		p25.add(startgame);
		p25.add(enterbank);
		
        JPanel p1=new JPanel();
        p1.setLayout(new GridLayout(1,2,5,5));
        p1.setBackground(Color.GRAY);
        p1.add(p21);
        p1.add(p25);
        
        JPanel p6=new JPanel();
		p6.setLayout(new BorderLayout()); 
        p6.add(p1,BorderLayout.CENTER);    // for transcation
        
        JPanel p10=new JPanel();
        p10.setLayout(new GridLayout(2,1,5,5));
        p10.setBackground(Color.CYAN);
        p10.add(p2);
        p10.add(p6);
	
                frame2.getContentPane().add(p10,BorderLayout.CENTER);
		//frame2.getContentPane().add(p2,BorderLayout.EAST);
		frame2.getContentPane().setBackground(Color.cyan);
		jtextarea1.setEditable(false);
		jtextarea2.setEditable(false);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		enterbank.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent evt) {
		    	 frame2.setVisible(false);
		    	 frame3.setVisible(true);
		   		      }
		    });    //end enterbank
			
		startgame.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent evt) {
		    try{
		    	if(turn==1){
		    	jtextarea2.setText("");	
    	    	               initializeRMI();    //for bank
				initializeRMI1();		
		            
			    w.addCallback(name,(PlayerCallBackInterface)g);
			    turn--;
				     }}
				catch(Exception e){	
					
				}		    			     
		   		      }
		    });    //end start game
		show.addActionListener(new ActionListener() { //   show my information
		    public void actionPerformed(ActionEvent evt) {
		    try{
		        
		    	jtextarea1.setText("");
		    	jtextarea1.setForeground(Color.BLACK);
		    	jtextarea1.append("My ibm Stock"+" "+"is "+stock1+'\n'
		    			+"My acer Stock"+" "+"is "+stock2+'\n'
		    			+"My dell Stock"+" "+"is "+stock3+'\n'+"MyCash"+" "+"is"+" "+balance+'\n'
		    			+"MyDeposit"+" "+"is "+" "+h.CheckBalance(name));
		     
		    }
		    catch(Exception e){	
		    }
		   		      }
		    });    // end show
		
		buy.addActionListener(new ActionListener() { //   buy 
		    public void actionPerformed(ActionEvent evt) {
		    try{
		    	int i =0;
		        try{
		    	     i = Integer.valueOf(jtextfield3.getText().trim()).intValue(); 
		        }catch(Exception e){
		        	jtextarea1.setText("");
		        	jtextarea1.setForeground(Color.red);
			    	jtextarea1.append("Please enter valid number you want to buy!"+'\n'
			    			+"It should be a number"+ '\n');
		        }
		    	// number
		    	double m= 0l;
		    	
	    	    try{
		    	     m = Double.parseDouble(jtextfield2.getText().trim());   //bid
		        }catch(Exception e){
		        	jtextarea1.setText("");
		        	jtextarea1.setForeground(Color.red);
			    	jtextarea1.append("Please enter valid Bid!"+'\n'
			    			+"It should be a number"+ '\n');
		        }
		        
		    	String StockName=jtextfield1.getText().trim().toLowerCase();
		    	if(StockName.equals("ibm")||StockName.equals("apple")||StockName.equals("dell")){
		    	if(balance>=(m*i)&&i>0&&m>0){
		    		
		    	 w.requesttoken((PlayerCallBackInterface)g);
		  
        	    jtextarea1.setText("");
	    		jtextarea1.setForeground(Color.red);
	    		jtextarea1.append("Server is deal with other transtion, Please Wait!!"+'\n');

		         while(myturn==0){
                 	 // when there is no token, the player will have to wait
		    	 }
		         
	        	 jtextarea1.setText("");
		    		
		         w.PlayerBuy(name,StockName,m,i,(PlayerCallBackInterface)g); 
		    	
		    	 jtextfield1.setText("");
			     jtextfield2.setText("");
			     jtextfield3.setText("");
			     jtextarea1.setForeground(Color.BLACK);
			  	 w.releasetoken();
			     myturn=0;
		    	}
		    	else if(balance<(m*i)&&i>0&&m>0){
		    		jtextarea1.setText("");
		    		jtextarea1.setForeground(Color.red);
		    		jtextarea1.append("no enough money"+'\n');
		    	}
		    }
		    	else{
		    		jtextarea1.setText("");
		    		jtextarea1.setForeground(Color.red);
			    	jtextarea1.append("Please enter valid StockName"+'\n'+"valid choices:"+
			    			"ibm"+" "+","+"acer"+" "+","+"dell"+'\n');
		    	}
		    	
		    	}
		    catch(Exception e){	
		    }
		   		      }
		    });    // end buy
		
		sell.addActionListener(new ActionListener() { //   sell
		    public void actionPerformed(ActionEvent evt) {
		    try{
		    
		        String StockName=jtextfield1.getText().trim().toLowerCase();
		        int i =0;
		        try{
		    	     i = Integer.valueOf(jtextfield3.getText().trim()).intValue(); 
		        }catch(Exception e){
		        	jtextarea1.setText("");
		        	jtextarea1.setForeground(Color.red);
			    	jtextarea1.append("Please enter valid number you want to buy!"+'\n'
			    			+"It should be a number"+ '\n');
		        }
		    	// number
		    	double m= 0l;
		    	
	    	    try{
		    	     m = Double.parseDouble(jtextfield2.getText().trim());   //bid
		        }catch(Exception e){
		        	jtextarea1.setText("");
		        	jtextarea1.setForeground(Color.red);
			    	jtextarea1.append("Please enter valid Bid!"+'\n'
			    			+"It should be a number"+ '\n');
		        }
		    	
		    	if(i>0&&m>0){
		    	if(StockName.equals("ibm")||StockName.equals("acer")||StockName.equals("dell")){
		    	 w.requesttoken((PlayerCallBackInterface)g);
		    	 while(myturn==0){
                     // when there is no token, the player will have to wait
                   }
		    	 
		    	if(StockName.equals("ibm")&&i<=stock1){
		        w.PlayerSell(name,StockName,m,i,(PlayerCallBackInterface)g); 
		        jtextfield1.setText("");
		        jtextfield2.setText("");
		        jtextfield3.setText("");
		        jtextarea1.setForeground(Color.black);
		        w.releasetoken();
		        myturn=0;
		    	}
		    	else if(StockName.equals("acer")&&i>stock1){
		    		jtextarea1.setText("");
		    		jtextarea1.setForeground(Color.red);
		    		jtextarea1.append("no enough stock"+'\n');}
		    	else if(StockName.equals("acer")&&i<=stock2){
			        w.PlayerSell(name,StockName,m,i,(PlayerCallBackInterface)g); 
			        jtextfield1.setText("");
			        jtextfield2.setText("");
			        jtextfield3.setText("");
			        w.releasetoken();
			        myturn=0;    
		    	}
		    	else if(StockName.equals("acer")&&i>stock2){
		    		    jtextarea1.setText("");
		    		    jtextarea1.setForeground(Color.red);
			    		jtextarea1.append("no enough stock"+'\n');}
		    	else if(StockName.equals("dell")&&i<=stock3){
			        w.PlayerSell(name,StockName,m,i,(PlayerCallBackInterface)g); 
			        jtextfield1.setText("");
			        jtextfield2.setText("");
			        jtextfield3.setText("");
			        jtextarea1.setForeground(Color.BLACK);
			        w.releasetoken();
			        myturn=0;
		    	}
		    	else if(StockName.equals("dell")&&i>stock3) {
		    		    jtextarea1.setText("");
		    		    jtextarea1.setForeground(Color.red);
			    		jtextarea1.append("no enough stock"+'\n');}
		    }
		    else{
		    	jtextarea1.setText("");
		    	jtextarea1.setForeground(Color.red);
		    	jtextarea1.append("Please enter valid StockName"+'\n'
		    			+"valid choices:"+
		    			"ibm"+" "+","+"acer"+" "+","+"dell"+'\n');
		    	}}
		    }
		    catch(Exception e){	
		    	
		    }
		   		      }
		    });    // end sell
		
		request.addActionListener(new ActionListener() { //   request company information
		    public void actionPerformed(ActionEvent evt) {
		    try{
		    	jtextarea1.setForeground(Color.BLACK);
		    	w.show((PlayerCallBackInterface)g);
		    	
		    }
		    catch(Exception e){	
		    }
		   		      }
		    });    // end request financial information
		
		request1.addActionListener(new ActionListener() { //   request player information
		    public void actionPerformed(ActionEvent evt) {
		    try{
		    	jtextarea1.setForeground(Color.BLACK);
		    	w.show2((PlayerCallBackInterface)g);
		    }
		    catch(Exception e){	
		    }
		   		      }
		    });    // end request financial information
		
	
		
		
        }     // end Graphic1
	
   //get the registry for the stock exchange server
   protected void initializeRMI1() {  
	    String host = "";
	    try {	    	
	    
	      Registry registry = LocateRegistry.getRegistry("localhost");
	      w = (StockExchangeInterface)registry.lookup("Stock Exchange");
	      System.out.println("Stock Exchange " + w + " found");
	    }
	    catch(Exception ex) {
	      System.out.println(ex);
	    }
	 
	  }
  
   //get the registry for the bank server
	protected void initializeRMI() {   //for bank
	    String host = "";
	    try {	    	
	    
	      Registry registry = LocateRegistry.getRegistry("localhost",1200);
	      h = (Bankinterface)registry.lookup("Bank");
	      System.out.println("Bank object " + h + " found");
	    }
	    catch(Exception ex) {
	      System.out.println(ex);
	    }
	 
	  }//end initializeRMI   
	
	public static void main(String args[]){
		Player player1=new Player();
	
	}
} //end Player
