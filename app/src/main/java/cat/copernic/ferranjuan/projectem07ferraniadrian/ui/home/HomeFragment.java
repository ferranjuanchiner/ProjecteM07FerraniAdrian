package cat.copernic.ferranjuan.projectem07ferraniadrian.ui.home;

import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cat.copernic.ferranjuan.projectem07ferraniadrian.Albumes;
import cat.copernic.ferranjuan.projectem07ferraniadrian.AlbumesAdapter;
import cat.copernic.ferranjuan.projectem07ferraniadrian.R;

public class HomeFragment extends Fragment {
    Spinner sp;
    private HomeViewModel homeViewModel;
    private RecyclerView mRecyclerView;
    private ArrayList<Albumes> mAlbumesData;
    private AlbumesAdapter mAdapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private cat.copernic.ferranjuan.projectem07ferraniadrian.HomeFragment.OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static cat.copernic.ferranjuan.projectem07ferraniadrian.HomeFragment newInstance(String param1, String param2) {
        cat.copernic.ferranjuan.projectem07ferraniadrian.HomeFragment fragment = new cat.copernic.ferranjuan.projectem07ferraniadrian.HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View rootView = inflater.inflate(R.layout.fragment_home, container, false);

       mRecyclerView = rootView.findViewById(R.id.recyclerView);
       RecyclerView.LayoutManager lm = new GridLayoutManager(getActivity(),2);
       mRecyclerView.setLayoutManager(lm);
       mAlbumesData = new ArrayList<>();
       mAdapter = new AlbumesAdapter(getActivity(),mAlbumesData);
       mRecyclerView.setAdapter(mAdapter);
        // Inflate the layout for this fragment
        initializeData();
        return inflater.inflate(R.layout.fragment_home,container,false);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private void initializeData() {
        // Get the resources from the XML file.
        String[] albumesList = getResources()
                .getStringArray(R.array.albums_titles);


        // Clear the existing data (to avoid duplication).
        mAlbumesData.clear();

        // Create the ArrayList of Sports objects with titles and
        // information about each sport.
        for(int i=0;i<albumesList.length;i++){
            TypedArray sportsImageResources =
                    getResources().obtainTypedArray(R.array.albumes_images);
            mAlbumesData.add(new Albumes(albumesList[i], sportsImageResources.getResourceId(i,0)));
            sportsImageResources.recycle();

        }

        // Notify the adapter of the change.
        mAdapter.notifyDataSetChanged();
    }

    public void resetSports(View view) {
        initializeData();
    }
}