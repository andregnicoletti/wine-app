package com.nicoletti.wineapp.service;

import com.nicoletti.wineapp.model.Wine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WineService {
    private final List<Wine> wines = new ArrayList<>();

    public WineService() {
        wines.add(new Wine("Cabernet Sauvignon", "Cabernet", 2018));
        wines.add(new Wine("Malbec Reserva", "Malbec", 2019));
        wines.add(new Wine("Sauvignon Blanc", "Sauvignon", 2021));
    }

    public List<Wine> findAll() {
        return Collections.unmodifiableList(wines);
    }

    public void add(Wine wine) {
        wines.add(wine);
    }

    public void remove(Wine wine) {
        wines.remove(wine);
    }
}
