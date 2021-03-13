package com.lernopus.lernopus.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lernopus.lernopus.exception.BadRequestException;
import com.lernopus.lernopus.exception.FileStorageException;
import com.lernopus.lernopus.exception.MyFileNotFoundException;
import com.lernopus.lernopus.exception.ResourceNotFoundException;
import com.lernopus.lernopus.model.LaLearnCourse;
import com.lernopus.lernopus.model.LaLearnCourseComments;
import com.lernopus.lernopus.model.LaLearnCourseRating;
import com.lernopus.lernopus.model.LaLearnTechnology;
import com.lernopus.lernopus.model.LaLearnUser;
import com.lernopus.lernopus.model.LaLearnUserFollowers;
import com.lernopus.lernopus.payload.LaCourseCommentsRequest;
import com.lernopus.lernopus.payload.LaCourseRatingRequest;
import com.lernopus.lernopus.payload.LaCourseRequest;
import com.lernopus.lernopus.payload.LaCourseResponse;
import com.lernopus.lernopus.payload.LaCourseViewRequest;
import com.lernopus.lernopus.payload.LaSearchResponse;
import com.lernopus.lernopus.payload.LaUserFollowersRequest;
import com.lernopus.lernopus.payload.LaUserSummary;
import com.lernopus.lernopus.payload.PagedResponse;
import com.lernopus.lernopus.repository.LaCategoryRepository;
import com.lernopus.lernopus.repository.LaCourseCommentRepository;
import com.lernopus.lernopus.repository.LaCourseRatingRepository;
import com.lernopus.lernopus.repository.LaCourseRepository;
import com.lernopus.lernopus.repository.LaUserFollowersRepository;
import com.lernopus.lernopus.repository.LaUserRepository;
import com.lernopus.lernopus.security.LaUserPrincipal;
import com.lernopus.lernopus.util.AppConstants;
import com.lernopus.lernopus.util.ModelMapper;

@Service
public class LaCourseService {

    @Autowired
    private LaCourseRepository laCourseRepository;

    @Autowired
    private LaUserRepository userRepository;
    
    @Autowired
    private LaCategoryRepository laCategoryRepository;
    
    @Autowired
    private LaCourseCommentRepository laCourseCommentRepository;
    
    @Autowired
    private LaCourseRatingRepository laCourseRatingRepository;
    
    @Autowired
    private LaUserFollowersRepository laUserFollowersRepository;

    public PagedResponse<LaCourseResponse> getAllCourses(LaUserPrincipal currentUser, int page, int size) {
        validatePageNumberAndSize(page, size);

        // Retrieve Courses
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "laCreatedAt");
        Page<LaLearnCourse> courses = laCourseRepository.findAll(pageable);

