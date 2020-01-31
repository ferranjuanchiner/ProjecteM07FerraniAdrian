package cat.copernic.ferranjuan.projectem07ferraniadrian.ui.send;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

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