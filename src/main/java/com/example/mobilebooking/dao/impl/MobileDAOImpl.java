package com.example.mobilebooking.dao.impl;

import static com.example.mobilebooking.constants.MobileBookingConstants.*;
import com.example.mobilebooking.dao.MobileDAO;
import com.example.mobilebooking.domain.Mobile;
import com.example.mobilebooking.exception.MobileBookingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author irfan.nagoo
 * @created 10-03-2023
 */

@Repository
public class MobileDAOImpl implements MobileDAO {

    @Value("${mobilebooking.datastore}")
    private String dataStore;

    @Override
    public List<Mobile> getMobileAll() {
        return readFromFile();
    }

    @Override
    public List<Mobile> getMobile(String modelName) {
        List<Mobile> mobileList = readFromFile();
        return mobileList.stream()
                .filter(mobile -> mobile.getModelName().equalsIgnoreCase(modelName))
                .collect(Collectors.toList());
    }

    @Override
    public void updateMobile(Mobile mobile, String bookedBy) {
        writeToFile(mobile, bookedBy);
    }

    private List<Mobile> readFromFile() {
        List<Mobile> mobileRecordList = new ArrayList<>();
        BufferedReader reader = null;
        try {
            URL resourceURL = this.getClass().getClassLoader().getResource(dataStore);
            reader = new BufferedReader(new FileReader(new File(resourceURL.toURI())));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] record = line.split(",");
                Mobile mobile = new Mobile(Long.parseLong(record[0]), record[1]);
                mobile.setAvailable(Boolean.valueOf(record[2]));
                mobile.setBookedBy(NULL.equalsIgnoreCase(record[3]) ? null : record[3]);
                mobile.setBookingDate(NULL.equalsIgnoreCase(record[4]) ? null : LocalDate.parse(record[4]));
                mobileRecordList.add(mobile);
            }
        } catch (Exception e) {
            // suppressing checked exceptions and throwing custom runtime exception
            // instead for common exception handling. This also avoids each method
            // declaring same set of exception in the throws clause
            throw new MobileBookingException(e.getMessage(), e);
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException ignore) {
                }
            }
        }
        return mobileRecordList;
    }

    private void writeToFile(Mobile mobile, String bookedBy) {
        try {
            StringBuilder output = new StringBuilder();
            URL resourceURL = this.getClass().getClassLoader().getResource(dataStore);
            List<String> lines = Files.readAllLines(Paths.get(resourceURL.toURI()));
            boolean isUpdated = false;
            for (String line : lines) {
                if (line.toUpperCase().contains(mobile.getModelName().toUpperCase()) &&
                        line.toUpperCase().contains(bookedBy.toUpperCase()) && !isUpdated) {
                    line = mobile.toString();
                    isUpdated = true;
                }
                output.append(line).append(System.lineSeparator());
            }
            Files.write(Paths.get(resourceURL.toURI()), output.toString().getBytes());
        } catch (Exception e) {
            throw new MobileBookingException(e.getMessage(), e);
        }
    }

}
