package com.example.pos.controller;
import com.example.pos.Service.Products_Service;
import com.example.pos.entity.FileImageDB;
import com.example.pos.entity.Products;
import com.example.pos.repository.Products_Repository;
import com.example.pos.repository.getalldataproducts;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.el.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriBuilderFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController{

    @Autowired
    private Products_Repository productsRepository;
    @Autowired
    private Products_Service productsService;
    @GetMapping(value = "/getallproducts")
    public ResponseEntity<List<Products>> getallCustomer()
    {
        return new ResponseEntity<List<Products>>(productsRepository.findAll(), HttpStatus.OK);
    }
    @PostMapping(value = "/addnewproducts")
    public ResponseEntity<Products> addUser(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name")String name,
            @RequestParam("description")String description,
            @RequestParam("category_id")int category_id,
            @RequestParam("quantity")int quantity,
            @RequestParam("brand")String brand,
            @RequestParam("model")String model,
            @RequestParam("configuration")String configuration,
            @RequestParam("price")double price,
            @RequestParam("feature")int feature,
            @RequestParam("create_at")int create_at

            ) throws IOException, URISyntaxException {
        productsService.uploadFile(file,name,description,quantity,brand,category_id,model,configuration,price,feature,create_at,brand);
        return new ResponseEntity<Products>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/updateproducts/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") Integer id, @RequestBody Products products ){
        Products pro = new Products();
        pro = productsRepository.findById(id).get();
        pro.setName(products.getName());
        pro.setDescription(products.getDescription());
        pro.setCategory_id(products.getCategory_id());
        pro.setQuantity(products.getQuantity());
        pro.setBrand(products.getBrand());
        pro.setModel(products.getModel());
        pro.setConfiguration(products.getConfiguration());
        pro.setPrice(products.getPrice());
        pro.setFeature(products.getFeature());
        pro.setCreate_at(products.getCreate_at());
        productsRepository.save(pro);
        return new ResponseEntity<String>("This product was updated",HttpStatus.OK);
    }
    @DeleteMapping(value = "/deleteproduct/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id){
        productsRepository.deleteById(id);
        return new ResponseEntity<String>("This product was deleted",HttpStatus.OK);
    }
    @GetMapping(value = "/photo/{id}")
    public ResponseEntity<String> findProductPhoto(@PathVariable("id") Integer id, HttpServletResponse response) throws SQLException, IOException {
        String image = productsRepository.findImageByID(id);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        String uri=ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(image).toUriString();
        if(image==null){
            return new ResponseEntity<>("No image for this product",HttpStatus.EXPECTATION_FAILED);
        }
        else{
            return new ResponseEntity<>(uri,HttpStatus.OK);
        }
    }
    @GetMapping(value = "images/{image}")
    public ResponseEntity<byte[]> downloadFileFromDB(@PathVariable(name = "image") String filename) throws IOException {
        byte[] imageData= productsService.downloadImage(filename);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);
    }
}
