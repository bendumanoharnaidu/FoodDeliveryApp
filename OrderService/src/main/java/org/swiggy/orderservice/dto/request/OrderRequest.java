package org.swiggy.orderservice.dto.request;

import lombok.Data;
import org.swiggy.orderservice.model.MenuItem;

import java.util.List;
@Data
public class OrderRequest {
    private Long userId;
    private String email;
    private String token;
    private Long restaurantId;
    private List<MenuItem> menuItems;
}
