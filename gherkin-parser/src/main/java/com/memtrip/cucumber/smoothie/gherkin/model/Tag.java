package com.memtrip.cucumber.smoothie.gherkin.model;

import java.util.Objects;

public class Tag {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Tag) {
            Tag that = (Tag) o;
            return Objects.equals(this.name, that.name);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return 3 * name.hashCode();
    }
}
