package com.example.rezakala.More_Product.Compare;

public class ChildModel implements Section {

    String child;
    private int section;
    String Header_child;
    String Values_2;

    public void setValues_2(String values_2) {
        Values_2 = values_2;
    }

    public void setHeader_child(String header_child) {
        Header_child = header_child;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    String info;

    public ChildModel(int section) {
        this.section = section;
    }

    public void setChild(String child) {
        this.child = child;
    }

    @Override
    public boolean isHeader() {
        return false;
    }

    @Override
    public String getName() {
        return child;
    }

    @Override
    public String getinfo() {
        return info;
    }

    @Override
    public String GetHeadr_child() {
        return Header_child;
    }

    @Override
    public String getValues_2() {
        return Values_2;
    }

    @Override
    public int sectionPosition() {
        return section;
    }
}