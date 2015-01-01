package kr.co.aroundthetruck.customer.data;

/**
 * Created by ebsud89 on 12/20/14.
 */
public class menu {

    int idx;

    String name;
    int price;

    int truck_idx;
    int photo_idx;

    String ingredients;     // 식재료
    String description;     // 메뉴 설명

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTruck_idx() {
        return truck_idx;
    }

    public void setTruck_idx(int truck_idx) {
        this.truck_idx = truck_idx;
    }

    public int getPhoto_idx() {
        return photo_idx;
    }

    public void setPhoto_idx(int photo_idx) {
        this.photo_idx = photo_idx;
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
}
