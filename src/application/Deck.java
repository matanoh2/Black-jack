package application;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
   private static final int NUM_DECKS = 4;
   private static final int RESHUFFLE_POINT = (int) (0.05 * (NUM_DECKS * 52) );
   private ArrayList<Card> deck;
   private final int NUM_OF_CARDS=52*NUM_DECKS;

	public Deck()
	{
		newDeck();	
	}
	
	public void newDeck() {
		String faces[]= { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
		char suits[]= { '\u2665', '\u2666' , '\u2663', '\u2660' };
		deck = new ArrayList<Card>(NUM_OF_CARDS);
		   for ( int numDecks = 0; numDecks < NUM_DECKS; ++numDecks )
		         for ( int j = 0; j < 4; ++j )
		            for ( int i = 0; i < 13; ++i)
		               deck.add(new Card(faces[i], suits[j]));
		shuffle();
	}
	
	public void shuffle() {
	    Collections.shuffle(deck);
	}
	
	public Card dealCard() 
	{
		if ((deck.isEmpty() == false) &&  (deck.size() > RESHUFFLE_POINT)) {
			Card returnCard;
			returnCard = deck.get(0);
			deck.remove(0);
			return returnCard;
		}
		else {
			newDeck();
			return dealCard();
			
		}
	}
}
