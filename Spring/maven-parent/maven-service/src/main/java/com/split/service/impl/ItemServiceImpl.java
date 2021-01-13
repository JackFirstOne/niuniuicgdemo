package com.split.service.impl;

import com.split.dao.ItemMapper;
import com.split.pojo.Item;
import com.split.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;


    @Override
    public Item findItemById(int id) {
        return itemMapper.findById(id);
    }
}
