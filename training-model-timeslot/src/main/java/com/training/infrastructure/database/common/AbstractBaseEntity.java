package com.training.infrastructure.database.common;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractBaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue
    protected long id;
	
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }
}
