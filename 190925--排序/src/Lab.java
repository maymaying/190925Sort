import java.util.Arrays;
import java.util.Random;

interface SortMethod {
    String getName();
    void sort(int[] a);
}

class InsertSortMethod implements SortMethod {
    @Override
    public String getName() {
        return "插入排序";
    }

    @Override
    public void sort(int[] a) {
        Sort.insertSort0(a);
    }
}

public class Lab {
    private static SortMethod[] methods = {
            new InsertSortMethod(),
    };

    public static int[] buildRandom(int n) {
        Random random = new Random(20190924);
        int[] r = new int[n];
        for (int i = 0; i < n; i++) {
            r[i] = random.nextInt(n);
        }
        return r;
    }

    public static int[] buildSorted(int n) {
        Random random = new Random(20190924);
        int[] r = new int[n];
        for (int i = 0; i < n; i++) {
            r[i] = random.nextInt(n);
        }
        Arrays.sort(r);
        return r;
    }

    private static void shellSortR(int[] array) {
        int gap = array.length;
        while (true) {
            gap = (gap / 3) + 1;
            //gap = gap / 2;
            insertSortWithGapR(array, gap);
            if (gap == 1) {
                break;
            }
        }
    }
    private static void insertSortWithGapR(int[] array, int gap) {
        for (int i = gap; i < array.length; i++) {
            int key = array[i];
            int j;
            for (j = i - gap; j >= 0 && array[j] < key; j -= gap) {
                array[j + gap] = array[j];
            }
            array[j + gap] = key;
        }
    }

    public static int[] buildReversed(int n) {
        Random random = new Random(20190924);
        int[] r = new int[n];
        for (int i = 0; i < n; i++) {
            r[i] = random.nextInt(n);
        }
        shellSortR(r);
        return r;
    }

    public static int[] buildEquals(int n) {
        return new int[n];
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 4; i++) {
            int n = i * 50000;
            int[] random = buildRandom(n);
            int[] sorted = buildSorted(n);
            int[] reversed = buildReversed(n);
            int[] equals = buildEquals(n);

            for (SortMethod method : methods) {
                int[] a = random.clone();
                long begin = System.nanoTime();
                method.sort(a);
                long end = System.nanoTime();
                double ms = (end - begin) * 1.0 / 1000000;
                System.out.printf("随机情况下: %s: 耗时 %.5f 毫秒%n", method.getName(), ms);
            }
        }
    }
}
