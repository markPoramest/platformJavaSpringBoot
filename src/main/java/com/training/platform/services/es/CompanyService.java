package com.training.platform.services.es;

import com.training.platform.entities.es.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    Iterable<Company> findAll();
    Iterable<Company> findAll(Optional<Integer> page, Optional<Integer> pageSize, Optional<String> order);
    Optional<Company> findById(String id);
    Company save(Company company);

    Company update(String id, Company company) throws Exception;

    void delete(String id);
    List<Company> getByGender(String gender);

    List<Company> getByMaritalStatusAndGender(String maritalStatus, String gender);




    List<Company> searchByFirstName(String firstName);
    List<Company> searchByWord(String word);
    List<Company> getAllWithSort(String sort);



}

