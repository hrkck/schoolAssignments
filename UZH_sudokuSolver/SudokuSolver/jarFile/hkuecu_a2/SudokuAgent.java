public interface SudokuAgent {

    int[][] solve(int dimension, int[][] puzzle);
    String getName();
    void setName(String name);
}
