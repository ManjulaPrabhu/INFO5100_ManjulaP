import java.util.Scanner;

public class ArrayPartition {
    public static void main(String[] args){
        int size;
        int sum=0;
        Scanner in=new Scanner(System.in);
        System.out.println("Enter the size of the array");
        size=in.nextInt();
        int[] input=new int[size];
        System.out.println("Enter the elements of the array");
        for(int i=0;i<size;i++){
            input[i]=in.nextInt();
        }

        for(int j=0;j<size;j++){
            sum+=input[j];
        }

        if(sum%2!=0){
            System.out.println("Cannot be partitioned into two subsets of equal sum");
        }

        else{
            if(isSubsetSumEqual(input,size,sum/2)){
                System.out.println("Can be partitioned into two subsets of equal sum");
            }
            else{
                System.out.println("Cannot be partitioned into two subsets of equal sum");
            }
        }
    }

    private static boolean isSubsetSumEqual(int[] input, int size, int sum) {
        if(sum==0)
            return true;
        if(size==0 && sum!=0)
            return false;
        if(input[size-1]>sum)
            return isSubsetSumEqual(input,size-1,sum);

        return isSubsetSumEqual(input,size-1,sum) || isSubsetSumEqual(input, size-1, sum-input[size-1]);
    }
} 
