package com.khs.shop.order.entity;

import com.khs.shop.member.entity.Member;
import com.khs.shop.order.constant.OrderStatus;
import com.khs.shop.utils.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "orders")
@NoArgsConstructor
public class Order extends BaseEntity {//주문
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true,
    fetch = FetchType.LAZY)
    private List<OrderItem> orderItems=new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @CreationTimestamp
    private LocalDateTime regTime;

    @CreationTimestamp
    private LocalDateTime updateTime;

}
