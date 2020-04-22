package com.example.keeper.event;

import java.util.HashMap;

public class KeeperEmpolyed {
    private Long id;
    private String name;

    public KeeperEmpolyed() {
    }

    public KeeperEmpolyed(HashMap<String, Object> map) {
        this.id = Long.valueOf((Integer) map.get("id"));
        this.name = (String) map.get("name");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
