package playingcards;

import java.util.*;

public class Hand {

    private int cardsInHand = 0, points = 0;
    private String playerName;
    private ArrayList<Card> cardList;

    public Hand() {
        cardList = new ArrayList();
    }

    public int getNoOfCards() {
        return cardsInHand;
    }

    public void addCard(Card c) {
        cardList.add(c);
        cardsInHand++;
    }

    public Card getCard(int index) {
        try {
            if (index >= 0 && index < cardsInHand) {
                return cardList.get(index);
            } else {
                throw new NoSuchCardException();
            }
        } catch (NoSuchCardException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    public Card removeCard(int index) {
        try {
            if (index >= 0 && index < cardsInHand) {
                Card c = cardList.get(index);
                cardList.remove(index);
                cardsInHand = cardList.size();
                return c;
            } else {
                throw new NoSuchCardException();
            }
        } catch (NoSuchCardException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public boolean removeCard(Card c) {
        int index = cardList.indexOf(c);
        if (index != -1) {
            cardList.remove(index);
            cardsInHand = cardList.size();
            return true;
        }
        return false;
    }

    public void setPlayerName(String pName) {
        playerName = pName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPoints(int p) {
        points = p;
    }

    public int getPoints() {
        return points;
    }

    public void cardSortbyRank() {
        Collections.sort(cardList, new CardSortbyRank());
    }
    
    public void cardSortbySuit() {
        Collections.sort(cardList, new CardSortbySuit());
    }

    @Override
    public String toString() {
        String handString = "";
        for (int i = 0; i < cardsInHand; i++) {
            handString += "Index : " + i + " || " + cardList.get(i).toString() + "\n";
        }
        return handString;
    }

}
