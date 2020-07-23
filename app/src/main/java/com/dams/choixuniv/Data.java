package com.dams.choixuniv;



public class Data {
    private String matricule, fullname, mail, field, password, Moy1, Moy2;

    public Data(){

    }
    public Data(String matricule, String fullname, String mail, String password, String field, String Moy1, String Moy2){
        this.matricule = matricule;
        this.fullname = fullname;
        this.mail = mail;

        this.field = field;
        this.password = password;
        this.Moy1 = Moy1;
        this.Moy2 = Moy2;

    }

    public String getField() {
        return field;
    }

    public String getMoy1() {
        return Moy1;
    }

    public String getMoy2() {
        return Moy2;
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


    public String getPassword() {
        return password;
    }
}
