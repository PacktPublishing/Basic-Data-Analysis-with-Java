/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packtpub.datatypes;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Erik Costlow
 */
public class POJO {
    private int id;
    
    private String name;
    
    private String favoriteBook;
    
    private Set<POJO> friends = new HashSet<>();

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

    public String getFavoriteBook() {
        return favoriteBook;
    }

    public void setFavoriteBook(String favoriteBook) {
        this.favoriteBook = favoriteBook;
    }

    public Set<POJO> getFriends() {
        return friends;
    }

    public void setFriends(Set<POJO> friends) {
        this.friends = friends;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final POJO other = (POJO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
