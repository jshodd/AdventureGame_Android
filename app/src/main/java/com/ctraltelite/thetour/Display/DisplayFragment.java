package com.ctraltelite.thetour.Display;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ctraltelite.thetour.Game.Command;
import com.ctraltelite.thetour.Game.CommandReader;
import com.ctraltelite.thetour.Game.TheTour;
import com.ctraltelite.thetour.MainActivity;
import com.ctraltelite.thetour.R;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by jshodd on 4/11/16.
 */
public class DisplayFragment extends Fragment {
    private EditText inputText;
    private TextView outputText;
    private ImageView mImageView;


    public DisplayFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_display, container,false);
        outputText = (TextView) view.findViewById(R.id.output_text);
        outputText.setText(MainActivity.getGame().getCurrentRoom().getDescription());
        mImageView = (ImageView) view.findViewById(R.id.image_view);


        inputText = (EditText) view.findViewById(R.id.input_text);
        inputText.setMovementMethod(new ScrollingMovementMethod());
        inputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO){
                    if (inputText.getText().length() != 0) {
                        ByteArrayInputStream bias = new ByteArrayInputStream(inputText.getText().toString().getBytes());
                        CommandReader cr = new CommandReader();
                        Command c = null;
                        try {
                            c = cr.readCommand(bias);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        MainActivity.getGame().performCommand(c);
                        inputText.setText("");

                        outputText.setText(MainActivity.getGame().getOS().toString());
                        mImageView.setImageResource(MainActivity.getGame().getCurrentRoom().getImage());
                        Log.w("image id",""+MainActivity.getGame().getCurrentRoom().getImage());
                        Log.w("thetour", "sent to output");
                    }

                }
                return false;
            }
        });

        return view;
    }



}

