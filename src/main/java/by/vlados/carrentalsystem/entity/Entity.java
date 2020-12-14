package by.vlados.carrentalsystem.entity;

import java.io.Serializable;

/**
 *
 * @author vlados
 */
public abstract class Entity implements Serializable {

    private int id;

    /**
     *
     */
    public Entity() {
    }

    /**
     *
     * @param id
     */
    public Entity(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Entity other = (Entity) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity{" + "id=" + id + '}';
    }

}
