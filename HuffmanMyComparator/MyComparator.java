/**
 * This class compare the values of two frequencies or data of
 * two nodes and return the values accordingly.
 *
 * @author  Ishika Prasad
 */
import java.util.Comparator;

public class MyComparator implements Comparator<HuffManNode> {
    public int compare(HuffManNode x, HuffManNode y) {
        if (x.data < y.data) {
            return -1;
        } else {
            return 1;
        }
    }
}