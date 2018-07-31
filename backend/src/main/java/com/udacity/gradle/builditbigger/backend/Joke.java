package com.udacity.gradle.builditbigger.backend;

/** The object model for the data we are sending through endpoints */
public class Joke {

    Joke(String joke) {
        this.joke = joke;
    }

    private String joke;

    public String getJoke() {
        return joke;
    }

    public void setJoke(String data) {
        joke = data;
    }
}