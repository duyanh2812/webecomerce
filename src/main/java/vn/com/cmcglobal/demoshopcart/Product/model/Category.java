package vn.com.cmcglobal.demoshopcart.Product.model;

public enum Category {
    MOBILE("MOBILE"),
    TABLET("TABLET"),
    LAPTOP("LAPTOP");
    public final String label;

    private Category(String label) {
        this.label = label;
    }
}
