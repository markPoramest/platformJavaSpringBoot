package com.training.platform.controllers;

import com.training.platform.entities.User;
import com.training.platform.entities.es.Company;
import com.training.platform.repositories.UserRepository;
import com.training.platform.services.UserService;
import com.training.platform.services.es.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private UserService userService;
    @Autowired
    CompanyService companyService;

    @GetMapping(value="/es_lists")
    public Iterable<Company> es_lists () {
        return companyService.findAll();
    }

    @GetMapping(value = "")
    public List<User> index() throws Exception {
        // Change from UserRepository to UserService
        return userService.findAll();
    }
    @Value("${cache.environment.name}")
    private String cacheEnvironmentName;
    @Value("${cache.cluster.name}")
    private String cacheClusterName;
    @Value("${cache.cluster.user}")
    private String cacheClusterUser;
    @Value("${spring.data.elasticsearch.cluster-name}")
    private String clusterName;
    @Value("${spring.data.elasticsearch.cluster-nodes}")
    private String clusterIp;
    @GetMapping("/showenv")
    public Map<String, String> showenv() {
        HashMap<String, String> myParam = new HashMap<>();
        myParam.put("environment", cacheEnvironmentName);
        myParam.put("clusterName", cacheClusterName);
        myParam.put("clusterUser", cacheClusterUser);
        myParam.put("esClusterName", clusterName);
        myParam.put("esClusterIp", clusterIp);
        return myParam;
    }




}

