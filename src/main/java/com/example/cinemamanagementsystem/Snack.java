package com.example.cinemamanagementsystem;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

public class Snack {
    public String Sid;
    public String SName;
    public double Sprice;
    public String Flavor;
    public String flavorDisplay;
    private String imagePath;
    public Snack(String SName, double Sprice, String Flavor,String imagePath) {
        this.SName = SName;
        this.Sprice = Sprice;
        this.Flavor = Flavor;
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        flavorDisplay = (Flavor != null) ? Flavor : " ";
        return SName + " " + flavorDisplay + " - $" + Sprice;
    }

    public double getSPrice() {
        return Sprice;
    }

    public String getImagePath() {
        return imagePath;
    }
}
