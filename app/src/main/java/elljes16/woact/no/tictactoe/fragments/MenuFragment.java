package elljes16.woact.no.tictactoe.fragments;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import elljes16.woact.no.R;
import elljes16.woact.no.tictactoe.activities.RequirementsActivity;
import elljes16.woact.no.tictactoe.activities.StatsActivity;

public class MenuFragment extends Fragment
{
    private Button btnSingle, btnMulti, btnHigh, btnRequirements;

    public MenuFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_menu, container, false);
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
        btnSingle.setOnClickListener((View v) -> {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentHolder, new SoloFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        btnMulti.setOnClickListener((View v) -> {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentHolder, new MultiFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        btnHigh.setOnClickListener((View v) -> {
            Intent intent = new Intent(getActivity(), StatsActivity.class);
            startActivity(intent);
        });

        btnRequirements.setOnClickListener((View v) -> {
            Intent intent = new Intent(getActivity(), RequirementsActivity.class);
            startActivity(intent);
        });
    }

    private void initWidgets()
    {
        btnSingle = (Button) getActivity().findViewById(R.id.btnSingle);
        btnMulti = (Button) getActivity().findViewById(R.id.btnMulti);
        btnHigh = (Button) getActivity().findViewById(R.id.btnHigh);
        btnRequirements = (Button) getActivity().findViewById(R.id.btnRequirements);
    }
}
