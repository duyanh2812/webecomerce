package vn.com.cmcglobal.demoshopcart.Cart.model;

public enum CartLineStatus {
    WAIT_FOR_PAY("WAIT_FOR_PAY"),
    PAYING_SUCCESSFUL("PAYING_SUCCESSFUL");
    public final String label;

    CartLineStatus(String label) {
        this.label = label;
    }
}
