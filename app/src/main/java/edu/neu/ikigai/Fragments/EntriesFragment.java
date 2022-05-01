package edu.neu.ikigai.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.neu.ikigai.R;

public class EntriesFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String key = "-N0wZ7olzZ2lP42l6JNK";
        long timestamp = decode(key);

        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date date = new Date(timestamp);

        String test = dateFormat.format(date);
        Log.d("date:", date.toString() + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        Log.d("test", test + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        return inflater.inflate(R.layout.fragment_entries, container, false);
    }

    String PUSH_CHARS = "-0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz";
    public long decode(String id) {
        id = id.substring(0,8);
        long timestamp = 0;
        for (int i=0; i < id.length(); i++) {
            char c = id.charAt(i);
            timestamp = timestamp * 64 + PUSH_CHARS.indexOf(c);
        }
        return timestamp;
    }




}
