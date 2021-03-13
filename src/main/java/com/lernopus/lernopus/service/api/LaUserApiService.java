package com.lernopus.lernopus.service.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.lernopus.lernopus.exception.AppException;
import com.lernopus.lernopus.model.LaLearnRole;
import com.lernopus.lernopus.model.LaLearnUser;
import com.lernopus.lernopus.model.LaRoleName;
import com.lernopus.lernopus.payload.PagedResponse;
import com.lernopus.lernopus.payload.api.LaApiFilterSorterRequest;
import com.lernopus.lernopus.payload.api.LaLearnUserApiResponse;
import com.lernopus.lernopus.repository.LaRoleRepository;
import com.lernopus.lernopus.repository.api.LaUserApiRepository;
import com.lernopus.lernopus.repository.predicate.LaApiPredicateBuilder;
import com.lernopus.lernopus.util.api.LaApiModelMapper;
import com.lernopus.lernopus.util.api.LaApiUtils;

@Service
public class LaUserApiService {

    @Autowired
    private LaUserApiRepository laUserApiRepository;
    
    @Autowired
    private LaRoleRepository roleRepository;
            
    @SuppressWarnings("unchecked")
	public PagedResponse<LaLearnUserApiResponse> getAllWithFilterLaLearnUser(int page, int size, LaApiFilterSorterRequest laApiFilterSorterRequest) {
    	LaApiUtils.validatePageNumberAndSize(page, size);
    	Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "laUserId");
    	if(Objects.nonNull(laApiFilterSorterRequest) && Objects.nonNull(laApiFilterSorterRequest.getSorterData()) && !laApiFilterSorterRequest.getSorterData().isEmpty() && Objects.nonNull(laApiFilterSorterRequest.getSorterData().get("field")) && Objects.nonNull(laApiFilterSorterRequest.getSorterData().get("order"))) {
    		Direction sortType = Sort.Direction.DESC;
    		if(String.valueOf(laApiFilterSorterRequest.getSorterData().get("order")).equalsIgnoreCase("ascend")) {
    			sortType = Sort.Direction.ASC;
    		}
    		pageable = PageRequest.of(page, size, sortType, String.valueOf(laApiFilterSorterRequest.getSorterData().get("field")));	
    	}
        Page<LaLearnUser> users = null;
        List<String> filterKeyList = new ArrayList<>();
        if(Objects.nonNull(laApiFilterSorterRequest) && ((Objects.nonNull(laApiFilterSorterRequest.getFilterData()) && !laApiFilterSorterRequest.getFilterData().isEmpty()) || (Objects.nonNull(laApiFilterSorterRequest.getSearchData()) && !laApiFilterSorterRequest.getSearchData().isEmpty()))) {
        	LaApiPredicateBuilder builder = new LaApiPredicateBuilder();
        	if(Objects.nonNull(laApiFilterSorterRequest) && Objects.nonNull(laApiFilterSorterRequest.getFilterData()) && !laApiFilterSorterRequest.getFilterData().isEmpty()) {
        		laApiFilterSorterRequest.getFilterData().entrySet().stream().forEach(filterData -> {
            		List<Object> filterValueList = (List<Object>)filterData.getValue();
            		if(Objects.nonNull(filterValueList) && !filterValueList.isEmpty() && Objects.nonNull(filterValueList.get(0))) {
            			if(filterValueList.get(0) instanceof List<?>) {
            				List<Object> filterDataList = (List<Object>)filterValueList.get(0);
            				filterDataList.stream().forEach(dataList -> {
            					builder.with(filterData.getKey(), ":", dataList);
            				});
            				filterKeyList.add(filterData.getKey());
            			} else {
            				builder.with(filterData.getKey(), ":", filterValueList.get(0));
            				filterKeyList.add(filterData.getKey());
            			}
            		}
        		});	
        	}
        	if(Objects.nonNull(laApiFilterSorterRequest) && Objects.nonNull(laApiFilterSorterRequest.getSearchData()) && !laApiFilterSorterRequest.getSearchData().isEmpty()) {
        		if(!filterKeyList.contains("laUserId")) {
        			builder.withOr("laUserId", ":", laApiFilterSorterRequest.getSearchData());
        		} if(!filterKeyList.contains("laUserName")) {
        			builder.withOr("laUserName", ":", laApiFilterSorterRequest.getSearchData());
        		} if(!filterKeyList.contains("laUserFullName")) {
        			builder.withOr("laUserFullName", ":", laApiFilterSorterRequest.getSearchData());
        		} if(!filterKeyList.contains("laMailId")) {
        			builder.withOr("laMailId", ":", laApiFilterSorterRequest.getSearchData());
        		}
        	}
        	users = laUserApiRepository.findAll(builder.build("laLearnUser", Arrays.asList("laUserId", "laPhoneNumber")), pageable);	
    	} else {
    		users = laUserApiRepository.getAllUsers(pageable);	
    	}
        if(users.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), users.getNumber(),
            		users.getSize(), users.getTotalElements(), users.getTotalPages(), users.isLast());
        }

        List<LaLearnUserApiResponse> userResponses = users.map(author -> {
            return LaApiModelMapper.mapUserToApiUserResponse(author);
        }).getContent();

        return new PagedResponse<>(userResponses, users.getNumber(),
        		users.getSize(), users.getTotalElements(), users.getTotalPages(), users.isLast());
    }
    
    public LaLearnUserApiResponse getLaLearnUser(String userNameMailIdPhoneNumberUserId) {
    	Optional<LaLearnUser> user = laUserApiRepository.getUser(Long.valueOf(userNameMailIdPhoneNumberUserId));
        if(user.isPresent()) {
        	return LaApiModelMapper.mapUserToApiUserResponse(user.get());
        } else {
        	return null;
        }
    }
    
    public LaLearnUserApiResponse searchLaLearnUser(String userNameMailIdPhoneNumberUserId) {
    	Optional<LaLearnUser> user = laUserApiRepository.searchUser(userNameMailIdPhoneNumberUserId);
        if(user.isPresent()) {
        	return LaApiModelMapper.mapUserToApiUserResponse(user.get());
        } else {
        	return null;
        }
    }
    
    public LaLearnUser insertLaLearnUser(LaLearnUserApiResponse laLearnUserRequest) {
        LaLearnUser user = new LaLearnUser();
        user.setProvider(laLearnUserRequest.getProvider());
        user.setProviderId(laLearnUserRequest.getProviderId());
        user.setLaUserFullName(laLearnUserRequest.getLaUserFullName());
        user.setLaMailId(laLearnUserRequest.getLaMailId());
        user.setLaImagePath(laLearnUserRequest.getLaImagePath());
        user.setLaUserName(laLearnUserRequest.getLaUserName());
        user.setLaPassword(laLearnUserRequest.getLaPassword());
        LaLearnRole userRole = roleRepository.findByLaRoleName(LaRoleName.ROLE_STUDENT)
                .orElseThrow(() -> new AppException("User Role not set."));
        user.setLaRoles(Collections.singleton(userRole));
        return laUserApiRepository.save(user);

    }
    
    public LaLearnUser updateLaLearnUser(LaLearnUserApiResponse laLearnUserRequest) {
    	Optional<LaLearnUser> userOptional = laUserApiRepository.findById(Long.valueOf(laLearnUserRequest.getLaUserId()));
    	if(userOptional.isPresent()) {
    		LaLearnUser user = 	userOptional.get();
            user.setProvider(laLearnUserRequest.getProvider());
            user.setProviderId(laLearnUserRequest.getProviderId());
            user.setLaUserFullName(laLearnUserRequest.getLaUserFullName());
            user.setLaMailId(laLearnUserRequest.getLaMailId());
            user.setLaImagePath(laLearnUserRequest.getLaImagePath());
            user.setLaUserName(laLearnUserRequest.getLaUserName());
            user.setLaPassword(laLearnUserRequest.getLaPassword());
            LaLearnRole userRole = roleRepository.findByLaRoleName(LaRoleName.ROLE_STUDENT)
                    .orElseThrow(() -> new AppException("User Role not set."));
            user.setLaRoles(Collections.singleton(userRole));
            return laUserApiRepository.save(user);
    	} else {
    		LaLearnUser user = new LaLearnUser();
            user.setProvider(laLearnUserRequest.getProvider());
            user.setProviderId(laLearnUserRequest.getProviderId());
            user.setLaUserFullName(laLearnUserRequest.getLaUserFullName());
            user.setLaMailId(laLearnUserRequest.getLaMailId());
            user.setLaImagePath(laLearnUserRequest.getLaImagePath());
            user.setLaUserName(laLearnUserRequest.getLaUserName());
            user.setLaPassword(laLearnUserRequest.getLaPassword());
            LaLearnRole userRole = roleRepository.findByLaRoleName(LaRoleName.ROLE_STUDENT)
                    .orElseThrow(() -> new AppException("User Role not set."));
            user.setLaRoles(Collections.singleton(userRole));
            return laUserApiRepository.save(user);
    	}
    }
    
    public void deleteLaLearnUser(String userId) {
    	laUserApiRepository.deleteById(Long.valueOf(userId));
    }

    public LaLearnUserApiResponse filterLaLearnUser(String filterKey, String filterValue) {
    	Optional<LaLearnUser> user = laUserApiRepository.filterLaLearnUser(filterKey, filterValue);
        if(user.isPresent()) {
        	return LaApiModelMapper.mapUserToApiUserResponse(user.get());
        } else {
        	return null;
        }
    }

}
