package com.prediction.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.prediction.app.facade.PredictionFacade;
import com.prediction.app.model.PredictionForm;
import com.prediction.app.model.User;
import com.prediction.app.service.UserService;

/**
 * @author Shaju K
 *
 */

@Controller
@SessionAttributes(types={PredictionForm.class})
public class PredictionController {

	@Autowired
	UserService userService;
	
	@Autowired
	PredictionFacade predictionFacade;
	
	@ModelAttribute("predictionForm")
	public PredictionForm getPredictionForm(){
		return new PredictionForm();
	}
	
	@RequestMapping(value = {"/","/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView model=new ModelAndView();
		model.setViewName("user/login");
		return model;
	}
	
	@RequestMapping(value = {"/signup"}, method = RequestMethod.GET)
	public ModelAndView signup(){
		ModelAndView model=new ModelAndView();
		User user=new User();
		model.addObject("user",user);
		model.setViewName("user/signup");
		return model;
	}
	
	@RequestMapping(value = {"/signup"}, method = RequestMethod.POST)
	public ModelAndView createUser(@Valid User user, BindingResult bindingResult){
		ModelAndView model=new ModelAndView();
		User userExists=userService.findUserByUsername(user.getUsername());
		if(null!=userExists){
			bindingResult.rejectValue("username", "error.user", "Username already exists!");
		}
		if(bindingResult.hasErrors()){
			model.setViewName("user/signup");
		}else{
			userService.saveUser(user);
			model.addObject("msg", "User has been registered successfully!");
			model.addObject("user",user);
			model.setViewName("user/signup");
		}
		return model;
	}
	
	@RequestMapping(value = {"/prediction/home"}, method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView model=new ModelAndView();
		PredictionForm predictionForm = prepareModelAndForm(model);
		model.addObject("predictionForm",predictionForm);
		predictionFacade.prepareAndGetPredictionForm(predictionForm);
		model.setViewName("home/home");
		return model;
	}
	
	@RequestMapping(value = {"/access_denied"}, method = RequestMethod.GET)
	public ModelAndView accessDenied(){
		ModelAndView model=new ModelAndView();
		model.setViewName("errors/access_denied");
		return model;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request,
			HttpServletResponse response, SessionStatus sessionStatus) {
		
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		sessionStatus.setComplete();
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response,
					authentication);
		}

