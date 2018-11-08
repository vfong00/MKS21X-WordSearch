public class DriverV {
  public static void main(String[] args) {
    System.out.println("\n1. CONSTRUCTOR INITIALIZATION + TOSTRING");
    // System.out.println("Creating a -1 x -1 WordSearch (should fail, print that it caught an exception )");
    System.out.println("Creating a 5 x 5 WordSearch and printing (hopefully y'all know what that is supposed to look like)");
    WordSearch WS = new WordSearch(5,5);
    System.out.println(WS);
    System.out.println("\n---------------\n");

    System.out.println("2. ADD WORD HORIZONTAL");
    System.out.println("Adding to a negative row index (should print false): " + WS.addWordHorizontal("abc",-1,2));
    System.out.println("Adding to a negative column index (should print false): " + WS.addWordHorizontal("abc",0,-1));
    System.out.println("Adding to an out of bounds row index (should print false): " + WS.addWordHorizontal("abc",10,0));
    System.out.println("Adding to an out of bounds column index (should print false): " + WS.addWordHorizontal("abc",0,10));
    System.out.println("Adding to last index of row, where a 3-letter word doesn't fit (should print false): " + WS.addWordHorizontal("abc",0,4));
    System.out.println("Adding a 6-letter word (doesn't fit, should print false): " + WS.addWordHorizontal("abcdef",0,4));
    System.out.println("Adding \"abc\" to last 3 slots of each row (should print true, once): " + (WS.addWordHorizontal("abc",0,2) && WS.addWordHorizontal("abc",1,2) && WS.addWordHorizontal("abc",2,2) && WS.addWordHorizontal("abc",3,2) && WS.addWordHorizontal("abc",4,2)));
    System.out.println("\nPrinting WS (see above description)\n" + WS);
    System.out.println("\n---------------\n");

    System.out.println("3. ADD WORD VERTICAL (with a new WordSearch! of the same dimensions!)");
    WordSearch SW = new WordSearch(5,5);
    System.out.println("Adding to a negative row index (should print false): " + SW.addWordVertical("abc",-1,2));
    System.out.println("Adding to a negative column index (should print false): " + SW.addWordVertical("abc",0,-1));
    System.out.println("Adding to an out of bounds row index (should print false): " + SW.addWordVertical("abc",10,0));
    System.out.println("Adding to an out of bounds column index (should print false): " + SW.addWordVertical("abc",0,10));
    System.out.println("Adding to last index of column, where a 3-letter word doesn't fit (should print false): " + SW.addWordVertical("abc",4,0));
    System.out.println("Adding a 6-letter word (doesn't fit, should print false): " + SW.addWordHorizontal("abcdef",0,0));
    System.out.println("Adding \"abc\" to last 3 slots of each column (should print true, once): " + (SW.addWordVertical("abc",2,0) && SW.addWordVertical("abc",2,1) && SW.addWordVertical("abc",2,2) && SW.addWordVertical("abc",2,3) && SW.addWordVertical("abc",2,4)));
    System.out.println("\nPrinting WS (see above description)\n" + SW);
    System.out.println("\n---------------\n");

    System.out.println("3. ADD WORD DIAGONAL (with a new WordSearch! of the same dimensions!)");
    WordSearch SWW = new WordSearch(5,5);
    System.out.println("Adding to a negative row index (should print false): " + SWW.addWordDiagonal("abc",-1,2));
    System.out.println("Adding to a negative column index (should print false): " + SWW.addWordDiagonal("abc",0,-1));
    System.out.println("Adding to an out of bounds row index (should print false): " + SWW.addWordDiagonal("abc",10,0));
    System.out.println("Adding to an out of bounds column index (should print false): " + SWW.addWordDiagonal("abc",0,10));
    System.out.println("Adding to last index of row, where a 3-letter word doesn't fit (should print false): " + SWW.addWordDiagonal("abc",0,4));
    System.out.println("Adding to last index of column, where a 3-letter word doesn't fit (should print false): " + SWW.addWordDiagonal("abc",4,0));
    System.out.println("Adding a 6-letter word (doesn't fit, should print false): " + SWW.addWordDiagonal("abcdef",0,0));
    System.out.println("Adding numbers to each diagonal (should print true, once): " + (SWW.addWordDiagonal("0",4,0) && SWW.addWordDiagonal("11",3,0) && SWW.addWordDiagonal("222",2,0) && SWW.addWordDiagonal("3333",1,0) && SWW.addWordDiagonal("44444",0,0) && SWW.addWordDiagonal("3333",0,1) && SWW.addWordDiagonal("222",0,2) && SWW.addWordDiagonal("222",0,2) && SWW.addWordDiagonal("11",0,3) && SWW.addWordDiagonal("0",0,4)));
    System.out.println("\nPrinting WS (see above description)\n" + SWW);
    System.out.println("\n---------------\n");
  }
}
