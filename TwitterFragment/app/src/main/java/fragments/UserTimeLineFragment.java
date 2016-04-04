package fragments;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import dise.vn.TwitterApp;
import dise.vn.TwitterClient;
import dise.vn.models.Tweet;

public class UserTimeLineFragment extends TweetsListFragment {

    private TwitterClient client;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApp.getRestClient();// singleton client
        populateTimeLine();
    }
    public static UserTimeLineFragment newInstance(String screenName){
        UserTimeLineFragment userFragment = new UserTimeLineFragment();
        Bundle args = new Bundle();
        args.putString("screen_name", screenName);
        userFragment.setArguments(args);
        return userFragment;
    }
    private void populateTimeLine() {
        String screenName= getArguments().getString("screen_name");
        client.getUserTimeLine(screenName,new JsonHttpResponseHandler() {
            //SUCCESS
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                //deserialize JSON
                Type listType = new TypeToken<List<Tweet>>() {
                }.getType();
                ArrayList<Tweet> temp = (new Gson().fromJson(response.toString(), listType));
                addAll(temp);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("Debug", responseString.toString());
            }
        });

    }
}
