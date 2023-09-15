/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;



/**
 *
 * @author Laptop
 */
public class flower {
    private String id;
    private String description;
    private String importdate;
    private float unitprice;
    private String category;

    public flower() {
    }

    public flower(String id, String description, String importdate, float unitprice, String category) {
        this.id = id;
        this.description = description;
        this.importdate = importdate;
        this.unitprice = unitprice;
        this.category = category;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        description = description.substring(0,1).toUpperCase() + description.substring(1).toLowerCase();
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImportdate() {
        return importdate;
    }

    public void setImportdate(String importdate) {
        this.importdate = importdate;
    }

    public float getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(float unitprice) {
        this.unitprice = unitprice;
    }

    public String getCategory() {
        category = description.substring(0,1).toUpperCase() + description.substring(1).toLowerCase();
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return this.getId() + ", " + this.getDescription() + ", " + this.getImportdate() + ", " +
                this.getUnitprice() + ", " + this.getCategory();
    }
    
}
