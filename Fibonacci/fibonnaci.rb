def fibonnaci_recursion(position , fibSeriesRec)
 tempList = fibSeriesRec
 currentPosition = fibSeriesRec.size
 if currentPosition < position
  if currentPosition <= 1
    tempList << 1
  else
    tempList << (tempList[currentPosition - 1] + tempList[currentPosition - 2])
  end
  fibonnaci_recursion(position , tempList)
 end
end

def fibonnaci_mutable(position , fibMutable)
  fibSeriesMutable = fibMutable
  for currentPosition in 0..position-1
    if currentPosition <= 1
        fibMutable << 1;
    else
        fibMutable << (fibMutable[currentPosition - 1] + fibMutable[currentPosition - 2])
    end
  end
end

def fibonnaci_immutable(position)
  if position == 1
   (1).inject(0){|result , index| index}
  else
   (2..position - 1).inject([1 , 1]){|result , index| result<<(result[index - 2] + result[index - 1])}
  end
end

def main()
fibTemp = []
position = 7
 if  position > 0
  puts "\nFibonnaci series of position #{position} :"
  #By Recursion
  puts "---> Implemented by using Recurssion is "
  fibonnaci_recursion(position , fibTemp)
  fibTemp.each {|value|  print value.to_s+" "}
  fibTemp.clear
  puts
  #By not using recurssion
  puts "---> Implemented with out using Recurssion is "
  fibonnaci_mutable(position , fibTemp)
  fibTemp.each {|value|  print value.to_s+" "}
  fibTemp.clear
  puts
  #Immutable
  puts "---> Implemented employing immutability is "
  fibTemp = fibonnaci_immutable(position)
  fibTemp.each {|value|  print value.to_s+" "}
  fibTemp.clear
  puts
else
  puts "Oops! Try again with a valid length"
end
end

main()
