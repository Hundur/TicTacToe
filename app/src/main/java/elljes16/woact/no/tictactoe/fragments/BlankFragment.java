package elljes16.woact.no.tictactoe.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import elljes16.woact.no.R;

public class BlankFragment extends Fragment
{


    public BlankFragment()
    {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

}
