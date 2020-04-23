package com.example.keeper;

import com.example.keeper.event.CommonEvent;
import com.example.keeper.event.KeeperDispatched;
import com.example.keeper.event.KeeperEmpolyed;

import javax.persistence.*;

@Entity
public class Keeper {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String space;

    @PostPersist
    public void publishKeeperEmployedEvent() {
        KeeperEmpolyed keeperEmpolyed = new KeeperEmpolyed();
        keeperEmpolyed.setId(this.id);
        keeperEmpolyed.setName(this.name);
        CommonEvent event = new CommonEvent(KeeperEmpolyed.class.getSimpleName(), keeperEmpolyed);
        event.publish();
    }

    @PostUpdate
    public void publishKeeperDispatchedEvent() {
        KeeperDispatched keeperDispatched = new KeeperDispatched();
        keeperDispatched.setId(this.id);
        keeperDispatched.setSpace(this.space);
        keeperDispatched.setName(this.name);
        CommonEvent event = new CommonEvent(KeeperDispatched.class.getSimpleName(), keeperDispatched);
        event.publish();;
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

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }
}
