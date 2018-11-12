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

    /**Initialize the grid to the size specified
     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */
    public WordSearch(int rows,int cols, String filename) {
      if ((rows > 0) && (cols > 0)) {
        data = new char[cols][rows];
        height = this.data.length;
        width = this.data[0].length;
        words = new ArrayList<String>();
        wordsToAdd = new ArrayList<String>();
        wordsAdded = new ArrayList<String>();
        seed = (int) System.currentTimeMillis();
        randgen = new Random();
        this.clear();

        try {
          File file = new File(filename);
          Scanner sc = new Scanner(file);

          while (sc.hasNext()) {
            String newWord = sc.nextLine().toUpperCase();
            wordsToAdd.add(newWord);
            words.add(newWord);
          }

          this.addAllWords();
        } catch (FileNotFoundException e) {
          System.out.println("File not found: " + filename);
          e.printStackTrace();
          System.exit(1);
        }
      } else {
        throw new IllegalArgumentException("error, negative index(es)");
      }
    }

    public WordSearch(int rows,int cols, String filename, int randSeed) {
      if ((rows > 0) && (cols > 0)) {
        data = new char[cols][rows];
        height = this.data.length;
        width = this.data[0].length;
        words = new ArrayList<String>();
        wordsToAdd = new ArrayList<String>();
        wordsAdded = new ArrayList<String>();
        seed = randSeed;
        randgen = new Random(randSeed);
        this.clear();

        try {
          File file = new File(filename);
          Scanner sc = new Scanner(file);

          while (sc.hasNext()) {
            String newWord = sc.nextLine().toUpperCase();
            wordsToAdd.add(newWord);
            words.add(newWord);
          }

           this.addAllWords();
        } catch (FileNotFoundException e) {
          System.out.println("File not found: " + filename);
          e.printStackTrace();
          System.exit(1);
        }
      } else {
        throw new IllegalArgumentException("error, negative index(es)");
      }
    }

    /**Set all values in the WordSearch to underscores'_'*/
    private void clear() {
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          this.data[i][j] = '_';
        }
      }
    }

    /**Each row is a new line, there is a space between each letter
     *@return a String with each character separated by spaces, and rows
     *separated by newlines.
     */
    public String toString() {
      String ans = "";
      for (int i = 0; i < height; i++) {
        ans += "|";
        for (int j = 0; j < width; j++) {
          ans += this.data[i][j];
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

    private boolean addWord(int r, int c, String word, int rowIncrement, int colIncrement) {
      if ((rowIncrement == 0) && (colIncrement == 0)) {
        return false;
      } else {
        int wordLength = word.length();
        if ((r >= 0) && (c >= 0) && ((r + (rowIncrement * wordLength)) <= width) && ((r + (rowIncrement * wordLength)) > -1) && ((c + (colIncrement * wordLength)) <= height) && ((c + (colIncrement * wordLength)) > -1)) {
          String atPlace = "";
          for (int i = 0; i < wordLength; i++) {
            if ((this.data[c + (i * colIncrement)][r + (i * rowIncrement)] != '_') && (this.data[c + (i * colIncrement)][r + (i * rowIncrement)] != word.charAt(i))) { // (you can add an or statement to check for perfect word collisions)
              return false;
            } else {
              atPlace += this.data[c + (i * colIncrement)][r + (i * rowIncrement)];
            }
          }
          if (atPlace.equals(word)) {
            return false;
          }
          for (int i = 0; i < wordLength; i++) {
            this.data[c + (i * colIncrement)][r + (i * rowIncrement)] = word.charAt(i);
          }
          return true;
        }
        return false;
      }
    }

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
          if (this.addWord(rPos,cPos,randWord,rInc,cInc)) {
            wordsToAdd.remove(randWord);
            wordsAdded.add(randWord);
            notadded = false;
            noPos = 0;
          }
          if (noPos > 1000) {
            wordsToAdd.remove(randWord);
            words.remove(randWord);
            failures++;
            notadded = false;
            noPos = 0;
          }
        }
      }
    }
}
