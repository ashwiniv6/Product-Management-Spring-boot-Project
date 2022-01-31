package com.example1.consumeapi.restController;

import com.example1.consumeapi.dto.ProductDTO;
import com.example1.consumeapi.mapper.ProductMapper;
import com.example1.consumeapi.model.Product;
import com.example1.consumeapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor

@RestController

@RequestMapping("/products")
public class ProductController {

    //Adding Loggers with slf4j
    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

   @Autowired(required=true)
   private  ProductService productService;

    @Autowired(required=true)
    private ProductMapper productMapper;

    //Rest Template
    @Autowired
    private RestTemplate restTemplate;

    private static String url="http://localhost:8082/departments";

    @GetMapping("/departments")
    public List<Object> getDepartments(){
        Object[] departments = restTemplate.getForObject(url, Object[].class);
        LOGGER.info("Inside getDepartments of ProductController");
        LOGGER.trace("getDepartments - ProductController");
        return Arrays.asList(departments);
    }


    @PostMapping("/")
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO)
    {
        System.out.println(productDTO);
        Product product = productMapper.toProduct(productDTO);
        System.out.println("productMapper = " + productMapper);
        System.out.println(product);
        System.out.println("productService = " + productService);
        productService.save(product);
        LOGGER.info("Inside create of ProductController");
        LOGGER.trace("create-ProductController");
        return ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable("id") Long id)
    {
        Optional<Product> product = productService.findById(id);
        ProductDTO productDTO = productMapper.toProductDTO(product.get());
        LOGGER.info("Inside findById of ProductController");
        LOGGER.trace("findById-ProductController");
        return ResponseEntity.ok(productDTO);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable("id") Long id,@RequestBody ProductDTO productDTO)
    {

        Product product =productMapper.toProduct(productDTO);
        product.setId(id);

        productService.save(product);
        LOGGER.info("Inside update of ProductController");
        LOGGER.trace("update-ProductController");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(productDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id)
    {
        productService.deleteById(id);
        LOGGER.info("Inside delete of ProductController");
        LOGGER.trace("delete-ProductController");
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

   @GetMapping("/getmap")
   public ResponseEntity<List<ProductDTO>> findAll()
    {
        List<ProductDTO> productList = productMapper.toProductDTOs(productService.findAll());
        LOGGER.info("Inside findAll of ProductController");
        LOGGER.trace("findAll-ProductController");
        return ResponseEntity.ok(productList);
    }
}
