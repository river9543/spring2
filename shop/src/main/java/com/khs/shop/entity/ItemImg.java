package com.khs.shop.entity;

import com.khs.shop.utils.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemImg extends BaseEntity {
    public Object setItem;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_img_id")
    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repImgYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public void updateItemImg(String imgName,String oriImgName,String imgUrl){
        this.imgName=imgName;
        this.oriImgName=oriImgName;
        this.imgUrl=imgUrl;
    }


}
