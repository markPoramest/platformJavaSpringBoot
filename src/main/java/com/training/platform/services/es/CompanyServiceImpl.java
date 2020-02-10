package com.training.platform.services.es;


import com.training.platform.entities.es.Company;
import com.training.platform.repositories.es.CompanyNativeRepository;
import com.training.platform.repositories.es.CompanyRepository;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchPhrasePrefixQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyServiceImpl implements CompanyService {
    private static final int INIT_PAGE = 0;
    private static final String INIT_ORDER_FIELD = "gender";
    private static final String INIT_ORDER_DIRECTION = "DESC";

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Iterable<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Iterable<Company> findAll(Optional<Integer> page, Optional<Integer> pageSize, Optional<String> order) {
        int evalPage = (page.orElse(0) < 1) ? INIT_PAGE : page.get() - 1;
        int evalPageSize = Integer.parseInt(String.valueOf(pageSize.orElse(20)));

        Sort.Direction sortOrderType = Sort.Direction.DESC;
        String orderDirection = order.orElse(INIT_ORDER_DIRECTION);

        if(orderDirection.equals("ASC")) {
            sortOrderType = Sort.Direction.ASC;
            //System.out.println("sortOrderType=" + sortOrderType);
        }

        //System.out.println("evalPage:" + evalPage + "evalPageSize:" + evalPageSize + "orderField:" + INIT_ORDER_FIELD + "Direction:" + orderDirection);
        return companyRepository.findAll(PageRequest.of(evalPage, evalPageSize, Sort.by(sortOrderType, INIT_ORDER_FIELD)));
    }

    @Override
    public Optional<Company> findById(String id) {
        return companyRepository.findById(id);
    }

    @Override
    public Company save(Company inputs) {
        try {
            String uniqueID = UUID.randomUUID().toString();
            Company company = new Company();
            company.setId(uniqueID);
            company.setFirstName(inputs.getFirstName());
            company.setLastName(inputs.getLastName());
            company.setDesignation(inputs.getDesignation());
            company.setSalary(inputs.getSalary());
            company.setDateOfJoining(inputs.getDateOfJoining());
            company.setAddress(inputs.getAddress());
            company.setGender(inputs.getGender());
            company.setAge(inputs.getAge());
            company.setMaritalStatus(inputs.getMaritalStatus());
            company.setInterests(inputs.getInterests());

            return companyRepository.save(company);
        }catch (Exception e) {
            throw e;
        }

    }

    @Override
    public Company update(String id, Company inputs) throws Exception {

        try {
            Optional<Company> company = companyRepository.findById(id);

            company.get().setFirstName(inputs.getFirstName());
            company.get().setLastName(inputs.getLastName());
            company.get().setDesignation(inputs.getDesignation());
            company.get().setSalary(inputs.getSalary());
            company.get().setDateOfJoining(inputs.getDateOfJoining());
            company.get().setAddress(inputs.getAddress());
            company.get().setGender(inputs.getGender());
            company.get().setAge(inputs.getAge());
            company.get().setMaritalStatus(inputs.getMaritalStatus());
            company.get().setInterests(inputs.getInterests());

            return companyRepository.save(company.get());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void delete(String id) {
        companyRepository.deleteById(id);
    }
    @Autowired
    CompanyNativeRepository companyNativeRepository;

    @Override
    public List<Company> getByGender(String gender) {
        return companyNativeRepository.findByGender(gender);
    }
    @Override
    public List<Company> getByMaritalStatusAndGender(String maritalStatus, String gender) {
        return companyNativeRepository.findByMaritalStatusAndGender(maritalStatus, gender);
    }
    @Override
    public List<Company> getAllWithSort(String sort) {
        return companyNativeRepository.findAllWithSort(sort);
    }


    @Override
    public List<Company> searchByFirstName(String firstName) {
        return companyNativeRepository.searchByFirstName(firstName);
    }

    @Override
    public List<Company> searchByWord(String word) {
        return companyNativeRepository.searchByword(word);
    }


}


