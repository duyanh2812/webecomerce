package vn.com.cmcglobal.demoshopcart;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import vn.com.cmcglobal.demoshopcart.Product.mapper.ProductMapper;
import vn.com.cmcglobal.demoshopcart.Product.model.Category;
import vn.com.cmcglobal.demoshopcart.Product.model.Product;
import vn.com.cmcglobal.demoshopcart.User.mapper.UserMapper;
import vn.com.cmcglobal.demoshopcart.User.model.Roles;
import vn.com.cmcglobal.demoshopcart.User.model.User;

@Component
@Transactional
public class AppRunner implements ApplicationRunner {
//    @Autowired private ProductMapper productMapper;
//    @Autowired private UserMapper userMapper;
    private void generateData() {
        
        //     Product product1 = Product.builder()
        //     .id(1001)
        //     .name("Iphone 13 Promax")
        //     .price(27000000)
        //     .amount(10)
        //     .category(Category.MOBILE)
        //     .createAt(LocalDateTime.now())
        //     .updateAt(LocalDateTime.now())
        //     .build();
    
        //     Product product2 = Product.builder()
        //     .id(1002)
        //     .name("Ipad Pro 2022 12.9inc")
        //     .price(30000000)
        //     .amount(5)
        //     .category(Category.TABLET)
        //     .createAt(LocalDateTime.now())
        //     .updateAt(LocalDateTime.now())
        //     .build();
            
        //     Product product3 = Product.builder()
        //     .id(1003)
        //     .name("Macbook Pro chip M2 2022")
        //     .price(45000000)
        //     .amount(3)
        //     .category(Category.LAPTOP)
        //     .createAt(LocalDateTime.now())
        //     .updateAt(LocalDateTime.now())
        //     .build();
        //     productMapper.insert(product1);
        //     productMapper.insert(product2);
        //     productMapper.insert(product3);

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        generateData();
        
    }
    
}
