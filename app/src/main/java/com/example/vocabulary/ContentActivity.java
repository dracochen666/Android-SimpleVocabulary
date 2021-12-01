package com.example.vocabulary;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ContentActivity extends AppCompatActivity {
    private static final String TAG = "ContentActivity";
    private Button btnAdd, btnDelete, btnUpdate, btnSearch;

    private String newId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_content);
//        btnAdd = this.findViewById(R.id.btn_add);
//        btnDelete = this.findViewById(R.id.btn_delete);
//        btnUpdate = this.findViewById(R.id.btn_update);
//        btnSearch = this.findViewById(R.id.btn_search_find);
//        btnAdd.setOnClickListener(this);
//        btnDelete.setOnClickListener(this);
//        btnUpdate.setOnClickListener(this);
////        btnSearch.setOnClickListener(this);
    }

//    @Override
//    public void onClick(View v) {
//        Uri uri;
//        ContentValues values;
//        switch (v.getId()) {
//            case R.id.btn_add:
//                uri = Uri.parse("content://com.example.iflab.rickygyy.notebook/note");
//                values = new ContentValues();
//                values.put("note", "content");
//                values.put("time", "1111/22/33");
//                Uri newUri = getContentResolver().insert(uri, values);
//                newId = newUri.getPathSegments().get(1);
//                break;
//            case R.id.btn_delete:
//                uri = Uri.parse("content://com.example.iflab.rickygyy.notebook/note" + newId);
//                getContentResolver().delete(uri, null, null);
//                break;
//            case R.id.btn_update:
//                uri = Uri.parse("content://com.example.iflab.rickygyy.notebook/note" + newId);
//                values = new ContentValues();
//                values.put("note", "new_content");
//                values.put("time", "3333/22/11");
//                getContentResolver().update(uri, values, null, null);
//                break;
//            case R.id.btn_search_find:
//                uri = Uri.parse("content://com.example.iflab.rickygyy.notebook/note");
//                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
//                if (cursor != null) {
//                    while (cursor.moveToNext()) {
//                        String note = cursor.getString(cursor.getColumnIndex("note"));
//                        String time = cursor.getString(cursor.getColumnIndex("time"));
//                        Log.d(TAG, "note: " + note);
//                        Log.d(TAG, "time: " + time);
//
//                    }
//                }
//                break;
//        }
//    }
}
