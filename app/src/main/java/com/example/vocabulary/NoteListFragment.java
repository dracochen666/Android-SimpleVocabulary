package com.example.vocabulary;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.vocabulary.module.Item;
import com.example.vocabulary.util.MyAdapter;
import com.example.vocabulary.util.MyDBOpenHelper;

import java.util.ArrayList;

public class NoteListFragment extends Fragment {

    private ListView listView;
    private ArrayList<Item> itemList;
    private MyAdapter<Item> mAdapter;
    private Button btn_compose;
    private MyDBOpenHelper mDBHelper;
    private SQLiteDatabase db;
    private Button btn_search, btn_content;

//    public ArrayList<Item> getSearchItem(String find) {
//        String sql = "select * from note where content=" + find + " order by time desc";
//
//    }

    public ArrayList<Item> getAllNotes() {
        String sql = "select * from note order by time desc ";
        ArrayList<Item> noteList = new ArrayList<>();
        Item note;
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            note = new Item();
            note.setContent(cursor.getString(cursor
                    .getColumnIndex("note")));
            note.setTime(cursor.getString(cursor
                    .getColumnIndex("time")));
            note.setId(cursor.getInt(cursor
                    .getColumnIndex("id")));
            noteList.add(note);
        }
        return noteList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_notelist, container, false);
        mDBHelper = new MyDBOpenHelper(view.getContext(), "my.db", null, 1);
        db = mDBHelper.getWritableDatabase();
        btn_compose = view.findViewById(R.id.btn_new);
        btn_search = view.findViewById(R.id.btn_search);
        btn_content = view.findViewById(R.id.btn_content);
        itemList = getAllNotes();
        listView = view.findViewById(R.id.main_list);
        mAdapter = new MyAdapter<Item>(itemList, R.layout.list_item) {
            @Override
            public void bindView(MyAdapter.ViewHolder holder, Item obj) {
                holder.setText(R.id.item_content, obj.getContent());
                holder.setText(R.id.item_time, obj.getTime());
            }
        };
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getContext(), SearchActivity.class));
            }
        });
        btn_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getContext(), ContentActivity.class));
            }
        });
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int h = getActivity().getWindowManager().getDefaultDisplay().getHeight();
                int w = getActivity().getWindowManager().getDefaultDisplay().getWidth();
                ComposeFragment fragment = new ComposeFragment();
                fragment.SetMethod(itemList.get(position).getId() + "", "update", itemList.get(position).getContent());
                if (h < w) {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.second_content, fragment, null)
                            .addToBackStack(null)
                            .commit();
                } else {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.first_content, fragment, null)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                db.execSQL("DELETE FROM note WHERE id = ?",
                        new String[]{itemList.get(position).getId().toString()});
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.first_content, new NoteListFragment())
                        .commit();
                return true;
            }
        });
        btn_compose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int h = getActivity().getWindowManager().getDefaultDisplay().getHeight();
                int w = getActivity().getWindowManager().getDefaultDisplay().getWidth();
                ComposeFragment fragment = new ComposeFragment();
                fragment.SetMethod("none", "insert", "");
                if (h < w) {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.second_content, fragment, null)
                            .addToBackStack(null)
                            .commit();
                } else {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.first_content, fragment, null)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
        return view;
    }


}
