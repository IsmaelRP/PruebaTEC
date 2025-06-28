package com.springboot.microservice.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.springboot.microservice.prices.infraestructure.outbound.database.PricesH2Repository;
import com.springboot.microservice.prices.infraestructure.outbound.database.entity.Prices;
import com.springboot.microservice.prices.infraestructure.outbound.database.entity.PricesId;
import com.springboot.microservice.shared.CurrEnum;
import com.springboot.microservice.users.infraestructure.outbound.database.UsersH2Repository;
import com.springboot.microservice.users.infraestructure.outbound.database.entity.Users;

@Component
public class CsvLoader implements CommandLineRunner {

    private final PricesH2Repository priceRepository;
    
    private final UsersH2Repository usersRepository;

    public CsvLoader(PricesH2Repository priceRepository, UsersH2Repository usersRepository) {
        this.priceRepository = priceRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public void run(String... args) throws Exception {
    	usersRepository.save(new Users("test", "pass"));
    	
        InputStream inputStream = getClass().getResourceAsStream("/prices.csv");
        if (inputStream == null) {
            throw new FileNotFoundException("data.csv not found in resources");
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line = reader.readLine(); // Skip header

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");

                Prices price = new Prices();
                price.setId(new PricesId(
                		Integer.parseInt(fields[4]),
                		Short.parseShort(fields[0]),
                		Integer.parseInt(fields[3])
                ));
                price.setStartDate(LocalDateTime.parse(fields[1], formatter));
                price.setEndDate(LocalDateTime.parse(fields[2], formatter));
                price.setPriority(Short.parseShort(fields[5]));
                price.setPrice(Float.parseFloat(fields[6]));
                price.setCurrency(CurrEnum.valueOf(fields[7]));
                price.setLastUpdate(LocalDateTime.parse(fields[8], formatter));
                price.setLastUpdateBy(fields[9]);

                priceRepository.save(price);
            }
        }
    }
}