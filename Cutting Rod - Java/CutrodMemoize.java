import java.util.*;

public class CutrodMemoize extends CutrodRecursive{
    
	private final Map<Integer,Integer> memoizeMap = new HashMap<>();
    
    @Override
    public int cuttingRod(int length, List<Integer> prices)
    {
        return memoizeMap.computeIfAbsent(length, currentLength -> super.cuttingRod(currentLength, prices));
    }    
}