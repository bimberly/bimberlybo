/*
Game of BlackJack like in the good old Vegas days
 */
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class JackBlack {
 public static void main(String[] args) {
  // TODO Auto-generated method stub
  //variable declarations
  Board b2D = new Board (8,13);
  int value = 0;        //value of card drawn (i.e. Jack is 10)
  int count=0;       //keeps track of index position of card drawn
  int user=0;        //counter - stores user's card sum
  int computer=0;       //counter - stores comp's card sum
  int userCash=100;      //counter - stores user's money
  int compCash=100;      //counter - stores comp's money
  int numAces=0;       //computer's number of aces
  int numAcesU=0;
  int location = 5;
  int userLocation = 6;
  int newLocation = 4;
  int compLoc = 5;//user's number of aces
  String[] deck;       //stores deck
  String currentCard;      //determine current card drawn
  String cardType;      //determines current card's value
  String plusCard;      //determines if user wants another card
  String anotherRound ="yes" ;
 
  //  Coordinate plusCard = null; //determines if user wants to keep playing
  String[] compCards = new String[11]; //record of computer's card
  String[] userAces = new String[4];  //record of user's Aces
  //Introduction sequence
  Scanner input = new Scanner(System.in);
  System.out.println("Your money: $" + userCash);
  System.out.println("Computer's money: $" + compCash);
  do {
   //update/ reset counters
   count=0;
   userCash-=2;
   compCash-=2;
   anotherRound="no";
   System.out.println("$2 have been subtracted from each of your totals. $4 are on the table.");
   for(int i=0; i<compCards.length;i++) {
    compCards[i]=null;     //clear record of computer's old cards
   }
   for(int i=0; i<4;i++) {
    userAces[i]=null;     //clear record of user's Aces
   }
   numAces=0;
   deck = prepareDeck(); //prepare new deck
   //draw 2 cards for computer
   for (int i=0; i<2; i++) {
    currentCard=deck[count];     //draw card
    compCards[i] = currentCard;     //store computer's cards
    count++;
    if (currentCard == "2") {
     b2D.putPeg("red_back", 2, location);
    }else {
     b2D.putPeg("red_back", 2, location);
    }
    location++;
    compCards[i]=currentCard;                                       //store computer's cards
    cardType=determineCardType(currentCard);
    value=determineValue(cardType);
    if (value == 2) {
     b2D.putPeg("red_back", 2, location);
    }else {
     b2D.putPeg("red_back", 2, location);
    }//determine value given card type
    //System.out.println("Card: "+ currentCard + ", value: " + value);
    computer+=value;
    // b2D.displayMessage("The computers card amount is " + computer);
   }
   //System.out.println("Computer = " + computer);
   //draw 2 cards for user
   System.out.println("Your cards: ");
   for (int i=0; i<2; i++) {
    currentCard=deck[count];
    count++;
    cardType=determineCardType(currentCard);
    value=determineValue(cardType);
    String suit = determineSuit(currentCard);
    
    if( suit.equals("Hearts")) {
    b2D.putPeg(cardType.substring(0,1) + "H" ,  6, userLocation);
    }else if ( suit.equals("Spades")) {
     b2D.putPeg(cardType.substring(0,1) + "S" ,  6, userLocation); 
    }else if (suit.equals("Clubs")) {
     b2D.putPeg(cardType.substring(0,1) + "C" ,  6, userLocation);
    }else{
     b2D.putPeg(cardType.substring(0,1) + "D" ,  6, userLocation);
    }
   userLocation--;
    if (cardType.equals("Ace")){
     value=0;
     System.out.println("You will choose the value of the Ace later.");
     userAces[numAcesU]=currentCard;
     numAcesU++;
    }
    user+=value;
    System.out.println("Card: "+ currentCard + ", value: " + value);
   }
   b2D.displayMessage("Your total = " + user);
   //algorithm to see if computer needs more cards
   computer = valueHand(compCards);
   int y =2;
   while (computer<=13) {
    // deal another card
    currentCard=deck[count];
    compCards[y]=currentCard;
    // re-compute the value of the hand
    computer = valueHand(compCards);
    count++;
    y++;
   }
   //ask user if they want more cards
   b2D.putPeg("gray_back", 4, 6);
   b2D.putPeg("yellow_back", 4, 5);
   b2D.putPeg("purple_back", 5, 12);
   b2D.putPeg("blue_back", 4, 12);
   //int counter =0;
   //System.out.print("Hit? ");
   //plusCard=input.next();
   Coordinate selected = null;
   do{
    selected = b2D.getClick();
    if (selected.getCol() == 6 && selected.getRow() == 4) {
     currentCard=deck[count];
     cardType=determineCardType(currentCard);
     value=determineValue(cardType);
     String suit = determineSuit(currentCard);
    // b2D.putPeg(value + cardType.substring(0,1),  6, newLocation);
     if( suit.equals("Hearts")) {
         b2D.putPeg(cardType.substring(0,1) + "H" ,  6, newLocation);
         }else if ( suit.equals("Spades")) {
          b2D.putPeg(cardType.substring(0,1) + "S" ,  6, newLocation); 
         }else if (suit.equals("Clubs")) {
          b2D.putPeg(cardType.substring(0,1) + "C" ,  6, newLocation);
         }else{
          b2D.putPeg(cardType.substring(0,1) + "D" ,  6, newLocation);
         }
     user+=value;
     newLocation--;
     // counter++;
     b2D.displayMessage("Your total = " + user);
     if (cardType.equals("Ace")) {
      value=0;
      System.out.println("You will choose the value of the Ace later.");
      userAces[numAcesU]=currentCard;
      numAcesU++;
     }
     // System.out.println("Card: "+ currentCard + ", value: " + value);
     // System.out.println("Your total = " + user);
     count++;
    }
    if(numAcesU>0 ){
     for (int i=0; i<4; i++) {
      if (userAces[i]!=null) {
       // System.out.println("Will the " + userAces[i] + " be equal to 1 or 11?");
       if ( selected.getCol() == 12 && selected.getRow() == 5) {
        value = (11);
       System.out.println("your ace value is ll"); 
       }else if(selected.getCol() == 12 && selected.getRow() == 4) {
        value = (1);
       System.out.println("your ace value is 1");
       }
       user+=value;
       b2D.displayMessage("Your total: " + user);
       //counter++;
      }
     }
    }
    //end of round conditional (updates banks)
    // System.out.println("Computer's cards:");
    for (int i=0; i<compCards.length; i++) {
     if (compCards[i]!=null){
      //System.out.println(compCards[i]);
     }
    }
    if (selected.getCol() == 5 && selected.getRow() == 4) {//this is the stand button so when this is hit it continues to the u win u lose stuff
     //System.out.println("asdasdasd");
     b2D.displayMessage("Computer total = "+ computer);
     break;
    }
    System.out.println(user);
   }while(user<=21);
   if(user>21 && computer>21){//put these into a pop up i.e omg u win u savage!!
    System.out.println("Both over 21, no winners for this round.");
    userCash+=2;
    compCash+=2;
   }else if (computer>21 && user<=21) {
    System.out.println("Computer is over 21, you win!");
    userCash+=4;
   }else if (user>21 && computer<=21) {
    System.out.println("You are over 21, computer wins!");
    compCash+=4;
   }else if (21>=computer && computer>user) {
    System.out.println("Computer is closer to 21, computer wins!");
    compCash+=4;
   }else if (21>=user && user>computer) {
    System.out.println("You are closer to 21, you win!");
    userCash+=4;
   }else if (computer==user) {
    System.out.println("It's a tie! No winners for this round.");
    userCash+=2;
    compCash+=2;
   }
   //System.out.println("asdasdasdas");
   System.out.println("Your current amount: $" + userCash);
   System.out.println("Computer's current amount: $" + compCash);
   //asks user if they want to keep playing
   System.out.print("Next round, baby?");
   anotherRound=input.next();
   if(anotherRound.equals("yes")){
    user=0;
    computer=0;
   }else {
    endingProcedure(userCash);
   }
  }while(anotherRound.equals("yes"));
 }

 /**
  * Value a black jack hand
  */
 public static int valueHand(String[] hand){
  int total = 0, i=0, numAces=0;
  while (hand[i]!=null) {
   String cardType=determineCardType(hand[i]);
   int value=determineValue(cardType);
   if (cardType.equals("Ace")) {
    numAces++;
   }
   total+=value;
   i++;
  }
  // Compensate for Aces having possible value of 1 to bring total under 21
  while (total>21 && numAces>0) {
   total-=10;
   numAces--;
  }
  return total;
 }
 /**
  Makes a randomly arranged deck (David Hua helped me)
  pre:
  post:
  */
 public static String[] prepareDeck() {
  //declare deck
  String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
  String[] rank = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
  String[] deck = new String[52];
  Random r = new Random();
  for (int i=0; i<52; i++) {
   deck[i]=rank[r.nextInt(13)]+ " of " + suits[r.nextInt(4)];
   for(int x=0; x<i; x++){
    if (deck[x].equals(deck[i])) {
     i--;
    }
   }
  }
  return(deck);
 }
 /**
  Determines card rank
  pre:
  post:
  */
 public static String determineCardType(String card){
  String cardType;
  int spcIndex;
  char spc=' ';
  spcIndex=card.indexOf(spc);
  cardType=card.substring(0, spcIndex);
  return cardType;
 }
 /**
  Assigns int value corresponding to card rank
  pre:
  post:
  */
 public static int determineValue(String type) {
  int value;
  if (type.equals("2")) {
   value =2;
  } else if (type.equals("3")) {
   value=3;
  } else if (type.equals("4")) {
   value=4;
  } else if (type.equals("5")) {
   value=5;
  } else if (type.equals("6")) {
   value=6;
  }else if (type.equals("7")) {
   value=7;
  }else if (type.equals("8")) {
   value=8;
  }else if (type.equals("9")) {
   value=9;
  }else if (type.equals("10") || type.equals("Jack") || type.equals("Queen") || type.equals("King")) {
   value=10;
  }else{
   value=11;
  }
  return(value);
 }
 /**
  Displays final results
  pre:
  post:
  */
 public static void endingProcedure(int userCash) {
  int difference=Math.abs(userCash-100);
  System.out.println("The game is over.");
  if(userCash>100){
   System.out.println("Congratulations! You won $" + difference + ".");
  }else if(userCash<100){
   System.out.println("You suck at gambling. You lost $" + difference + ".");
  }else {
   System.out.println("You broke even!");
  }
 }
 /**
 Determines card's suit
 pre: Card name must have 2 spaces
 post: nothing
 */
 public static String determineSuit(String card) {
     String suit;
     int lastSpcIndex;
     char spc=' ';
     lastSpcIndex=card.lastIndexOf(spc);
     suit=card.substring(lastSpcIndex);
     return suit;
 }

 //  public void playAgainMenu() {
 //   setLayout(new FlowLayout());
 //   JFrame frame = new JFrame("Blackjack");
 //
 //   //creating buttons
 //
 //   JButton b1 = new JButton("Play Again?");
 //   b1.setActionCommand("Play Again?");
 //   b1.addActionListener(this);
 //   add(b1);
 //
 //   JButton b2 = new JButton("Exit");
 //   b2.setActionCommand("Exit");
 //   b2.addActionListener(this);
 //   add(b2);
 //
 //
 //  }
 //
 //  @Override
 //  public void actionPerformed(ActionEvent argae) {
 //   // TODO Auto-generated method stub
 //   if(argae.getActionCommand().equals("Play Again?")) {
 //    this.setVisible(false);
 //    //clear cards, shuffle deck, and restart basically
 //   } else if(argae.getActionCommand().equals("Exit")) {
 //    System.exit(0);
 //   }
 //  }
 //
}



