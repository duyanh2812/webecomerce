package vn.com.cmcglobal.demoshopcart.Product.controller;


import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import vn.com.cmcglobal.demoshopcart.exception.BadRequestException;
import vn.com.cmcglobal.demoshopcart.exception.NotFoundException;
import vn.com.cmcglobal.demoshopcart.Product.dto.ProductRequest;
import vn.com.cmcglobal.demoshopcart.Product.dto.SearchProductRequest;
import vn.com.cmcglobal.demoshopcart.Product.mapper.ProductMapper;
import vn.com.cmcglobal.demoshopcart.Product.model.Product;
import vn.com.cmcglobal.demoshopcart.Product.service.serviceImpl.ProductServiceImpl;
import vn.com.cmcglobal.demoshopcart.User.dto.UserDto;

@RestController
@RequestMapping("/api/product")
public class RestProductController {
    @Autowired private ProductServiceImpl productServiceImpl;
    @Autowired private ProductMapper productMapper;
    
    @Operation(summary = "Get All Products")
    @GetMapping("/all-products")
    public ResponseEntity<?> getAllProduct(@RequestParam("currentPage") int currentPage){
        List<Product> allProducts = productServiceImpl.getAll(currentPage);
        return ResponseEntity.ok(allProducts);
    }

    @Operation(summary = "Find Product By Id")
    @GetMapping("/findbyid/{productID}")
    public ResponseEntity<?> findProductByID(@PathVariable int productID){
        Product currentProduct = productServiceImpl.getById(productID);
        return ResponseEntity.ok(currentProduct);
    }

    @Operation(summary = "Search Product By Name")
    @GetMapping("/search")
    public ResponseEntity<?> searchProduct(
        SearchProductRequest searchProductRequest,
        @RequestParam("currentPage") int currentPage){
        List<Product> listSearch = productServiceImpl.searchByUser(searchProductRequest, currentPage);
        return ResponseEntity.ok(listSearch);
    }

    @Operation(summary = "Creat New Product")
    @PostMapping("/addnew")
    public ResponseEntity<?> insertnewProduct(@RequestBody ProductRequest productRequest,HttpSession session){
        UserDto userDTO = (UserDto) session.getAttribute("user");
        if(!userDTO.getRole().toString().equals("ADMIN")){
            throw new BadRequestException("B???n kh??ng ph???i Admin, l?????n ??i");
        }
        if(productMapper.getById(productRequest.getId()) != null){
            return 
            ResponseEntity.ok("Update th??nh c??ng s???n ph???m ID: "+productRequest.getId()
            +"\n"+"Th??ng tin thay ?????i:"
            +"\n"+productServiceImpl.update(productRequest));
        }
        return ResponseEntity.ok("T???o th??nh c??ng s???n ph???m: "+"\n"+productServiceImpl.insert(productRequest));
    }

    @Operation(summary = "Update Product")
    @PutMapping("/update/{productID}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductRequest productRequest,@PathVariable int productID,HttpSession session){
        UserDto userDTO = (UserDto) session.getAttribute("user");
        if(!userDTO.getRole().toString().equals("ADMIN")){
            throw new BadRequestException("B???n kh??ng ph???i Admin, l?????n ??i");
        }
        Product checkProduct = productServiceImpl.getById(productID);
        if(checkProduct == null){
            throw new NotFoundException("Kh??ng c?? s???n ph???m v???i ID: " + productID);
        }
        productRequest.setId(productID);
        productServiceImpl.update(productRequest);
        return ResponseEntity.ok("Update th??nh c??ng s???n ph???m ID: "+productID
        +"\n"+"Th??ng tin thay ?????i:"
        +"\n"+productServiceImpl.update(productRequest));
    }

    @Operation(summary = "Delete Product By Id")
    @DeleteMapping("/delete/{productID}")
    public ResponseEntity<?> deleteProduct(@PathVariable int productID,HttpSession session){
        UserDto userDTO = (UserDto) session.getAttribute("user");
        if(!userDTO.getRole().toString().equals("ADMIN")){
            throw new BadRequestException("B???n kh??ng ph???i Admin, l?????n ??i");
        }
        productServiceImpl.delete(productID);
        return ResponseEntity.ok().body("Xo?? th??nh c??ng s???n ph???m c?? ID: "+productID);
    }
}