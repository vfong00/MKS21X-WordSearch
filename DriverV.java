public class DriverV {
  public static void main(String[] args) {
    WordSearch WS = new WordSearch(5,5,"words.txt");
    WS.addWord(2,2,"abc",-1,1);
    System.out.println(WS);
  }
}
