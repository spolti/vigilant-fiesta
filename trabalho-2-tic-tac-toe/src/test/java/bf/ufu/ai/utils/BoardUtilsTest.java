package bf.ufu.ai.utils;

import br.ufu.ai.model.Player;
import br.ufu.ai.utils.BoardUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class BoardUtilsTest {

    @Test
    public void getAllAvailableMovesTest() {
        char board[][] =  {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };
        Assertions.assertEquals(9, BoardUtils.getAvailableMoves(board).size());
    }

    @Test
    public void firstAvailableMovesTest() {
        char board[][] = new char[][]{
                {'X', 'X', 'X'},
                {'X', 'O', ' '},
                {'O', 'X', 'O'},
        };
        List<int[]> currentMoves = Arrays.asList(new int[]{1,2});
        Assertions.assertArrayEquals(currentMoves.toArray(), BoardUtils.getAvailableMoves(board).toArray());
    }

    @Test
    public void secondAvailableMovesTest() {
        char board[][] = new char[][]{
                {' ', 'X', 'X'},
                {'X', 'O', ' '},
                {'O', ' ', 'O'},
        };
        List<int[]> currentMoves = Arrays.asList(new int[]{0,0}, new int[]{1,2}, new int[]{2,1});
        Assertions.assertArrayEquals(currentMoves.toArray(), BoardUtils.getAvailableMoves(board).toArray());
    }


    @Test
    public void rowsWinnerTest() {
        char board[][] = new char[][]{
                {'X', 'X', 'X'},
                {'O', 'X', ' '},
                {'O', ' ', 'O'},
        };
        Assertions.assertTrue('X' == BoardUtils.hasWon(board));

        char board1[][] = new char[][]{
                {'X', 'O', 'X'},
                {'O', 'O', 'O'},
                {'X', ' ', 'O'},
        };
        Assertions.assertTrue('O' == BoardUtils.hasWon(board1));

        char board2[][] = new char[][]{
                {' ', 'O', 'X'},
                {'O', 'X', ' '},
                {'X', 'X', 'X'},
        };
        Assertions.assertTrue('X' == BoardUtils.hasWon(board2));
    }

    @Test
    public void colsWinnerTest() {
        char board[][] = new char[][]{
                {'O', 'X', 'X'},
                {'O', 'X', ' '},
                {'O', ' ', 'O'},
        };
        Assertions.assertTrue('O' == BoardUtils.hasWon(board));

        char board1[][] = new char[][]{
                {'O', 'X', 'O'},
                {'O', 'X', 'X'},
                {'X', 'X', 'O'},
        };
        Assertions.assertTrue('X' == BoardUtils.hasWon(board1));

        char board2[][] = new char[][]{
                {' ', 'O', 'O'},
                {'O', 'X', 'O'},
                {'O', 'X', 'O'},
        };
        Assertions.assertTrue('O' == BoardUtils.hasWon(board2));
    }

    @Test
    public void diagonalsWinnerTest() {
        char board[][] = new char[][]{
                {'O', 'X', 'X'},
                {'O', 'X', 'O'},
                {'X', ' ', 'O'},
        };
        Assertions.assertTrue('X' == BoardUtils.hasWon(board));

        char board1[][] = new char[][]{
                {'O', 'X', 'O'},
                {'O', 'O', 'X'},
                {'X', 'X', 'O'},
        };
        Assertions.assertTrue('O' == BoardUtils.hasWon(board1));
    }

    @Test
    public void drawTest() {
        char board[][] = new char[][]{
                {'O', 'O', 'X'},
                {'X', 'X', 'O'},
                {'O', 'X', 'O'},
        };
        Assertions.assertTrue(BoardUtils.isDraw(board));
    }


    @Test
    public void moveTest() {
        char board[][] = new char[][]{
                {' ', 'X', 'X'},
                {'X', 'O', ' '},
                {'O', ' ', 'O'},
        };

        char targetBoard[][] = new char[][]{
                {'O', 'X', 'X'},
                {'X', 'O', ' '},
                {'O', ' ', 'O'},
        };

        Player player = new Player('O', "Mini", true);

        char[][] afterMoveBoard = BoardUtils.move(board, 0, 0, 'O');
        Assertions.assertArrayEquals(targetBoard, afterMoveBoard);
        Assertions.assertFalse(BoardUtils.isDraw(board), "Board should not be a draw");
        BoardUtils.plotBoard(afterMoveBoard, player);
        Assertions.assertTrue('O' == BoardUtils.hasWon(afterMoveBoard), "Board should have a winner");
    }

}
