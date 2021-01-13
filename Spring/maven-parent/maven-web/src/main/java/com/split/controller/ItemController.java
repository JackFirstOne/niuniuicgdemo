package com.split.controller;

import com.split.pojo.Item;
import com.split.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ItemController {

      @Autowired
      private ItemService itemService;

      @RequestMapping("/showItem/{id}")
      public String getItemById(@PathVariable(value = "id") int id, Model model){
            Item itemById = itemService.findItemById(id);
            model.addAttribute("item",itemById);
            return "item";
      }
}
