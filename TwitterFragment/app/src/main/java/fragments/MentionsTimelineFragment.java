package fragments;

import android.app.Fragment;
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


public class MentionsTimelineFragment extends TweetsListFragment {
    private TwitterClient client;
    private static String userName;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApp.getRestClient();// singleton client
        populateTimeLine();
    }
    public static String getUserName(){
        return userName;
    }
    private void populateTimeLine() {
        client.getMentionsTimeLine(new JsonHttpResponseHandler() {
            //SUCCESS
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                //deserialize JSON
                Type listType = new TypeToken<List<Tweet>>() {}.getType();
                ArrayList<Tweet> temp = (new Gson().fromJson(response.toString(), listType));
                userName = temp.get(0).getUser().getScreenName();
                addAll(temp);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("Debug", responseString.toString());
            }
        });

    }
}
