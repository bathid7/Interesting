import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Fibonnaci{

    public static List<Integer> mutable(int position)
    {   
        List<Integer> list = new ArrayList<Integer>();
        for(int currentPosition = 0 ; currentPosition < position ; currentPosition++)
        {  
            if(currentPosition <= 1)
              list.add(1);
            else
              list.add(list.get(currentPosition - 1) + list.get(currentPosition - 2));
        }
        return list;
    }

    public static void recursive(int position , List<Integer> fibRecMutable)
    {   
        List<Integer> fibTemp = fibRecMutable;
        int size = fibTemp.size();
        if(size < position)
        {
           if(size <= 1)
              fibTemp.add(1);
           else 
              fibTemp.add(fibTemp.get(size - 1) + fibTemp.get(size - 2));
           recursive(position , fibTemp);
        }
    }

    public static List<Integer> immutable(int position)
    {    
        List<Integer> fibImmutable;
        if(position == 1)
           fibImmutable = Stream.of(1).collect(Collectors.toList());
        else
        {
           fibImmutable = Stream.iterate(2 , i -> i + 1)
                                .limit(position - 2)
                                .reduce(Stream.of(1 , 1) , (a , b) -> process(a , b) , Stream::concat)
                                .collect(Collectors.toList());
        }
        
        return (fibImmutable);
    }
     
    public static Stream process(final Stream<Integer> tempStream, final int index)
    {
       final List<Integer> tempList = tempStream.collect(Collectors.toList());
       final Stream<Integer> nextStream = Stream.concat(tempList.stream() , Stream.of(tempList.stream().skip(index-2).reduce(0,Integer::sum)));
       return nextStream;
    }

    public static void main(String[] args){
        List<Integer> fibTemp = new ArrayList<Integer>();
        int position = 7;
        if(position > 0)
        {
          System.out.println("Fibanocci Recursive ");
          recursive(position , fibTemp);
          for (int value : fibTemp)
            System.out.print(value + " ");
          System.out.println();
          fibTemp.clear();
          System.out.println("Fibanocci Mutable");
          fibTemp = mutable(position);
          for(int value : fibTemp)
            System.out.print(value + " ");
          System.out.println();      
          fibTemp.clear();
          fibTemp = immutable(position);
          System.out.println("Fibanocci Immutable");
          for (int value : fibTemp)
            System.out.print(value + " ");
          System.out.println();
        }
        else
            System.out.println("Oops! Try again with a valid position");
    }
}
