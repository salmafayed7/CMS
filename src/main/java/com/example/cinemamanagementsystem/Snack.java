package com.example.cinemamanagementsystem;

public class Snack {
    public String Sid;
    public String SName;
    public double Sprice;
    public String Flavor;
    public int Quantity;
    public String flavorDisplay;
    public Snack(String SName, double Sprice, String Flavor, int Quantity) {
        this.SName = SName;
        this.Sprice = Sprice;
        this.Flavor = Flavor;
        this.Quantity = Quantity;
    }

    @Override
    public String toString() {
        flavorDisplay = (Flavor != null) ? Flavor : " ";
        return SName + " " + flavorDisplay + " - $" + Sprice;
    }

    public double getSPrice() {
        return Sprice;
    }
}
