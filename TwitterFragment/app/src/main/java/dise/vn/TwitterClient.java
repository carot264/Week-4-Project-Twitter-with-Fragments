package dise.vn;
import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;
import android.content.Context;

import com.loopj.android.http.*;
import com.codepath.oauth.OAuthBaseClient;

public class TwitterClient extends OAuthBaseClient {
	public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class; // Change this
	public static final String REST_URL = "https://api.twitter.com/1.1"; // Change this, base API URL
	public static final String REST_CONSUMER_KEY = "uFwaI7SSkccoofWwXP112SGNH";       // Change this
	public static final String REST_CONSUMER_SECRET = "2JttUKXVsAXT6jQMrGH1Ru9nlsLXdlbQOU0gZfm5K1siuwmDdJ"; // Change this
	public static final String REST_CALLBACK_URL = "oauth://TwitterBasicApp"; // Change this (here and in manifest)

	public TwitterClient(Context context) {
		super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
	}

	// CHANGE THIS
	// DEFINE METHODS for different API endpoints here
	public void getInterestingnessList(AsyncHttpResponseHandler handler) {
		String apiUrl = getApiUrl("?nojsoncallback=1&method=flickr.interestingness.getList");
		// Can specify query string params directly or through RequestParams.
		RequestParams params = new RequestParams();
		params.put("format", "json");
		client.get(apiUrl, params, handler);
	}
	//Method = endpoit
	//ex: HomeTimeline = gets us the home timeline
    public void getHomeTimeLine(int page, AsyncHttpResponseHandler handler){
		String apiUrl = getApiUrl("statuses/home_timeline.json");
		//Specify  the params
		RequestParams params = new RequestParams();
		params.put("count",25);
		params.put("page",page);
		//excute the request
        getClient().get(apiUrl, params, handler);
	}

	public void getMentionsTimeLine(AsyncHttpResponseHandler handler) {
		String apiUrl = getApiUrl("statuses/mentions_timeline.json");
		//Specify  the params
		RequestParams params = new RequestParams();
		params.put("count",25);
		//params.put("page",0);
		//excute the request
		getClient().get(apiUrl, params, handler);

	}
	public void getUserTimeLine(String screenName, AsyncHttpResponseHandler handler) {
		String apiUrl = getApiUrl("statuses/user_timeline.json");
		//Specify  the params
		RequestParams params = new RequestParams();
		params.put("count",25);
		params.put("screen_name",screenName);
		//params.put("page",0);
		//excute the request
		getClient().get(apiUrl, params, handler);

	}
	public void getUserInfo(AsyncHttpResponseHandler handler){
		String apiUrl = getApiUrl("account/verify_credentials.json");
		getClient().get(apiUrl, null, handler);
	}
	//COMPOSE TWEET

	/* 1. Define the endpoint URL with getApiUrl and pass a relative path to the endpoint
	 * 	  i.e getApiUrl("statuses/home_timeline.json");
	 * 2. Define the parameters to pass to the request (query or body)
	 *    i.e RequestParams params = new RequestParams("foo", "bar");
	 * 3. Define the request method and make a call to the client
	 *    i.e client.get(apiUrl, params, handler);
	 *    i.e client.post(apiUrl, params, handler);
	 */
}