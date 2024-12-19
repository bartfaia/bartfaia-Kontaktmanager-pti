package com.jb3bma.bartfaiattila.Kontaktmanager_sppti.Kontaktmanager_sppti;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
@Table(name = "nevjegyek")
public class Nevjegy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer azonosito;

    private String megszolitas;
    private String titulus;
    private String vezeteknev;
    private String keresztnev;
    private String utonev;

    private String iranyitoszam;
    private String telepules;
    private String utca;
    private String hazszam;
    private String emeletAjto;

    private String telefonszam;
    private String email;

 // Getters and setters
    public Integer getAzonosito() {
        return azonosito;
    }

    public void setAzonosito(Integer azonosito) {
        this.azonosito = azonosito;
    }

    public String getMegszolitas() {
        return megszolitas;
    }

    public void setMegszolitas(String megszolitas) {
        this.megszolitas = megszolitas;
    }

    public String getTitulus() {
        return titulus;
    }

    public void setTitulus(String titulus) {
        this.titulus = titulus;
    }

    public String getVezeteknev() {
        return vezeteknev;
    }

    public void setVezeteknev(String vezeteknev) {
        this.vezeteknev = vezeteknev;
    }

    public String getKeresztnev() {
        return keresztnev;
    }

    public void setKeresztnev(String keresztnev) {
        this.keresztnev = keresztnev;
    }

    public String getUtonev() {
        return utonev;
    }

    public void setUtonev(String utonev) {
        this.utonev = utonev;
    }

    public String getIranyitoszam() {
        return iranyitoszam;
    }

    public void setIranyitoszam(String iranyitoszam) {
        this.iranyitoszam = iranyitoszam;
    }

    public String getTelepules() {
        return telepules;
    }

    public void setTelepules(String telepules) {
        this.telepules = telepules;
    }

    public String getUtca() {
        return utca;
    }

    public void setUtca(String utca) {
        this.utca = utca;
    }

    public String getHazszam() {
        return hazszam;
    }

    public void setHazszam(String hazszam) {
        this.hazszam = hazszam;
    }

    public String getEmeletAjto() {
        return emeletAjto;
    }

    public void setEmeletAjto(String emeletAjto) {
        this.emeletAjto = emeletAjto;
    }

    public String getTelefonszam() {
        return telefonszam;
    }

    public void setTelefonszam(String telefonszam) {
        this.telefonszam = telefonszam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
