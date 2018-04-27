package elljes16.woact.no.tictactoe.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import elljes16.woact.no.R;
import elljes16.woact.no.tictactoe.activities.PlayActivity;
import elljes16.woact.no.tictactoe.activities.StatsActivity;

public class PlayAgainFragment extends Fragment
{

    Button btnPlayAgain, btnToStats;

    public PlayAgainFragment()
    {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_play_again, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        initWidgets();
        initListeners();
    }

    private void initListeners()
    {
        btnPlayAgain.setOnClickListener((View v) -> {
            ((PlayActivity)getActivity()).resetGame();
        });

        btnToStats.setOnClickListener((View v) -> {
            Intent intent = new Intent(getActivity(), StatsActivity.class);
            startActivity(intent);
        });
    }

    private void initWidgets()
    {
        btnPlayAgain = (Button) getActivity().findViewById(R.id.btnPlayAgain);
        btnToStats = (Button) getActivity().findViewById(R.id.btnToStats);
    }
}
