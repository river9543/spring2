package com.khs.shop.repository;

import com.khs.shop.constant.ItemSellStatus;

import com.khs.shop.entity.Item;
import com.khs.shop.entity.QItem;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Table;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("상품저장테스트")
    public void createItemTest(){
        Item item=new Item();
        item.setItemNm("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());
        Item saveItem=itemRepository.save(item);
        System.out.println(saveItem.toString());


    }

    public void createItemList(){
        for(int i=1;i<=10;i++){
           Item item=new Item();
           item.setItemNm("테스트 상품"+i);
           item.setPrice(10000+i);
           item.setItemDetail("테스트 상품 상세 설명"+i);
           item.setItemSellStatus(ItemSellStatus.SELL);
           item.setStockNumber(100);
           item.setRegTime(LocalDateTime.now());
           item.setUpdateTime(LocalDateTime.now());
           Item saveItem=itemRepository.save(item);



        }
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNmTest(){
        this.createItemList();
        List<Item>itemList=itemRepository.findByItemNm("테스트 싱품1");
        for (Item item:itemList){
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("상품명,상품 상세설명or테스트")
    public void findByItemNmOrItemDetailTest(){
        this.createItemList();
        List<Item>itemList=itemRepository
                .findByItemNmOrItemDetail("테스트 상품1","테스트 상품 상세 설명3");
        for (Item item:itemList){
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("가격 Less Than 테스트")
    public void findByPriceLessThanTest(){
        this.createItemList();
        List<Item> itemList=itemRepository.findByPriceLessThan(10005);
        for (Item item:itemList){
            System.out.println(item.toString());
        }
    }


    public void createItemList2(){
        for(int i=1;i<=5;i++){
            Item item = new Item();
            item.setItemNm("테스트 상품" + i);
            item.setPrice(10000 + i);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            itemRepository.save(item);
        }

        for(int i=6;i<=10;i++){
            Item item = new Item();
            item.setItemNm("테스트 상품" + i);
            item.setPrice(10000 + i);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SOLD_OUT);
            item.setStockNumber(0);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            itemRepository.save(item);
        }
    }


    @Test
    @DisplayName("가격 내림차순 조회 테스트")
    public void findByPriceLessThanOrderByPriceDesc(){
        this.createItemList();
        List<Item> itemList=
                itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
        for (Item item:itemList) {
            System.out.println(item.toString());

        }

    }

    @Test
    @DisplayName("@Query를 이용한 상품 조회 테스트")
    public void findByItemDetailTest(){
        this.createItemList();
        List<Item> itemList=itemRepository.findByItemDetail("테스트 상품 상세 설명");
        for (Item item:itemList){
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("nativeQuery 속성을 이용한 상품 조회 테스트")
    public void findByItemDetailMyNative(){
        this.createItemList();
        List<Item> itemList=itemRepository.findByItemDetailByNative("테스트 상품 상세 설명");
        for (Item item:itemList){
            System.out.println(item.toString());
        }
    }
    @Test
    @DisplayName("querydsl 테스트2")
    public void querydslTest2(){
        createItemList2();
        String itemDetail="테스트";
        int price=10003;
        String itemSellState="SELL";

        QItem item=QItem.item;

        BooleanBuilder builder=new BooleanBuilder();
        builder.and(item.itemDetail.like("%"+itemDetail+"%"));
        builder.and(item.price.gt(price));
        if (StringUtils.equals(itemSellState,ItemSellStatus.SELL)){
            builder.and(item.itemSellStatus.eq(ItemSellStatus.SELL));
        }

        Pageable pageable= PageRequest.of(0,5);
        Page<Item>findALL=itemRepository.findAll(builder,pageable);
        System.out.println("전체갯수"+findALL.getNumberOfElements());
        List<Item> content=findALL.getContent();
        for (Item item1:content){
            System.out.println(item1);
        }
    }

    @Test
    @DisplayName("Querydsl 조회테스트1")
    public void queryDslTest(){
        this.createItemList();
        JPAQueryFactory queryFactory=new JPAQueryFactory(em);
        QItem qItem=QItem.item;
        JPAQuery<Item> query=queryFactory.selectFrom(qItem)
                .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(qItem.itemDetail.like("%"+"테스트 상품 상세 설명"+"%"))
                .orderBy(qItem.price.desc());
        List<Item> itemList=query.fetch();

        for (Item item:itemList){
            System.out.println(item.toString());
        }

    }

}