package pl.coderslab.model;

public class Recipe {
    private int ID, preparationTime, adminId;
    private String name, ingredients, description, created, updated, preparation;

    public Recipe(String name, String ingredients, String description, String created, String updated, int preparationTime, String preparation, int adminId) {
        this.preparation = preparation;
        this.adminId = adminId;
        this.name = name;
        this.ingredients = ingredients;
        this.description = description;
        this.created = created;
        this.preparationTime = preparationTime;
        this.updated = updated;
    }

    public Recipe(int ID, String name, String description) {
        this.ID = ID;
        this.name = name;
        this.description = description;

    }

    public Recipe(String name, String ingredients, String description, int preparationTime, String preparation, int adminId) {
        this.preparation = preparation;
        this.adminId = adminId;
        this.name = name;
        this.ingredients = ingredients;
        this.description = description;
        this.preparationTime = preparationTime;
    }

    public Recipe() {
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "ID=" + ID +
                ", preparationTime=" + preparationTime +
                ", adminId=" + adminId +
                ", name='" + name + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", description='" + description + '\'' +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", preparation='" + preparation + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof Recipe) {
            Recipe r = (Recipe) obj;
            return r.name.toLowerCase().replaceAll("\\s", "").equals(this.name.toLowerCase().replaceAll("\\s", "")) && r.description.toLowerCase().replaceAll("\\s", "").equals(this.description.toLowerCase().replaceAll("\\s", "")) && r.ingredients.toLowerCase().replaceAll("\\s", "").equals(this.ingredients.toLowerCase().replaceAll("\\s", ""));
        }
        return false;
    }
}
