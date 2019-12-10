package com.mr2.zaiko.Domain.Image;

import java.util.Date;

public class Image {
    private long id;
    private long item_id; //VO
    private String address; //VO
    private Date createdAt; //VOじゃなくてもよくない？日付に振る舞いあるの？？いやDeletedAtと区別するための意味づけが必要か。あとDefault値どうすんねｎ
    private Date deletedAt; //Date型じゃなくStringで持たせる？？？
    private boolean isPrimary;

    //created
    public Image(long item_id, String address){
        this.item_id = item_id;
        this.address = address;
    }

    //read
    public Image(long id, long item_id, String address, Date createdAt, Date deletedAt, boolean isPrimary) {
        this.id = id;
        this.item_id = item_id;
        this.address = address;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
        this.isPrimary = isPrimary;
    }

    public Image create(){
        return null;
    }

    private void setId(long id){ //自己カプセル化
        //validate
    }
}
