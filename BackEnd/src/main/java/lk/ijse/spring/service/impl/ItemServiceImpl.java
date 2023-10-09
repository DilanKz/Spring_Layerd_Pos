package lk.ijse.spring.service.impl;


import lk.ijse.spring.dto.ItemDTO;
import lk.ijse.spring.entity.Item;
import lk.ijse.spring.repo.ItemRepo;
import lk.ijse.spring.service.ItemService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * `@authority Tharindu Dilan`
 * 12:15 PM
 * 2023-10-09 - 10 - 2023
 */

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepo itemRepo;

    @Autowired
    ModelMapper mapper;

    @Override
    public void addItem(ItemDTO dto) {
        Item map = mapper.map(dto, Item.class);
        itemRepo.save(map);
    }

    @Override
    public void deleteItem(String id) {
        itemRepo.deleteById(id);
    }

    @Override
    public List<ItemDTO> getAllItem() {
        List<Item> all = itemRepo.findAll();
        return mapper.map(all, new TypeToken<List<ItemDTO>>() {
        }.getType());
    }

    @Override
    public ItemDTO findItem(String id) {
        Item item= itemRepo.findById(id).get();
        return mapper.map(item,ItemDTO.class);
    }

    @Override
    public void updateItem(ItemDTO obj) {
        Item map = mapper.map(obj, Item.class);
        itemRepo.save(map);
    }

}
