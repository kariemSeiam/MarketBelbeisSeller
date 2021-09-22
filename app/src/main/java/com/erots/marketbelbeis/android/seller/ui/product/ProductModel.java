package com.erots.marketbelbeis.android.seller.ui.product;

public class ProductModel {

    final private String productType; //
    final private String productStoreName; //
    final private String productCreatedAt;
    final private String productStoreUid;
    final private int productImage;
    final private String productUid;
    private String productTitle; //
    private String productContent; //
    private String productPrice; //
    private String productStatues; //
    private String productDiscount; //
    private String productDiscountStartsAt; //
    private String productDiscountEndsAt; //
    private String productQuantity; //
    private String productPublishedAt; //
    private String productUpdatedAt;
    private int productId;


    public ProductModel(String productType, String productStoreName, String productCreatedAt, String productStoreUid, int productImage, String productUid, String productTitle, String productContent, String productPrice, String productStatues, String productDiscount, String productDiscountStartsAt, String productDiscountEndsAt, String productQuantity, String productPublishedAt, String productUpdatedAt) {
        this.productType = productType;
        this.productStoreName = productStoreName;
        this.productCreatedAt = productCreatedAt;
        this.productStoreUid = productStoreUid;
        this.productImage = productImage;
        this.productUid = productUid;
        this.productTitle = productTitle;
        this.productContent = productContent;
        this.productPrice = productPrice;
        this.productStatues = productStatues;
        this.productDiscount = productDiscount;
        this.productDiscountStartsAt = productDiscountStartsAt;
        this.productDiscountEndsAt = productDiscountEndsAt;
        this.productQuantity = productQuantity;
        this.productPublishedAt = productPublishedAt;
        this.productUpdatedAt = productUpdatedAt;
    }

    public String getProductType() {
        return productType;
    }

    public String getProductStoreName() {
        return productStoreName;
    }

    public String getProductCreatedAt() {
        return productCreatedAt;
    }

    public String getProductStoreUid() {
        return productStoreUid;
    }

    public int getProductImage() {
        return productImage;
    }

    public String getProductUid() {
        return productUid;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductContent() {
        return productContent;
    }

    public void setProductContent(String productContent) {
        this.productContent = productContent;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductStatues() {
        return productStatues;
    }

    public void setProductStatues(String productStatues) {
        this.productStatues = productStatues;
    }

    public String getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(String productDiscount) {
        this.productDiscount = productDiscount;
    }

    public String getProductDiscountStartsAt() {
        return productDiscountStartsAt;
    }

    public void setProductDiscountStartsAt(String productDiscountStartsAt) {
        this.productDiscountStartsAt = productDiscountStartsAt;
    }

    public String getProductDiscountEndsAt() {
        return productDiscountEndsAt;
    }

    public void setProductDiscountEndsAt(String productDiscountEndsAt) {
        this.productDiscountEndsAt = productDiscountEndsAt;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductPublishedAt() {
        return productPublishedAt;
    }

    public void setProductPublishedAt(String productPublishedAt) {
        this.productPublishedAt = productPublishedAt;
    }

    public String getProductUpdatedAt() {
        return productUpdatedAt;
    }

    public void setProductUpdatedAt(String productUpdatedAt) {
        this.productUpdatedAt = productUpdatedAt;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

}