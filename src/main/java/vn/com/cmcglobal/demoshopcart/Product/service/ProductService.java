package vn.com.cmcglobal.demoshopcart.Product.service;

import java.util.List;

import vn.com.cmcglobal.demoshopcart.Product.dto.ProductRequest;
import vn.com.cmcglobal.demoshopcart.Product.dto.SearchProductRequest;
import vn.com.cmcglobal.demoshopcart.Product.model.Product;

public interface ProductService {
    public List<Product> getAll();
    public List<Product> getAll(int currentPage);
    public Product getById(int id);
    public List<Product> searchByUser(SearchProductRequest searchProductRequest, int currentPage);
    public Product insert(ProductRequest productRequest);
    public Product update(ProductRequest productRequest);
    public void delete(int id);
}
