public class WordSearch {
    private char[][]data;

    /**Initialize the grid to the size specified
     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */
    public WordSearch(int rows,int cols) {
      if ((rows > 0) && (cols > 0)) {
        data = new char[rows][cols];
        this.clear();
      } else {
        throw new IllegalArgumentException("error, negative index(es)");
      }
    }

    public int rowLength() {
      return this.data.length;
    }

    public int columnLength() {
      return this.data[0].length;
    }

    /**Set all values in the WordSearch to underscores'_'*/
    private void clear() {
      for (int i = 0; i < this.data.length; i++) {
        for (int j = 0; j < this.data[i].length; j++) {
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
      for (int i = 0; i < this.data.length; i++) {
        for (int j = 0; j < this.data[i].length; j++) {
          ans += this.data[i][j] + " ";
        }
        if (i != this.data.length - 1) {
          ans += "\n";
        }
      }
      return ans;
    }


    /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from left to right, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     * or there are overlapping letters that do not match, then false is returned
     * and the board is NOT modified.
     */
    public boolean addWordHorizontal(String word,int row, int col) {
      int wordLength = word.length();
      if ((row < this.rowLength()) && (row >= 0) && ((wordLength + col) <= this.columnLength()) && (col >= 0)) {
        for(int i = col; i < (col + wordLength); i++) {
          this.data[row][i] = word.charAt(i - col);
        }
        return true;
      } else {
        return false;
      }
    }

   /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from top to bottom, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     *or there are overlapping letters that do not match, then false is returned.
     *and the board is NOT modified.
     */
    public boolean addWordVertical(String word,int row, int col) {
      int wordLength = word.length();
      if ((col < this.columnLength()) && (col >= 0) && ((wordLength + row) <= this.rowLength()) && (row >= 0)) {
        for(int i = row; i < (row + wordLength); i++) {
          this.data[i][col] = word.charAt(i - row);
        }
        return true;
      } else {
        return false;
      }
    }

    /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from top left to bottom right, must fit on the WordGrid,
     *and must have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     *or there are overlapping letters that do not match, then false is returned.
     */
    public boolean addWordDiagonal(String word,int row, int col) {
      int wordLength = word.length();
      if (((wordLength + col) <= this.columnLength()) && (col >= 0) && ((wordLength + row) <= this.rowLength()) && (row >= 0)) {
        for(int i = 0; i < wordLength; i++) {
          this.data[row + i][col + i] = word.charAt(i);
        }
        return true;
      } else {
        return false;
      }
    }
}
