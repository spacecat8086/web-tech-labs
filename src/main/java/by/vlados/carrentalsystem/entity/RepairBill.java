
package by.vlados.carrentalsystem.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author vlados
 */
public class RepairBill extends Entity implements Serializable{
    private String carname;
    private int sum;
    private String damage;
    private int repaired;

    public RepairBill() {
    }
    

    public String getCarname() {
        return carname;
    }

    public void setCarname(String carname) {
        this.carname = carname;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public int getRepaired() {
        return repaired;
    }

    public void setRepaired(int repaired) {
        this.repaired = repaired;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.carname);
        hash = 41 * hash + this.sum;
        hash = 41 * hash + Objects.hashCode(this.damage);
        hash = 41 * hash + this.repaired;
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
        final RepairBill other = (RepairBill) obj;
        if (!Objects.equals(this.carname, other.carname)) {
            return false;
        }
        if (this.sum != other.sum) {
            return false;
        }
        if (!Objects.equals(this.damage, other.damage)) {
            return false;
        }
        if (this.repaired != other.repaired) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RepairBill{" + "carname=" + carname + ", sum=" + sum + ", damage=" + damage + ", repaired=" + repaired + '}';
    }
    
    
}
