package com.aptech.springboot.api;

import com.aptech.springboot.entity.Product;
import com.aptech.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(path = "api/v1/products")
public class ProductApi {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Product>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Product> save(@RequestBody Product product){
        return ResponseEntity.ok(productService.save(product));
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<?> findById(@PathVariable String id){
        Optional<Product> optionalProduct = productService.findById(id);
        if (!optionalProduct.isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalProduct.get());
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id){
        if (!productService.findById(id).isPresent()){
            ResponseEntity.badRequest().build();
        }
        productService.deletedById(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<Product> update(@PathVariable String id, @RequestBody Product updateProduct){
        Optional<Product> optionalProduct = productService.findById(id);
        if (!optionalProduct.isPresent()){
            ResponseEntity.badRequest().build();
        }
        Product existingProduct = optionalProduct.get();
        existingProduct.setProductName(updateProduct.getProductName());
        existingProduct.setSlug(updateProduct.getSlug());
        existingProduct.setDescription(updateProduct.getDescription());
        existingProduct.setDetail(updateProduct.getDetail());
        existingProduct.setPrice(updateProduct.getPrice());
        existingProduct.setThumbnail(updateProduct.getThumbnail());
        existingProduct.setProducer(updateProduct.getProducer());
        existingProduct.setDeletedAt(updateProduct.getDeletedAt());
        existingProduct.setCreatedBy(updateProduct.getCreatedBy());
        existingProduct.setUpdatedBy(updateProduct.getUpdatedBy());
        existingProduct.setDeletedBy(updateProduct.getDeletedBy());
        return ResponseEntity.ok(productService.save(existingProduct));
    }
}
