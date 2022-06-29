package com.example.rezakala.Viewpager_Tablayout_Category.More_Category;

public class PeopleListItem extends ExpandableRecyclerAdapter.ListItem {
    public String Text;
    public String Categoryid;
    public PeopleListItem(String tText, String categoryid) {
        super(1000);
        Text=tText;
        this.Categoryid=categoryid;
    }
    public PeopleListItem(String frist, String categoryid, String t) {
        super(1001);
        Text=frist;
        this.Categoryid=categoryid;
    }
}
