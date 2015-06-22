package org.trypticon.megahal.engine;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * A splitter which splits on word boundaries, splitting the string into a list of 'word'
 * fragments and 'non-word' fragments.
 *
 * @author Trejkaz
 */
public class WordNonwordSplitter implements Splitter {

    /**
     * The regex pattern which defines word boundaries.
     */
    private static Pattern boundaryPattern = Pattern.compile("([\\w']+|[^\\w']+)");

    /**
     * Symbol factory for creating symbols.
     */
    private final SymbolFactory symbolFactory;

    /**
     * Creates the splitter.
     *
     * @param symbolFactory symbol factory for creating symbols.
     */
    public WordNonwordSplitter(SymbolFactory symbolFactory) {
        this.symbolFactory = symbolFactory;
    }

    public List<Symbol> split(String text) {
        List<Symbol> symbolList = new ArrayList<Symbol>();
        symbolList.add(Symbol.START);

        Matcher m = boundaryPattern.matcher(text);
        while (m.find()) {
            Symbol s = symbolFactory.createSymbol(m.group().toUpperCase().intern());
            symbolList.add(s);
        }
        symbolList.add(Symbol.END);
        return symbolList;
    }

    public String join(List<Symbol> symbols) {
        // Chop off the <START> and <END>
        symbols = symbols.subList(1, symbols.size() - 1);

        // Build up the rejoined list.
        StringBuffer result = new StringBuffer();
        for (Symbol symbol : symbols) {
            result.append(symbol.toString().toLowerCase());
        }
        return result.toString();
    }
}

