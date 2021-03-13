package com.lernopus.lernopus.controller.api;

import java.net.URI;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.lernopus.lernopus.model.LaLearnUser;
import com.lernopus.lernopus.payload.LaApiResponse;
import com.lernopus.lernopus.payload.PagedResponse;
import com.lernopus.lernopus.payload.api.LaApiFilterSorterRequest;
import com.lernopus.lernopus.payload.api.LaLearnUserApiResponse;
import com.lernopus.lernopus.service.api.LaUserApiService;
import com.lernopus.lernopus.util.AppConstants;

@RestController
@RequestMapping("/backend/api/LaLearnUser")
public class LaUserApiController {
    
    @Autowired
    private LaUserApiService laUserApiService;
    
    @GetMapping("/getAllLaLearnUser")
    public PagedResponse<LaLearnUserApiResponse> getAllLaLearnUser(
                                                @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return laUserApiService.getAllWithFilterLaLearnUser(page, size, null);
    }
    
    @PostMapping("/getAllWithFilterLaLearnUser")
    public PagedResponse<LaLearnUserApiResponse> getAllWithFilterLaLearnUser(
                                                @Valid @RequestBody LaApiFilterSorterRequest laApiFilterSorterRequest) {
    	return laUserApiService.getAllWithFilterLaLearnUser(laApiFilterSorterRequest.getPage(), laApiFilterSorterRequest.getSize(), laApiFilterSorterRequest);
    }
    
    @GetMapping("/getLaLearnUser/{key}")
    public LaLearnUserApiResponse getLaLearnUser(@PathVariable(value = "key") String userNameMailIdPhoneNumberUserId) {
        return laUserApiService.getLaLearnUser(userNameMailIdPhoneNumberUserId);
    }
    
    @PostMapping("/insertLaLearnUser")
    public ResponseEntity<?> insertLaLearnUser(@Valid @RequestBody LaLearnUserApiResponse laLearnUserRequest) {
    	LaLearnUser user = laUserApiService.insertLaLearnUser(laLearnUserRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{getLaLearnUser}")
                .buildAndExpand(user.getLaUserId()).toUri();
        return ResponseEntity.created(location)
                .body(new LaApiResponse(true, "User Created Successfully"));
    }
    
    @PostMapping("/updateLaLearnUser")
    public ResponseEntity<?> updateLaLearnUser(@Valid @RequestBody LaLearnUserApiResponse laLearnUserRequest) {
    	LaLearnUser user = laUserApiService.updateLaLearnUser(laLearnUserRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/getLaLearnUser/")
                .buildAndExpand(user.getLaUserId()).toUri();
        return ResponseEntity.created(location)
                .body(new LaApiResponse(true, "User Updated Successfully"));
    }
    
    @GetMapping("/deleteLaLearnUser")
    public boolean deleteLaLearnUser(@RequestParam(value = "key") String userId) {
        laUserApiService.deleteLaLearnUser(userId);
        return true;
    }
    
    @GetMapping("/searchLaLearnUser")
    public LaLearnUserApiResponse searchLaLearnUser(@RequestParam(value = "searchKey") String userNameMailIdPhoneNumberUserId) {
        return laUserApiService.searchLaLearnUser(userNameMailIdPhoneNumberUserId);
    }

}
