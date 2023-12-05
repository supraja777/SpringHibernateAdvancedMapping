package com.supraja.cruddemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "review")
public class Review {
    // define fields

    //define constructors

    // define setters and getters

    // define toString methods

    public Review(String comment) {
        this.comment = comment;
    }

    public Review() {
    }

    public int getId() {
        return Id;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "Review{" +
                "Id=" + Id +
                ", comment='" + comment + '\'' +
                '}';
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setId(int id) {
        Id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int Id;
    @Column(name="comment")
    private String comment;
}
