package com.example.ecostyle;

public class Brands {
    private int idImageDrawable;
    private String nameBrand;
    private String costBrand;
    private int cost;

    public Brands(int idImageDrawable, String nameBrand, String costBrand, int cost)
    {
        this.idImageDrawable = idImageDrawable;
        this.nameBrand = nameBrand;
        this.nameBrand = nameBrand;
        this.costBrand = costBrand;
        this.cost = cost;
    }

    public int getCost()
    {
        return cost;
    }

    public int getIdImageDrawable()
    {
        return idImageDrawable;
    }

    public String getNameBrand()
    {
        return nameBrand;
    }

    public String getCostBrand()
    {
        return costBrand;
    }

}
