package ru.ancevt.util;

import java.util.List;

/**
 *
 * @author ancevt
 */
public class ListUtil {

    public static final int[] listToIntArray(List<Integer> list) {
        final int arrSize = list.size();
        final int[] result = new int[arrSize];
        final Integer[] temp = list.toArray(new Integer[arrSize]);
        for (int n = 0; n < arrSize; ++n) {
            result[n] = temp[n];
        }
        return result;
    }
    
    public static final byte[] listToByteArray(List<Byte> list) {
        final int arrSize = list.size();
        final byte[] result = new byte[arrSize];
        final Byte[] temp = list.toArray(new Byte[arrSize]);
        for (int n = 0; n < arrSize; ++n) {
            result[n] = temp[n];
        }
        return result;
    }
}
