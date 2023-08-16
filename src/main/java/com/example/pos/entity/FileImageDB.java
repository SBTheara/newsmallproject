package com.example.pos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "fileImg")
@NoArgsConstructor
@Getter
@Setter
public class FileImageDB {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "img_id")
    private String id;
    @Column(length = 3000)
    private String name;
    @Column(length = 3000)
    private String type;
    @Lob
    @Column(length = 300000)
    private byte[] data;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "product_id")
//    private Products products;

    public FileImageDB(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }
}
