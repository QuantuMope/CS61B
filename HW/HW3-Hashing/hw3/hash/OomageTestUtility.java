package hw3.hash;

import java.util.List;

/**
 * @author Andrew Choi
 * Date: 05/28/2019
 */

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int[] bucketSizes = new int[M];
        double N = 0;
        for (Oomage o : oomages) {
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            bucketSizes[bucketNum] += 1;
            N += 1;
        }
        for (Oomage o : oomages) {
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            if (bucketSizes[bucketNum] < N / 50 || bucketSizes[bucketNum] > N / 2.5) {
                return false;
            }
        }
        return true;
    }
}
