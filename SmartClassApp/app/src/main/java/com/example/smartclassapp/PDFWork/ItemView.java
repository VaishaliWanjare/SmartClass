package com.example.smartclassapp.PDFWork;



public class ItemView {

    String nameOfFile;
    String txtImageName;

    public ItemView(String nameOfFile, String txtImageName) {
        this.nameOfFile = nameOfFile;
        this.txtImageName = txtImageName;
    }

    public String getNameOfFile() {
        return nameOfFile;
    }

    public void setNameOfFile(String nameOfFile) {
        this.nameOfFile = nameOfFile;
    }

    public String getTxtImageName() {
        return txtImageName;
    }

    public void setTxtImageName(String txtImageName) {
        this.txtImageName = txtImageName;
    }
}
