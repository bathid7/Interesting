import java.util.*;
import java.util.function.Supplier;
public class CutrodMutable {

    public static void main(String[] args) {
        
        List<Integer> prices = Arrays.asList(2,7,9,11,17,18,23);
        
        for(int i = 0; i<5; i++)
        {
            System.out.println("\nRecursive solution : ");
            List<Double> results = timeCode(() -> new CutrodRecursive().cuttingRod(prices.size(),prices));
            System.out.println("Maximum price is : " + results.get(0)+ "\nTotal time for execution : " + results.get(1)+" milliseconds");

            System.out.println("Using memoization : ");
            results = timeCode(() -> new CutrodMemoize().cuttingRod(prices.size(),prices));
            System.out.println("Maximum price is : " + results.get(0)+ "\nTotal time for execution : " + results.get(1)+" milliseconds");
        }
    }
    
    public static List<Double> timeCode(Supplier<Integer> functionCall) {
         long start = System.nanoTime();
         double maxPrice = functionCall.get();
         long end = System.nanoTime();
         return (Arrays.asList(maxPrice, (double)(end-start)/1000000));
    }
    
}
