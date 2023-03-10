package com.example.mobilebooking.dao;

import com.example.mobilebooking.domain.Mobile;

import java.util.List;

/**
 * @author irfan.nagoo
 * @created 10-03-2023
 */
public interface MobileDAO {

    /**
     * Returns the list of all the mobile phones
     * @return
     */
    List<Mobile> getMobileAll();

    /**
     *  Returns the mobile object based on the model name
     * @param modelName
     * @return mobile object
     */
    List<Mobile> getMobile(String modelName);

    /**
     *  Updates the existing mobile object
     * @param mobile
     */
    void updateMobile(Mobile mobile, String bookedBy);
}
