package com.training.platform.repositories.es;



import com.training.platform.entities.es.Company;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchPhrasePrefixQueryBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
@Repository
public class CompanyNativeRepositoryImpl implements CompanyNativeRepository {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public List<Company> findByGender(String gender) {
        BoolQueryBuilder builder = new BoolQueryBuilder();
        builder.must(matchQuery("gender", gender));

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(builder).build();

        List<Company> companyList = elasticsearchTemplate.queryForList(searchQuery, Company.class);

        return companyList;

    }


    @Override
    public List<Company> findByMaritalStatusAndGender(String maritalStatus, String gender) {
        BoolQueryBuilder builder = new BoolQueryBuilder();
        builder.must(matchQuery("maritalStatus", maritalStatus));
        builder.must(matchQuery("gender", gender));

        String[] includeFields = new String[]{"firstName", "lastName", "age", "gender", "maritalStatus"};
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withSourceFilter(new FetchSourceFilter(includeFields, null))
                .withQuery(builder).build();

        List<Company> companyList = elasticsearchTemplate.queryForList(searchQuery, Company.class);

        return companyList;
    }


    @Override
    public List<Company> findAllWithSort(String sort) {
        BoolQueryBuilder builder = new BoolQueryBuilder();
        builder.must(matchAllQuery());

        SortOrder sortOrder = SortOrder.ASC;
        if (!sort.isEmpty()) {
            sortOrder = SortOrder.DESC;
        }

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withSort(SortBuilders.fieldSort("salary").order(sortOrder))
                .withQuery(builder).build();

        List<Company> companyList = elasticsearchTemplate.queryForList(searchQuery, Company.class);
        return companyList;
    }
    @Override
    public List<Company> searchByFirstName(String firstName) {
        BoolQueryBuilder builder = new BoolQueryBuilder();
        builder.must(new MatchPhrasePrefixQueryBuilder("firstName", firstName));

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(builder).build();

        List<Company> companyList = elasticsearchTemplate.queryForList(searchQuery, Company.class);
        return companyList;
    }

    @Override
    public List<Company> searchByword(String word) {
        BoolQueryBuilder builder = new BoolQueryBuilder();
        builder.should(new MatchPhrasePrefixQueryBuilder("firstName", word));
        builder.should(new MatchPhrasePrefixQueryBuilder("lastname", word));
        builder.should(new MatchPhrasePrefixQueryBuilder("address", word));
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(builder).build();

        List<Company> companyList = elasticsearchTemplate.queryForList(searchQuery, Company.class);
        return companyList;
    }


}
