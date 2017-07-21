package luistorm.tasseladmin;

/**
 * Created by luis on 03/07/17.
 */

public class product {
    private int id;
    private String name;
    private float price;
    private String description;
    private String imagePath;
    private String ingredients;
    private int special;

    public product(int id, String name, float price, String description, int special) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        imagePath = null;
        ingredients = null;
        this.special = special;
    }

    public int getSpecial() {
        return special;
    }

    public void setSpecial(int special) {
        this.special = special;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
