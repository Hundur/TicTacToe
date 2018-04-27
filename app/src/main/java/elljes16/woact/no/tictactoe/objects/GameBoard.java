package elljes16.woact.no.tictactoe.objects;

public class GameBoard
{
    private Boolean[] gameBoard;

    public GameBoard()
    {
        gameBoard = new Boolean[] { null, null, null,
                                    null, null, null,
                                    null, null, null };
    }

    public void resetGameBoard()
    {
        gameBoard = new Boolean[] { null, null, null,
                                    null, null, null,
                                    null, null, null };
    }

    public void updateBoard(int position, boolean playerTurn)
    {
        gameBoard[position] = playerTurn;
    }

    public boolean checkIfBoardIsFull()
    {
        // If the GameBoard still has empty spaces, return false
        for(int i = 0; i < gameBoard.length; i++)
        {
            if(gameBoard[i] == null)
                return false;
        }

        return true;
    }

    public boolean checkIfPlayfieldValid(int position)
    {
        // Checks if the currently clicked Playerfield hasn't already been clicked
        if(gameBoard[position] == null)
            return true;
        else
            return false;
    }

    public boolean checkIfPlayerOneWon()
    {
        if(checkVerticalPlayerOne() || checkHorizontalPlayerOne() || checkDiagonalPlayerOne())
            return true;
        else
            return false;
    }

    public boolean checkIfPlayerTwoWon()
    {
        if(checkVerticalPlayerTwo() || checkHorizontalPlayerTwo() || checkDiagonalPlayerTwo())
            return true;
        else
            return false;
    }

    private boolean checkHorizontalPlayerOne()
    {
        // First checks if the pieces aren't null, to avoid NullPointerException
        // Then checks to see if someone has three in a row
        if (gameBoard[0] != null && gameBoard[1] != null && gameBoard[2] != null)
        {
            if (gameBoard[0] && gameBoard[1] && gameBoard[2])
                return true;
        }

        if (gameBoard[3] != null && gameBoard[4] != null && gameBoard[5] != null)
        {
            if (gameBoard[3] && gameBoard[4] && gameBoard[5])
                return true;
        }

        if (gameBoard[6] != null && gameBoard[7] != null && gameBoard[8] != null)
        {
            if (gameBoard[6] && gameBoard[7] && gameBoard[8])
                return true;
        }

        return false;
    }

    // --------------------------------------------------------------------------

    private boolean checkVerticalPlayerOne()
    {
        // First checks if the pieces aren't null, to avoid NullPointerException
        // Then checks to see if someone has three in a row
        if(gameBoard[0] != null && gameBoard[3] != null && gameBoard[6] != null)
        {
            if(gameBoard[0] && gameBoard[3] && gameBoard[6])
                return true;
        }

        if(gameBoard[1] != null && gameBoard[4] != null && gameBoard[7] != null)
        {
            if(gameBoard[1] && gameBoard[4] && gameBoard[7])
                return true;
        }

        if(gameBoard[2] != null && gameBoard[5] != null && gameBoard[8] != null)
        {
            if(gameBoard[2] && gameBoard[5] && gameBoard[8])
                return true;
        }

        return false;
    }

    private boolean checkDiagonalPlayerOne()
    {
        // First checks if the pieces aren't null, to avoid NullPointerException
        // Then checks to see if someone has three in a row
        if(gameBoard[0] != null && gameBoard[4] != null && gameBoard[8] != null)
        {
            if(gameBoard[0] && gameBoard[4] && gameBoard[8])
                return true;
        }

        if(gameBoard[6] != null && gameBoard[4] != null && gameBoard[2] != null)
        {
            if(gameBoard[6] && gameBoard[4] && gameBoard[2])
                return true;
        }

        return false;
    }

    // --------------------------------------------------------------------------

    private boolean checkHorizontalPlayerTwo()
    {
        // First checks if the pieces aren't null, to avoid NullPointerException
        // Then checks to see if someone has three in a row
        if(gameBoard[0] != null && gameBoard[1] != null && gameBoard[2] != null)
        {
            if(!gameBoard[0] && !gameBoard[1] && !gameBoard[2])
                return true;
        }

        if(gameBoard[3] != null && gameBoard[4] != null && gameBoard[5] != null)
        {
            if(!gameBoard[3] && !gameBoard[4] && !gameBoard[5])
                return true;
        }

        if(gameBoard[6] != null && gameBoard[7] != null && gameBoard[8] != null)
        {
            if(!gameBoard[6] && !gameBoard[7] && !gameBoard[8])
                return true;
        }

        return false;
    }

    private boolean checkVerticalPlayerTwo()
    {
        // First checks if the pieces aren't null, to avoid NullPointerException
        // Then checks to see if someone has three in a row
        if (gameBoard[0] != null && gameBoard[3] != null && gameBoard[6] != null)
        {
            if (!gameBoard[0] && !gameBoard[3] && !gameBoard[6])
                return true;
        }

        if (gameBoard[1] != null && gameBoard[4] != null && gameBoard[7] != null)
        {
            if(!gameBoard[1] && !gameBoard[4] && !gameBoard[7])
                return true;
        }

        if(gameBoard[2] != null && gameBoard[5] != null && gameBoard[8] != null)
        {
            if(!gameBoard[2] && !gameBoard[5] && !gameBoard[8])
                return true;
        }

        return false;
    }

    private boolean checkDiagonalPlayerTwo()
    {
        // First checks if the pieces aren't null, to avoid NullPointerException
        // Then checks to see if someone has three in a row
        if(gameBoard[0] != null && gameBoard[4] != null && gameBoard[8] != null)
        {
            if (!gameBoard[0] && !gameBoard[4] && !gameBoard[8])
                return true;
        }

        if(gameBoard[6] != null && gameBoard[4] != null && gameBoard[2] != null)
        {
            if (!gameBoard[6] && !gameBoard[4] && !gameBoard[2])
                return true;
        }

        return false;
    }

    // --------------------------------------------------------------------------

    public Boolean[] getGameBoard()
    {
        return gameBoard;
    }

    public void setGameBoard(Boolean[] gameBoard)
    {
        this.gameBoard = gameBoard;
    }
}
