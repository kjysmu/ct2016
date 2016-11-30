/**
 * 
 */
package util;

import java.util.Comparator;
import java.util.Map;

/**
 * @author Jonghyun Han
 *
 */
public class DoubleValueComparator implements Comparator<String> {
    Map<String, Double> base;
    public DoubleValueComparator(Map<String, Double> map) {
        this.base = map;
    }

    // Note: this comparator imposes orderings that are inconsistent with equals.    
    public int compare(String a, String b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}


