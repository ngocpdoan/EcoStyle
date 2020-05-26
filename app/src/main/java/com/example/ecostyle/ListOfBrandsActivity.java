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
        brandList.add(new Brands(R.drawable.reformation, "Reformation", "$$-$$$", 3));
        brandList.add(new Brands(R.drawable.everlane, "Everlane", "$-$$", 2));
        brandList.add(new Brands(R.drawable.boden, "Boden", "$$-$$$", 3));
        brandList.add(new Brands(R.drawable.able, "Able", "$-$$", 2));
        brandList.add(new Brands(R.drawable.tradlands, "Tradlands", "$$-$$$", 3));
        brandList.add(new Brands(R.drawable.outer, "Outerknown", "$$-$$$", 3));
        brandList.add(new Brands(R.drawable.pata, "Patagonia", "$-$$", 2));
        brandList.add(new Brands(R.drawable.madewell, "Madewell", "$-$$", 2));

        brandAdapter = new BrandsAdapter(this, brandList);
        listView.setAdapter(brandAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (brandList.get(position).getNameBrand().contains("Pact"))
                {
                    Intent i = new Intent(getApplicationContext(), MyWebView.class);
                    i.putExtra("name", brandList.get(position).getNameBrand());
                    startActivity(i);
                }
                else if (brandList.get(position).getNameBrand().contains("womn"))
                {
                    Intent i = new Intent(getApplicationContext(), MyWebView.class);
                    i.putExtra("name", brandList.get(position).getNameBrand());
                    startActivity(i);
                }
                else if (brandList.get(position).getNameBrand().contains("Able"))
                {
                    Intent i = new Intent(getApplicationContext(), MyWebView.class);
                    i.putExtra("name", brandList.get(position).getNameBrand());
                    startActivity(i);
                }
                else if (brandList.get(position).getNameBrand().contains("Everlane"))
                {
                    Intent i = new Intent(getApplicationContext(), MyWebView.class);
                    i.putExtra("name", brandList.get(position).getNameBrand());
                    startActivity(i);
                }
                else if (brandList.get(position).getNameBrand().contains("Patagonia"))
                {
                    Intent i = new Intent(getApplicationContext(), MyWebView.class);
                    i.putExtra("name", brandList.get(position).getNameBrand());
                    startActivity(i);
                }
                else if (brandList.get(position).getNameBrand().contains("Madewell"))
                {
                    Intent i = new Intent(getApplicationContext(), MyWebView.class);
                    i.putExtra("name", brandList.get(position).getNameBrand());
                    startActivity(i);
                }
                else if (brandList.get(position).getNameBrand().contains("Outerknown"))
                {
                    Intent i = new Intent(getApplicationContext(), MyWebView.class);
                    i.putExtra("name", brandList.get(position).getNameBrand());
                    startActivity(i);
                }
                else if (brandList.get(position).getNameBrand().contains("Tradlands"))
                {
                    Intent i = new Intent(getApplicationContext(), MyWebView.class);
                    i.putExtra("name", brandList.get(position).getNameBrand());
                    startActivity(i);
                }
                else if (brandList.get(position).getNameBrand().contains("Boden"))
                {
                    Intent i = new Intent(getApplicationContext(), MyWebView.class);
                    i.putExtra("name", brandList.get(position).getNameBrand());
                    startActivity(i);
                }
                else if (brandList.get(position).getNameBrand().contains("Reformation"))
                {
                    Intent i = new Intent(getApplicationContext(), MyWebView.class);
                    i.putExtra("name", brandList.get(position).getNameBrand());
                    startActivity(i);
                }
                else {
                    Intent i = new Intent(getApplicationContext(), MyWebView.class);
                    i.putExtra("name", brandList.get(position).getNameBrand());
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
                        if(o.getCost() - o2.getCost() != 0) {
                            return o.getCost() - o2.getCost();
                        }
                        else return o.getNameBrand().compareTo(o2.getNameBrand());


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
                        if(o.getCost() - o2.getCost() != 0) {
                            return o.getCost() - o2.getCost();
                        }
                        else return o.getNameBrand().compareTo(o2.getNameBrand());
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
