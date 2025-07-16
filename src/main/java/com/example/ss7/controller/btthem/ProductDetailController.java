package com.example.ss7.controller.btthem;

import com.example.ss7.entity.btthem.ProductDetail;
import com.example.ss7.repository.btthem.ProductDetailRepository;
import com.example.ss7.repository.btthem.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-details")
public class ProductDetailController {

    @Autowired
    private ProductDetailRepository productDetailRepo;

    @Autowired
    private ProductRepository productRepo;

    @GetMapping
    public List<ProductDetail> getAll() {
        return productDetailRepo.findAll();
    }

    @GetMapping("/{id}")
    public ProductDetail getById(@PathVariable Long id) {
        return productDetailRepo.findById(id).orElse(null);
    }

    @PostMapping
    public ProductDetail create(@RequestBody ProductDetail detail) {
        // Optional: Kiểm tra product có tồn tại
        if (detail.getProduct() != null) {
            productRepo.findById(detail.getProduct().getProductId()).orElseThrow();
        }
        return productDetailRepo.save(detail);
    }

    @PutMapping("/{id}")
    public ProductDetail update(@PathVariable Long id, @RequestBody ProductDetail detail) {
        ProductDetail old = productDetailRepo.findById(id).orElseThrow();
        old.setYearMaking(detail.getYearMaking());
        old.setColor(detail.getColor());
        old.setSize(detail.getSize());
        old.setPrice(detail.getPrice());
        old.setProduct(detail.getProduct());
        return productDetailRepo.save(old);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productDetailRepo.deleteById(id);
    }
}
