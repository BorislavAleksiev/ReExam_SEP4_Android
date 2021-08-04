package view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sep4_android.R;

import java.util.ArrayList;
import java.util.List;

import adapter.SaunaAdapter;
import model.room.entity.Sauna.Sauna;
import viewmodel.SaunaViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SaunasFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SaunasFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SaunasFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SaunasFrag.
     */
    // TODO: Rename and change types and number of parameters
    private TextView textView;
    private Button bookingBtn,updateBtn;
    private SaunaViewModel mSauna;
    private RecyclerView recyclerView;
    private SaunaAdapter adapter;
    private List<Sauna> list;
    private ArrayList<Integer> imgList;


    public static SaunasFrag newInstance(String param1, String param2) {
        SaunasFrag fragment = new SaunasFrag();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saunas, container, false);
        recyclerView = view.findViewById(R.id.rViewSauna);
        mSauna = new ViewModelProvider(this).get(SaunaViewModel.class);


        bookingBtn = view.findViewById(R.id.btnBook1);
        updateBtn = view.findViewById(R.id.updateBtn);
        imgList = new ArrayList<>();

        imgList.add(R.drawable.sauna_1_c);
        imgList.add(R.drawable.sauna_2_c);
        imgList.add(R.drawable.sauna_3_c);
        imgList.add(R.drawable.sauna_4_c);
        imgList.add(R.drawable.sauna_5_c);
        imgList.add(R.drawable.sauna_6_c);
        imgList.add(R.drawable.sauna_7_c);
        imgList.add(R.drawable.sauna_1_c);
        imgList.add(R.drawable.sauna_2_c);


        mSauna.getAllSaunas().observe(getViewLifecycleOwner(), new Observer<List<Sauna>>() {
            @Override
            public void onChanged(List<Sauna> saunas) {
                list = saunas;
                initRecyclerView();
                update();
            }
        });

        initRecyclerView();

        return view;
    }

    public void initRecyclerView(){
        RecyclerView.LayoutManager linearLayoutMananger = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutMananger);
        adapter = new SaunaAdapter(getContext(),list,imgList);
        recyclerView.setAdapter(adapter);

    }
    public void update(){

        adapter = new SaunaAdapter(getContext(),list,imgList);
        recyclerView.setAdapter(adapter);
    }
}