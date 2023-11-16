package com.example.rvclicknew;
public class Radio {
    int _id;
    String radioName;
    String radioUrl;
    String radioPic;

    public Radio() {
    }

    public Radio(int id, String radioname, String radiourl, String radiopic) {
        this._id = id;
        this.radioName = radioname;
        this.radioUrl = radiourl;
        this.radioPic = radiopic;
    }

    public Radio(String radioname, String radiourl, String radiopic) {
        this.radioName = radioname;
        this.radioUrl = radiourl;
        this.radioPic = radiopic;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getRadioName() {
        return radioName;
    }

    public void setRadioName(String radioName) {
        this.radioName = radioName;
    }

    public String getRadioUrl() {
        return radioUrl;
    }

    public void setRadioUrl(String radioUrl) {
        this.radioUrl = radioUrl;
    }

    public String getRadioPic() {
        return radioPic;
    }

    public void setRadioPic(String radioPic) {
        this.radioPic = radioPic;
    }
}


