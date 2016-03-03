-module(hanoi).  
-export([start/0]).  
  
start() ->  
    NDisks = 5,
    Source = {1, lists:seq(NDisks, 1, -1)},
    Destination = {3, []},
    Using = {2, []},
    Towers = [[Source]++[Using]++[Destination]],
    Moves = round(math:pow(2,NDisks))-1,
    io:format("~nTotal Steps for ~w pegs is ~w~n",[NDisks,Moves]),
    io:format("~nIterative moves : ~n"),
    IterativeResult = iterativeHanoi(NDisks, Towers),
    printHanoi(IterativeResult),
    io:format("~nRecursive moves : ~n"),
    [_,_,_,RecursiveResult] = recursiveHanoi(NDisks, Source, Destination, Using, Towers ),
    printHanoi(RecursiveResult),
    io:format("~nTailRecursive moves : ~n"),
    TailResult = tailRecursionHanoi(Moves+1, Towers),
    printHanoi(TailResult).

%% Recursive :
recursiveHanoi(1, {I1, Source}, {I2, Dest}, Using, Towers) -> 
    TempList = [{I1, lists:sublist(Source, length(Source) - 1)}] ++ [{I2, Dest++[lists:last(Source)]}] ++ [Using],
    SortList = lists:sort(TempList),
    [{I1, lists:sublist(Source, length(Source) - 1)}, {I2, Dest ++ [lists:last(Source)]}, Using, Towers ++ [SortList]];  
  
recursiveHanoi(NDisks, Source, Dest, Using, Towers) ->  
    [S1, U1, D1, T1] = recursiveHanoi(NDisks-1, Source, Using, Dest, Towers),  
    [S2, D2, U2, T2] = recursiveHanoi(1, S1, D1, U1, T1),  
    [U3, D3, S3, T3] = recursiveHanoi(NDisks-1, U2, D2, S2, T2),  
    [S3, D3, U3, T3].

%% Iteration :
iterativeHanoi(NDisks, Towers) ->  
    FinalList = lists:foldl(fun(Index, TempTowers) -> TempTowers ++ moveDisks(Index, lists:last(TempTowers)) end, Towers, lists:seq(1, round(math:pow(2, NDisks)) - 1)),
    FinalList.

%% move disks :
moveDisks(Index,PrevStep) ->
    From = (((Index band (Index - 1))) rem 3) + 1,
    To = (((Index bor (Index - 1)) + 1) rem 3) + 1,
    {I1, Lfrom}= lists:nth(From, PrevStep),
    {I2, Lto}= lists:nth(To, PrevStep),
    {I3, Ltemp}= lists:nth((6-To-From), PrevStep),
    TempList = [{I1,lists:sublist(Lfrom, length(Lfrom)-1)}] ++ [{I2, Lto++[lists:last(Lfrom)]}] ++ [{I3, Ltemp}],
    NextStep = lists:sort(TempList),
    [NextStep].

%% Tail Recursion :
tailRecursionHanoi(1, Towers) ->
    Towers;

tailRecursionHanoi(Steps, Towers) when Steps > 1 ->
    Index = length(Towers),
    NewList = Towers ++ moveDisks(Index,lists:last(Towers)),
    tailRecursionHanoi(Steps-1, NewList).


%% Printing the Lists :
printHanoi([]) ->
    ok;

printHanoi([First|Rest]) ->
    [S,T,D] = [V || {_,V} <- First],
    lists:foreach(fun(List) -> if length(List) == 0 -> io:format(" . "); true ->lists:foreach(fun(Value)-> io:format("~w", [Value]) end, List), io:format("  ")  end end, [S,T,D]),
    io:format("~n"),
    printHanoi(Rest).