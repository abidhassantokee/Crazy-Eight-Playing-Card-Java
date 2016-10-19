package playingcards;

import java.util.*;

public class Game {

    Deck deck;
    Hand user;
    Hand computer;
    Scanner inString;
    Scanner inInt;
    Card tableCard;
    boolean gameOver = false;

    public Game() {
        inString = new Scanner(System.in);
        inInt = new Scanner(System.in);
        deck = new Deck();
        deck.shuffleCards();
        computer = new Hand();
        computer.setPlayerName("Computer");
        user = new Hand();
        System.out.print("Enter Your Name : ");
        String userName = inString.nextLine();
        user.setPlayerName(userName);
    }

    void distributeCard() {
        Card c;
        for (int i = 0; i < 14; i++) {
            if (i % 2 == 0) {
                c = deck.dealCard();
                user.addCard(c);
            } else {
                c = deck.dealCard();
                computer.addCard(c);
            }
        }
    }

    boolean haveValidCard(Hand h) {
        if (tableCard.getRank() == Rank.EIGHT) {
            return true;
        }
        for (int i = 0; i < h.getNoOfCards(); i++) {
            if (h.getCard(i).getRank() == Rank.EIGHT || h.getCard(i).getSuit() == tableCard.getSuit() || h.getCard(i).getRank() == tableCard.getRank()) {
                return true;
            }
        }
        return false;
    }

    int calculatePoints(Hand h) {
        int points = 0;
        int rankVal;
        for (int i = 0; i < h.getNoOfCards(); i++) {
            rankVal = h.getCard(i).getRankValue();
            if (rankVal == 8) {
                points += 50;
            }
            if (rankVal == 1 || rankVal > 10) {
                points += 10;
            }
            if (rankVal != 1 && rankVal != 8 && rankVal <= 10) {
                points += rankVal;
            }
        }
        return points;
    }

    void computerTurn() {
        boolean input = false;
        while (!input) {
            if (haveValidCard(computer)) {
                Random randomGenerator = new Random();
                int randomInt = randomGenerator.nextInt(computer.getNoOfCards());
                if (computer.getCard(randomInt).getRank() == Rank.EIGHT || tableCard.getRank() == Rank.EIGHT || computer.getCard(randomInt).getSuit() == tableCard.getSuit() || computer.getCard(randomInt).getRank() == tableCard.getRank()) {
                    tableCard = computer.removeCard(randomInt);
                    System.out.println("Computer Throwed " + tableCard);
                    input = true;
                } else {
                    input = false;
                }
            } else {
                computer.addCard(deck.dealCard());
                System.out.println("Computer Picked a Card from Deck");
                input = true;
            }
        }
        System.out.println("Cards in Computer's Hand: " + computer.getNoOfCards());
    }

    void userTurn() {
        int choice;
        boolean input = false;
        Card uCard;
        user.cardSortbyRank();
        System.out.println("Cards in Your Hand:");
        System.out.println(user);
        while (!input) {
            System.out.println("Enter 1 to Throw Card");
            System.out.println("Enter 2 to Deal Card");
            System.out.print("Enter Choice: ");
            choice = inInt.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Index No. of Card: ");
                    int indexChoice = inInt.nextInt();
                    uCard = user.getCard(indexChoice);
                    if (uCard != null) {
                        if (uCard.getRank() == Rank.EIGHT || tableCard.getRank() == Rank.EIGHT || uCard.getSuit() == tableCard.getSuit() || uCard.getRank() == tableCard.getRank()) {
                            user.removeCard(indexChoice);
                            System.out.println("You Throwed: " + uCard);
                            tableCard = uCard;
                            input = true;
                        } else {
                            input = false;
                            System.out.println("Suit or Rank Did Not Match With The Table Card");
                        }
                    } else {
                        input = false;
                    }
                    break;
                case 2:
                    if (!haveValidCard(user)) {
                        uCard = deck.dealCard();
                        user.addCard(uCard);
                        System.out.println("You Picked: " + uCard);
                        input = true;
                    } else {
                        System.out.println("You Can Not Deal a Card While You Have a Valid Card in Your Hand");
                        input = false;
                    }
                    break;
                default:
                    System.out.println("Invalid Input");
                    input = false;
                    break;
            }
        }
    }

    void play() {
        System.out.println("\n*Game Started*");
        distributeCard();

        while (true) {
            if (deck.getTopCard().getRank() == Rank.EIGHT) {
                deck.shuffleCards();
            } else {
                tableCard = deck.dealCard();
                break;
            }
        }

        while (!gameOver) {
            if (!gameOver) {
                System.out.println("\nTable Card: " + tableCard.toString() + "\n");
                userTurn();
                if (user.getNoOfCards() == 0) {
                    gameOver = true;
                }
            }
            if (!gameOver) {
                System.out.println("\nTable Card: " + tableCard.toString() + "\n");
                computerTurn();
                if (computer.getNoOfCards() == 0) {
                    gameOver = true;
                }
            }
            if (deck.getNoOfCards() == 0) {
                gameOver = true;
            }
        }

        if (gameOver) {
            System.out.println("\n*Game Over*");
            user.setPoints(calculatePoints(user));
            System.out.println(user.getPlayerName() + "'s Score: " + user.getPoints());
            computer.setPoints(calculatePoints(computer));
            System.out.println(computer.getPlayerName() + "'s Score: " + computer.getPoints());
            if (user.getPoints() < computer.getPoints()) {
                System.out.println(user.getPlayerName() + " Won");
            } else {
                System.out.println(computer.getPlayerName() + " Won");
            }
        }
    }
}
