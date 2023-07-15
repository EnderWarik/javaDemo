package com.example.demo.service.user;

import com.example.demo.model.Cart;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.CartRepository;
import com.example.demo.service.cart.CartService;
import com.example.demo.service.cart.CartServiceImpl;
import com.example.demo.service.cart.argument.CreateCartArgument;
import com.example.demo.service.cart.argument.UpdateCartArgument;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;


public class CartServiceTest {
    private final CartRepository cartRepository = Mockito.mock(CartRepository.class);

    private final CartService service = new CartServiceImpl(cartRepository);
    @Test
    public void create()
    {
        Cart cart = Mockito.mock(Cart.class);
            CreateCartArgument argument = CreateCartArgument.builder()
                .products(List.of(
                                Product.builder()
                                        .title("1")
                                        .price(1L)
                                        .category(Category.builder()
                                                .title("1-11")
                                                .build())
                                        .build()
                        )
                ).build();

        Mockito.when(cartRepository.save(any())).thenReturn(cart);

        Cart actual = service.create(argument);

        //захват аргумента, переданного методу
        ArgumentCaptor<Cart> cartArgumentCaptor =  ArgumentCaptor.forClass(Cart.class);
        Mockito.verify(cartRepository).save(cartArgumentCaptor.capture());
        Cart capturedCart = cartArgumentCaptor.getValue();

        Cart expectedCaptureCart = Cart.builder()
                .products(List.of(
                                Product.builder()
                                        .title("1")
                                        .price(1L)
                                        .category(Category.builder()
                                                .title("1-11")
                                                .build())
                                        .build()
                        )
                ).build();

        Assertions.assertThat(capturedCart)
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .isEqualTo(expectedCaptureCart);

        Assertions.assertThat(actual).isEqualTo(cart);
    }

    @Test
    public void update()
    {
        Cart cart = Mockito.mock(Cart.class);
        UUID id = UUID.randomUUID();
        List<Product> listOfProducts = List.of(
                Product.builder()
                        .title("1")
                        .price(1L)
                        .category(Category.builder()
                                .title("1-11")
                                .build())
                        .build()
        );

        Cart updating = Cart.builder()
                .id(id)
                .products(listOfProducts)
                .build();
        UpdateCartArgument argument = UpdateCartArgument.builder()
                .products(listOfProducts)
                .build();


        Mockito.when(cartRepository.findById(id)).thenReturn(Optional.of(updating));
        Mockito.when(cartRepository.save(any())).thenReturn(cart);

        Cart actual = service.update(id,argument);

        ArgumentCaptor<UUID> uuidCartArgumentCaptor =  ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<Cart> cartArgumentCaptor =  ArgumentCaptor.forClass(Cart.class);

        Mockito.verify(cartRepository).findById(uuidCartArgumentCaptor.capture());
        Mockito.verify(cartRepository).save(cartArgumentCaptor.capture());

        Cart capturedCart = cartArgumentCaptor.getValue();
        UUID uuidCapturedCart = uuidCartArgumentCaptor.getValue();

        Cart expectedCaptureCart = Cart.builder()
                .id(id)
                .products(listOfProducts)
                .build();


        Assertions.assertThat(uuidCapturedCart).isEqualTo(id);
        Assertions.assertThat(capturedCart)
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .isEqualTo(expectedCaptureCart);

        Assertions.assertThat(actual).isEqualTo(cart);
    }

    @Test
    public void getExisting()
    {
        Cart cart = Mockito.mock(Cart.class);

        UUID id = UUID.randomUUID();

        Mockito.when(cartRepository.findById(id)).thenReturn(Optional.of(cart));
        Mockito.when(cartRepository.save(any())).thenReturn(cart);

        Cart actual = service.getExisting(id);

        ArgumentCaptor<UUID> uuidCartArgumentCaptor =  ArgumentCaptor.forClass(UUID.class);

        Mockito.verify(cartRepository).findById(uuidCartArgumentCaptor.capture());

        UUID uuidCapturedCart = uuidCartArgumentCaptor.getValue();

        Assertions.assertThat(uuidCapturedCart).isEqualTo(id);

        Assertions.assertThat(actual).isEqualTo(cart);
    }

    @Test
    public void list()
    {

        List<Cart> expectedCards = new ArrayList<Cart>();
        Mockito.when(cartRepository.findAll()).thenReturn(expectedCards);

        List<Cart> cards = service.list();

        Mockito.verify(cartRepository).findAll();

        Assertions.assertThat(cards)
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .isEqualTo(expectedCards);

    }
}
