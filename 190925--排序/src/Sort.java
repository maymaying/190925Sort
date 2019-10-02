import java.util.Arrays;
import java.util.Random;

public class Sort {

    // 直接插入排序
    public static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            // 有序   [0, i)
            // 无序   [i, array.length)
            int key = array[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
                /*
                if (array[j] < key) {
                    break;
                } else if(array[j]==key) {
                    break;
                }else{
                    array[j + 1] = array[j];
                }
                */
                if (array[j] <= key) {
                    break;
                } else {
                    array[j + 1] = array[j];
                }
            }
            array[j + 1] = key;
        }
    }
    public static void insertSort0(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j;
            for (j = i - 1; j >= 0 && array[j] > key; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = key;
        }
    }
    //折半插入排序
    public static void bsInsertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int v = array[i];
            int left = 0;
            int right = i;
            while (left < right) {
                int m = (left + right) / 2;
                if (v >= array[m]) {
                    left = m + 1;
                } else {
                    right = m;
                }
            }
            for (int j = i; j > left; j--) {
                array[j] = array[j - 1];
            }
            array[left] = v;
        }
    }
    //希尔排序
    public static void shellSort(int[] array) {
        int gap = array.length;//gap 间隔
        while (true) {
            gap = (gap / 3) + 1;
            //gap = gap / 2;
            insertSortWithGap(array, gap);
            if (gap == 1) {
                break;
            }
        }
    }
    private static void insertSortWithGap(int[] array, int gap) {
        for (int i = gap; i < array.length; i++) {
            int key = array[i];
            int j;
            for (j = i - gap; j >= 0 && array[j] > key; j -= gap) {
                array[j + gap] = array[j];
            }
            array[j + gap] = key;
        }
    }
    private static void swap(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }
    //选择排序
    public static void selectSort(int[] array) {
        // 每次选最大的放到无序区间的最后
        for (int i = 0; i < array.length - 1; i++) {
            // 无序 [0, array.length - i)
            // 有序 [array.length - i, array.length)
            int maxIndex = 0;
            for (int j = 1; j < array.length - i; j++) {
                if (array[j] > array[maxIndex]) {
                    maxIndex = j;
                }
            }
            swap(array, maxIndex, array.length - i - 1);
        }
    }
    public static void selectSort0(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            // 有序区间 [0,i)
            // 无序区间 [i, array.length)
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            swap(array, minIndex, i);
        }
    }
    //双向选择排序
    public static void selectSort2(int[] array) {
        // 无序: [begin, end]
        int begin = 0;
        int end = array.length - 1;
        while (begin < end) {
            int minIndex = begin;
            int maxIndex = begin;
            for (int j = begin + 1; j <= end; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
                if (array[j] > array[maxIndex]) {
                    maxIndex = j;
                }
            }
            swap(array, minIndex, begin);
            if (maxIndex == begin) {
                maxIndex = minIndex;
            }
            swap(array, maxIndex, end);
            begin++; end--;
        }
    }
    //堆排序
    public static void heapSort(int[] array) {
        createHeapBig(array);
        for (int i = 0; i < array.length - 1; i++) {
            // 无序 [0, array.length - i)
            // 交换 array[0], array[length - i - 1]
            // 无序 [0, array.length - i - 1)
            // 无序长度 array.length - i - 1
            // 下标 0 进行向下调整
            swap(array, 0, array.length - i - 1);
            shiftDownBig(array, 0, array.length - i - 1);
        }
    }
    private static void shiftDownBig(int[] array, int i, int size) {
        while (2 * i + 1 < size) {
            int max = 2 * i + 1;
            if (max + 1 < size && array[max+1] > array[max]) {
                max = max + 1;
            }
            if (array[i] >= array[max]) {
                return;
            }
            swap(array, i, max);
            i = max;
        }
    }
    private static void createHeapBig(int[] array) {
        for (int i = (array.length - 2) / 2; i >= 0; i--) {
            shiftDownBig(array, i, array.length);
        }
    }
    //冒泡排序
    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            boolean isSort = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                    isSort = false;
                }
            }
            if (isSort) {
                return;
            }
        }
    }
    public static void testRight(){
        int[] a={2,3,7,9,4,5,6,9,1,4,7,8};
        int[] b=a.clone();
        heapSort(b);
        System.out.println(Arrays.toString(b));
        int[] c=a.clone();
        Arrays.sort(c);
        System.out.println(Arrays.toString(c));
        System.out.println(Arrays.equals(b,c));
    }
    public static void testSpeed() {
        Random random = new Random(20190924);
        int[] a = new int[10 * 10000];
        for (int i = 0; i < 10 * 10000; i++) {
            a[i] = random.nextInt(10 * 10000);
        }
        long begin = System.nanoTime();
        heapSort(a);
        long end = System.nanoTime();
        double ms = (end - begin) * 1.0 / 1000 / 1000;
        System.out.printf("一共耗时: %.5f 毫秒%n", ms);
    }
    public static void main(String[] args) {
        testRight();
        testSpeed();
    }
}