package elljes16.woact.no.tictactoe.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;

import elljes16.woact.no.R;
import elljes16.woact.no.tictactoe.fragments.BlankFragment;
import elljes16.woact.no.tictactoe.fragments.PlayAgainFragment;
import elljes16.woact.no.tictactoe.objects.GameBoard;
import elljes16.woact.no.tictactoe.objects.Player;
import elljes16.woact.no.tictactoe.objects.TTTBot;

public class PlayActivity extends AppCompatActivity
{
    private DatabaseReference myRef;
    private ImageView[] imgViewArray;
    private TextView txtPlayerOne, txtPlayerTwo, txtTimeCounter;
    private Player playerOne, playerTwo;
    private String nameOne, nameTwo;
    private Boolean gameMode, turnSwitch, gameActive;
    private GameBoard gameBoard;
    private TTTBot tttBot;
    private int timeCounter;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        initFirebase();
        initWidgets();
        initListeners();
        initTimer();
        initGameInformation();
    }

    public void resetGame()
    {
        gameBoard.resetGameBoard();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentHolderButtons, new BlankFragment());
        fragmentTransaction.commit();

        turnSwitch = true;
        gameActive = true;

        timeCounter = 0;

        for(int i = 0; i < imgViewArray.length; i++)
            imgViewArray[i].setImageResource(R.drawable.default_piece);
    }

    private void initFirebase()
    {
        myRef = FirebaseDatabase.getInstance().getReference("TicTacToe");
    }

    private void setOnClickPlayfield(ImageView imgView, int position)
    {
        imgView.setOnClickListener((View v) -> {
            if(gameActive)
            {
                if (gameBoard.checkIfPlayfieldValid(position))
                {
                    changePicture(imgView);
                    updateGame(position);
                }
            }
        });
    }

    private void changePicture(ImageView imgView)
    {
        //Depending on who's turn it is, switch the image to their respected piece
        if(turnSwitch)
           imgView.setImageResource(R.drawable.player_one_piece);
        else
            imgView.setImageResource(R.drawable.player_two_piece);
    }

    private void updateGame(int position)
    {
        gameBoard.updateBoard(position, turnSwitch);

        //If the player is not in the database, make a new player
        if(playerOne == null)
            playerOne = new Player(nameOne);
        if(playerTwo == null)
            playerTwo = new Player(nameTwo);

        //Opponents turn
        turnSwitch = !turnSwitch;

        checkWin();

        // AI's turn
        if(gameMode && gameActive)
        {
            /*
            for(int i = 0; i < gameBoard.getGameBoard().length; i++)
            {
                if(gameBoard.checkIfPlayfieldValid(i))
                {
                    gameBoard.getGameBoard()[i] = false;
                    changePicture(imgViewArray[i]);
                    break;
                }
            }
            */
            tttBot.makeMove(gameBoard, imgViewArray);

            checkWin();
            turnSwitch = !turnSwitch;
        }
    }

    private void checkWin()
    {
        //Check if someone won or if the GameBoard is full
        if(gameBoard.checkIfPlayerOneWon())
        {
            playerOne.matchWin();
            playerTwo.matchLoss();

            toast = Toast.makeText(this, playerOne.getName() + " wins!", Toast.LENGTH_LONG);
            toast.show();

            endGame();
        }
        else if(gameBoard.checkIfPlayerTwoWon())
        {
            playerOne.matchLoss();
            playerTwo.matchWin();

            toast = Toast.makeText(this, playerTwo.getName() + " wins!", Toast.LENGTH_LONG);
            toast.show();

            endGame();
        }
        else if(gameBoard.checkIfBoardIsFull())
        {
            playerOne.matchTie();
            playerTwo.matchTie();

            toast = Toast.makeText(this, "It's a tie!", Toast.LENGTH_LONG);
            toast.show();

            endGame();
        }
    }

    private void endGame()
    {
        gameActive = false;

        updateDatabase(playerOne);
        updateDatabase(playerTwo);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentHolderButtons, new PlayAgainFragment());
        fragmentTransaction.commit();
    }

    private void updateDatabase(Player player)
    {
        myRef.child("Users").child(player.getName()).setValue(player);
    }

    private void initGameInformation()
    {
        nameOne = getIntent().getStringExtra("PlayerOneName");
        nameTwo = getIntent().getStringExtra("PlayerTwoName");

        gameBoard = new GameBoard();

        turnSwitch = true;
        gameActive = true;

        setPlayerOne(nameOne);
        setPlayerTwo(nameTwo);

        gameMode = getIntent().getBooleanExtra("GameMode", true);

        if(gameMode)
            tttBot = new TTTBot();
    }

    private void setPlayerOne(String name)
    {
        setListener(name);
        txtPlayerOne.setText(name);
    }

    private void setPlayerTwo(String name)
    {
        setListener(name);
        txtPlayerTwo.setText(name);
    }

    private void setListener(String name)
    {
        myRef.child("Users").child(name).addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                Player player = dataSnapshot.getValue(Player.class);

                if(player != null)
                {
                    if(player.getName().equals(nameOne))
                        playerOne = player;
                    else if(player.getName().equals(nameTwo))
                        playerTwo = player;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
            }
        });
    }

    private void initTimer()
    {
        timeCounter = 0;
        Timer t = new Timer();

        t.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run()
            {
                runOnUiThread((() ->
                {
                    if(gameActive)
                    {
                        txtTimeCounter.setText(timeCounter + "s");
                        timeCounter++;
                    }
                }));
            }
        },0,1000);
    }

    private void initWidgets()
    {
        txtPlayerOne = (TextView) findViewById(R.id.txtNameHolder1);
        txtPlayerTwo = (TextView) findViewById(R.id.txtNameHolder2);
        txtTimeCounter = (TextView) findViewById(R.id.txtGameTime);

        imgViewArray = new ImageView[9];

        imgViewArray[0] = (ImageView) findViewById(R.id.imgPlayfield1);
        imgViewArray[1] = (ImageView) findViewById(R.id.imgPlayfield2);
        imgViewArray[2] = (ImageView) findViewById(R.id.imgPlayfield3);
        imgViewArray[3] = (ImageView) findViewById(R.id.imgPlayfield4);
        imgViewArray[4] = (ImageView) findViewById(R.id.imgPlayfield5);
        imgViewArray[5] = (ImageView) findViewById(R.id.imgPlayfield6);
        imgViewArray[6] = (ImageView) findViewById(R.id.imgPlayfield7);
        imgViewArray[7] = (ImageView) findViewById(R.id.imgPlayfield8);
        imgViewArray[8] = (ImageView) findViewById(R.id.imgPlayfield9);
    }

    private void initListeners()
    {
        for(int i = 0; i < imgViewArray.length; i++)
            setOnClickPlayfield(imgViewArray[i], i);
    }
}
