package vn.com.cmcglobal.demoshopcart.maintest;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import vn.com.cmcglobal.demoshopcart.Cart.mapper.CartMapper;
import vn.com.cmcglobal.demoshopcart.Cart.model.Cart;
import vn.com.cmcglobal.demoshopcart.Product.mapper.ProductMapper;
import vn.com.cmcglobal.demoshopcart.Product.model.Category;
import vn.com.cmcglobal.demoshopcart.Product.model.Product;
import vn.com.cmcglobal.demoshopcart.User.mapper.UserMapper;
import vn.com.cmcglobal.demoshopcart.User.model.Roles;
import vn.com.cmcglobal.demoshopcart.User.model.User;
public class InsertTest {
    public static void main(String[] args) throws IOException {
        Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sqlSessionFactory.openSession();
 
        // create product mapper
        UserMapper userMapper = session.getMapper(UserMapper.class);

        User user1 = User.builder()
        .id(10)
        .userName("cuong333")
        .password("123456")
        .fullName("Vu Manh Cuong")
        .mobile("0945940246")
        .email("vmcuong2192@gmail.com")
        .address("Ha Noi")
        .role(Roles.MEMBER)
        .createAt(LocalDateTime.now())
        .updateAt(LocalDateTime.now())
        .build();
        userMapper.insert(user1);
        session.commit();
        System.out.println("insert product sucessfully");
 
        // close session
        session.close();
    }
}
