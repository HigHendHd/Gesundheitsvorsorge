package gvapp.diplomprojekt.at.gv_appandroid.Basisklassen;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import gvapp.diplomprojekt.at.gv_appandroid.DesignKlassen.DividerItemDecoration;
import gvapp.diplomprojekt.at.gv_appandroid.DownloadTasks.DownloadXmlTask;
import gvapp.diplomprojekt.at.gv_appandroid.R;

/**
 * Created by Dennis on 14.11.2015.
 */
public class Liste extends Fragment implements ListenAdapter.ClickListener, DownloadXmlTask.XmlDownloader {

    protected List retList;
    protected List<ListenEintrag> eintraege = new ArrayList<ListenEintrag>();
    protected RecyclerView.Adapter mAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressBar prgBar;

    public Liste() {
        // Required empty public constructor
    }

    public void setEintraege(InputStream result) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_liste, container, false);

        prgBar = (ProgressBar) view.findViewById(R.id.pbProgress);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        //mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ListenAdapter(eintraege);
        ((ListenAdapter) mAdapter).setClickListener(this);
        setmAdapter(mAdapter);

        return view;
    }

    private void dataLoaded() {
        mRecyclerView.setVisibility(View.VISIBLE);
        prgBar.setVisibility(View.INVISIBLE);
    }

    private void setmAdapter(RecyclerView.Adapter adapter) {
        mAdapter = adapter;
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void itemClicked(View v, int position) {

    }

    @Override
    public void xmlDownloaded(InputStream result) {

    }

    @Override
    public void fillData() {
        dataLoaded();
        eintraege.clear();
        try {
            eintraege.addAll(retList);
        } catch (Exception ex) {
            Snackbar.make(getView(), getActivity().getString(R.string.keininternet),
                    Snackbar.LENGTH_INDEFINITE).show();
        }
        mAdapter.notifyDataSetChanged();
    }
}
