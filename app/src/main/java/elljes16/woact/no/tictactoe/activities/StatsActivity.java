package elljes16.woact.no.tictactoe.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

import elljes16.woact.no.R;
import elljes16.woact.no.tictactoe.objects.Player;

public class StatsActivity extends AppCompatActivity
{
    private DatabaseReference myRef;
    private ListView lstStats;
    private Button btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        initWidgets();
        initListeners();
        initFirebase();
        loadData();
    }

    private void initFirebase()
    {
        myRef = FirebaseDatabase.getInstance().getReference("TicTacToe");
    }

    private void loadData()
    {
        myRef.child("Users").addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                ArrayList<Player> playerArray = new ArrayList<>();
                ArrayList<String> arrayList = new ArrayList<>();

                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    playerArray.add(ds.getValue(Player.class));
                }

                Collections.sort(playerArray);

                for(Player player : playerArray)
                {
                    arrayList.add(player.getName() +
                            " | W " + player.getWin() +
                            " | L " + player.getLoss() +
                            " | T " + player.getTie());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(StatsActivity.this, android.R.layout.simple_list_item_1, arrayList);
                lstStats.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });
    }

    private void initListeners()
    {
        btnMenu.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void initWidgets()
    {
        lstStats = (ListView) findViewById(R.id.lstStats);
        btnMenu = (Button) findViewById(R.id.btnMenu);
    }
}
