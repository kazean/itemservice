package hello.itemservice.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    public void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    public void save() throws Exception{
        //given
        Item itemA = new Item("itemA", 10000, 10);

        //when
        Item savedItem = itemRepository.save(itemA);

        //then
        assertThat(savedItem).isEqualTo(itemA);
    }

    @Test
    public void findAll() throws Exception{
        //given
        Item itemA = new Item("itemA", 10000, 10);
        Item itemB = new Item("itemB", 20000, 20);

        itemRepository.save(itemA);
        itemRepository.save(itemB);
        //when
        List<Item> items = itemRepository.findAll();
        //then
        assertThat(items.size()).isEqualTo(2);
    }

    @Test
    public void updateItem() throws Exception{
        //given
        Item itemA = new Item("itemA", 10000, 20);
        Item savedItem = itemRepository.save(itemA);
        //when
        Item updateParam = new Item("itemUpdate", 20000, 30);
        itemRepository.update(savedItem.getId(), updateParam);
        Item findItem = itemRepository.findById(savedItem.getId());

        //then
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }
}