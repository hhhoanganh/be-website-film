package com.example.fullstackbookjwtspringboot.film.Entity;


import javax.persistence.*;

@Entity
@Table(name= "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name= "name")
    private String name;
    @Column(name = "link_image")
    private String linkimage;
    @Column(name = "price")
    private int price;
    @Column(name = "category")
    private String category;
    @Column(name = "desciption1")
    private String desciption1;
    @Column(name = "desciption2")

    private String desciption2="";



    public Film(String name, String linkimage, int price, String category, String desciption) {
        this.name = name;
        this.linkimage = linkimage;
        this.price = price;
        this.category = category;
        if (desciption.length()<255)
            this.desciption1 = desciption;
        else{
            this.desciption1 = desciption.substring(0,254);
            this.desciption2 = desciption.substring(255);
        }
    }

    public Film() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkimage() {
        return linkimage;
    }

    public void setLinkimage(String linkimage) {
        this.linkimage = linkimage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDesciption() {
        return desciption1+desciption2;
    }

    public void setDesciption(String desciption) {
        if (desciption.length()<255)
            this.desciption1 = desciption;
        else{
            this.desciption1 = desciption.substring(0,254);
            this.desciption2 = desciption.substring(255);
        }
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", linkimage='" + linkimage + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", desciption='" + desciption1+" "+desciption2 + '\'' +
                '}';
    }
}

