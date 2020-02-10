package com.training.platform.repositories.es;

import com.training.platform.entities.es.Company;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CompanyRepository extends ElasticsearchRepository<Company, String> {
}

