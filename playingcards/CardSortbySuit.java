/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playingcards;

import java.util.Comparator;

/**
 *
 * @author DELL
 */
public class CardSortbySuit implements Comparator<Card> {

    @Override
    public int compare(Card o1, Card o2) {
        if (o1.getSuitValue() > o2.getSuitValue()) {
            return 1;
        } else if (o1.getSuitValue() == o2.getSuitValue()) {
            return 0;
        } else {
            return -1;
        }
    }

}
