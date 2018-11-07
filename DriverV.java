public class DriverV {
  public static void main(String[] args) {
    WordSearch WS = new WordSearch(5,5);
    WS.addWordHorizontal("abc",0,2);
    System.out.println(WS);
  }
}