        if(courses.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), courses.getNumber(),
                    courses.getSize(), courses.getTotalElements(), courses.getTotalPages(), courses.isLast());
        }

        Map<Long, LaLearnUser> creatorMap = getCourseCreatorMap(courses.getContent());

        List<LaCourseResponse> courseResponses = courses.map(course -> {
            return ModelMapper.mapCourseToCourseResponse(course,
                    creatorMap.get(course.getLaCreatedUser()));
        }).getContent();

        return new PagedResponse<>(courseResponses, courses.getNumber(),
                courses.getSize(), courses.getTotalElements(), courses.getTotalPages(), courses.isLast());
    }
    
    public PagedResponse<LaCourseResponse> getAllRootCourses(LaUserPrincipal currentUser, int page, int size) {
        validatePageNumberAndSize(page, size);

        // Retrieve Courses
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "laCreatedAt");
        Page<LaLearnCourse> courses = laCourseRepository.getAllRootCourse(pageable);

        if(courses.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), courses.getNumber(),
                    courses.getSize(), courses.getTotalElements(), courses.getTotalPages(), courses.isLast());
        }

        Map<Long, LaLearnUser> creatorMap = getCourseCreatorMap(courses.getContent());

        List<LaCourseResponse> courseResponses = courses.map(course -> {
            return ModelMapper.mapCourseToCourseResponse(course,
                    creatorMap.get(course.getLaCreatedUser()));
        }).getContent();

        return new PagedResponse<>(courseResponses, courses.getNumber(),
                courses.getSize(), courses.getTotalElements(), courses.getTotalPages(), courses.isLast());
    }
    
    public PagedResponse<LaCourseResponse> getAllChildCourses(LaUserPrincipal currentUser, int page, int size, String searchedValue) {
        validatePageNumberAndSize(page, size);

        // Retrieve Courses
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "laCreatedAt");
        Page<LaLearnCourse> courses = null;
        if(!searchedValue.equalsIgnoreCase(""))
        {
        	Pageable userPageable = PageRequest.of(0, size, Sort.Direction.ASC, "laUserFullName");
            Page<LaLearnUser> users = userRepository.getSearchResultsForUsers(searchedValue, currentUser.getLaUserId(), userPageable);
            if(users.getNumberOfElements() > 0)
            {        	
            	courses = laCourseRepository.findByLaCreatedUser(users.get().findFirst().get().getLaUserId(), pageable);
            }
            else
            {
            	
            	courses = laCourseRepository.getAllChildCourseForSearch(pageable, searchedValue);
            }
        }
        else
        {        	
        	courses = laCourseRepository.getAllChildCourse(pageable);
        }

        if(courses.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), courses.getNumber(),
                    courses.getSize(), courses.getTotalElements(), courses.getTotalPages(), courses.isLast());
        }

        Map<Long, LaLearnUser> creatorMap = getCourseCreatorMap(courses.getContent());

        List<LaCourseResponse> courseResponses = courses.map(course -> {
            return ModelMapper.mapCourseToCourseResponse(course,
                    creatorMap.get(course.getLaCreatedUser()));
        }).getContent();

        return new PagedResponse<>(courseResponses, courses.getNumber(),
                courses.getSize(), courses.getTotalElements(), courses.getTotalPages(), courses.isLast());
    }
    
    public PagedResponse<LaCourseResponse> getAllCoursesForCategory(long categoryId, LaUserPrincipal currentUser, int page, int size, String searchedValue) {
        validatePageNumberAndSize(page, size);
        
        LaLearnTechnology laLearnTechnology = laCategoryRepository.getOne(categoryId);

        // Retrieve Courses
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "laCreatedAt");
        Page<LaLearnCourse> courses = null;
        if(!searchedValue.equalsIgnoreCase(""))
        {
        	courses = laCourseRepository.getAllCourseForCategoryForSearch(laLearnTechnology, pageable, searchedValue);
        }
        else
        {
        	courses = laCourseRepository.getAllCourseForCategory(laLearnTechnology, pageable);
        }

        if(courses.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), courses.getNumber(),
                    courses.getSize(), courses.getTotalElements(), courses.getTotalPages(), courses.isLast());
        }

        Map<Long, LaLearnUser> creatorMap = getCourseCreatorMap(courses.getContent());

        List<LaCourseResponse> courseResponses = courses.map(course -> {
            return ModelMapper.mapCourseToCourseResponse(course,
                    creatorMap.get(course.getLaCreatedUser()));
        }).getContent();

        return new PagedResponse<>(courseResponses, courses.getNumber(),
                courses.getSize(), courses.getTotalElements(), courses.getTotalPages(), courses.isLast());
    }


    public PagedResponse<LaCourseResponse> getCoursesCreatedBy(String username, LaUserPrincipal currentUser, int page, int size, String searchedValue) {
        validatePageNumberAndSize(page, size);

        LaLearnUser user = userRepository.findByLaUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        Page<LaLearnCourse> courses = null;
        // Retrieve all courses created by the given username
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "laCreatedAt");
        if(!searchedValue.equalsIgnoreCase(""))
        {
        	courses = laCourseRepository.findByLaCreatedUserForSearch(user.getLaUserId(), pageable, searchedValue);
        }
        else
        {        	
        	courses = laCourseRepository.findByLaCreatedUser(user.getLaUserId(), pageable);
        }

        if (courses.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), courses.getNumber(),
                    courses.getSize(), courses.getTotalElements(), courses.getTotalPages(), courses.isLast());
        }

        List<LaCourseResponse> courseResponses = courses.map(course -> {
            return ModelMapper.mapCourseToCourseResponse(course,
                    user);
        }).getContent();

        return new PagedResponse<>(courseResponses, courses.getNumber(),
                courses.getSize(), courses.getTotalElements(), courses.getTotalPages(), courses.isLast());
    }
    
    public List<LaSearchResponse> getCoursesCreatedByForSearch(String username, LaUserPrincipal currentUser, int page, int size, String searchedValue) {
        validatePageNumberAndSize(page, size);

        LaLearnUser user = userRepository.findByLaUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        Page<LaLearnCourse> courses = null;
        // Retrieve all courses created by the given username
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "laCreatedAt");
        if(!searchedValue.equalsIgnoreCase(""))
        {
        	courses = laCourseRepository.findByLaCreatedUserForSearch(user.getLaUserId(), pageable, searchedValue);
        	if(courses.getNumberOfElements() == 0) {
                return new ArrayList();
            }

            List<LaSearchResponse> courseResponses = courses.map(course -> {
                return ModelMapper.mapCourseToSearchResponse(course);
            }).getContent();

            return courseResponses;

        }
        else
        {
        	return new ArrayList();
        }
    }

    public LaLearnCourse createCourse(LaCourseRequest courseRequest) {
        LaLearnCourse course = new LaLearnCourse();
        course.setLaCourseName(courseRequest.getLaCourseName());
        course.setLaCourseDescription(courseRequest.getLaCourseDescription());
        course.setLaCourseBackgroundImage(courseRequest.getLaCourseBackgroundImage());
        course.setLaCourseContentHtml(courseRequest.getLaCourseContentHtml());
        course.setLaCourseContentText(courseRequest.getLaCourseContentText());
        course.setLaIsNote(courseRequest.getLaIsNote());
        course.setLaAuthorId(courseRequest.getLaAuthorId());
        course.setLaTechTag(courseRequest.getLaTechTag());
        course.setLaAllowComment(courseRequest.getLaAllowComment());
        course.setLaAllowRating(courseRequest.getLaAllowRating());
        course.setLaWhatWillILearn(courseRequest.getLaWhatWillILearn());
        course.setLaPrerequisite(courseRequest.getLaPrerequisite());
        course.setLaUrlReference(courseRequest.getLaUrlReference());
        course.setLaSlideShowUrlReference(courseRequest.getLaSlideShowUrlReference());
        course.setLaVideoUrlReference(courseRequest.getLaVideoUrlReference());
        course.setAttachmentMeta(courseRequest.getAttachmentMeta());
        return laCourseRepository.save(course);
    }

    public LaCourseResponse getCourseById(Long courseId, LaUserPrincipal currentUser) {
        LaLearnCourse course = laCourseRepository.findById(courseId).orElseThrow(
                () -> new ResourceNotFoundException("Course", "id", courseId));

        // Retrieve course creator details
        LaLearnUser creator = userRepository.findById(course.getLaCreatedUser())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", course.getLaCreatedUser()));

        return ModelMapper.mapCourseToCourseResponse(course, 
                creator);
    }
    
    public LaCourseResponse getCourseById(Long courseId) {
        LaLearnCourse course = laCourseRepository.findById(courseId).orElseThrow(
                () -> new ResourceNotFoundException("Course", "id", courseId));

        return ModelMapper.mapCourseToCourseResponse(course, 
                null);
    }
    
    
    public LaLearnCourse updateParentAndRootId(LaLearnCourse course, Long laCourseParentId, Long laCourseRootId) {
    	course.setLaCourseParentId(laCourseParentId);
    	course.setLaCourseRootId(laCourseRootId);
        return laCourseRepository.save(course);
    }



    private void validatePageNumberAndSize(int page, int size) {
        if(page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }

        if(size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }

    Map<Long, LaLearnUser> getCourseCreatorMap(List<LaLearnCourse> course) {
        // Get Course Creator details of the given list of courses
        List<Long> creatorIds = course.stream()
                .map(LaLearnCourse::getLaCreatedUser)
                .distinct()
                .collect(Collectors.toList());

        List<LaLearnUser> creators = userRepository.findByLaUserIdIn(creatorIds);
        Map<Long, LaLearnUser> creatorMap = creators.stream()
                .collect(Collectors.toMap(LaLearnUser::getLaUserId, Function.identity()));

        return creatorMap;
    }
    
    public LaLearnCourseComments addComments(LaCourseCommentsRequest laCourseCommentsRequest) {
    	LaLearnCourse course = laCourseRepository.findById(Long.valueOf(laCourseCommentsRequest.getLaCourseId())).orElseThrow(
                () -> new ResourceNotFoundException("Course", "id", Long.valueOf(laCourseCommentsRequest.getLaCourseId())));
    	if(Objects.nonNull(course)) {
    		LaLearnCourseComments laLearnCourseComments = new LaLearnCourseComments(laCourseCommentsRequest.getLaCourseCommentsContent(), course);
    		return laCourseCommentRepository.save(laLearnCourseComments);
    	} else {
    		return null;
    	}
    }
    
    public LaLearnCourseRating addRating(LaCourseRatingRequest laCourseRatingRequest) {
    	LaLearnCourse course = laCourseRepository.findById(Long.valueOf(laCourseRatingRequest.getLaCourseId())).orElseThrow(
                () -> new ResourceNotFoundException("Course", "id", Long.valueOf(laCourseRatingRequest.getLaCourseId())));
    	LaLearnUser creator = userRepository.findById(Long.valueOf(laCourseRatingRequest.getLaUserId()))
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", Long.valueOf(laCourseRatingRequest.getLaUserId())));
    	Pageable pageable = PageRequest.of(0, 1, Sort.Direction.ASC, "laRatingId");
    	Page<LaLearnCourseRating> laLearnCourseRatingExists = laCourseRatingRepository.findByUserAndCourse(Long.valueOf(laCourseRatingRequest.getLaCourseId()), Long.valueOf(laCourseRatingRequest.getLaUserId()), pageable);
    	laLearnCourseRatingExists.getContent().size();
    	if(Objects.nonNull(course) && Objects.nonNull(creator)) {
    		if(laLearnCourseRatingExists.getContent().size() > 0) {
    			LaLearnCourseRating laLearnCourseRating = new LaLearnCourseRating(laCourseRatingRequest.getLaUpvoteCount(), laCourseRatingRequest.getLaDownvoteCount(), laCourseRatingRequest.getLaUserRating(), laLearnCourseRatingExists.getContent().get(0).getLaNoOfViews(), course, creator, Long.valueOf(laCourseRatingRequest.getLaCourseId()), Long.valueOf(laCourseRatingRequest.getLaUserId()));
    			laCourseRatingRepository.updateRatingByUserOnCourse(Long.valueOf(laCourseRatingRequest.getLaCourseId()), Long.valueOf(laCourseRatingRequest.getLaUserId()), laCourseRatingRequest.getLaUserRating(), laCourseRatingRequest.getLaUpvoteCount(), laCourseRatingRequest.getLaDownvoteCount());
        		return laLearnCourseRating;
    		} else {
    			LaLearnCourseRating laLearnCourseRating = new LaLearnCourseRating(laCourseRatingRequest.getLaUpvoteCount(), laCourseRatingRequest.getLaDownvoteCount(), laCourseRatingRequest.getLaUserRating(), 0L, course, creator, Long.valueOf(laCourseRatingRequest.getLaCourseId()), Long.valueOf(laCourseRatingRequest.getLaUserId()));
        		return laCourseRatingRepository.save(laLearnCourseRating);	
    		}
    	} else {
    		return null;
    	}
    }
    
    public LaLearnCourseRating uploadCourseViewCount(LaCourseViewRequest laCourseViewRequest) {
    	LaLearnCourse course = laCourseRepository.findById(Long.valueOf(laCourseViewRequest.getLaCourseId())).orElseThrow(
                () -> new ResourceNotFoundException("Course", "id", Long.valueOf(laCourseViewRequest.getLaCourseId())));
    	LaLearnUser creator = userRepository.findById(Long.valueOf(laCourseViewRequest.getLaUserId()))
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", Long.valueOf(laCourseViewRequest.getLaUserId())));
    	Pageable pageable = PageRequest.of(0, 1, Sort.Direction.ASC, "laRatingId");
    	Page<LaLearnCourseRating> laLearnCourseRatingExists = laCourseRatingRepository.findByUserAndCourse(Long.valueOf(laCourseViewRequest.getLaCourseId()), Long.valueOf(laCourseViewRequest.getLaUserId()), pageable);
    	laLearnCourseRatingExists.getContent().size();
    	if(Objects.nonNull(course) && Objects.nonNull(creator)) {
    		if(laLearnCourseRatingExists.getContent().size() > 0) {
    			LaLearnCourseRating laLearnCourseRating = new LaLearnCourseRating(laLearnCourseRatingExists.getContent().get(0).getLaUpvoteCount(), laLearnCourseRatingExists.getContent().get(0).getLaDownvoteCount(), laLearnCourseRatingExists.getContent().get(0).getLaUserRating(), laLearnCourseRatingExists.getContent().get(0).getLaNoOfViews() + 1L, course, creator, Long.valueOf(laCourseViewRequest.getLaCourseId()), Long.valueOf(laCourseViewRequest.getLaUserId()));
    			laCourseRatingRepository.updateViewsByUserOnCourse(Long.valueOf(laCourseViewRequest.getLaCourseId()), Long.valueOf(laCourseViewRequest.getLaUserId()), laLearnCourseRatingExists.getContent().get(0).getLaNoOfViews() + 1);
        		return laLearnCourseRating;
    		} else {
    			LaLearnCourseRating laLearnCourseRating = new LaLearnCourseRating(0L, 0L, 0L, 1L, course, creator, Long.valueOf(laCourseViewRequest.getLaCourseId()), Long.valueOf(laCourseViewRequest.getLaUserId()));
        		return laCourseRatingRepository.save(laLearnCourseRating);	
    		}
    	} else {
    		return null;
    	}
    }
    
    public LaLearnUserFollowers addFollowersForUser(LaUserFollowersRequest laCourseRatingRequest) {
    	LaLearnUser user = userRepository.findById(Long.valueOf(laCourseRatingRequest.getLaUserId()))
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", Long.valueOf(laCourseRatingRequest.getLaUserId())));
    	LaLearnUser userFollowers = userRepository.findById(Long.valueOf(laCourseRatingRequest.getLaUserFollowerId()))
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", Long.valueOf(laCourseRatingRequest.getLaUserFollowerId())));
    	if(Objects.nonNull(user) && Objects.nonNull(userFollowers)) {
    		LaLearnUserFollowers laLearnUserFollowers = new LaLearnUserFollowers(user, userFollowers);
    		return laUserFollowersRepository.save(laLearnUserFollowers);
    	} else {
    		return null;
    	}
    }

    public PagedResponse<LaCourseResponse> getAllChildCourses(Long courseId, LaUserPrincipal currentUser, int page, int size) {
        validatePageNumberAndSize(page, size);

        // Retrieve Courses
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "laCreatedAt");
        Page<LaLearnCourse> courses = laCourseRepository.getAllChildCourse(courseId, pageable);

        if(courses.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), courses.getNumber(),
                    courses.getSize(), courses.getTotalElements(), courses.getTotalPages(), courses.isLast());
        }

        Map<Long, LaLearnUser> creatorMap = getCourseCreatorMap(courses.getContent());

        List<LaCourseResponse> courseResponses = courses.map(course -> {
            return ModelMapper.mapCourseToCourseResponse(course,
                    creatorMap.get(course.getLaCreatedUser()));
        }).getContent();

        return new PagedResponse<>(courseResponses, courses.getNumber(),
                courses.getSize(), courses.getTotalElements(), courses.getTotalPages(), courses.isLast());
    }
    
    public PagedResponse<LaSearchResponse> getSearchResults(String searchedValue, int size) {
		
    	List<LaSearchResponse> searchResponses = new ArrayList<>();
    	
    	List<LaSearchResponse> courseResponses = getSearchResultsForCourse(searchedValue, size);
    	List<LaSearchResponse> courseCreatedByResponses = getSearchResultsForCourseCreatedBy(13,searchedValue, size);
    	List<LaSearchResponse> officialCategoryResponses = getSearchResultsForOfficialCategory(searchedValue, size);
    	List<LaSearchResponse> specialCategoryResponses = getSearchResultsForSpecialCategory(searchedValue, size);
    	List<LaSearchResponse> authorResponses = getSearchResultsForAuthors(0, searchedValue, size);
    	List<LaSearchResponse> userResponses = getSearchResultsForUsers(13, searchedValue, size);
    	searchResponses.addAll(courseResponses);
    	searchResponses.addAll(officialCategoryResponses);
    	searchResponses.addAll(specialCategoryResponses);
    	searchResponses.addAll(authorResponses);
    	searchResponses.addAll(userResponses);
    	searchResponses.addAll(courseCreatedByResponses);
        return new PagedResponse<>(searchResponses, 0,
        		searchResponses.size(), searchResponses.size(), 1, true);

	}
    
    public PagedResponse<LaSearchResponse> getSearchResultsForParentMapping(String searchedValue, int size) {
		
    	List<LaSearchResponse> searchResponses = new ArrayList<>();
    	List<LaSearchResponse> courseResponses = getSearchResultsForParentCourse(searchedValue, size);
    	searchResponses.addAll(courseResponses);
        return new PagedResponse<>(searchResponses, 0,
        		searchResponses.size(), searchResponses.size(), 1, true);

	}

	public List<LaSearchResponse> getSearchResultsForCourse(String searchedValue, int size) {
		// Retrieve Courses
        Pageable pageable = PageRequest.of(0, size, Sort.Direction.DESC, "laCreatedAt");
        Page<LaLearnCourse> courses = laCourseRepository.getSearchResultsForCourse(searchedValue, pageable);

        if(courses.getNumberOfElements() == 0) {
            return new ArrayList();
        }

        List<LaSearchResponse> courseResponses = courses.map(course -> {
            return ModelMapper.mapCourseToSearchResponse(course);
        }).getContent();

        return courseResponses;

	}
	
	public List<LaSearchResponse> getSearchResultsForParentCourse(String searchedValue, int size) {
		// Retrieve Courses
        Pageable pageable = PageRequest.of(0, size, Sort.Direction.DESC, "laCreatedAt");
        Page<LaLearnCourse> courses = laCourseRepository.getSearchResultsForParentCourse(searchedValue, pageable);

        if(courses.getNumberOfElements() == 0) {
            return new ArrayList();
        }

        List<LaSearchResponse> courseResponses = courses.map(course -> {
            return ModelMapper.mapCourseToSearchResponse(course);
        }).getContent();

        return courseResponses;

	}
	
	public List<LaSearchResponse> getSearchResultsForCourseCreatedBy(long loggedInUserId, String searchedValue, int size) {
		
		Pageable userPageable = PageRequest.of(0, size, Sort.Direction.ASC, "laUserFullName");
        Page<LaLearnUser> users = userRepository.getSearchResultsForUsers(searchedValue, loggedInUserId, userPageable);
        if(users.getNumberOfElements() > 0)
        {        	
        	// Retrieve all courses created by the given username
        	Pageable pageable = PageRequest.of(0, size, Sort.Direction.DESC, "laCreatedAt");
        	Page<LaLearnCourse> courses = laCourseRepository.findByLaCreatedUser(users.get().findFirst().get().getLaUserId(), pageable);
        	if(courses.getNumberOfElements() == 0) {
        		return new ArrayList();
        	}
        	
        	List<LaSearchResponse> courseResponses = courses.map(course -> {
        		return ModelMapper.mapCourseToSearchResponse(course);
        	}).getContent();
        	
        	return courseResponses;
        }
        else
        {
        	return new ArrayList();
        }

	}
	
	public List<LaSearchResponse> getSearchResultsForOfficialCategory(String searchedValue, int size) {
		// Retrieve Courses
		Pageable pageable = PageRequest.of(0, size, Sort.Direction.ASC, "laTechId");
        Page<LaLearnTechnology> categories = laCategoryRepository.getSearchResultsForOfficialCategory(searchedValue, "1", pageable);

        if(categories.getNumberOfElements() == 0) {
            return new ArrayList();
        }

        List<LaSearchResponse> courseResponses = categories.map(course -> {
            return ModelMapper.mapOfficialCategoryToSearchResponse(course);
        }).getContent();

        return courseResponses;

	}
	
	public List<LaSearchResponse> getSearchResultsForSpecialCategory(String searchedValue, int size) {
		// Retrieve Courses
		Pageable pageable = PageRequest.of(0, size, Sort.Direction.ASC, "laTechId");
        Page<LaLearnTechnology> categories = laCategoryRepository.getSearchResultsForSpecialCategory(searchedValue, "2", pageable);

        if(categories.getNumberOfElements() == 0) {
            return new ArrayList();
        }

        List<LaSearchResponse> courseResponses = categories.map(course -> {
            return ModelMapper.mapSpecialCategoryToSearchResponse(course);
        }).getContent();

        return courseResponses;

	}
	
	public List<LaSearchResponse> getSearchResultsForAuthors(long loggedInUserId, String searchedValue, int size) {
        // Retrieve Courses
        Pageable pageable = PageRequest.of(0, size, Sort.Direction.ASC, "laUserFullName");
        Page<LaLearnUser> authors = userRepository.getSearchResultsForAuthors(searchedValue, loggedInUserId, pageable);

        if(authors.getNumberOfElements() == 0) {
            return new ArrayList();
        }

        List<LaSearchResponse> authorResponses = authors.map(author -> {
            return ModelMapper.mapAuthorToSearchResponse(author);
        }).getContent();

        return authorResponses;
	}
	
	public List<LaSearchResponse> getSearchResultsForUsers(long loggedInUserId, String searchedValue, int size) {
        // Retrieve Courses
        Pageable pageable = PageRequest.of(0, size, Sort.Direction.ASC, "laUserFullName");
        Page<LaLearnUser> authors = userRepository.getSearchResultsForUsers(searchedValue, loggedInUserId, pageable);

        if(authors.getNumberOfElements() == 0) {
            return new ArrayList();
        }

        List<LaSearchResponse> authorResponses = authors.map(author -> {
            return ModelMapper.mapUserToSearchResponse(author);
        }).getContent();

        return authorResponses;
	}
}
