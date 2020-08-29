package com.javatar.uploaddownloading.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "IMAGE")
@Data
@AllArgsConstructor
@Builder
public class Image {

    Image(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "path")
    private String path;
    @Column(name = "description")
    private String description;
    @Column(name = "url")
    private String url;
}
