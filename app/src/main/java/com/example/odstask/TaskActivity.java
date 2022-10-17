package com.example.odstask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Filter;

public class TaskActivity extends AppCompatActivity {


    Spinner countrySpinner;
    GridView gridView;
    EditText search;

    CountryModel countryModel;
    CountryAdapter countryAdapter;
    List<CountryModel> countryModelList=new ArrayList<>();

    StateModel stateModelIndia,stateModelchina;
    StateAdapter stateAdapter;
    List<StateModel> stateModelList=new ArrayList<>();

    List<StateModel> filterlist=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search=findViewById(R.id.edit_view);
         /*autoCompleteTextView=findViewById(R.id.auto_complete);
        arrayAdapter=new ArrayAdapter<>(this, android.R.layout.select_dialog_item,States);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(arrayAdapter);*/

        countrySpinner=findViewById(R.id.Countryspinner);
        gridView=findViewById(R.id.gridView);

        /*stateAdapter=new StateAdapter(this,stateModelList);
        gridView.setAdapter((ListAdapter) stateModelList);*/


        countryAdapter=new CountryAdapter(this,countryModelList);
        countrySpinner.setAdapter(countryAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(TaskActivity.this, ""+stateModelList.get(i).getStatecm()+"\n"+
                        stateModelList.get(i).getStatename(), Toast.LENGTH_SHORT).show();
            }
        });
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filterlist.clear();

                if (s.toString().isEmpty()){
                    gridView.setAdapter(new StateAdapter(getApplicationContext(),stateModelList));
                    stateAdapter.notifyDataSetChanged();
                }
                else {
                    Filter(s.toString());
                }

            }
        });

        loadCountries();
        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                Toast.makeText(TaskActivity.this, ""+countryModelList.get(i).getName(), Toast.LENGTH_SHORT).show();


                if(countryModelList.get(i).getName()=="India")
                {
                    indiaStates();
                }
                else if(countryModelList.get(i).getName()=="China")
                {
                    chinaStates();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void Filter(String text)
    {
        for (StateModel post:stateModelList)
        {
            if (post.getStatename().toLowerCase(Locale.ROOT).contains(text)){
                filterlist.add(post);
            }
        }
        gridView.setAdapter(new StateAdapter(getApplicationContext(),filterlist));
        stateAdapter.notifyDataSetChanged();
    }

    private void chinaStates() {

        stateModelList.clear();

        stateModelchina=new StateModel("CHINA STATE 1","CHINA CM1", R.drawable.img_7);
        stateModelList.add(stateModelchina);
        stateModelchina=new StateModel("CHINA STATE 2","CHINA CM2", R.drawable.img_7);
        stateModelList.add(stateModelchina);
        stateModelchina=new StateModel("CHINA STATE 3","CHINA CM3", R.drawable.img_7);
        stateModelList.add(stateModelchina);
        stateModelchina=new StateModel("CHINA STATE 4","CHINA CM4", R.drawable.img_7);
        stateModelList.add(stateModelchina);
        stateModelchina=new StateModel("CHINA STATE 5","CHINA CM5", R.drawable.img_7);
        stateModelList.add(stateModelchina);

        stateAdapter=new StateAdapter(getApplicationContext(),stateModelList);
        gridView.setAdapter(stateAdapter);
    }

    private void indiaStates() {
        stateModelList.clear();
        stateModelIndia=new StateModel("TELANGANA","CM1", R.drawable.img);
        stateModelList.add(stateModelIndia);
        stateModelIndia=new StateModel("AP","CM2", R.drawable.img_2);
        stateModelList.add(stateModelIndia);
        stateModelIndia=new StateModel("TAMILNADU","CM3", R.drawable.img_3);
        stateModelList.add(stateModelIndia);
        stateModelIndia=new StateModel("KERALA","CM4", R.drawable.img_4);
        stateModelList.add(stateModelIndia);
        stateModelIndia=new StateModel("KARNATAKA","CM5", R.drawable.img_5);
        stateModelList.add(stateModelIndia);

        stateAdapter=new StateAdapter(getApplicationContext(),stateModelList);
        gridView.setAdapter(stateAdapter);
    }

    private void loadCountries() {

        countryModel=new CountryModel(R.drawable.img_1,"India");
        countryModelList.add(countryModel);
        countryModel=new CountryModel(R.drawable.img_6,"China");
        countryModelList.add(countryModel);

        countryAdapter.notifyDataSetChanged();
    }
}