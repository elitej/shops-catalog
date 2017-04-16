package ru.testwork.web.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.testwork.model.Shop;
import ru.testwork.service.ShopService;

import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-test-mvc.xml")
@WebAppConfiguration
public class ShopsCatalogControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ShopService shopServiceMock;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        reset(shopServiceMock);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void showShopsForPage_ShouldAddShopsToModelAndRenderMainView() throws Exception {
        Page pageMock = Mockito.mock(Page.class);
        when(pageMock.getContent()).thenReturn(Arrays.asList(new Shop(), new Shop()));
        when(pageMock.getNumber()).thenReturn(0);
        when(pageMock.getTotalPages()).thenReturn(1);
        when(shopServiceMock.findForPage(1)).thenReturn(pageMock);

        mockMvc.perform(get("/shops/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("shops"))
                .andExpect(forwardedUrl("/WEB-INF/views/shops.jsp"))
                .andExpect(model().attribute("shops", hasSize(2)))
                .andExpect(model().attribute("currentIndex", 1))
                .andExpect(model().attribute("totalPages", 1));

        verify(shopServiceMock, times(1)).findForPage(1);
        verifyNoMoreInteractions(shopServiceMock);
    }
}
