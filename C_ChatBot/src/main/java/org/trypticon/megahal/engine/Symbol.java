package org.trypticon.megahal.engine;

/**
 * Wrapper class for symbols.  This class solves several problems:
 * (*) Enables faster comparison of symbols, by internalising all strings so we can use == instead of equals().
 * (*) Enables faster HashMap lookup, by implementing hashCode as an identity hashmap, which allows the same
 * sort of performance as using IdentityHashMap, but without breaking the general contract of the Map interface.
 * (*) Enables faster checks of whether a word is a keyword, by caching this check when the symbol is created.
 *
 * TODO: Check if we really need this class.  The original needs for it have been put aside for now, perhaps
 * it is possible to address some of those above performance problems in the TrieNode and TrieNodeMap classes.
 *
 * @author Trejkaz
 */
public class Symbol {

    public static final Symbol START = new Symbol("<START>", false);
    public static final Symbol END = new Symbol("<END>", false);

    private String symbol;
    private boolean keyword;

    public Symbol(String symbol, boolean keyword) {
        this.symbol = symbol.intern();
        this.keyword = keyword;
    }

    public String toString() {
        return symbol;
    }

    public boolean equals(Object other) {
        // Because we intern them.
        //noinspection StringEquality
        return (other instanceof Symbol) && (symbol == ((Symbol) other).symbol);
    }

    public int hashCode() {
        return System.identityHashCode(symbol);
    }

    public boolean isKeyword() {
        return keyword;
    }
}
