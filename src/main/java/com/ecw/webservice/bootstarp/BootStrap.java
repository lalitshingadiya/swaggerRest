package com.ecw.webservice.bootstarp;

import com.ecw.webservice.entity.Category;
import com.ecw.webservice.entity.Customer;
import com.ecw.webservice.repository.CategoryRepository;
import com.ecw.webservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrap implements CommandLineRunner {

    CategoryRepository categoryRepository;

    CustomerRepository  customerRepository;

    public BootStrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("#######  Loading Data   #######");

        loadCategoryData();

        loadCustomerData();


        System.out.println("Total Category :" + categoryRepository.count());
        System.out.println("Total Customer :" + customerRepository.count());
    }

    private void loadCustomerData() {
        customerRepository.save(Customer.builder().firstName("Joe").lastName("Newman").build());
        customerRepository.save(Customer.builder().firstName("Michael").lastName("Michael").build());
        customerRepository.save(Customer.builder().firstName("David").lastName("Winter").build());
        customerRepository.save(Customer.builder().firstName("Anne").lastName("Hine").build());
        customerRepository.save(Customer.builder().firstName("Alice").lastName("Eastman").build());
        customerRepository.save(Customer.builder().firstName("Laid").lastName("Sangria").build());

    }

    private void loadCategoryData() {
        categoryRepository.save(Category.builder().name("Fruit").build());
        categoryRepository.save(Category.builder().name("Dried").build());
        categoryRepository.save(Category.builder().name("Fresh").build());
        categoryRepository.save(Category.builder().name("Exotic").build());
        categoryRepository.save(Category.builder().name("Nuts").build());
        categoryRepository.save(Category.builder().name("Vegetables").build());
    }
}
