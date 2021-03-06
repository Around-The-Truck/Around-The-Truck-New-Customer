package kr.co.aroundthetruck.customer.data;

/**
 * Created by ebsud89 on 12/20/14.
 */
public class Menu {

    int idx;

    String name;
    int price;

    int truck_idx;
    String truck_name;
    int photo_idx;

    public String getPhoto_filename() {
        return photo_filename;
    }

    public void setPhoto_filename(String photo_filename) {
        this.photo_filename = photo_filename;
    }

    public String getTruck_name() {
        return truck_name;
    }

    public void setTruck_name(String truck_name) {
        this.truck_name = truck_name;
    }

    String photo_filename;

    String ingredients;     // 식재료
    String description;     // 메뉴 설명

    public Menu() {

    }

    public Menu(int idx, String name, int price, int truck_idx, String truck_name, String photo_filename, String ingredients, String description) {
        this.idx = idx;
        this.name = name;
        this.price = price;
        this.truck_idx = truck_idx;
        this.truck_name = truck_name;
        this.photo_filename = photo_filename;
        this.ingredients = ingredients;
        this.description = description;
    }

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
