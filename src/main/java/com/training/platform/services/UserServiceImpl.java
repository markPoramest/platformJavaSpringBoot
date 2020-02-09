package com.training.platform.services;

import com.training.platform.entities.User;
import com.training.platform.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UtilsService utilsService;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public Page<User> findAllByLimit(Integer start, Integer limit, String field) {
        PageRequest page = PageRequest.of(start, limit, Sort.by(Sort.Direction.ASC, field));
        return userRepository.findAll(page);
    }

    @Override
    public List<User> findByCityAndActiveAndAge(String city, Integer active, Integer age) {
        return userRepository.findByCityAndActiveAndAge(city, active, age);
    }
    @Override
    public List<User> findByAgeIn(List<Integer> ages) {
        return userRepository.findByAgeIn(ages);
    }
    @Override
    public List<User> findAllByQuery() {
        return userRepository.findAllByQuery();
    }
    @Override
    public List<User> findAllByParamsQuery(Integer active, String city) {
        return userRepository.findAllByParamsQuery(active, city);
    }
    @Override
    public List<User> findAllByJpqlQuery() {
        return userRepository.findAllByJpqlQuery();
    }
    @Override
    public List<User> findAllByJpqlParamsQuery(Integer active, String city) {
        return userRepository.findAllByJpqlParamsQuery(active, city);
    }
    @Override
    public Page<User> findAll(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest);
    }
    @Override
    public Map<String, String> getCities() {
        Map<String, String> cities = new HashMap<String, String>();
        cities.put("bangkok","bangkok");
        cities.put("nakornpathom","nakornpathom");
        return cities;
    }
    @Override
    public User save(Map<String,String> inputs) throws Exception {
        try {
            User user = new User();
            user.setName(inputs.get("name"));
            user.setSurname(inputs.get("surname"));
            user.setEmail(inputs.get("email"));
            //user.setPassword(inputs.get("password"));
            //user.setConfirm_password(inputs.get("confirm_password"));
            user.setPassword(utilsService.encrytePassword(inputs.get("password")));
            user.setConfirm_password(utilsService.encrytePassword(inputs.get("confirm_password")));
            user.setAge(Integer.parseInt(inputs.get("age")));
            user.setAddress(inputs.get("address"));
            user.setCity(inputs.get("city"));
            user.setMobile(inputs.get("mobile"));
            user.setActive(1);
            user.setCreatedAt(new Date());
            return userRepository.save(user);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public boolean isEmailAlreadyInUse(String email) {
        boolean emailInuse = true;
        if (userRepository.findByEmail(email) == null) emailInuse = false;
        return emailInuse;
    }
    @Override
    public User update(Optional<User> user, Map<String,String> inputs) throws Exception
    {
        try {
            user.get().setName(inputs.get("name"));
            user.get().setSurname(inputs.get("surname"));
            user.get().setAge(Integer.parseInt(inputs.get("age")));
            user.get().setAddress(inputs.get("address"));
            user.get().setCity(inputs.get("city"));
            user.get().setMobile(inputs.get("mobile"));
            user.get().setUpdatedAt(new Date());
            return userRepository.save(user.get());
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        userRepository.deleteById(id);
    }





}
