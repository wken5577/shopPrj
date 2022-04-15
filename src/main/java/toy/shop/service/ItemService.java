package toy.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.shop.entity.Category;
import toy.shop.entity.Item;
import toy.shop.entity.ItemImages;
import toy.shop.entity.User;
import toy.shop.repository.CategoryRepository;
import toy.shop.repository.item.ItemRepository;
import toy.shop.repository.UserRepository;
import toy.shop.web.dto.dtoresponse.ItemResponseDto;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public Long addItem(String username, Long categoryId, String name, int price, List<ItemImages> itemImages, String itemInfo) {
        User findUser = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalStateException("회원 정보가 없습니다."));

        Category findCategory = categoryRepository.findById(categoryId).orElseThrow(
                () -> new IllegalStateException("카테고리 정보가 없습니다.")
        );

        Item item = new Item(findCategory, name, price, findUser, itemImages, itemInfo);
        itemRepository.save(item);

        return item.getId();
    }

    public Long updateItem(Long categoryId, Long itemId, String name, int price, int quantity) {
        Category findCategory = categoryRepository.findById(categoryId).orElseThrow(
                () -> new IllegalStateException("카테고리 정보가 없습니다.")
        );

        Item target = itemRepository.findById(itemId).orElseThrow(
                () -> new IllegalStateException("상품 정보가 없습니다."));

        target.update(findCategory,name,price,quantity);

        return target.getId();
    }


    public Long deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
        return itemId;
    }

    @Transactional(readOnly = true)
    public Page<ItemResponseDto> findItemsByCategory(Long categoryId, Pageable pageable) {
        Page<Item> findItems = itemRepository.findByCategoryId(categoryId, pageable);

        Page<ItemResponseDto> result = findItems.map(ItemResponseDto::new);

        return result;
    }

    @Transactional(readOnly = true)
    public Page<ItemResponseDto> findAll(Pageable pageable) {
        Page<Item> itemPage = itemRepository.findAll(pageable);
        Page<ItemResponseDto> items = itemPage.map(ItemResponseDto::new);

        return items;
    }

    @Transactional(readOnly = true)
    public ItemResponseDto findById(Long itemId) {
        ItemResponseDto item = itemRepository.findById(itemId)
                .map(ItemResponseDto::new).get();

        return item;
    }

}
