package com.example.pos.Service;

import com.example.pos.entity.Products;
import com.example.pos.repository.Products_Repository;
import com.example.pos.util.image_util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Service
public class Products_Service {
    @Autowired
    private Products_Repository productsRepository;
    private final String FILE_PATH="C:\\Users\\Public\\Pictures\\";
    private final String FOLDER_PATH = "C:/Users/Theara/Pictures/Camera Roll";
//    public void saveProductToDB(MultipartFile file,String name,String description,int quantity, String brand,
//    String model, String configuration,double price,int feature,int Create_at,String image){
//        String filename = FOLDER_PATH+file.getOriginalFilename();
//
//        if(filename.contains("..")){
//            System.out.println("not a valid file");
//        }
//        Products products = new Products();
//        products.setName(name);
//        products.setDescription(description);
//        products.setQuantity(quantity);
//        products.setBrand(brand);
//        products.setModel(model);
//        products.setConfiguration(configuration);
//        products.setPrice(price);
//        products.setFeature(feature);
//        products.setCreate_at(Create_at);
//        productsRepository.save(products);
//    }

    public String uploadFile(MultipartFile file,String name,String description,int quantity, String brand,int category_id,
                             String model, String configuration,double price,int feature,int Create_at,String image) throws IOException, URISyntaxException {
        String filepath =FILE_PATH + file.getOriginalFilename();
        Products productsdata = productsRepository.save(Products.builder()
                .name(name)
                .description(description)
                .category_id(category_id)
                .quantity(quantity)
                .brand(brand)
                .model(model)
                .configuration(configuration)
                .price(price)
                .feature(feature)
                .Create_at(Create_at)
                .image(image_util.compressImage(file.getBytes()))
                .imageFilePath(filepath)
                .imageName(file.getOriginalFilename())
                .build()
        );
        file.transferTo(new File(filepath));
        if(productsdata != null){
            return "upload successful "+file.getOriginalFilename();
        }
        return null;
    }
    public byte[] downloadImage(String filename) throws IOException {
        Products dbImageData= productsRepository.findByImageName(filename).get();
        String filepath=dbImageData.getImageFilePath();
        byte[] image = Files.readAllBytes(new File(filepath).toPath());
        return image;
    }
}
