package application;

import java.util.ArrayList;

class Hand
{
   private ArrayList<Card> cards = new ArrayList<Card>();
   
   public Hand(Deck deck)
   {
	   newHand(deck);
   }
   
   public void newHand(Deck deck) {
	   cards.clear();
	   cards.add(deck.dealCard());
	   cards.add(deck.dealCard());
   }

   /** Calculates the value of the hand.  Picture cards are worth 10 and an
    *  Ace is worth 1 or 11.
    */
   public int value ()
   {
      int total = 0;
      boolean hasAce = false;
      for ( Card card : cards )
      {
         int val = card.getValue();
         if  ( val == 1 )  
        	 hasAce = true;
         total += val;
      }
       
      while ( total <= (21 - 10) && hasAce )
         total += 10;
      return total;
   }
 
   // Add one card to the hand from the shoe.
   public void hit (Card newCard) {  
	   cards.add(newCard); 
	   }
 
   // During the play only the first card of the dealer's hand should be shown.
   public Card firstCard() { 
	   return cards.get(0);  
	   }
   
   public String toString()
   {
      StringBuilder sb = new StringBuilder();
      for (Card card : cards)
      {
         sb.append( card );
         if (card != cards.get(cards.size()-1))
        	 sb.append( ", " );
      }
      return sb.toString();
   }
}