package org.trypticon.megahal.engine;

import java.util.Set;

/**
 * A simple keyword checker, that returns true if the word is not in the ban list.
 * An auxilliary list is also provided, however this method does not presently use it.
 *
 * @author Trejkaz
 */
public class SimpleKeywordChecker extends KeywordChecker {

    private Set<?> banWords;
    @SuppressWarnings("unused")
    private Set<?> auxWords;

    public SimpleKeywordChecker(Set<?> banWords, Set<?> auxWords) {
        this.banWords = banWords;
        this.auxWords = auxWords;
    }

    public boolean isKeyword(String symbol) {
        return isWord(symbol) && !banWords.contains(symbol);
    }
}