		return "redirect:/";
	}
	
	@RequestMapping(value = {"/prediction/dp"}, method = RequestMethod.GET)
	public ModelAndView dailyPrediction(){
		ModelAndView model=new ModelAndView();
		PredictionForm predictionForm = prepareModelAndForm(model);
		model.addObject("predictionForm",predictionForm);
		predictionFacade.prepareAndGetPredictionForm(predictionForm);
		
		model.setViewName("home/dailyprediction");
		return model;
	}
	
	@RequestMapping(value = {"/prediction/dp"}, method = RequestMethod.POST)
	public ModelAndView savePrediction(@Valid @ModelAttribute PredictionForm predictionForm, 
			BindingResult bindingResult){
		ModelAndView model=new ModelAndView();
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		User user=userService.findUserByUsername(auth.getName());
		model.addObject("userName", user.getFirstname());
		model.addObject("predictionForm",predictionForm);
		predictionFacade.validateForm(predictionForm,bindingResult);
		model.setViewName("home/dailyprediction");
		if(!bindingResult.hasErrors()){
			predictionFacade.saveDailyPredictions(predictionForm);
			model.addObject("successMessage", "Your choice is saved successfully !");
		}
		
		return model;
	}
	
	@RequestMapping(value = {"/prediction/sf"}, method = RequestMethod.GET)
	public ModelAndView semiFinalPrediction(){
		ModelAndView model=new ModelAndView();
		PredictionForm predictionForm = prepareModelAndForm(model);
		model.addObject("predictionForm",predictionForm);
		predictionFacade.prepareAndGetPredictionForm(predictionForm);
		model.setViewName("home/semifinalist");
		return model;
	}
	
	@RequestMapping(value = {"/prediction/sf"}, method = RequestMethod.POST)
	public ModelAndView semiFinalPrediction(@Valid @ModelAttribute PredictionForm predictionForm, 
			BindingResult bindingResult){
		ModelAndView model=new ModelAndView();
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		User user=userService.findUserByUsername(auth.getName());
		model.addObject("userName", user.getFirstname());
		model.addObject("predictionForm",predictionForm);
		predictionFacade.validateSemiFinalists(predictionForm,bindingResult);
		model.setViewName("home/semifinalist");
		if(!bindingResult.hasErrors()){
			predictionFacade.saveSemiFinalPredictions(predictionForm);
			model.addObject("successMessage", "Your selections are saved successfully !");
		}
		return model;
	}
	
	@RequestMapping(value = {"/prediction/fnl"}, method = RequestMethod.GET)
	public ModelAndView finalPrediction(){
		ModelAndView model=new ModelAndView();
		PredictionForm predictionForm = prepareModelAndForm(model);
		model.addObject("predictionForm",predictionForm);
		predictionFacade.prepareAndGetPredictionForm(predictionForm);
		model.setViewName("home/finalist");
		return model;
	}
	
	@RequestMapping(value = {"/prediction/fnl"}, method = RequestMethod.POST)
	public ModelAndView saveFinalPrediction(@Valid @ModelAttribute PredictionForm predictionForm, 
			BindingResult bindingResult){
		ModelAndView model=new ModelAndView();
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		User user=userService.findUserByUsername(auth.getName());
		model.addObject("userName", user.getFirstname());
		model.addObject("predictionForm",predictionForm);
		predictionFacade.validateFinalists(predictionForm,bindingResult);
		model.setViewName("home/finalist");
		if(!bindingResult.hasErrors()){
			predictionFacade.saveFinalPredictions(predictionForm);
			model.addObject("successMessage", "Your selections are saved successfully !");
		}
		return model;
	}
	
	@RequestMapping(value = {"/prediction/chmp"}, method = RequestMethod.GET)
	public ModelAndView championPrediction(){
		ModelAndView model=new ModelAndView();
		PredictionForm predictionForm = prepareModelAndForm(model);
		model.addObject("predictionForm",predictionForm);
		predictionFacade.prepareAndGetPredictionForm(predictionForm);
		model.setViewName("home/champion");
		return model;
	}
	
	@RequestMapping(value = {"/prediction/chmp"}, method = RequestMethod.POST)
	public ModelAndView saveChampionPrediction(@Valid @ModelAttribute PredictionForm predictionForm, 
			BindingResult bindingResult){
		ModelAndView model=new ModelAndView();
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		User user=userService.findUserByUsername(auth.getName());
		model.addObject("userName", user.getFirstname());
		model.addObject("predictionForm",predictionForm);
		predictionFacade.validateChampion(predictionForm,bindingResult);
		model.setViewName("home/champion");
		if(!bindingResult.hasErrors()){
			predictionFacade.saveChampionPredictions(predictionForm);
			model.addObject("successMessage", "Your selection is saved successfully !");
		}
		return model;
	}
	
	@RequestMapping(value = {"/prediction/fixture"}, method = RequestMethod.GET)
	public ModelAndView getFixture() {
		ModelAndView model=new ModelAndView();
		PredictionForm predictionForm = prepareModelAndForm(model);
		predictionFacade.getMatchFixture(predictionForm);
		model.addObject("predictionForm",predictionForm);
		model.setViewName("home/fixture");
		return model;
	}
	
	private PredictionForm prepareModelAndForm(ModelAndView model) {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		User user=userService.findUserByUsername(auth.getName());
		model.addObject("userName", user.getFirstname());
		PredictionForm predictionForm=getPredictionForm();
		predictionForm.setUser(user);
		return predictionForm;
	}
}
