package lk.ijse.spring.controller;

import lk.ijse.spring.dto.ItemDTO;
import lk.ijse.spring.service.ItemService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * `@authority Tharindu Dilan`
 * 9:19 AM
 * 2023-10-09 - 10 - 2023
 */
@RestController
@RequestMapping("/item")
@CrossOrigin
public class ItemController {
    @Autowired
    ItemService itemService;

    @PostMapping
    public ResponseUtil saveItem(ItemDTO item){
        itemService.addItem(item);
        return new ResponseUtil("Ok","Successfully saved",item.getCode());
    }

    @DeleteMapping(params = {"id"})
    public ResponseUtil deleteItem(String id){
        itemService.deleteItem(id);
        return new ResponseUtil("Ok","Successfully Deleted",id);
    }

    @GetMapping
    public ResponseUtil getAllItem(){
        return new ResponseUtil("Ok","Successfully loaded",itemService.getAllItem());
    }

    @GetMapping(params = {"id"})
    public ResponseUtil findItem(String id){
        return new ResponseUtil("Ok","Successfully fetched",itemService.findItem(id));
    }

    @PutMapping
    public ResponseUtil updateItem(@RequestBody ItemDTO itemDTO){
        itemService.updateItem(itemDTO);
        return new ResponseUtil("Ok","Successfully updated",itemDTO.getCode());
    }
}
