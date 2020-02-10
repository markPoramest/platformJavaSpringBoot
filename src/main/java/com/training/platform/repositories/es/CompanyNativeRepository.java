package com.training.platform.repositories.es;



import com.training.platform.entities.es.Company;

import java.util.List;

public interface CompanyNativeRepository {
    List<Company> findByGender(String gender);
    List<Company> findByMaritalStatusAndGender(String maritalStatus, String gender);
    List<Company> findAllWithSort(String sort);
    List<Company> searchByFirstName(String firstName);
    List<Company> searchByword(String word);

}

