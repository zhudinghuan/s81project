package s81project.Model.pojo;

import java.io.Serializable;

public class Goods implements Serializable {
  private int goods_id;
  private String goods_name;
  private float goods_price;
  private int goods_type;
  private int goods_state;
  private int goods_stock;
  private int goods_sell;
  private String goods_brand;
  private String goods_img;
  private String goods_discrible;
  private String state;

    public Goods() {
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getGoods_img() {
        return goods_img;
    }

    public void setGoods_img(String goods_img) {
        this.goods_img = goods_img;
    }

    public String getGoods_discrible() {
        return goods_discrible;
    }

    public void setGoods_discrible(String goods_discrible) {
        this.goods_discrible = goods_discrible;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public float getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(float goods_price) {
        this.goods_price = goods_price;
    }

    public int getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(int goods_type) {
        this.goods_type = goods_type;
    }

    public int getGoods_state() {
        return goods_state;
    }

    public void setGoods_state(int goods_state) {
        this.goods_state = goods_state;
    }

    public int getGoods_stock() {
        return goods_stock;
    }

    public void setGoods_stock(int goods_stock) {
        this.goods_stock = goods_stock;
    }

    public int getGoods_sell() {
        return goods_sell;
    }

    public void setGoods_sell(int goods_sell) {
        this.goods_sell = goods_sell;
    }

    public String getGoods_brand() {
        return goods_brand;
    }

    public void setGoods_brand(String goods_brand) {
        this.goods_brand = goods_brand;
    }
}
