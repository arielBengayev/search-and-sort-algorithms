public class Main {
    public static void main(String[] args) {
//        bubbleSort(new int[]{1,5,2,4,3});
//        System.out.println();
//        selectionSort(new int[]{1,5,2,4,3});
//        System.out.println();
        int[] numbers = {1,5,2,4,3,6,9,7,8};
//        mergeSort(numbers);
//        for (int i : numbers)
//            System.out.print(i+"|");
        quickSort(numbers,0,numbers.length-1);
        for (int i : numbers)
            System.out.print(i+"|");
    }

//----------------------sort--------------------------------------------------------------------

    //bubble sort(not effective)(sorting from the end of the array)
    public static void bubbleSort(int[] numbers){//1,3,2,6,4
        for (int i=0; i< numbers.length-1; i++) {
            for (int j = 0; j < numbers.length-i-1; j++){
                if (numbers[j] > numbers[j+1]){
                    int temp = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = temp;
                }
                System.out.print("+");//check how many iteration(not need in original code)
            }
        }
    }

    //selection sort(not affective but better than bubble sort)(sorting from the beginning of the array)
    public static void selectionSort(int[] numbers){
        for (int i=0; i<numbers.length-1; i++){//1,3,2,4,5
            int min = i;
            for (int j=i+1; j<numbers.length; j++){
                if (numbers[min] > numbers[j]){
                    min = j;
                }
                System.out.print("+");//check how many iteration(not need in original code)
            }
            int temp = numbers[i];
            numbers[i] = numbers[min];
            numbers[min] = temp;
        }
    }

    //insertion sort(not very effective but better than bubble and selection sorts)
    public static void insertionSort(int[] numbers){
        for (int i=1; i<numbers.length; i++){
            int temp = numbers[i];
            int j=i-1;
            while (j >= 0 && numbers[j] > temp){
                numbers[j+1] = numbers[j];
                j--;
            }
            numbers[j+1] = temp;
        }
    }

    //merge sort(faster than insertion, selection and bubble sorts but use more memory than them)
    public static void mergeSort(int[] numbers){
        if (numbers.length <= 1)
            return;
        int middle = numbers.length / 2;
        int[] leftSide = new int[middle];
        int[] rightSide = new int[numbers.length - middle];
        int j = 0;
        for (int i=0; i< numbers.length; i++){
            if (i<middle){
                leftSide[i] = numbers[i];
            }
            else {
                rightSide[j] = numbers[i];
                j++;
            }
        }
        mergeSort(leftSide);
        mergeSort(rightSide);
        mergeHelper(numbers,leftSide,rightSide);
    }
    public static void mergeHelper(int[] numbers, int[] leftSide, int[] rightSide){
        int leftSize = numbers.length/2, rightSize = numbers.length-leftSize;
        int i=0, l=0, r=0;//all,leftSide,rightSide
        while (l < leftSize && r < rightSize){
            if (leftSide[l] < rightSide[r]){
                numbers[i] = leftSide[l];
                i++;
                l++;
            }
            else {
                numbers[i] = rightSide[r];
                i++;
                r++;
            }
        }
        while (l < leftSize){
            numbers[i] = leftSide[l];
            i++;
            l++;
        }
        while (r < rightSize){
            numbers[i] = rightSide[r];
            i++;
            r++;
        }
    }

    //quickSort()
    public static void quickSort(int[] numbers, int start, int end){
        if (end <= start)
            return;
        int pivot = partition(numbers,start,end);
        quickSort(numbers,start,pivot-1);
        quickSort(numbers,pivot+1,end);
    }
    public static int partition(int[] numbers, int start, int end){
        int pivot = numbers[end];
        int i = start-1;
        for (int j=start; j<end; j++){
            if (numbers[j] < pivot){
                i++;
                int temp = numbers[i];
                numbers[i] = numbers[j];
                numbers[j] = temp;
            }
        }
        i++;
        int temp = numbers[i];
        numbers[i] = numbers[end];
        numbers[end] = temp;
        return i;
    }

//-------------------search----------------------------------------------------------

    //linear search(bad for big data / the data needs to be sorted)
    private static int linearSearch(int[] numbers, int toSearch){
        for (int i=0; i< numbers.length; i++){
            if (numbers[i] == toSearch){
                return i;
            }
        }
        return -1;
    }

    //binary search(good for a big data / the data needs to be sorted)
    //ככל שהמספר שמחפשים קרוב לאמצע יהיו פחות איטרציות
    private static int binarySearch(int[] numbers, int toSearch){

        //if regular array is used we can use a build method
        //int index = Arrays.binarySearch(numbers, toSearch);

        //if not regular array we can use this method
        int lowIndex = 0;
        int highIndex = numbers.length-1;
        while (toSearch >= numbers[lowIndex] && toSearch <= numbers[highIndex] &&
                lowIndex <= highIndex){
            System.out.print("+");
            int middleIndex = lowIndex + (highIndex - lowIndex) / 2;
            int value = numbers[middleIndex];
            if (value < toSearch){
                lowIndex = middleIndex + 1;
            }
            else if (value > toSearch){
                highIndex = middleIndex - 1;
            }
            else{
                return middleIndex;
            }
        }
        return -1;
    }

    //interpolation search(the data needs to be sorted)
    //ככל שהמספר שמחפשים יהיה רחוק מהאמצע יהיו פחות איטרציות
    private static int interpolation(int[] numbers, int toSearch){
        int lowIndex = 0;
        int highIndex = numbers.length-1;
        while (toSearch >= numbers[lowIndex] && toSearch <= numbers[highIndex] &&
                lowIndex <= highIndex){
            System.out.print("+");
            int probe = lowIndex + (highIndex - lowIndex) * (toSearch - numbers[lowIndex]) /
                    (numbers[highIndex] - numbers[lowIndex]);
            if(numbers[probe] == toSearch){
                return probe;
            }
            else if(numbers[probe] < toSearch){
                lowIndex = probe+1;
            }
            else{
                highIndex = probe-1;
            }
        }
        return -1;
    }
}