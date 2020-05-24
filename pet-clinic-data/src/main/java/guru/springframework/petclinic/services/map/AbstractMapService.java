package guru.springframework.petclinic.services.map;

import guru.springframework.petclinic.model.BaseEntity;

import java.util.*;

// Type coming into this class has to extend Base entity and the ID has to extend Long
public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {

    protected Map<Long, T> map = new HashMap<>();

    Set<T> findAll(){
        return new HashSet(map.values());
    }

    T findById(ID id) {
        return map.get(id);
    }

    T save(T object) {
        // If the object is not null and doesn't have an ID then give it one from getNextId()
        if (object != null) {
            if (object.getId() == null) {
                object.setId(getNextId());
            }
            // After it gets and ID then put it in the map
            map.put(object.getId(),object);

        } else {
            throw new RuntimeException("Object cannot be null");
        }
        return object;
    }

    void deleteById(ID id) {
        map.remove(id);
    }

    void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    private Long getNextId(){

        Long nextId = null;

        try {
            nextId = Collections.max(map.keySet()) + 1;
        } catch (NoSuchElementException e) {
            nextId = 1L;
        }
        return nextId;
    }

}
