package cat.copernic.ferranjuan.projectem07ferraniadrian.ui.home;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import cat.copernic.ferranjuan.projectem07ferraniadrian.Album;
import cat.copernic.ferranjuan.projectem07ferraniadrian.AlbumesAdapter;
import cat.copernic.ferranjuan.projectem07ferraniadrian.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    private RecyclerView mRecyclerView;
    private ArrayList<Album> mAlbumesData;
    private AlbumesAdapter mAdapter;
    Spinner spinneroder;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FirebaseAuth mAuth;
    DatabaseReference myRef;

    private cat.copernic.ferranjuan.projectem07ferraniadrian.ui.home.HomeFragment.OnFragmentInteractionListener mListener;

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
    public static cat.copernic.ferranjuan.projectem07ferraniadrian.ui.home.HomeFragment newInstance(String param1, String param2) {
        cat.copernic.ferranjuan.projectem07ferraniadrian.ui.home.HomeFragment fragment = new cat.copernic.ferranjuan.projectem07ferraniadrian.ui.home.HomeFragment();
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
       // afegirAlbums();
    }

    /*private void afegirAlbums() {
        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("albums/"+"/album1/");
        myRef.child("nom").setValue("Ghosts I–IV");
        myRef.child("img").setValue(R.drawable.ghostsi_iv);
        myRef.child("nomCanço").setValue("Ghosts I");
        myRef.child("canço").setValue(R.raw.ghosts_i);

        myRef = database.getReference("albums/"+"/album2/");
        myRef.child("nom").setValue("The Slip");
        myRef.child("img").setValue(R.drawable.theslip);
        myRef.child("nomCanço").setValue("Head Down");
        myRef.child("canço").setValue(R.raw.head_down);

        myRef = database.getReference("albums/"+"/album3/");
        myRef.child("nom").setValue("No Nations");
        myRef.child("img").setValue(R.drawable.nonations);
        myRef.child("nomCanço").setValue("I Should Be Born");
        myRef.child("canço").setValue(R.raw.i_should_be_born);

        myRef = database.getReference("albums/"+"/album4/");
        myRef.child("nom").setValue("Goverment Plates");
        myRef.child("img").setValue(R.drawable.governmentplates);
        myRef.child("nomCanço").setValue("Birds");
        myRef.child("canço").setValue(R.raw.birds);

        myRef = database.getReference("albums/"+"/album5/");
        myRef.child("nom").setValue("The Fall");
        myRef.child("img").setValue(R.drawable.thefall);
        myRef.child("nomCanço").setValue("Detroit");
        myRef.child("canço").setValue(R.raw.detroit);

        myRef = database.getReference("albums/"+"/album6/");
        myRef.child("nom").setValue("The Wired CD");
        myRef.child("img").setValue(R.drawable.wired);
        myRef.child("nomCanço").setValue("Now Get Busy");
        myRef.child("canço").setValue(R.raw.now_get_busy);

        myRef = database.getReference("albums/"+"/album7/");
        myRef.child("nom").setValue("No Love Deep Web");
        myRef.child("img").setValue(R.drawable.nolovedeepweb);
        myRef.child("nomCanço").setValue("Come Up And Get Me");
        myRef.child("canço").setValue(R.raw.come_up_and_g);

        myRef = database.getReference("albums/"+"/album8/");
        myRef.child("nom").setValue("The powers that b");
        myRef.child("img").setValue(R.drawable.thepowersthatb);
        myRef.child("nomCanço").setValue("Up my Sleeves");
        myRef.child("canço").setValue(R.raw.up_my_sleevesw);
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerView = rootView.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager lm = new GridLayoutManager(getActivity(), 2);
        GridLayoutManager gridLayout = new GridLayoutManager(getActivity(), 2);
        spinneroder = rootView.findViewById(R.id.spinner);
        mRecyclerView.setLayoutManager(gridLayout);
        mAlbumesData = new ArrayList<>();
        mAdapter = new AlbumesAdapter(getActivity(), mAlbumesData);
        mRecyclerView.setAdapter(mAdapter);
        initializeData();
        // Inflate the layout for this fragment
        /*if(spinneroder.getSelectedItem().toString().equals(R.string.orderza) ){
        initializeDataza();}
        else if(spinneroder.getSelectedItem().toString().equals(R.string.orderaz)){
            initializeDataaz();}
        else {
            initializeData();}*/
        return rootView;



    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private void initializeData() {
        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference( "albums/");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //dataSnapshot viene de leer "usuarios/" + user.getUid() + "/comidas"
                for (DataSnapshot datos : dataSnapshot.getChildren())
                {

                    String nombd = datos.child("nom").getValue(String.class);
                    String nomCançobd = datos.child("nomCanço").getValue(String.class);
                    int imgbd = datos.child("img").getValue(Integer.class);
                    int cançobd = datos.child("canço").getValue(Integer.class);
                    mAlbumesData.add(new Album(nombd,imgbd,nomCançobd,cançobd));
                }
                mAdapter = new AlbumesAdapter(getActivity(), mAlbumesData);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Failed to read value
                Log.w("perfil", "Failed to read value.", databaseError.toException());

            }
        });


        /*mAlbumesData.add(new Album("No Nations", R.drawable.nonations, new Album.Cancion("I Should Be Born", R.raw.i_should_be_born)));
        mAlbumesData.add(new Album("Goverment Plates", R.drawable.governmentplates, new Album.Cancion("Birds", R.raw.birds)));
        mAlbumesData.add(new Album("The Fall", R.drawable.thefall, new Album.Cancion("Detroit", R.raw.detroit)));
        mAlbumesData.add(new Album("The Wired CD", R.drawable.wired, new Album.Cancion("Now Get Busy", R.raw.now_get_busy)));
        mAlbumesData.add(new Album("No Love Deep Web", R.drawable.nolovedeepweb, new Album.Cancion("Come Up and Get Me", R.raw.come_up_and_g)));
        mAlbumesData.add(new Album("The Powers That B", R.drawable.thepowersthatb, new Album.Cancion("Up My Sleeves", R.raw.up_my_sleevesw)));*/
    }
    /*private void initializeDataaz() {

        mAlbumesData.add(new Album("Ghosts I–IV", R.drawable.ghostsi_iv, new Album.Cancion("Ghosts I",R.raw.ghosts_i)));
        mAlbumesData.add(new Album("Goverment Plates", R.drawable.governmentplates, new Album.Cancion("Birds", R.raw.birds)));
        mAlbumesData.add(new Album("No Love Deep Web", R.drawable.nolovedeepweb, new Album.Cancion("Come Up and Get Me", R.raw.come_up_and_g)));
        mAlbumesData.add(new Album("No Nations", R.drawable.nonations, new Album.Cancion("I Should Be Born", R.raw.i_should_be_born)));
        mAlbumesData.add(new Album("The Fall", R.drawable.thefall, new Album.Cancion("Detroit", R.raw.detroit)));
        mAlbumesData.add(new Album("The Powers That B", R.drawable.thepowersthatb, new Album.Cancion("Up My Sleeves", R.raw.up_my_sleevesw)));
        mAlbumesData.add(new Album("The Slip", R.drawable.theslip, new Album.Cancion("Head Down", R.raw.head_down)));
        mAlbumesData.add(new Album("The Wired CD", R.drawable.wired, new Album.Cancion("Now Get Busy", R.raw.now_get_busy)));
    }
    private void initializeDataza() {

        mAlbumesData.add(new Album("The Wired CD", R.drawable.wired, new Album.Cancion("Now Get Busy", R.raw.now_get_busy)));
        mAlbumesData.add(new Album("The Slip", R.drawable.theslip, new Album.Cancion("Head Down", R.raw.head_down)));
        mAlbumesData.add(new Album("The Powers That B", R.drawable.thepowersthatb, new Album.Cancion("Up My Sleeves", R.raw.up_my_sleevesw)));
        mAlbumesData.add(new Album("The Fall", R.drawable.thefall, new Album.Cancion("Detroit", R.raw.detroit)));
        mAlbumesData.add(new Album("No Nations", R.drawable.nonations, new Album.Cancion("I Should Be Born", R.raw.i_should_be_born)));
        mAlbumesData.add(new Album("No Love Deep Web", R.drawable.nolovedeepweb, new Album.Cancion("Come Up and Get Me", R.raw.come_up_and_g)));
        mAlbumesData.add(new Album("Goverment Plates", R.drawable.governmentplates, new Album.Cancion("Birds", R.raw.birds)));
        mAlbumesData.add(new Album("Ghosts I–IV", R.drawable.ghostsi_iv, new Album.Cancion("Ghosts I",R.raw.ghosts_i)));
    }*/




    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}