package org.trypticon.megahal.engine;

import java.util.List;

/**
 * Interface defining a method to split a string into a list of symbols.
 *
 * @author Trejkaz
 */
public interface Splitter {
    /**
     * Split a string into a list of symbols.
     *
     * @param text the string to split.
     * @return a list of Symbol objects.
     */
    public List<Symbol> split(String text);

    /**
     * Join a list of symbols into a string.
     *
     * @param symbols the symbols to join.
     * @return the joined string.
     */
    public String join(List<Symbol> symbols);
}
