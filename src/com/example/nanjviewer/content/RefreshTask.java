
package com.example.nanjviewer.content;

import java.io.IOException;
import java.util.List;

import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.util.Log;

import com.example.nanjviewer.ItemFragment;
import com.example.nanjviewer.content.FeedContent.FeedItem;

/**
 * @author du
 */
public class RefreshTask extends AsyncTask<String, Integer, Integer> {
    private static final String TAG = RefreshTask.class.getSimpleName();

    private final static String api_url = "http://183.181.168.21/api/nanj.cgi";

    private ItemFragment mItemFragment;
    int statusCode = -1;
    String response = null;
    private FeedResult feed_result;

    @Override
    protected Integer doInBackground(String... params) {
        // TODO Auto-generated method stub
        Log.d(TAG, "start requests");
        try {
            HttpPost req = new HttpPost(api_url);
            DefaultHttpClient client = new DefaultHttpClient();
            HttpResponse httpResponse = client.execute(req);
            // obtain status code
            statusCode = httpResponse.getStatusLine().getStatusCode();
            // obtain response
            HttpEntity entity = httpResponse.getEntity();
            response = EntityUtils.toString(entity);
            Log.d(TAG, "resultJson = " + response);
            feed_result = JSON.decode(response, FeedResult.class);
            return statusCode;
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Integer statusCode) {
        if(feed_result == null){
            return;
        }
        feed_result.show();
        FeedContent.clear();
        for (int i = 0; i < feed_result.size(); i++) {
            Log.d(TAG, "add " + feed_result.getItem(i));
            FeedContent.addItem(feed_result.getItem(i));
        }
        Log.d(TAG, "FeedContent size = " + FeedContent.ITEMS.size());
        this.mItemFragment.refresh();
    }

    class FeedResult {
        int resultCode;
        String msg;
        List<FeedItem> entries;

        public int getResultCode() {
            return resultCode;
        }

        public void setResultCode(int resultCode) {
            this.resultCode = resultCode;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public List<FeedItem> getEntries() {
            return entries;
        }

        public void setEntries(List<FeedItem> entries) {
            this.entries = entries;
        }

        public int size() {
            return entries.size();
        }

        public FeedItem getItem(int location) {
            return entries.get(location);
        }

        public void show() {
            Log.d(TAG, "resultCode = " + resultCode);
            Log.d(TAG, "msg = " + msg);
            for (int i = 0; i < entries.size(); i++) {
                Log.d(TAG, String.format("item[%d] = %s", i, entries.get(i).title));
            }
        }

    }

    public void setItemFragment(ItemFragment itemFragment) {
        // TODO Auto-generated method stub
        mItemFragment = itemFragment;
    }
    
}
