package com.solvd.itcompany2.helpers;

import java.util.List;

public class ConnectionPool {

    private final static ConnectionPool INSTANCE = new ConnectionPool();
    private List<Connection> connections;
    private int size = 5;

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }
}