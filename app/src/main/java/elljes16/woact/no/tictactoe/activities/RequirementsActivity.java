package elljes16.woact.no.tictactoe.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import elljes16.woact.no.R;
import elljes16.woact.no.tictactoe.localdatabase.UsersDataSource;

public class RequirementsActivity extends AppCompatActivity
{
    private ListView lstUsers;
    private Button btnLocalDatabase, btnMenu;
    private UsersDataSource dataSource;
    private ArrayAdapter<String> usersAdapter;
    private int usersAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requirements);

        dataSource = new UsersDataSource(this);
        dataSource.open();

        initWidgets();
        initListeners();
        initUsersList();
        downloadAndLoadImage();
    }

    private void downloadAndLoadImage()
    {
        new AsyncTask<Void, Void, String>()
        {
            @Override
            protected String doInBackground(final Void... params)
            {
                try
                {
                    HttpURLConnection connection = (HttpURLConnection) new URL("https://b.thumbs.redditmedia.com/9wH4sGk0rpHfnJigTkJbP8ENoPuAajA5e2-NvBV6wXc.jpg").openConnection();

                    Bitmap bmp = BitmapFactory.decodeStream(connection.getInputStream());
                    ImageView imgAww = (ImageView) findViewById(R.id.imgAww);
                    imgAww.setImageBitmap(bmp);
                    return null;
                }
                catch (IOException e)
                {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(final String result)
            {

            }
        }.execute();
    }

    private void initUsersList()
    {
        List<String> users = dataSource.getAllUsernames();

        usersAmount = users.size();

        usersAdapter = new ArrayAdapter<>(RequirementsActivity.this, android.R.layout.simple_list_item_1, users);
        lstUsers.setAdapter(usersAdapter);
    }

    private void initListeners()
    {
        btnLocalDatabase.setOnClickListener((View v) -> {
            usersAdapter.add("User" + usersAmount);
            dataSource.createUser("User" + usersAmount);
            usersAmount++;
        });

        btnMenu.setOnClickListener((View v) -> {
            dataSource.close();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void initWidgets()
    {
        lstUsers = (ListView) findViewById(R.id.lstUsers);
        btnLocalDatabase = (Button) findViewById(R.id.btnLocalDatabase);
        btnMenu = (Button) findViewById(R.id.btnMenu);
    }
}
