package elljes16.woact.no.tictactoe.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import elljes16.woact.no.R;
import elljes16.woact.no.tictactoe.activities.PlayActivity;

public class SoloFragment extends Fragment
{
    private EditText edtPlayerOne;
    private Button btnPlay;

    public SoloFragment()
    {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_solo, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        initWidgets();
        initListeners();
    }

    public void initWidgets()
    {
        edtPlayerOne = (EditText) getActivity().findViewById(R.id.edtSoloPlayerOne);
        btnPlay = (Button) getActivity().findViewById(R.id.btnSoloPlay);
    }

    public void initListeners()
    {
        edtPlayerOne.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                edtPlayerOne.setText("");
                return false;
            }
        });

        btnPlay.setOnClickListener((View v) -> {
            Intent intent = new Intent(getActivity(), PlayActivity.class);
            String playerOneName = edtPlayerOne.getText().toString();

            intent.putExtra("GameMode", true);
            intent.putExtra("PlayerOneName", playerOneName);
            intent.putExtra("PlayerTwoName", "TTTBot");
            startActivity(intent);
        });
    }
}
