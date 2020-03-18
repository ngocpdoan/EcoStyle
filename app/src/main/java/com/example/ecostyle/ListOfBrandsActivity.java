package com.example.ecostyle;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListOfBrandsActivity extends AppCompatActivity {
    private ListView listView;
    private BrandsAdapter brandAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listofbrands);

        listView = (ListView) findViewById(R.id.brands_list);
        final ArrayList<Brands> brandList = new ArrayList<>();
        brandList.add(new Brands(R.drawable.womn, "womn.", "$-$$", 2));
        brandList.add(new Brands(R.drawable.pact, "Pact", "$", 1));
        brandList.add(new Brands(R.drawable.reformation, "Reformation", "$$$", 10));
        brandList.add(new Brands(R.drawable.everlane, "Everlane", "$-$$", 4));
        brandList.add(new Brands(R.drawable.boden, "Boden", "$$-$$$", 9));
        brandList.add(new Brands(R.drawable.able, "Able", "$-$$", 3));
        brandList.add(new Brands(R.drawable.tradlands, "Tradlands", "$$-$$$", 8));
        brandList.add(new Brands(R.drawable.outer, "Outerknown", "$$-$$$", 7));
        brandList.add(new Brands(R.drawable.pata, "Patagonia", "$$", 5));
        brandList.add(new Brands(R.drawable.madewell, "Madewell", "$$", 6));

        brandAdapter = new BrandsAdapter(this, brandList);
        listView.setAdapter(brandAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (brandList.get(position).getCost()== 1)
                {
                    Intent i = new Intent(getApplicationContext(), MyWebView.class);
                    i.putExtra("cost", brandList.get(position).getCost());
                    startActivity(i);
                }
                else if (brandList.get(position).getCost()== 2)
                {
                    Intent i = new Intent(getApplicationContext(), MyWebView.class);
                    i.putExtra("cost", brandList.get(position).getCost());
                    startActivity(i);
                }
                else if (brandList.get(position).getCost()== 3)
                {
                    Intent i = new Intent(getApplicationContext(), MyWebView.class);
                    i.putExtra("cost", brandList.get(position).getCost());
                    startActivity(i);
                }
                else if (brandList.get(position).getCost()== 4)
                {
                    Intent i = new Intent(getApplicationContext(), MyWebView.class);
                    i.putExtra("cost", brandList.get(position).getCost());
                    startActivity(i);
                }
                else if (brandList.get(position).getCost()== 5)
                {
                    Intent i = new Intent(getApplicationContext(), MyWebView.class);
                    i.putExtra("cost", brandList.get(position).getCost());
                    startActivity(i);
                }
                else if (brandList.get(position).getCost()== 6)
                {
                    Intent i = new Intent(getApplicationContext(), MyWebView.class);
                    i.putExtra("cost", brandList.get(position).getCost());
                    startActivity(i);
                }
                else if (brandList.get(position).getCost()== 7)
                {
                    Intent i = new Intent(getApplicationContext(), MyWebView.class);
                    i.putExtra("cost", brandList.get(position).getCost());
                    startActivity(i);
                }
                else if (brandList.get(position).getCost()== 8)
                {
                    Intent i = new Intent(getApplicationContext(), MyWebView.class);
                    i.putExtra("cost", brandList.get(position).getCost());
                    startActivity(i);
                }
                else if (brandList.get(position).getCost()== 9)
                {
                    Intent i = new Intent(getApplicationContext(), MyWebView.class);
                    i.putExtra("cost", brandList.get(position).getCost());
                    startActivity(i);
                }
                else if (brandList.get(position).getCost()== 10)
                {
                    Intent i = new Intent(getApplicationContext(), MyWebView.class);
                    i.putExtra("cost", brandList.get(position).getCost());
                    startActivity(i);
                }
                else {
                    Intent i = new Intent(getApplicationContext(), MyWebView.class);
                    i.putExtra("cost", brandList.get(position).getCost());
                    startActivity(i);
                }
            }
        });


        findViewById(R.id.top_rated).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.shuffle(brandList);
                brandAdapter.notifyDataSetChanged();
            }
        });

        findViewById(R.id.aToZ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(brandList, new Comparator<Brands>() {
                    @Override
                    public int compare(Brands o, Brands o2) {
                        return o.getNameBrand().compareTo(o2.getNameBrand());
                    }
                });
                brandAdapter.notifyDataSetChanged();
            }
        });

        findViewById(R.id.lowToHigh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(brandList, new Comparator<Brands>() {
                    @Override
                    public int compare(Brands o, Brands o2) {
                        return o.getCost() - o2.getCost();
                    }
                });
                brandAdapter.notifyDataSetChanged();
            }
        });

        findViewById(R.id.highToLow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(brandList, new Comparator<Brands>() {
                    @Override
                    public int compare(Brands o, Brands o2) {
                        return o.getCost() - o2.getCost();
                    }

                });
                Collections.reverse(brandList);
                brandAdapter.notifyDataSetChanged();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                brandAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}
