import java.util.List;
import java.util.stream.IntStream;

public class CutrodRecursive{
    
    public  int cuttingRod(int length, List<Integer> prices)
    {
        if (length < 2)
            return prices.get(0);
        return IntStream.rangeClosed(1, length/2)
                        .reduce(prices.get(length - 1),
                               (maxprice, index) -> Math.max(maxprice, cuttingRod(index, prices) +
                                                                       cuttingRod(length - index, prices)));
 
    }
    
}