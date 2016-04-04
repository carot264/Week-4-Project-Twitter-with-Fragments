package dise.vn.adapters;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.vn.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import dise.vn.models.Dimension;
import dise.vn.models.ImageSize;
import dise.vn.models.Media;
import dise.vn.models.Tweet;
import dise.vn.utils.Util;


public class TweetsArrayAdapter extends RecyclerView.Adapter<TweetsArrayAdapter.ViewHolder> {

    private List<Tweet> tweets;
    private Context context;

    public TweetsArrayAdapter(List<Tweet> tweetList) {
        tweets = tweetList;
    }

    @Override
    public TweetsArrayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_tweet, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TweetsArrayAdapter.ViewHolder holder, int position) {

        Tweet tweet = tweets.get(position);
        ImageView ivProfileImage = holder.ivProfileImage;
        TextView tvFullName = holder.tvFullName;
        TextView tvUserName = holder.tvUserName;
        TextView tvBody = holder.tvBody;
        TextView tvCreateAt = holder.tvCreateAt;
        ImageView ivBodyImage = holder.ivBodyImage;
        Button bTweet = holder.bTweet;
        Button bReTweet = holder.bReTweet;
        Button bFavourist = holder.bFavourist;

        tvFullName.setText(tweet.getUser().getName());
        tvUserName.setText(tweet.getUser().getScreenName());
        tvBody.setText(tweet.getBody());
        bReTweet.setText(""+ tweet.getRetweet_count());
        bFavourist.setText("" + tweet.getFavorite_count());


        if (tweet.getCreateAt() != "") {
            tvCreateAt.setText(Util.getRelativeTimeAgo(tweet.getCreateAt()));
        }
        ivProfileImage.setImageResource(android.R.color.transparent);
        Picasso.with(context).load(tweet.getUser().getProfileImageUrl()).into(ivProfileImage);

        ArrayList<Media> mediaList = tweet.getEntity().getMedia();
        if((mediaList!= null) && mediaList.size()  > 0) {

            Media media = mediaList.get(0);
            String urlImageBody = media.getUrlPhoto();
            if (urlImageBody != "") {

               ImageSize imageSizes = media.getImageSizes();
               ivBodyImage.getLayoutParams().height = imageSizes.getMedium().getHeight() ;
               ivBodyImage.getLayoutParams().width = imageSizes.getMedium().getWidth();
               Picasso.with(context).load(urlImageBody).into(ivBodyImage);
            }
            String urlBody = media.getUrlInBody();
            if(urlBody != ""){
                if(tvBody.getText().toString().contains(urlBody)){
                    String temp  = tvBody.getText().toString().replace(urlBody, "");
                    tvBody.setText(Html.fromHtml(temp +
                            " <a href=\"" +urlBody + "\"> "+ urlBody + "</a> "));
                    tvBody.setMovementMethod(LinkMovementMethod.getInstance());
                }

            }
        }
    }

    @Override
    public int getItemCount() {
        if (tweets == null) {
            return 0;
        }
        return tweets.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.ivProfileImage) ImageView ivProfileImage;
        @Bind(R.id.tvFullName) TextView tvFullName;
        @Bind(R.id.tvUserName) TextView tvUserName;
        @Bind(R.id.tvBody) TextView tvBody;
        @Bind(R.id.tvCreateAt) TextView tvCreateAt;
        @Bind(R.id.ivBodyImage) ImageView ivBodyImage;
        @Bind(R.id.bTweet)Button bTweet;
        @Bind(R.id.bReTweet)Button bReTweet;
        @Bind(R.id.bFavourist)Button bFavourist;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

}

