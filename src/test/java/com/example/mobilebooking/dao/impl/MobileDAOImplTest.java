package com.example.mobilebooking.dao.impl;

import com.example.mobilebooking.domain.Mobile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
class MobileDAOImplTest {

    @InjectMocks
    private MobileDAOImpl mobileDAO;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(mobileDAO, "dataStore", "test-mobile.dat");
    }

    @Test
    void getMobileAll() {
        List<Mobile> mobileList = mobileDAO.getMobileAll();
        assertEquals(10, mobileList.size());
        assertEquals("Samsung Galaxy S9", mobileList.get(0).getModelName());
    }

    @Test
    void getMobile() {
        List<Mobile> mobileList = mobileDAO.getMobile("Samsung Galaxy S8");
        assertEquals(2, mobileList.size());
        assertEquals("Samsung Galaxy S8", mobileList.get(0).getModelName());
    }

    @Test
    void updateMobile() {
        mobileDAO.updateMobile(new Mobile(10l, "Samsung Galaxy S8"), "TestUser");
        assertTrue(true);
    }
}