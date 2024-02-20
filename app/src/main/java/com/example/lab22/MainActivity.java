package com.example.lab22;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Button btnThem;
    private Button btnXoa;

    ArrayList<Name> users = new ArrayList<Name>();



    SparseBooleanArray mCheckStates ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.listView = (ListView) findViewById(R.id.list_item);
        this.btnThem = (Button) findViewById(R.id.button2);
        this.btnXoa = (Button) findViewById(R.id.button3);
        this.listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        Name Mot = new Name(1,"Một","34567",false);
        Name Hai = new Name(2,"Hai","0987",true);
        Name Ba = new Name(3,"Ba","56789",true);

         users.add(Mot);
         users.add(Hai);
         users.add(Ba);
        ArrayAdapter<Name> arrayAdapter
                = new ArrayAdapter<Name>(this, android.R.layout.simple_list_item_multiple_choice , users);

        this.listView.setAdapter(arrayAdapter);

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemClick: " + position);
                CheckedTextView v = (CheckedTextView) view;
                boolean currentCheck = v.isChecked();
                Name user = (Name) listView.getItemAtPosition(position);
                user.setActive(!currentCheck);
            }
        });
        this.btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        this.initListViewData();
        this.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray checked = listView.getCheckedItemPositions();
//                for (int i = 0; i < listView.getCount(); i++){
//
//                    if (checked.get(i)==true)
//                    {
//                        users.remove(i);
//
//                    }
//                    arrayAdapter.notifyDataSetChanged();
//
//                }
//                listView.clearChoices();
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                for(int i=listView.getCount()-1; i >= 0; i--){
                    if(checked.get(i)){
//                        alertDialogBuilder.setMessage("Bán có muốn xóa");
//
                        users.remove(users.get(i));
                    }
                }
                checked.clear();
                arrayAdapter.notifyDataSetChanged();
            }
        });

        //this.initListViewData();

    }

        private void initListViewData()  {

            // android.R.layout.simple_list_item_checked:
            // ListItem is very simple (Only one CheckedTextView)

            for(int i=0;i< users.size(); i++ )  {
                this.listView.setItemChecked(i, users.get(i).isActive());
            }
        }


    }
