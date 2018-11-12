public class DriverV {
  public static void main(String[] args) {
    try {
      if (args.length == 3) {
        WordSearch WS = new WordSearch(Integer.parseInt(args[0]),Integer.parseInt(args[1]),args[2]);
        System.out.println(WS);
      } else if (args.length == 4) {
        WordSearch WS = new WordSearch(Integer.parseInt(args[0]),Integer.parseInt(args[1]),args[2],Integer.parseInt(args[3]));
        System.out.println(WS);
      } else {
        System.out.println("incorrect number of args given, must be:\nint rows, int columns, textfile of words, (optional) int seed");
      }
    } catch (NumberFormatException e) {
      System.out.println("invalid format of args given, must be:\nint rows, int columns, textfile of words, (optional) int seed");
    }
  }
}
