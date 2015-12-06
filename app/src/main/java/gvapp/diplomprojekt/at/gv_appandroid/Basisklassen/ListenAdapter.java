package gvapp.diplomprojekt.at.gv_appandroid.Basisklassen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import gvapp.diplomprojekt.at.gv_appandroid.DownloadTasks.DownloadImageTask;
import gvapp.diplomprojekt.at.gv_appandroid.R;

/**
 * Created by Dennis on 14.11.2015.
 */
public class ListenAdapter extends RecyclerView.Adapter<ListenAdapter.ViewHolder> {

    private List<ListenEintrag> list;
    private ClickListener clickListener;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListenAdapter(List<ListenEintrag> liste) {
        list = liste;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListenAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                 int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v, clickListener);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.tvTitle.setText(list.get(position).getLisTitel() + "");
        holder.tvSubtitle.setText(list.get(position).getLisUntertitel() + "");
        // show The Image

        holder.ivThumbnail.setImageResource(0);
        if (list.get(position).getThumbnailUrl() != null) {
            new DownloadImageTask(holder.ivThumbnail)
                    .execute(list.get(position).getThumbnailUrl());
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface ClickListener {
        void itemClicked(View v, int position);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView tvTitle, tvSubtitle;
        public View view;
        public ImageView ivThumbnail;
        ClickListener clickListener;

        public ViewHolder(View v, ClickListener clkLis) {
            super(v);
            view = v;
            clickListener = clkLis;
            v.setOnClickListener(this);
            tvTitle = (TextView) v.findViewById(R.id.tvListItemTitle);
            tvSubtitle = (TextView) v.findViewById(R.id.tvListItemSubTitle);
            ivThumbnail = (ImageView) v.findViewById(R.id.ivThumbnail);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.itemClicked(v, getAdapterPosition());
            }
        }
    }
}
