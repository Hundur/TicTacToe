package elljes16.woact.no.tictactoe.objects;

import android.widget.ImageView;

import elljes16.woact.no.R;

public class TTTBot
{
    public TTTBot()
    {

    }

    // MAKE UNBEATABLE... or atleast random
    public void makeMove(GameBoard gameBoard, ImageView[] imgViewArray)
    {
        for(int i = 0; i < gameBoard.getGameBoard().length; i++)
        {
            if(gameBoard.checkIfPlayfieldValid(i))
            {
                gameBoard.getGameBoard()[i] = false;
                imgViewArray[i].setImageResource(R.drawable.player_two_piece);
                break;
            }
        }
    }
}
