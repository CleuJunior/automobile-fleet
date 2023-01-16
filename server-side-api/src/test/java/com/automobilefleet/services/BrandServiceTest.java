package com.automobilefleet.services;

import com.automobilefleet.repositories.BrandRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class BrandServiceTest {

    @InjectMocks
    private BrandService service;

    @Mock
    private BrandRepository repository;

}
