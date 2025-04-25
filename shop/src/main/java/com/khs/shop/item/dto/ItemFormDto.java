package com.khs.shop.item.dto;

import com.khs.shop.constant.ItemSellStatus;
import com.khs.shop.entity.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ItemFormDto {
    private Long id;

    @NotBlank(message = "상품명은 필수 항목입니다.")
    private String itemNm;

    @NotNull(message = "가격은 필수 항목입니다.")
    private int price;

    @NotNull(message = "재고는 필수 항목입니다.")
    private int stockNumber;

    @NotBlank(message = "상품설명은 필수 항목입니다.")
    private String itemDetail;

    private ItemSellStatus itemSellStatus; // ✅ 추가됨

    private List<ItemImgDto> itemImgDtos = new ArrayList<>();

    private List<Long> itemImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Item createItem() {
        return modelMapper.map(this, Item.class);
    }

    public static ItemFormDto of(Item item) {
        return modelMapper.map(item, ItemFormDto.class);
    }
}
