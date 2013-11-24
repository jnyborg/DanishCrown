package gui;

import java.util.ArrayList;
import java.util.List;

/**
 * Modellerer interfacet Subject til Observer pattern.
 * Her findes den statiske liste af observers som alle nedarver for Subject. 
 * Vi sikrer også at alle klasser der nedarver Subjec skal have en notifyObservers metode.
 */
public interface Subject{
	public static List<Observer> observers = new ArrayList<Observer>();
	public void notifyObservers();
}