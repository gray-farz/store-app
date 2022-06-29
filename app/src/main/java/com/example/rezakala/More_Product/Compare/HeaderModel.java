package com.example.rezakala.More_Product.Compare;

public class HeaderModel implements Section {
 
    String header;
    private int section;
    public HeaderModel(int section) {
        this.section = section;
    }
 
    public void setheader(String header) {
        this.header = header;
    }
 
    @Override
    public boolean isHeader() {
        return true;
    }
 
    @Override
    public String getName() {
        return header;
    }

    @Override
    public String getinfo() {
        return null;
    }

    @Override
    public String GetHeadr_child() {
        return null;
    }

    @Override
    public String getValues_2() {
        return null;
    }

    @Override
    public int sectionPosition() {
        return section;
    }
}