package com.dams.choixuniv;



public class Data {
    private String matricule, fullname, mail, field, password;

    public Data(){

    }
    public Data(String matricule, String fullname, String mail, String field, String password){
        this.matricule = matricule;
        this.fullname = fullname;
        this.mail = mail;
        this.field = field;
        this.password = password;

    }

    public String getMatricule() {
        return matricule;
    }

    public String getFullname() {
        return fullname;
    }

    public String getMail() {
        return mail;
    }

    public String getField() {
        return field;
    }

    public String getPassword() {
        return password;
    }
}
