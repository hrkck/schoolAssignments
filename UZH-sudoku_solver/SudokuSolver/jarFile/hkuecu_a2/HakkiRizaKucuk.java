public class HakkiRizaKucuk implements SudokuAgent {
    
    private String name;

    HakkiRizaKucuk() {
        this.name = "hkuecu";
    }

    /**
     * Implementation of "solve" method. It initiates the actual algorithm.
     * The recursive method you will encounter below solves the puzzle globally.
     * So this function returns the input puzzle array in straightforward way.
     *
     * @param dimension Dimension input received from file input.
     * @param puzzle Sudoku puzzle as a two dimensional integer array. Also received from file input.
     * @return puzzle input.
     */
    public int[][] solve(int dimension, int[][] puzzle){
        recursiveSolver(0, 0, dimension, puzzle); // puzzle is passed by reference
        return puzzle;
    }


    /**
     * This algorithm solves Sudoku puzzle, given as a two dimensional integer array.
     * It recursively loops through all puzzle and checks for values if they are valid or not.
     * For value validation another function is used, below.
     * Finally, if the puzzle is solved successfully it returns boolean value 'true'.
     * If not, it returns 'false'.
     * Puzzle itself is not necessarily returned. Because the function updates the values of the array.
     *
     * @param x Current row coordinate.
     * @param y Current column coordinate.
     * @param dimension Given dimension of the Sudoku puzzle.
     * @param puzzle Sudoku puzzle as a two dimensional integer array.
     * @return Boolean value whether the puzzle is solved or not.
     */
    private boolean recursiveSolver(int x, int y, int dimension, int[][] puzzle) {
        if (x == dimension * dimension) {
            x = 0;
            if (++y == dimension * dimension){ return true; }
        }
        if (puzzle[x][y] != 0) return recursiveSolver(x + 1, y, dimension, puzzle);

        for (int value = 1; value <= dimension * dimension; ++value) {
            if (checkValue(x, y, dimension, value, puzzle)) {
                puzzle[x][y] = value;
                if (recursiveSolver(x + 1, y, dimension, puzzle))
                    return true;
            }
        }
        puzzle[x][y] = 0; // when no solution found
        return false;
    }


    /**
     * This function checks if a given value fits to the given coordinate.
     * It uses a very basic approach. Simply put, it checks for the row, column and smaller square for the value.
     * If value is not found in any of those fields, the value is considered to be a appropriate for the given coordinates.
     *
     * @param x Requested row coordinate for the 'value'.
     * @param y Requested column coordinate for the 'value'.
     * @param dimension Given dimension of the Sudoku puzzle.
     * @param value Requested integer value to check validity.
     * @param puzzle Sudoku puzzle as a two dimensional integer array.
     * @return Boolean value whether the value fits to the coordinate or not.
     */
    private boolean checkValue(int x, int y, int dimension, int value, int[][] puzzle) {
        // Check for the whole row and column:
        for (int r = 0; r < dimension * dimension; ++r)
            if (value == puzzle[r][y]) // Does value exist in the row?
                return false;

        for (int c = 0; c < dimension * dimension; ++c)
            if (value == puzzle[x][c]) // Does value exist in the column?
                return false;

        // Check for the smaller square:
        int rowMargin = (x / dimension) * dimension;
        int columnMargin = (y / dimension) * dimension;
        for (int r = 0; r < dimension; ++r)
            for (int c = 0; c < dimension; ++c)
                if (value == puzzle[rowMargin + r][columnMargin + c]) // Does value exist in the smaller square?
                    return false;

        return true; // value is approved
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
