package elljes16.woact.no.tictactoe.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import elljes16.woact.no.R;
import elljes16.woact.no.tictactoe.fragments.MenuFragment;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMenuFragment();
    }

    private void initMenuFragment()
    {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentHolder, new MenuFragment());
        fragmentTransaction.commit();
    }
}
