package com.ea.ordermanagementapi.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ea.ordermanagementapi.util.DateUtils;

@Document(collection = "Customer")
public class Customer implements Serializable
{
    private static final long serialVersionUID = 1778515744318629980L;

    @Id
    public String id;

    public String firstName;

    public String lastName;

    private String createdDate = DateUtils.getNowDate();

    public Customer()
    {
    }

    public Customer(String firstName)
    {
        this.firstName = firstName;
    }

    public Customer(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId()
    {
        return id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(String createdDate)
    {
        this.createdDate = createdDate;
    }

    @Override
    public String toString()
    {
        return "Customer{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}