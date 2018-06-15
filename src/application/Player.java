package application;

public class Player {
	private String name;
	private int amount;
	private Hand hand;
	
	public Player(String name, int amuont, Deck deck)  {
		this.name = name;
		this.amount = amuont;
		hand = new Hand(deck);
	}
	
	public void newHand(Deck deck) {
		hand.newHand(deck);
	}
	public int getAmuont() {
		return amount;
	}
	
	public void setAmuont(int amuont) {
		this.amount = amuont;
	}
	
	public String getName() {
		return name;
	}

	public int getHand() {
		return hand.value();
	}
	public void hit(Deck deck) {
		hand.hit(deck.dealCard());
	}
	
	public String toString() {
		return hand.toString();
	}
	
}
