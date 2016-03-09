package bartoszsawaniuk.braintriinterviewproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import bartoszsawaniuk.braintriinterviewproject.MainActivity;
import bartoszsawaniuk.braintriinterviewproject.R;
import bartoszsawaniuk.braintriinterviewproject.models.TumblrPost;

/**
 * Created by bartoszsawaniuk on 05/03/16.
 */
public class TumblrListAdapter extends ArrayAdapter<TumblrPost> {

    private ArrayList<TumblrPost> list;
    private MainActivity mContext;

    public TumblrListAdapter(MainActivity context, ArrayList<TumblrPost> list) {
        super(context, -1, list);
        this.list = list;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public TumblrPost getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        TumblrPost post = list.get(position);

        View view = convertView;

        if(view == null){

            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listrow_view, null);

            holder = new ViewHolder();

            holder.textView = (TextView) view.findViewById(R.id.view);
            holder.view = (WebView) view.findViewById(R.id.web);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        if(post.getSlug() != null)
            holder.textView.setText(post.getLinkText());
        if(post.getPhotoURL75() != null)
            holder.view.loadUrl(post.getPhotoURL75());

        return view;
    }

    static class ViewHolder{
        WebView view;
        TextView textView;
    }
}
