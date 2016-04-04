package dise.vn.activities;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.vn.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import dise.vn.TwitterApp;
import dise.vn.TwitterClient;
import dise.vn.models.Tweet;
import dise.vn.models.User;
import fragments.UserTimeLineFragment;

public class ProfileActivity extends AppCompatActivity {

    TwitterClient client;
    @Bind(R.id.tvHeaderFullName) TextView tvName;
    @Bind(R.id.tvFollower) TextView tvFollower;
    @Bind(R.id.tvFollowing)TextView tvFollowing;
    @Bind(R.id.ivProfileImage)ImageView ivProfileImage;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // This is the up button
            case android.R.id.home:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //get from activity to launch it
        String screenName = getIntent().getStringExtra("screen_name");
        if(savedInstanceState == null) {
            UserTimeLineFragment userFragment = UserTimeLineFragment.newInstance(screenName);
            //display fragment within activity
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flContainer, userFragment);
            ft.commit();
        }
        client = TwitterApp.getRestClient();
        //get User info
        client.getUserInfo(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //deserialize JSON
                //Type listType = new TypeToken<List<User>>() {}.getType();
                User user = (new Gson().fromJson(response.toString(), User.class));
                //User user = User.fromJSON(response);
                getSupportActionBar().setTitle(user.getScreenName());
                PopulateProfileHeader(user);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.i("Debug", responseString);
            }
        });
    }
    private void PopulateProfileHeader(User user) {
        tvName.setText(user.getName());
        tvFollower.setText(user.getFollowerCount()+ " Followers");
        tvFollowing.setText(user.getFollowingCount() + " Following");
        Picasso.with(this).load(user.getProfileImageUrl()).into(ivProfileImage);
    }

}
