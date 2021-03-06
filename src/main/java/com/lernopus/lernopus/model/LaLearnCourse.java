package com.lernopus.lernopus.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.lernopus.lernopus.model.audit.LaUserDateAudit;

/**
 * Created by amernath v on 2019-09-05.
 */
@Entity
@Table(name = "la_learn_course")
public class LaLearnCourse extends LaUserDateAudit {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "la_corse_id")
    private Long laCourseId;
	
	@Column(name = "la_corse_parent_id")
    private Long laCourseParentId;
	
	@Column(name = "la_corse_root_id")
    private Long laCourseRootId;

    @NotBlank
    @Size(max = 250)
    @Column(name = "la_course_name")
    private String laCourseName;
    
    @Size(max = 1000)
    @Column(name = "la_course_description")
    private String laCourseDescription;
    
    @Lob
    @Column(name = "la_course_background_image")
    private String laCourseBackgroundImage;
    
    @NotBlank
    @Lob
    @Column(name = "la_course_content_text")
    private String laCourseContentText;
    
    @NotBlank
    @Lob
    @Column(name = "la_course_content_html")
    private String laCourseContentHtml;

    @NotBlank
    @Size(max = 50)
    @Column(name = "la_author_id")
    private String laAuthorId;
    
    @Column(name = "la_is_note")
    private Boolean laIsNote;
    
    @Column(name = "la_allow_comment")
    private Boolean laAllowComment;
    
    @Column(name = "la_allow_rating")
    private Boolean laAllowRating;
    
    @Lob
    @Column(name = "la_what_will_i_learn")
    private String laWhatWillILearn;
    
    @Lob
    @Column(name = "la_prerequisite")
    private String laPrerequisite;
    
    @Lob
    @Column(name = "la_url_reference")
    private String laUrlReference;
    
    @Lob
    @Column(name = "la_slide_show_url_reference")
    private String laSlideShowUrlReference;
    
    @Lob
    @Column(name = "la_video_url_reference")
    private String laVideoUrlReference;
    
    @Lob
    @Column(name = "la_file_and_url_mapping")
    private String attachmentMeta;
    
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            },
            mappedBy = "laAuthor")
    private Set<LaLearnUser> laAuthorSubscription = new HashSet<>();
    
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            },
            mappedBy = "laCourse")
    private Set<LaLearnUser> laCourseSubscription = new HashSet<>();
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        })
    @JoinTable(name = "la_learn_course_tech",
            joinColumns = { @JoinColumn(name = "la_course_id") },
            inverseJoinColumns = { @JoinColumn(name = "la_tech_id") })
    private Set<LaLearnTechnology> laTechTag = new HashSet<>();
    
    @OneToMany(cascade = CascadeType.ALL,
    		fetch = FetchType.LAZY, 
    		mappedBy = "laLearnCourse")
    private List<LaLearnCourseComments> laLearnCourseComments = new LinkedList<>();
    
    @OneToMany(cascade = CascadeType.ALL,
    		fetch = FetchType.LAZY, 
    		mappedBy = "laLearnCourse")
    private List<LaLearnCourseRating> laLearnCourseRating = new LinkedList<>();
    
    public LaLearnCourse(String laCourseName , String laAuthorId, Boolean laIsNote, String laCourseContentText, String laCourseContentHtml, Long laCourseParentId, Long laCourseRootId, Boolean laAllowComment, Boolean laAllowRating, String laWhatWillILearn, String laPrerequisite, String laUrlReference, String laSlideShowUrlReference, String laVideoUrlReference, String laCourseDescription, String laCourseBackgroundImage, String attachmentMeta, List<LaLearnCourseComments> laLearnCourseComments, List<LaLearnCourseRating> laLearnCourseRating) {
        this.laCourseName = laCourseName;
        this.laAuthorId = laAuthorId;
        this.laIsNote = laIsNote;
        this.laCourseContentHtml = laCourseContentHtml;
        this.laCourseContentText = laCourseContentText;
        this.laCourseParentId = laCourseParentId;
        this.laCourseRootId = laCourseRootId;
        this.laAllowComment = laAllowComment;
        this.laAllowRating = laAllowRating;
        this.laWhatWillILearn = laWhatWillILearn;
        this.laPrerequisite = laPrerequisite;
        this.laUrlReference = laUrlReference;
        this.laSlideShowUrlReference = laSlideShowUrlReference;
        this.laVideoUrlReference = laVideoUrlReference;
        this.laCourseDescription = laCourseDescription;
        this.laCourseBackgroundImage = laCourseBackgroundImage;
        this.attachmentMeta = attachmentMeta; 
        this.laLearnCourseComments = laLearnCourseComments;
        this.laLearnCourseRating = laLearnCourseRating; 
    }
    
    public LaLearnCourse() {

    }


    public Long getLaCourseId() {
        return laCourseId;
    }

    public void setLaCourseId(Long laCourseId) {
        this.laCourseId = laCourseId;
    }
    
    public Long getLaCourseParentId() {
        return laCourseParentId;
    }

    public void setLaCourseParentId(Long laCourseParentId) {
        this.laCourseParentId = laCourseParentId;
    }
    
    public Long getLaCourseRootId() {
        return laCourseRootId;
    }

    public void setLaCourseRootId(Long laCourseRootId) {
        this.laCourseRootId = laCourseRootId;
    }

    public String getLaCourseName() {
        return laCourseName;
    }

    public void setLaCourseName(String laCourseName) {
        this.laCourseName = laCourseName;
    }
    
    public String getLaCourseDescription() {
        return laCourseDescription;
    }

    public void setLaCourseDescription(String laCourseDescription) {
        this.laCourseDescription = laCourseDescription;
    }
    
    public String getLaCourseBackgroundImage() {
        return laCourseBackgroundImage;
    }

    public void setLaCourseBackgroundImage(String laCourseBackgroundImage) {
        this.laCourseBackgroundImage = laCourseBackgroundImage;
    }
    
    public String getLaCourseContentText() {
        return laCourseContentText;
    }

    public void setLaCourseContentText(String laCourseContentText) {
        this.laCourseContentText = laCourseContentText;
    }
    
    public String getLaCourseContentHtml() {
        return laCourseContentHtml;
    }

    public void setLaCourseContentHtml(String laCourseContentHtml) {
        this.laCourseContentHtml = laCourseContentHtml;
    }


    public String getLaAuthorId() {
        return laAuthorId;
    }

    public void setLaAuthorId(String laAuthorId) {
        this.laAuthorId = laAuthorId;
    }

    public Boolean getLaIsNote() {
        return laIsNote;
    }

    public void setLaIsNote(Boolean laIsNote) {
        this.laIsNote = laIsNote;
    }
    
    public Boolean getLaAllowComment() {
        return laAllowComment;
    }

    public void setLaAllowComment(Boolean laAllowComment) {
        this.laAllowComment = laAllowComment;
    }
    
    public Boolean getLaAllowRating() {
        return laAllowRating;
    }

    public void setLaAllowRating(Boolean laAllowRating) {
        this.laAllowRating = laAllowRating;
    }
    
    public Set<LaLearnUser> getLaAuthorSubscription() {
        return laAuthorSubscription;
    }
    
    public void setLaAuthorSubscription(Set<LaLearnUser> laAuthorSubscription) {
        this.laAuthorSubscription = laAuthorSubscription;
    }
    
    public Set<LaLearnUser> getLaCourseSubscription() {
        return laCourseSubscription;
    }
    
    public void setLaCourseSubscription(Set<LaLearnUser> laCourseSubscription) {
        this.laCourseSubscription = laCourseSubscription;
    }
    
    public Set<LaLearnTechnology> getLaTechTag() {
        return laTechTag;
    }
    
    public void setLaTechTag(Set<LaLearnTechnology> laTechTag) {
        this.laTechTag = laTechTag;
    }
    
    public String getLaWhatWillILearn() {
        return laWhatWillILearn;
    }

    public void setLaWhatWillILearn(String laWhatWillILearn) {
        this.laWhatWillILearn = laWhatWillILearn;
    }
    
    public String getLaPrerequisite() {
        return laPrerequisite;
    }

    public void setLaPrerequisite(String laPrerequisite) {
        this.laPrerequisite = laPrerequisite;
    }
    
    public String getLaUrlReference() {
        return laUrlReference;
    }

    public void setLaUrlReference(String laUrlReference) {
        this.laUrlReference = laUrlReference;
    }

    public String getLaSlideShowUrlReference() {
        return laSlideShowUrlReference;
    }

    public void setLaSlideShowUrlReference(String laSlideShowUrlReference) {
        this.laSlideShowUrlReference = laSlideShowUrlReference;
    }
    
    public String getLaVideoUrlReference() {
        return laVideoUrlReference;
    }

    public void setLaVideoUrlReference(String laVideoUrlReference) {
        this.laVideoUrlReference = laVideoUrlReference;
    }
    
    public String getAttachmentMeta() {
        return attachmentMeta;
    }

    public void setAttachmentMeta(String attachmentMeta) {
        this.attachmentMeta = attachmentMeta;
    }
    
    public List<LaLearnCourseComments> getLaLearnCourseComments() {
        return laLearnCourseComments;
    }
    
    public void setLaLearnCourseComments(List<LaLearnCourseComments> laLearnCourseComments) {
        this.laLearnCourseComments = laLearnCourseComments;
    }
    
    public void addLaLearnCourseComments(LaLearnCourseComments laLearnCourseComments) {
        this.laLearnCourseComments.add(laLearnCourseComments);
        laLearnCourseComments.setLaLearnCourse(this);
    }

    public void removeLaLearnCourseComments(LaLearnCourseComments laLearnCourseComments) {
        this.laLearnCourseComments.remove(laLearnCourseComments);
        laLearnCourseComments.setLaLearnCourse(null);
    }
    
    public List<LaLearnCourseRating> getLaLearnCourseRating() {
        return laLearnCourseRating;
    }
    
    public void setLaLearnCourseRating(List<LaLearnCourseRating> laLearnCourseRating) {
        this.laLearnCourseRating = laLearnCourseRating;
    }
    
    public void addLaLearnCourseRating(LaLearnCourseRating laLearnCourseRating) {
        this.laLearnCourseRating.add(laLearnCourseRating);
        laLearnCourseRating.setLaLearnCourse(this);
    }

    public void removeLaLearnCourseRating(LaLearnCourseRating laLearnCourseRating) {
        this.laLearnCourseRating.remove(laLearnCourseRating);
        laLearnCourseRating.setLaLearnCourse(null);
    }

}
