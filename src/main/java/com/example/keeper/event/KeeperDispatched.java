package com.example.keeper.event;

import java.util.HashMap;

public class KeeperDispatched {
    private Long id;
    private String name;
    private String space;

    public KeeperDispatched() {
    }

    public KeeperDispatched(HashMap<String, Object> map) {
        this.id = Long.valueOf((Integer) map.get("id"));
        this.space = (String) map.get("space");
        this.name = (String) map.get("name");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
