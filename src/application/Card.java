package application;


public class Card {
	 
	private char suit;
	private String face;
	private int value;
	 
	public String getFace() {
		return face;
	}
	
	public Card(String face, char suit) {
		this.face = face;
		this.suit = suit;
		
		if (face.equals("J") || face.equals("Q")|| face.equals("K"))
			value=10;		
		else if (face.equals("A"))
			value=1;
		else if (Integer.valueOf(face) >= 2 && Integer.valueOf(face) <= 10)
			value = Integer.valueOf(face);
	
	}
	
	@Override
	public String toString() {
		return face + " " + suit ;
	 }
	
	public int getValue() {
	  return value;
	 }
}