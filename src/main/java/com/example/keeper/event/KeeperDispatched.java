package com.example.keeper.event;

import java.util.HashMap;

public class KeeperDispatched {
    private Long id;
    private String space;

    public KeeperDispatched() {
    }

    public KeeperDispatched(HashMap<String, Object> map) {
        this.id = Long.valueOf((Integer) map.get("id"));
        this.space = (String) map.get("space");
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
}
