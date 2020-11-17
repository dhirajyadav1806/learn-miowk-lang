package com.jeeduniya.miwokapp;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class FamilyClickListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), "open the list of family", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(v.getContext(), FamilyActivity.class);
//        startActivity(i);
    }
}
