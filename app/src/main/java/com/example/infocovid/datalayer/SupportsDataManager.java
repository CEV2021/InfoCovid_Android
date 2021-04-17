package com.example.infocovid.datalayer;

import com.example.infocovid.datalayer.connection.eldiario.Connection;

public interface SupportsDataManager {
    void refreshView();
    void setConnection(Connection connection);
}
