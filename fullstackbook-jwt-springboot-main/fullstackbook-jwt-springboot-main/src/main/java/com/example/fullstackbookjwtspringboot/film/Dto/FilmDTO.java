package com.example.fullstackbookjwtspringboot.film.Dto;

public class FilmDTO {

    private int Id;
    private String name;
    private String linkimage;
    private int price;
    private String category;
    private String desciption;



    public FilmDTO(int id,String name, String linkimage, int price, String category, String desciption) {
        Id = id;
        this.name=name;
        this.linkimage = linkimage;
        this.price = price;
        this.category = category;
        this.desciption = desciption;
    }

    public FilmDTO() {
    }

    public int getId() {
        return Id;
    }

    public String getLinkimage() {
        return linkimage;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setLinkimage(String linkimage) {
        this.linkimage = linkimage;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCategory(String category){this.category=category;}

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    @Override
    public String toString() {
        return "Film{" +
                "Id='" + Id + '\'' +
                ", name='" + name + '\'' +
                ", linkimage='" + linkimage + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", desciption='" + desciption + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
