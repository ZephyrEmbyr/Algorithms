import java.time.*;

public class ChenGentle_quicksort {

    public static void main(String[] args)
    {
    	// tests quicksort with different sizes of arrays
        tester(1000);
        tester(1000000);
        tester(10000000);
        tester(100000000);
        tester(1000000000);
    }

    // builds an array of doubles of n random elements in range 0...n
    private static double[] makeRandom(int n)
    {
        double[] toBeSorted = new double[n];
        for(int k = 0; k < n; k++)
            toBeSorted[k] = Math.random() * n;
        return toBeSorted;
    }

    // Quicksort. Checks if the left bound is less than the right bound. If so,
    // it calls partition and sets the index to be between the left and right
    // partitions. Then it recursively calls itself on the smaller partitions.
    private static void sort(double[] A, int lBound, int rBound)
    {
        int index;
        if(lBound<rBound)
        {
            index = partition(A,lBound,rBound);
            sort(A, lBound, index-1);
            sort(A, index+1, rBound);
        }
    }

    
    // Partitions the array. It sets the pivot to be the last element in the
    // array. Then it loops through the array and compares it to the pivot.
    // If its less than the pivot, it's swapped with the farthest left element
    // that's greater than the pivot, which is kept track by i. After the 
    // iteration, the pivot is swapped with the farthest left element greater
    // than it, which successfully splits the array into 3 parts: elements
    // smaller than the pivot, the pivot itself, and elements larger than the
    // pivot.
    private static int partition(double[] A, int lBound, int rBound)
    {
        double pivot = A[rBound];
        int i = lBound-1;
        for(int k = lBound; k < rBound; k++)
        {
            if(A[k] <= pivot)
            {
                i++;
                swap(A, i, k);
            }
        }
        swap(A, i+1, rBound);
        return i+1;
    }

    // Iterates over the given array to check to see if it is sorted
    private static boolean isSorted(double[] A)
    {
        for(int k = 1; k < A.length; k++)
        {
            if(A[k]<A[k-1])
                return false;
        }
        return true;
    }
    
    // Simple function that swaps two elements in an array
    private static void swap(double[] A, int p, int q)
    {
        double temp = A[p];
        A[p] = A[q];
        A[q] = temp;
    }

    
    // Tests our quicksort function. Times quicksort and calls isSorted to
    // see if it is properly sorted, then prints out the time elapsed.
    private static int tester(int n)
    {
        double[] test = makeRandom(n);
        long startTime = System.currentTimeMillis();
        sort(test, 0, test.length-1);
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime-startTime;
        if(!isSorted(test))
        {
            System.out.println("Sort failed");
            return 1;
        }
        System.out.println("Sorting of "+n+" integers completed successfully in " +timeElapsed+ " milliseconds");
        return 0;
    }
}