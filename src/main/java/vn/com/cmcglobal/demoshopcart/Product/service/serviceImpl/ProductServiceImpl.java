package vn.com.cmcglobal.demoshopcart.Product.service.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.cmcglobal.demoshopcart.Product.dto.ProductRequest;
import vn.com.cmcglobal.demoshopcart.Product.dto.SearchProductRequest;
import vn.com.cmcglobal.demoshopcart.Product.mapper.ProductMapper;
import vn.com.cmcglobal.demoshopcart.Product.model.Product;
import vn.com.cmcglobal.demoshopcart.Product.service.ProductService;
import vn.com.cmcglobal.demoshopcart.exception.NotFoundException;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> getAll() {
        return productMapper.getAll();
    }

    @Override
    public List<Product> getAll(int currentPage) {
        final int itemsPerPage = 3;
        if (currentPage <= 0) {
            return productMapper.getAllPagiNation(currentPage, itemsPerPage);
        }
        int offset = (currentPage - 1) * itemsPerPage;
        return productMapper.getAllPagiNation(offset, itemsPerPage);
    }

    @Override
    public Product getById(int id) {
        Product currentProduct = productMapper.getById(id);
        if (currentProduct == null) {
            throw new NotFoundException("Không có sản phẩm với ID: " + id);
        }
        return currentProduct;
    }

    @Override
    public List<Product> searchByUser(SearchProductRequest searchProductRequest, int currentPage) {
        final int itemsPerPage = 3;
        if (currentPage <= 0) {
            SearchProductRequest needSearch1 = SearchProductRequest.builder()
                    .name(searchProductRequest.getName())
                    .offset(currentPage)
                    .limit(itemsPerPage)
                    .build();
            List<Product> allProduct1 = productMapper.searchByUser(needSearch1);
            return allProduct1;
        }
        int offset = (currentPage - 1) * itemsPerPage;
        SearchProductRequest needSearch2 = SearchProductRequest.builder()
                .name(searchProductRequest.getName())
                .offset(offset)
                .limit(itemsPerPage)
                .build();
        List<Product> allProduct2 = productMapper.searchByUser(needSearch2);
        return allProduct2;
    }

    @Override
    public Product insert(ProductRequest productRequest) {
        Product newProduct = Product.builder()
                .id(productRequest.getId())
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .amount(productRequest.getAmount())
                .category(productRequest.getCategory())
                .createAt(LocalDateTime.now())
                .updateAt(productRequest.getCreateAt())
                .build();
        productMapper.insert(newProduct);
        return newProduct;
    }

    @Override
    public Product update(ProductRequest productRequest) {
        Product currentProduct = Product.builder()
                .id(productRequest.getId())
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .amount(productRequest.getAmount())
                .category(productRequest.getCategory())
                .createAt(productRequest.getCreateAt())
                .updateAt(LocalDateTime.now())
                .build();
        productMapper.update(currentProduct);
        return currentProduct;
    }

    @Override
    public void delete(int id) {
        Product currentProduct = productMapper.getById(id);
        if (currentProduct == null) {
            throw new NotFoundException("Không có sản phẩm với ID: " + id);
        }
        productMapper.delete(id);
    }

}