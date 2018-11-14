import java.util.*; //random, scanner, arraylist
import java.io.*; //file, filenotfoundexception

public class WordSearch {
    private char[][]data;
    private int width;
    private int height;
    //the random seed used to produce this WordSearch
    private int seed;
    //a random Object to unify your random calls
    private Random randgen;
    //all words from a text file get added to wordsToAdd, indicating that they have not yet been added
    private ArrayList<String>wordsToAdd;
    //all words that were successfully added get moved into wordsAdded.
    private ArrayList<String>wordsAdded;
    private ArrayList<String>words;

    /**The same as the first constructor but with a given seed
     *for the Random object for word addition.
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     *@param filename is the textfile from which the words of the WordSearch come from
     *@param randSeed is the seed for the Random object that initializes the WordSearch
     */
    public WordSearch(int rows,int cols, String filename, int randSeed, String answer) throws FileNotFoundException {
      if ((rows > 0) && (cols > 0)) {
        data = new char[rows][cols];
        height = data.length;
        width = data[0].length;
        words = new ArrayList<String>();
        wordsToAdd = new ArrayList<String>();
        wordsAdded = new ArrayList<String>();
        seed = randSeed;
        randgen = new Random(randSeed);
        clear();

        File file = new File(filename);
        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
          String newWord = sc.nextLine().toUpperCase();
          wordsToAdd.add(newWord);
          words.add(newWord);
        }

        this.addAllWords();

        boolean showKey = answer.equals("key");
        fillIn(showKey);
      } else {
        throw new IllegalArgumentException();
      }
    }

    /**Set all values in the WordSearch to underscores'_'*/
    private void clear() {
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          data[i][j] = '_';
        }
      }
    }

    /**Each row is a new line of the WordSearch, there is a space between each letter
     *@return a String with each character separated by spaces, and rows
     *separated by newlines, and borders on the left and right.
     */
    public String toString() {
      String ans = "";
      for (int i = 0; i < height; i++) {
        ans += "|";
        for (int j = 0; j < width; j++) {
          ans += data[i][j];
          if (j != width - 1) {
            ans += " ";
          }
        }
        ans += "|\n";
      }
      ans += "Words: ";
      for (int k = 0; k < words.size(); k++) {
        ans += words.get(k);
        if (k != words.size() - 1) {
          ans += ", ";
        } else {
          ans += "  ";
        }
      }
      ans += "(seed: "+ seed + ")";
      return ans;
    }

    /**Helper function for addAllWords, attempts to adds one word into WordSearch
     *@param word is the word to be added
     *@param r is the starting row where the word is added
     *@param c is the starting column where the word is added
     *@param rowIncrement is the increment between each letter by row position
     *@param colIncrement is the increment between each letter by column position
     *@return true if addition was successful, false otherwise.
     */
    private boolean addWord(String word, int r, int c, int rowIncrement, int colIncrement) {
      if (word.length() == 0) {
        return false;
      }
      if ((rowIncrement == 0) && (colIncrement == 0)) {
        return false;
      } else {
        int wordLength = word.length();
        if ((r >= 0) && (c >= 0) && ((r + (rowIncrement * wordLength)) <= height) && ((r + (rowIncrement * wordLength)) >= -1) && ((c + (colIncrement * wordLength)) <= width) && ((c + (colIncrement * wordLength)) >= -1)) {
          String atPlace = "";
          for (int i = 0; i < wordLength; i++) {
            if ((data[r + (i * rowIncrement)][c + (i * colIncrement)] != '_') && (data[r + (i * rowIncrement)][c + (i * colIncrement)] != word.charAt(i))) {
              return false;
            } else {
              atPlace += data[r + (i * rowIncrement)][c + (i * colIncrement)];
            }
          }
          if (atPlace.equals(word)) {
            return false;
          }
          for (int i = 0; i < wordLength; i++) {
            data[r + (i * rowIncrement)][c + (i * colIncrement)] = word.charAt(i);
          }
          return true;
        }
        return false;
      }
    }

    /**Function that operates within constructor to add all words.
    /*If a word cannot be added after 1000 tries of an algorithm (rare),
    /*it is removedfrom the word list. Otherwise, it is added to a random
    /*location, with random increments.
    */
    private void addAllWords() {
      int rInc = 0;
      int cInc = 0;
      int rPos = 0;
      int cPos = 0;
      int wordIndex = 0;
      String randWord = "";
      boolean notadded = true;
      int noPos = 0;
      int failures = 0;
      while (!(wordsToAdd.isEmpty())) {
        notadded = true;
        wordIndex = randgen.nextInt(wordsToAdd.size());
        randWord = wordsToAdd.get(wordIndex);
        // i got real inspired what in tarnation
        while (notadded) {
          rInc = randgen.nextInt(3) - 1;
          cInc = randgen.nextInt(3) - 1;
          while ((rInc == 0) && (cInc == 0)) {
            rInc = randgen.nextInt(3) - 1;
            cInc = randgen.nextInt(3) - 1;
          }
          noPos++;
          rPos = randgen.nextInt(width - (Math.abs(rInc) * (randWord.length() - 1)));
          if (rInc == -1) {
            rPos += randWord.length() - 1;
          }
          cPos = randgen.nextInt(height - (Math.abs(cInc) * (randWord.length() - 1)));
          if (cInc == -1) {
            cPos += randWord.length() - 1;
          }
          if (addWord(randWord,rPos,cPos,rInc,cInc)) {
            wordsToAdd.remove(randWord);
            wordsAdded.add(randWord);
            notadded = false;
            noPos = 0;
          }
          if (noPos > 500) {
            wordsToAdd.remove(randWord);
            words.remove(randWord);
            failures++;
            notadded = false;
            noPos = 0;
          }
        }
      }
    }

    private void fillIn(boolean dispKey) {
      String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      if (dispKey) {
        for (int i = 0; i < height; i++) {
          for (int j = 0; j < width; j++) {
            if (data[i][j] == '_') {
              data[i][j] = ' ';
            }
          }
        }
      } else {
        for (int i = 0; i < height; i++) {
          for (int j = 0; j < width; j++) {
            if (data[i][j] == '_') {
              data[i][j] = alphabet.charAt((int)(Math.random() * 26));
            }
          }
        }
      }
    }

    public static void main(String[] args) {
      try {
        if (args.length < 3) {
          System.out.println("not enough args given, must be:\ninteger rows, integer columns, textfile of words, (optional) integer seed, (optional) \"key\"");
        } else if (args.length == 3) {
          WordSearch WS = new WordSearch(Integer.parseInt(args[0]),Integer.parseInt(args[1]),args[2], (int) (Math.random() * 100000), "");
          System.out.println(WS);
        } else if (args.length == 4) {
          WordSearch WS = new WordSearch(Integer.parseInt(args[0]),Integer.parseInt(args[1]),args[2],Integer.parseInt(args[3]),"");
          System.out.println(WS);
        } else {
          WordSearch WS = new WordSearch(Integer.parseInt(args[0]),Integer.parseInt(args[1]),args[2],Integer.parseInt(args[3]), args[4]);
          System.out.println(WS);
        }
      } catch (NumberFormatException e) {
        System.out.println("not enough args given, must be:\ninteger rows, integer columns, textfile of words, (optional) integer seed, (optional) \"key\"");
      } catch (FileNotFoundException e) {
        System.out.println("File not found: " + args[2]);
        System.exit(1);
      } catch (IllegalArgumentException e) {
        System.out.println("non-positive row/column length given, must be 1 or greater");
      }
    }
}
