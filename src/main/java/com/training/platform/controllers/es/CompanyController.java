package com.training.platform.controllers.es;


import com.training.platform.entities.es.Company;
import com.training.platform.services.es.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping(value="")
    public Iterable<Company> index () {
        return companyService.findAll();
    }

    @GetMapping(value="/lists")
    public Iterable<Company> lists (
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("pageSize") Optional<Integer> pageSize,
            @RequestParam("order") Optional<String> order) {

        return companyService.findAll(page, pageSize, order);
    }

    @GetMapping(value="/{id}")
    public Optional<Company> show(@PathVariable String id) throws Exception {
        return companyService.findById(id);
    }

    @PostMapping(value="", consumes = "application/json", produces = "application/json")
    public Company store(@Valid @RequestBody Company inputs) throws Exception {
        return companyService.save(inputs);
    }

    @PutMapping(value="/{id}", consumes =  "application/json", produces = "application/json")
    public Company update(@Valid @PathVariable String id, @RequestBody Company inputs) throws Exception {
        return companyService.update(id, inputs);
    }

    @DeleteMapping(value="{id}")
    public void delete(@PathVariable String id) throws Exception {
        Optional<Company> company = companyService.findById(id);

        if(company.isPresent()) {
            companyService.delete(id);
        }
    }
    @GetMapping(value="/find")
    public List<Company> getByGender (@RequestParam("gender") String gender) {
        return companyService.getByGender(gender);
    }


    @GetMapping(value="/find_field")
    public List<Company> getByMaritalStatusAndGender (
            @RequestParam("gender") String gender,
            @RequestParam("maritalStatus") String maritalStatus){

        return companyService.getByMaritalStatusAndGender(maritalStatus , gender);
    }



    @GetMapping(value="/find_sort")
    public List<Company> getAllWithSort (
            @RequestParam(value = "sort", defaultValue = "", required = false) String sort){

        return companyService.getAllWithSort(sort);
    }


    @GetMapping(value="/search")
    public List<Company> searchByFirstName (@RequestParam(value = "firstName") String firstName){

        return companyService.searchByFirstName(firstName);
    }



    @GetMapping(value="/search_word")
    public List<Company> searchByWord (
            @RequestParam(value = "word") String word){
        return companyService.searchByWord(word);
    }




}

