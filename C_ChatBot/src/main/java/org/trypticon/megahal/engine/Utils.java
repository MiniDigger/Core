package org.trypticon.megahal.engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Class for static utility methods.
 *
 * @author Trejkaz
 */
public class Utils {

    public static Map<Symbol,Symbol> readSymbolMapFromFile(String filename) throws IOException {
        Map<Symbol,Symbol> map = new HashMap<Symbol,Symbol>();

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.length() == 0) {
                continue;
            }
            if (line.charAt(0) == '#') {
                continue;
            }
            String[] words = line.split("\t");
            if (words.length != 2) {
                continue;
            }
            map.put(new Symbol(words[0], false), new Symbol(words[1], false));
        }
        reader.close();

        return map;
    }

    public static Set<Symbol> readSymbolSetFromFile(String filename) throws IOException {
        HashSet<Symbol> set = new HashSet<Symbol>();

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.length() == 0) {
                continue;
            }
            if (line.charAt(0) == '#') {
                continue;
            }
            set.add(new Symbol(line, false));
        }
        reader.close();

        return set;
    }

    public static boolean equals(List<?> l1, List<?> l2) {
        if (l1.size() != l2.size()) {
            return false;
        }
        Iterator<?> il1 = l1.iterator();
        Iterator<?> il2 = l2.iterator();
        while (il1.hasNext()) {
            if (!il1.next().equals(il2.next())) {
                return false;
            }
        }
        return true;
    }
}
