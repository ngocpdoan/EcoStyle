package com.example.ecostyle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ecostyle.Brands;
import com.example.ecostyle.R;

import java.util.ArrayList;
import java.util.List;

public class BrandsAdapter extends ArrayAdapter<Brands> implements Filterable {
    private Context myContext;
    private List<Brands> brandsList;
    private List<Brands> brandsListFull;
    public BrandsAdapter(@NonNull Context context, ArrayList<Brands> listOfBrands)
    {
        super(context, 0 , listOfBrands);
        myContext = context;
        brandsList = listOfBrands;
        brandsListFull = new ArrayList<>(listOfBrands);
    }

    @Override
    public View getView(int position, @Nullable View convertView,@NonNull ViewGroup parent)
    {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(myContext).inflate(R.layout.list_item, parent, false);

        Brands currentBrands = brandsList.get(position);

        ImageView image = (ImageView)listItem.findViewById(R.id.imageView);
        image.setImageResource(currentBrands.getIdImageDrawable());

        TextView name = (TextView)listItem.findViewById(R.id.textViewName);
        name.setText(currentBrands.getNameBrand());

        TextView price = (TextView)listItem.findViewById(R.id.textViewAfford);
        price.setText(currentBrands.getCostBrand());

        return listItem;
    }
    @Override
    public Filter getFilter()
    {
        return listFilter;
    }

    private Filter listFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Brands> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() ==0)
            {
                filteredList.addAll(brandsListFull);
            }
            else{
                String filterdPattern = constraint.toString().toLowerCase().trim();

                for (Brands item : brandsListFull)
                {
                    if(item.getNameBrand().toLowerCase().contains(filterdPattern))
                    {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
                brandsList.clear();
                brandsList.addAll((List)results.values);
                notifyDataSetChanged();
        }
    };
}
