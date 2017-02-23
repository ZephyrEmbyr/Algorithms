import java.time.*;

class qeapsort {

    public static void main(String[] args)
    {
        tester(10);
        //tester(1000000);
        //tester(10000000);
        //tester(100000000);
        //tester(1000000000);
    }

    private static double[] makeRandom(int n)
    {
        double[] toBeSorted = new double[n+1];
        for(int k = 0; k < toBeSorted.length; k++)
            toBeSorted[k] = Math.random() * n+1;
        return toBeSorted;
    }

    private static void sort(double[] A)
    {
        buildHeap(A);
        removeMax(A, A.length);
    }

    private static void buildHeap(double[] A)
    {
        for(int k = 1; k < A.length; k++)
        {
            int  index = k;
            while(A[index] > A[index/2] && index>1)
            {
                swap(A, index, index/2);
                index=index/2;
            }
        }
    }

    private static void heapify(double[] A, int k, int index)
    {
        int max = 2*k;
        if(index > 0)
        {
            if(2*k >= index)
                removeMax(A, index);
            if(2*k+1<index)
                max = Math.max(2*k,2*k+1);
            swap(A, index, max);
            heapify(A, max, index);
        }
        
    }

    private static void removeMax(double[] A, int index)
    {
        swap(A, index-1, 1);
        index--;
        heapify(A, 1, index);
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
        for(int k = 0; k < 10; k++){
            System.out.println(test[k]);
        }
        long startTime = System.currentTimeMillis();
        sort(test);
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime-startTime;
        if(!isSorted(test))
        {
            System.out.println("Sort failed");
            return 1;
        }
        System.out.println("Sorting of "+n+" integers completed successfully in " +timeElapsed+ " milliseconds");
        for(int k = 0; k < 10; k++){
            System.out.println(test[k]);
        }
        return 0;
    }
}
