package fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.apps.vn.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import dise.vn.TwitterApp;
import dise.vn.TwitterClient;
import dise.vn.activities.ProfileActivity;
import dise.vn.adapters.TweetsArrayAdapter;
import dise.vn.models.Tweet;
import dise.vn.utils.EndlessRecyclerViewScrollListener;

public class TweetsListFragment extends Fragment {

    private ArrayList<Tweet> tweets;
    private TweetsArrayAdapter aTweets;
    private TwitterClient client;
    @Bind(R.id.rvTweet) RecyclerView rvTweets;


    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tweets_list,parent,false);
        ButterKnife.bind(this, v);
        //rvTweets = (RecyclerView) v.findViewById(R.id.rvTweet);
        rvTweets.setAdapter(aTweets);
        //rvTweets.setLayoutManager(new LinearLayoutManager(getActivity()));

        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        //enable optimizations if all item views are of the same height and width for significantly smoother scrolling
        rvTweets.setHasFixedSize(true);
        //reflow item
        gridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        // Attach the layout manager to the recycler view
        rvTweets.setLayoutManager(gridLayoutManager);

        // Add the scroll listener
        rvTweets.addOnScrollListener(new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                populateTimeLine(page);
            }

        });

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tweets = new ArrayList<>();
        aTweets = new TweetsArrayAdapter(tweets);
        client = TwitterApp.getRestClient();// singleton client
    }
    public void addAll(List<Tweet> tweet){
        tweets.addAll(tweet);
        int curSize = aTweets.getItemCount();
        aTweets.notifyItemRangeInserted(curSize, tweets.size());
    }
    public void populateTimeLine(int page) {

        client.getHomeTimeLine(0, new JsonHttpResponseHandler() {
            //SUCCESS
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                //deserialize JSON
                Type listType = new TypeToken<List<Tweet>>() {
                }.getType();
                ArrayList<Tweet> temp = (new Gson().fromJson(response.toString(), listType));
                //Log.i("debug",response.toString());
                addAll(temp);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("Debug", responseString.toString());
            }
        });

    }
}
