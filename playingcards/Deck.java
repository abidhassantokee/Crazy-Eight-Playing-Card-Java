package playingcards;

import java.util.*;

public class Deck {

    private int noOfCards = 0;
    private ArrayList<Card> cardList;

    public Deck() {
        cardList = new ArrayList();
        for (Suit s : Suit.values()) {
            for (Rank r : Rank.values()) {
                // här kan du använda dig r, som får värdena ACE, TWO, ..., för varje varv
                Card c = new Card(r, s);
                cardList.add(c);
            }
        }
        noOfCards = cardList.size();
    }

    public int getNoOfCards() {
        return noOfCards;
    }

    public Card getTopCard() {
        try {
            if (noOfCards > 0) {
                return cardList.get(noOfCards - 1);
            } else {
                throw new NoSuchCardException();
            }
        } catch (NoSuchCardException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public Card dealCard() {
        try {
            if (noOfCards > 0) {
                Card c = cardList.get(noOfCards - 1);
                cardList.remove(noOfCards - 1);
                noOfCards = cardList.size();
                return c;
            } else {
                throw new NoSuchCardException();
            }
        } catch (NoSuchCardException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void shuffleCards() {
        Collections.shuffle(cardList);
    }

    public void fill() {
        cardList.clear();
        for (Suit s : Suit.values()) {
            for (Rank r : Rank.values()) {
                // här kan du använda dig r, som får värdena ACE, TWO, ..., för varje varv
                Card c = new Card(r, s);
                cardList.add(c);
            }
        }
        noOfCards = cardList.size();
    }

    @Override
    public String toString() {
        String deckString = "";
        for (int i = 0; i < noOfCards; i++) {
            deckString += cardList.get(i).toString() + "\n";
        }
        return deckString;
    }
}
