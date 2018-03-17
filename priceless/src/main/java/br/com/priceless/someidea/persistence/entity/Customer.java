package br.com.priceless.someidea.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String username;
    private String firstName;
    private String lastName;

    protected Customer() {}

    public Customer(String username, String firstName, String lastName) {
        this.username = username;
    	this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, username='%s', firstName='%s', lastName='%s']",
                id, username, firstName, lastName);
    }

}