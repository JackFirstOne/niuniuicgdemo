package com.ssm.service.impl;

import com.ssm.dao.ItemMapper;
import com.ssm.pojo.Item;
import com.ssm.service.ItemService;
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
