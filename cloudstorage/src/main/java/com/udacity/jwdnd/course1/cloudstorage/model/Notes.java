package com.udacity.jwdnd.course1.cloudstorage.model;


public class Notes {
    private String noteId;
    private String notetitle;
    private String notedescription;
    private int userid;


    public Notes(String noteId, String notetitle, String notedescription, int userid) {
        this.noteId = noteId;
        this.notetitle = notetitle;
        this.notedescription = notedescription;
        this.userid = userid;
    }



    public String getNoteid() {
        return noteId;
    }

    public void setNoteid(String noteid) {
        this.noteId = noteid;
    }

    public String getNotetitle() {
        return notetitle;
    }

    public void setNotetitle(String notetitle) {
        this.notetitle = notetitle;
    }

    public String getNotedescription() {
        return notedescription;
    }

    public void setNotedescription(String notedescription) {
        this.notedescription = notedescription;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }


}
