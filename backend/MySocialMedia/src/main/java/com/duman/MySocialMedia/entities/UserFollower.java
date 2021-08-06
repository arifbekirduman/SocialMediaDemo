package com.duman.MySocialMedia.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userfollower")
public class UserFollower {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "source_id")
    private long sourceId;

    @Column(name = "target_id")
    private long targetId;

    public UserFollower() {
    }

    public UserFollower(long id, long sourceId, long targetId) {
        this.id = id;
        this.sourceId = sourceId;
        this.targetId = targetId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSourceId() {
        return sourceId;
    }

    public void setSourceId(long sourceId) {
        this.sourceId = sourceId;
    }

    public long getTargetId() {
        return targetId;
    }

    public void setTargetId(long targetId) {
        this.targetId = targetId;
    }
}
