package com.ea.ordermanagementapi.configurator;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import com.ea.ordermanagementapi.domain.Customer;
import com.ea.ordermanagementapi.domain.Product;
import com.ea.ordermanagementapi.domain.enums.ProductStatus;
import com.ea.ordermanagementapi.domain.enums.QuantityType;
import com.ea.ordermanagementapi.repository.CustomerRepository;
import com.ea.ordermanagementapi.repository.ProductRepository;
import com.ea.ordermanagementapi.service.CacheService;

@Configuration
@EnableMongoAuditing
public class MongoConfiguration
{
    @Autowired
    private CacheService cacheService;

    @Bean
    CommandLineRunner init(CustomerRepository customerRepository, ProductRepository productRepository)
    {
        cacheService.evictAllCaches();

        return strings -> {
            initCustomerData(customerRepository);
            initProductData(productRepository);
        };
    }

    private void initCustomerData(CustomerRepository customerRepository)
    {
        customerRepository.deleteAll();
        customerRepository.save(new Customer("Ercin", "Akcay"));
        customerRepository.save(new Customer("Default", "Sample"));
        customerRepository.save(new Customer("Spring", "On"));
    }

    private void initProductData(ProductRepository productRepository)
    {
        productRepository.deleteAll();
        productRepository.save(new Product("Domates",
                "Mukemmel tatli domates.",
                BigDecimal.valueOf(7),
                1000,
                QuantityType.KG,
                ProductStatus.ACTIVE));
        productRepository.save(new Product("Sut",
                "Mukemmel beyaz sut.",
                BigDecimal.valueOf(6),
                200,
                QuantityType.ITEM,
                ProductStatus.ACTIVE));
        productRepository.save(new Product("Enfes Cikolata",
                "Temiz cikolata. Agizda dagiliyor.",
                BigDecimal.valueOf(14),
                10,
                QuantityType.ITEM,
                ProductStatus.ACTIVE));
        productRepository.save(new Product("Iphone Telefon",
                "Temiz iphone telefon.",
                BigDecimal.valueOf(700),
                10,
                QuantityType.ITEM,
                ProductStatus.PASSIVE));
        productRepository.save(new Product("Muz",
                "Cikita Muz",
                BigDecimal.valueOf(14),
                1000,
                QuantityType.KG,
                ProductStatus.REMOVED));
    }

}