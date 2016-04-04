package dise.vn.models;

import com.google.gson.annotations.SerializedName;

public class Tweet {
    @SerializedName("text")
    private String body;
    private long uid;// unique for the tweet
    @SerializedName("created_at")
    private String createAt;
    @SerializedName("user")
    private User user;
    @SerializedName("entities")
    private Entity entity;

    @SerializedName("favourites_count")
    private int favorite_count;
     @SerializedName("retweet_count")
    private int retweet_count;

    public int getFavorite_count() {
        return favorite_count;
    }

    public void setFavorite_count(int favorite_count) {
        this.favorite_count = favorite_count;
    }

    public int getRetweet_count() {
        return retweet_count;
    }

    public void setRetweet_count(int retweet_count) {
        this.retweet_count = retweet_count;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @SerializedName("id")
    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }




    /*public static Tweet fromJSON(JSONObject jsonObject){
        Tweet tweet = new Tweet();
        //Extract  the values  from the Json , store them
        try {
            tweet.body = jsonObject.getString("text");
            tweet.uid = jsonObject.getLong("id");
            tweet.createAt = jsonObject.getString("created_at");
            tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));

        }catch (JSONException e){
            e.printStackTrace();
        }
        return tweet;
    }
    public static ArrayList<Tweet> fromJSONArray(JSONArray array){

        ArrayList<Tweet> tweets = new ArrayList<>();
        //Extract  the values  from the Json , store them
        for(int i = 0; i < array.length(); i++){
            try {
              JSONObject tweetJSon = array.getJSONObject(i);
                Tweet tweet = Tweet.fromJSON(tweetJSon);
                if(tweet != null)
                    tweets.add(tweet);

            }catch (JSONException e){
                e.printStackTrace();
            }
        }

        return tweets;
    }*/

}
