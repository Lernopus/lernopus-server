package com.lernopus.lernopus.repository.predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import io.jsonwebtoken.lang.Collections;

public class LaApiPredicateBuilder {
    private List<LaApiSearchCriteria> params;
    private List<LaApiSearchCriteria> paramsForOr;

    public LaApiPredicateBuilder() {
        params = new ArrayList<>();
        paramsForOr = new ArrayList<>();
    }

    public LaApiPredicateBuilder with(
      String key, String operation, Object value) {
        params.add(new LaApiSearchCriteria(key, operation, value));
        return this;
    }
    
    public LaApiPredicateBuilder withOr(
    	      String key, String operation, Object value) {
    			paramsForOr.add(new LaApiSearchCriteria(key, operation, value));
    	        return this;
    	    }

    public BooleanExpression build(String tableName, List<String> numericalColumnName) {
        if (params.size() == 0 && paramsForOr.size() == 0) {
            return null;
        }

        List<BooleanExpression> predicates = params.stream().map(param -> {
        	LaApiPredicate predicate = new LaApiPredicate(param);
            return predicate.getPredicate(tableName, numericalColumnName);
        }).filter(Objects::nonNull).collect(Collectors.toList());
        
        BooleanExpression result = Expressions.asBoolean(true).isTrue();
        for (BooleanExpression predicate : predicates) {
            result = result.and(predicate);
        }
        
        if(paramsForOr != null && !Collections.isEmpty(paramsForOr)) {
        	List<BooleanExpression> predicatesForOr = paramsForOr.stream().map(param -> {
            	LaApiPredicate predicate = new LaApiPredicate(param);
                return predicate.getPredicate(tableName, numericalColumnName);
            }).filter(Objects::nonNull).collect(Collectors.toList());
            
            BooleanExpression resultForOr = Expressions.asBoolean(false).isTrue();
            for (BooleanExpression predicate : predicatesForOr) {
            	resultForOr = resultForOr.or(predicate);
            }
            if(resultForOr != null) {
            	result = result.and(resultForOr);	
            }
        }
        
        return result;
    }
}
