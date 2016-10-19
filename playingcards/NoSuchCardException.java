/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playingcards;

/**
 *
 * @author DELL
 */
public class NoSuchCardException extends RuntimeException {

    public NoSuchCardException() {

    }

    public NoSuchCardException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Invalid Card Index";
    }

}
