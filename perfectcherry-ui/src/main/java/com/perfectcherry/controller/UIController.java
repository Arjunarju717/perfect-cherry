package com.perfectcherry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UIController {

	@GetMapping("/")
	public ModelAndView hello() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}

	@GetMapping("home")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		return modelAndView;
	}

	@GetMapping("interestReceived")
	public ModelAndView interestReceived() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("interest-received");
		return modelAndView;
	}

	@GetMapping("searchYourMatch")
	public ModelAndView searchYourMatch() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("search-your-match");
		return modelAndView;
	}

	@GetMapping("liveChat")
	public ModelAndView liveChat() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("live-chat");
		return modelAndView;
	}

	@GetMapping("editProfile")
	public ModelAndView editProfile() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("edit-profile");
		return modelAndView;
	}

	@GetMapping("profileVisitors")
	public ModelAndView profileVisitors() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("profile-visitors");
		return modelAndView;
	}

	@GetMapping("searchProfileById")
	public ModelAndView searchProfileById() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("search-profile-by-id");
		return modelAndView;
	}

	@GetMapping("singleProfileView")
	public ModelAndView singleProfileView() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("single-profile-view");
		return modelAndView;
	}

	@GetMapping("featuresList")
	public ModelAndView featuresList() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("features-list");
		return modelAndView;
	}

	@GetMapping("settings")
	public ModelAndView settings() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("settings");
		return modelAndView;
	}

	@GetMapping("login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@GetMapping("signUp")
	public ModelAndView signUp() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sign-up");
		return modelAndView;
	}

	@GetMapping("acceptedMembers")
	public ModelAndView acceptedMembers() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("accepted-members");
		return modelAndView;
	}

	@GetMapping("editProfile1438")
	public ModelAndView editProfile1438() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("edit-profile1438");
		return modelAndView;
	}

	@GetMapping("editProfilee57f")
	public ModelAndView editProfilee57f() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("edit-profilee57f");
		return modelAndView;
	}

	@GetMapping("editProfile0c37")
	public ModelAndView editProfile0c37() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("edit-profile0c37");
		return modelAndView;
	}

	@GetMapping("editProfilebc83")
	public ModelAndView editProfilebc83() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("edit-profilebc83");
		return modelAndView;
	}

	@GetMapping("editProfile3254")
	public ModelAndView editProfile3254() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("edit-profile3254");
		return modelAndView;
	}

	@GetMapping("editProfile209f")
	public ModelAndView editProfile209f() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("edit-profile209f");
		return modelAndView;
	}

	@GetMapping("editProfile3a98")
	public ModelAndView editProfile3a98() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("edit-profile3a98");
		return modelAndView;
	}

	@GetMapping("allCardsList")
	public ModelAndView allCardsList() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("all-cards-list");
		return modelAndView;
	}

	@GetMapping("allCardsListByFilter")
	public ModelAndView allCardsListByFilter() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("all-cards-list");
		return modelAndView;
	}

	@GetMapping("allCardsListByIdOrName")
	public ModelAndView allCardsListByIdOrName() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("all-cards-list");
		return modelAndView;
	}

	@GetMapping("personalPopUpDetails")
	public ModelAndView personalPopUpDetails() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("personal-popup-details");
		return modelAndView;
	}

	@GetMapping("basicDetailsPop")
	public ModelAndView basicDetailsPop() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("basic-details-pop");
		return modelAndView;
	}

	@GetMapping("ethnicity")
	public ModelAndView ethnicity() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("ethnicity");
		return modelAndView;
	}

	@GetMapping("beliefSystem")
	public ModelAndView beliefSystem() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("belief-system");
		return modelAndView;
	}

	@GetMapping("collegeDetails")
	public ModelAndView collegeDetails() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("college-details");
		return modelAndView;
	}

	@GetMapping("careerDetails")
	public ModelAndView careerDetails() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("career-details");
		return modelAndView;
	}

	@GetMapping("parentsDetails")
	public ModelAndView parentsDetails() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("parents-details");
		return modelAndView;
	}

	@GetMapping("siblings")
	public ModelAndView siblings() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("siblings");
		return modelAndView;
	}

	@GetMapping("familyValues")
	public ModelAndView familyValues() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("family-values");
		return modelAndView;
	}

	@GetMapping("habits")
	public ModelAndView habits() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("habits");
		return modelAndView;
	}

	@GetMapping("assets")
	public ModelAndView assets() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("assets");
		return modelAndView;
	}

	@GetMapping("hobbies")
	public ModelAndView hobbies() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("hobbies");
		return modelAndView;
	}

	@GetMapping("interest")
	public ModelAndView interest() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("interest");
		return modelAndView;
	}

	@GetMapping("rememberPassword")
	public ModelAndView rememberPassword() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("remember-password");
		return modelAndView;
	}

	@GetMapping("changePassword")
	public ModelAndView changePassword() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@GetMapping("logout")
	public ModelAndView logout() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}

	@GetMapping("sampleShopList")
	public ModelAndView sampleShopList() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sample-shop-list");
		return modelAndView;
	}

	@GetMapping("contactList")
	public ModelAndView contactList() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("contact-list");
		return modelAndView;
	}

	@GetMapping("shortlistProfiles")
	public ModelAndView shortlistProfiles() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("shortlist-profiles");
		return modelAndView;
	}

	@GetMapping("notification")
	public ModelAndView notification() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("notification");
		return modelAndView;
	}

}
