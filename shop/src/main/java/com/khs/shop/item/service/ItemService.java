package com.khs.shop.item.service;

import com.khs.shop.entity.Item;
import com.khs.shop.entity.ItemImg;
import com.khs.shop.item.dto.ItemFormDto;
import com.khs.shop.item.repository.ItemImgRepository;
import com.khs.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemImgRepository itemImgRepository;
    private final ItemImageService itemImageService;

    public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws IOException {

        Item item=itemFormDto.createItem();
        itemRepository.save(item);

        for(int i=0;i<itemImgFileList.size();i++){
            ItemImg itemImg=new ItemImg();
            itemImg.setItem(item);
            if(i==0){
                itemImg.setRepImgYn("Y");
            }else {
                itemImg.setRepImgYn("N");
            }
            itemImageService.saveItemImg(itemImg,itemImgFileList.get(i));


        }

        return item.getId();

    }
}
