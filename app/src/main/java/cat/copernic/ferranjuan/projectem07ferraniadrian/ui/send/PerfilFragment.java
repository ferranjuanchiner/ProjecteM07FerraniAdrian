package cat.copernic.ferranjuan.projectem07ferraniadrian.ui.send;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import cat.copernic.ferranjuan.projectem07ferraniadrian.R;

public class PerfilFragment extends Fragment {

    private SendViewModel sendViewModel;
    Switch sw;
    String espanol;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_send, container, false);
        final TextView textView = root.findViewById(R.id.text_send);
        sendViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        espanol = root.getResources().getString(R.string.es);
        sw = root.findViewById(R.id.swColor);
        Spinner spinner = (Spinner) root.findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        AdapterView.OnItemSelectedListener mListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             if   (espanol == parent.getSelectedItem().toString()){
                 Locale locale = new Locale("es");

                 Locale.setDefault(locale);
                 Toast.makeText(getActivity(),"español",Toast.LENGTH_SHORT);
             }
                Toast.makeText(getActivity(),"no español",Toast.LENGTH_SHORT);
            } //still never shows up in toast

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(),"nada",Toast.LENGTH_SHORT);
            }};
        spinner.setOnItemSelectedListener(mListener);
        return root;
    }

    public void onClick(View view) {
        final int uiModeNight;
        if (view.getId()== R.id.swColor){
            if (sw.isChecked()){
                uiModeNight = Configuration.UI_MODE_NIGHT_YES;
            }else {
                uiModeNight= Configuration.UI_MODE_NIGHT_NO;
            }
        }
    }
}