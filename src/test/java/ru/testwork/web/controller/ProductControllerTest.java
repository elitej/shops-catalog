package ru.testwork.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.testwork.exception.ProductNotFoundException;
import ru.testwork.model.Product;
import ru.testwork.service.ProductService;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-test-mvc.xml")
@WebAppConfiguration
public class ProductControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private ProductService productServiceMock;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        reset(productServiceMock);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void showItem_ShouldAddItemToModelAndRenderItemView() throws Exception {
        when(productServiceMock.findById(5)).thenReturn(new Product());

        mockMvc.perform(get("/product/{productId}", 5))
                .andExpect(status().isOk())
                .andExpect(view().name("product"))
                .andExpect(forwardedUrl("/WEB-INF/views/product.jsp"));
        // TODO: 12.04.2017 Add checks for item properties
        verify(productServiceMock, times(1)).findById(5);
        verifyNoMoreInteractions(productServiceMock);
    }

    @Test
    public void showItem_ItemEntryNotFound_ShouldRender404View() throws Exception {
        ProductNotFoundException exception = new ProductNotFoundException(1);
        when(productServiceMock.findById(1)).thenThrow(exception);

        mockMvc.perform(get("/product/{productId}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("error/404"))
                .andExpect(forwardedUrl("/WEB-INF/views/error/404.jsp"))
                .andExpect(model().attribute("message", exception.getMessage()));

        verify(productServiceMock, times(1)).findById(1);
        verifyNoMoreInteractions(productServiceMock);
    }
}
