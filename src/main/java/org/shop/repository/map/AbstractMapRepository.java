package org.shop.repository.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.shop.data.Entity;

public class AbstractMapRepository<T extends Entity> {
    
    protected long sequence = 0;
    
    protected final Map<Long, T> register = new HashMap<>();
    
    public AbstractMapRepository() {
        this(0);
    }

    public AbstractMapRepository(long initialSequence) {
        super();
        this.sequence = initialSequence;
    }

    protected final Long nextPk() {
        return ++sequence;
    }
    
    protected T get(Long id) {
        return register.get(id);
    }
    
    protected Long create(T entity) {
        Long id = nextPk();
        
        entity.setId(id);
        
        register.put(id, entity);
        
        return id;
    }
    
    protected void update(T entity) {
        if (entity.getId() != null) {
            register.put(entity.getId(), entity);
        }
    }
    
    protected void delete(Long id) {
        register.remove(id);
    }
    
    protected List<T> select(String name) {
        return register.values().stream().filter(name::equals).collect(Collectors.toList());
    }
}
