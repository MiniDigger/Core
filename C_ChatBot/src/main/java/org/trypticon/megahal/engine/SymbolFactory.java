package org.trypticon.megahal.engine;

/**
 * Factory which creates symbols.
 *
 * @author Trejkaz
 */
public class SymbolFactory {

	/**
	 * Checks whether symbols are keywords.
	 */
	private final KeywordChecker checker;

	/**
	 * Constructs the factory.
	 *
	 * @param checker
	 *            checks whether symbols are keywords.
	 */
	public SymbolFactory(final KeywordChecker checker) {
		this.checker = checker;
	}

	/**
	 * Creates a symbol.
	 *
	 * @param symbol
	 *            the string value.
	 * @return the symbol.
	 */
	public Symbol createSymbol(final String symbol) {
		return new Symbol(symbol, checker.isKeyword(symbol));
	}

}
