import java.time.*;

public class quicksort {

    public static void main(String[] args)
    {
        tester(1000);
        tester(1000000);
        tester(10000000);
        //tester(100000000);
        //tester(1000000000);
    }

    private static double[] makeRandom(int n)
    {
        double[] toBeSorted = new double[n];
        for(int k = 0; k < n; k++)
            toBeSorted[k] = Math.random() * n;
        return toBeSorted;
    }

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

    private static boolean isSorted(double[] A)
    {
        for(int k = 1; k < A.length; k++)
        {
            if(A[k]<A[k-1])
                return false;
        }
        return true;
    }
    private static void swap(double[] A, int p, int q)
    {
        double temp = A[p];
        A[p] = A[q];
        A[q] = temp;
    }

    private static int tester(int n)
    {
        double[] test = makeRandom(n);
        long startTime = System.currentTimeMillis();
        sort(test,0, test.length-1);
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
