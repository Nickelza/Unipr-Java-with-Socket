package it.unipr.ingegneria.impl;

import it.unipr.ingegneria.request.search.SearchRequest;
import it.unipr.ingegneria.request.search.WineSearchCriteria;
import it.unipr.ingegneria.response.ModelListResponse;
import it.unipr.ingegneria.response.ModelResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class SearchRequestManagerTest {
    private WineShop shop;
    private SearchRequest o;
    private WineSearchCriteria wineSearchTestCriteria;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.shop=new WineShop("prova", mock(Warehouse.class));
        this.o=mock(SearchRequest.class);
        wineSearchTestCriteria = new WineSearchCriteria()
                .setName("vino 1")
                .setYear(2020);
    }

    @Test
    void ShouldFillWithResponseSuccess() {
        when(o.getModel()).thenReturn(wineSearchTestCriteria);
        ModelListResponse response = SearchRequestManager.fillWithResponse(shop,  o);
        assertEquals( ModelListResponse.class, response.getClass());
    }
}