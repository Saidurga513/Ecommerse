package com.example.mainproject;

public class SignUp {
    public static boolean customerLogin(Customer customer) {
        DbConnection dbConnection = new DbConnection();
        String query = "INSERT INTO customer(name,email,mobile,password) values("
                + "'" + customer.name + "'" + "," +
                "'" + customer.email + "'" + "," +
                "'" + customer.mobile + "'" + "," +
                "'" + customer.password + "'" + ")";
        int rs = dbConnection.updateDataBase(query);
        return rs != 0;

    }
}