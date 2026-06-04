package com.example.systemdesign.moviebooking;

import java.util.ArrayList;
import java.util.List;

public class Theater {
    private final String theaterId;
    private final String name;
    private final List<Screen> screens;

    public Theater(String theaterId, String name) {
        this.theaterId = theaterId;
        this.name = name;
        this.screens = new ArrayList<>();
    }

    public void addScreen(Screen screen) { screens.add(screen); }

    public String getTheaterId() { return theaterId; }
    public String getName() { return name; }
    public List<Screen> getScreens() { return screens; }
}
