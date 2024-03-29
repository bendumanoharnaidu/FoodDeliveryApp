package org.swiggy.catalogservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.swiggy.catalogservice.dto.request.MenuItemIdsRequest;
import org.swiggy.catalogservice.dto.request.MenuItemListRequest;
import org.swiggy.catalogservice.dto.request.MenuItemRequest;
import org.swiggy.catalogservice.dto.response.MenuItemListResponse;
import org.swiggy.catalogservice.dto.response.MenuItemResponse;
import org.swiggy.catalogservice.execptions.RestaurantNotFoundException;
import org.swiggy.catalogservice.service.MenuItemService;
import org.swiggy.catalogservice.util.UserUtils;

@RestController
@RequestMapping("/menu-items")
public class MenuItemController {
    @Autowired
    private MenuItemService menuItemService;
    @Autowired
    private UserUtils userUtils;
    @PostMapping("/{restaurantId}")
    public ResponseEntity<MenuItemResponse> addMenuItems(@PathVariable("restaurantId") Long restaurantId, @RequestBody MenuItemListRequest request) {
        if(!userUtils.validateToken(request.getEmail(), request.getToken())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        MenuItemResponse response = menuItemService.addMenuItem(restaurantId, request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PutMapping("/{itemId}")
    public ResponseEntity<MenuItemResponse> updateMenuItem(@PathVariable("itemId") Long itemId, @RequestBody MenuItemRequest request) {
        if(!userUtils.validateToken(request.getEmail(), request.getToken())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        MenuItemResponse response = menuItemService.updateMenuItem(itemId, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MenuItemListResponse> getMenuItems(@RequestBody MenuItemIdsRequest request) throws RestaurantNotFoundException {
        MenuItemListResponse response = menuItemService.getMenuItems(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
