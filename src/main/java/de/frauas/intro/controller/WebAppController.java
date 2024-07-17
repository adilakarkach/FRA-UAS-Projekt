package de.frauas.intro.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import de.frauas.intro.form.NameForm_Test;
import de.frauas.intro.form.UserForm;
import de.frauas.intro.model.Characters_Test;
import de.frauas.intro.model.Name_Test;
import de.frauas.intro.model.Test_Statistics;
import de.frauas.intro.model.User;

/**
 * This class represents the app controller which executes the requested pages
 * calculations and commands
 * 
 * @author Adil Akarkach
 *
 */
@Controller
@RequestMapping
public class WebAppController {

	// Array List which stores the data of users and their comments in the comment
	// box
	private static List<User> users = new ArrayList<User>();

	// Array List which stores all character end results of users for calculating
	// the average rate
	private static List<Characters_Test> charas = new ArrayList<Characters_Test>();

	// Array List which stores the user name and their picked answers in the test
	// for
	// calculating their end result
	private static List<Name_Test> name_results = new ArrayList<Name_Test>();

	// Array List which stores the overall statistics of their result from the test
	private static List<Test_Statistics> stats = new ArrayList<Test_Statistics>();

	@Value("${error.message}")
	private String errorMessage;
	
	/**
	 * This method opens the HTML page requested by the user
	 * 
	 * @return A HTML page called 'index' which is the home menu
	 */
	@RequestMapping(value = { "/Index" }, method = RequestMethod.GET)
	public String home_menu() {
		return "Index";
	}

	/**
	 * This method opens the HTML page requested by the user
	 * 
	 * @return A HTML page called 'characters' which shows characters
	 */
	@RequestMapping(value = { "/Characters" }, method = RequestMethod.GET)
	public String characters() {
		return "Characters";
	}

	/**
	 * This method opens the HTML page requested by the user
	 * 
	 * @return An HTML page called 'test' which is the start page of the test
	 */
	@RequestMapping(value = { "/Test" }, method = RequestMethod.GET)
	public String test_start() {
		return "Test";
	}

	/**
	 * This method opens the HTML page requested by the user
	 * 
	 * @return An HTML page called 'error_test' which shows an error warning
	 */
	@RequestMapping(value = { "/error_test" }, method = RequestMethod.GET)
	public String check_error() {
		return "error_test";
	}

	/**
	 * This method opens the HTML page requested by the user
	 * 
	 * @param model Model which supplies the attributes from the object for
	 * rendering the view
	 * @return An HTML page called 'Test_Name' which is the test
	 */
	@RequestMapping(value = { "/Test_Name" }, method = RequestMethod.GET)
	public String addName(Model model) {
		// Name of the array list
		NameForm_Test nameForm_Test = new NameForm_Test();
		// Adds the attributes of the object from the array list to the model
		model.addAttribute("nameForm_Test", nameForm_Test);
		return "Test_Name";
	}

	/**
	 * This method gets the attributes from the array lists to calculate the
	 * statistics of the test results and returns the matching HTML page
	 * 
	 * @param model A model which supplies the attributes from the
	 * objects for rendering the view
	 * @param nameForm_Test Class where object is created to access its
	 * attributes
	 * 
	 * @return An HTML page which presents the end results and statistics to the
	 * user
	 */
	@RequestMapping(value = { "/Test_Name" }, method = RequestMethod.POST)
	public String saveResult(Model model, @ModelAttribute("nameForm_Test") NameForm_Test nameForm_Test) {

		// If user chooses no answer in the test, an error page is returned
		if (nameForm_Test.getUserResult_Test() == null) {
			return ("error_test");
		}
		
		else if (nameForm_Test.getUserName_Test() == null) {
			return ("Test_Name");
		}

		// Saving the attributes of the object nameForm_Test into strings
		String userName_Test = nameForm_Test.getUserName_Test();
		String userResult_Test = nameForm_Test.getUserResult_Test();

		// Create new object with the above named attributes and save it in array list
		Name_Test newResult = new Name_Test(userName_Test, userResult_Test);
		name_results.add(newResult);

		// Object attribute 'userResult_Test', which stores all the test answers
		// of the user, gets saved into a string
		var str_result = userResult_Test;
		// Split string by ',' and saves values in a list
		var result_split = str_result.split(",");
		// Declare variables for finding max value
		var newMax = 0;
		var countMax = 0;
		var newMax_name = result_split[0];

		// Loop goes through the list to find most picked character by the user
		for (var i = 0; i < result_split.length; i++) {

			for (var j = 0; j < result_split.length; j++) {
				// Counter goes up when loop finds equal result
				if (result_split[i].equals(result_split[j])) {
					countMax++;
				}

			}

			// Replace variable for maximum value with counter value if counter is higher
			if (countMax > newMax) {
				newMax = countMax;
				// Store test character as current maximum result
				newMax_name = result_split[i];

				// In case of two equal maximum results the first maximum is chosen
			} else if (countMax == newMax) {
				newMax = countMax;
				newMax_name = result_split[i];
			}
			countMax = 0;
		}
		
		// Object is created with the attribute of the maximum value
		Characters_Test resultChar = new Characters_Test(newMax_name);
		charas.add(resultChar);

		// Declare variables to calculate the statistics of the test results
		// First there are the counters
		var stats_countNU = 0;
		var stats_countKH = 0;
		var stats_countSU = 0;
		var stats_countSH = 0;
		
		// Variables to store final statistic results
		var statsNU = 0;
		var statsKH = 0;
		var statsSU = 0;
		var statsSH = 0;
		
		// Variables to store the average rate of the same test result
		var stats_others = 0;
		var stats_othersNU = 0;
		var stats_othersKH = 0;
		var stats_othersSU = 0;
		var stats_othersSH = 0;

		// Loop goes through list to count the hit rate of picked answers
		for (var k = 0; k < result_split.length; k++) {		
			// Checks if value equals the abbreviation of the characters name
			if (result_split[k].equals("NU")) {
				// Counter goes up when found
				stats_countNU++;
			}

			else if (result_split[k].equals("KH")) {
				stats_countKH++;
			}

			else if (result_split[k].equals("SU")) {
				stats_countSU++;
			}

			else if (result_split[k].equals("SH")) {
				stats_countSH++;
			}
		}

		// Formula for calculating the quota of other characters and stores into variable
		statsNU = (stats_countNU * 100) / (stats_countSH + stats_countNU + stats_countKH + stats_countSU);
		statsSU = (stats_countSU * 100) / (stats_countSH + stats_countNU + stats_countKH + stats_countSU);
		statsKH = (stats_countKH * 100) / (stats_countSH + stats_countNU + stats_countKH + stats_countSU);
		statsSH = (stats_countSH * 100) / (stats_countSH + stats_countNU + stats_countKH + stats_countSU);

		// Loop goes through array list to count the hit rate of end results
		for (var n = 0; n < charas.size(); n++) {

			if (charas.get(n).getCharID().equals("NU")) {
				stats_othersNU++;
			}

			else if (charas.get(n).getCharID().equals("SU")) {
				stats_othersSU++;
			}

			else if (charas.get(n).getCharID().equals("KH")) {
				stats_othersKH++;
			}

			else if (charas.get(n).getCharID().equals("SH")) {
				stats_othersSH++;
			}

		}

		// Checks users end result to return the matching HTML page
		if (newMax_name.equals("NU")) {

			// Formula for the average hit rate of the end result by all users
			stats_others = (stats_othersNU * 100) / charas.size();
			
			// All statistic results are stored into an object
			Test_Statistics newStat = new Test_Statistics(statsSH, statsNU, statsKH, statsSU, stats_others);
			stats.add(newStat);

			// Variables get set back for next user result
			stats_countNU = 0;
			stats_countKH = 0;
			stats_countSU = 0;
			stats_countSH = 0;
			statsNU = 0;
			statsKH = 0;
			statsSU = 0;
			statsSH = 0;
			stats_others = 0;
			stats_othersNU = 0;
			stats_othersKH = 0;
			stats_othersSU = 0;
			stats_othersSH = 0;

			return "redirect:/Test_Result_NU";
		}

		else if (newMax_name.equals("KH")) {

			stats_others = (stats_othersKH * 100) / charas.size();
			Test_Statistics newStat = new Test_Statistics(statsSH, statsNU, statsKH, statsSU, stats_others);
			stats.add(newStat);
			stats_countNU = 0;
			stats_countKH = 0;
			stats_countSU = 0;
			stats_countSH = 0;
			statsNU = 0;
			statsKH = 0;
			statsSU = 0;
			statsSH = 0;
			stats_others = 0;
			stats_othersNU = 0;
			stats_othersKH = 0;
			stats_othersSU = 0;
			stats_othersSH = 0;

			return "redirect:/Test_Result_KH";
		}

		else if (newMax_name.equals("SU")) {

			stats_others = (stats_othersSU * 100) / charas.size();
			Test_Statistics newStat = new Test_Statistics(statsSH, statsNU, statsKH, statsSU, stats_others);
			stats.add(newStat);
			stats_countNU = 0;
			stats_countKH = 0;
			stats_countSU = 0;
			stats_countSH = 0;
			statsNU = 0;
			statsKH = 0;
			statsSU = 0;
			statsSH = 0;
			stats_others = 0;
			stats_othersNU = 0;
			stats_othersKH = 0;
			stats_othersSU = 0;
			stats_othersSH = 0;

			return "redirect:/Test_Result_SU";
		}

		else if (newMax_name.equals("SH")) {

			stats_others = (stats_othersSH * 100) / charas.size();
			Test_Statistics newStat = new Test_Statistics(statsSH, statsNU, statsKH, statsSU, stats_others);
			stats.add(newStat);
			stats_countNU = 0;
			stats_countKH = 0;
			stats_countSU = 0;
			stats_countSH = 0;
			statsNU = 0;
			statsKH = 0;
			statsSU = 0;
			statsSH = 0;
			stats_others = 0;
			stats_othersNU = 0;
			stats_othersKH = 0;
			stats_othersSU = 0;
			stats_othersSH = 0;

			return "redirect:/Test_Result_SH";
		}

		else {
			return "redirect:/Test_Result";
		}

	}

	/**
	 * This method adds attributes to the model and opens the requested HTML page
	 * The test results will be shown
	 * 
	 * @param model Model is created with the attributes from the array lists
	 * @return An HTML page for the test results
	 */
	@RequestMapping(value = { "/Test_Result_NU" }, method = RequestMethod.GET)
	public String showResultNU(Model model) {
		model.addAttribute("name_results", name_results);
		model.addAttribute("charas", charas);
		model.addAttribute("stats", stats);

		return "Test_Result_NU";
	}

	/**
	 * This method deletes the object with the picked answers from the test of the user
	 * and opens the requested HTML page
	 * 
	 * @param model References the model which supplies the attributes from the object
	 * @param nameForm_Test References the class of the object's type
	 * 
	 * @return An HTML page which represents the home menu
	 */
	@RequestMapping(value = { "/Test_Result_NU" }, method = RequestMethod.POST)
	public String deleteUserNU(Model model, @ModelAttribute("nameForm_Test") NameForm_Test nameForm_Test) {

		for (Name_Test name_Test : name_results) {
			name_results.remove(name_Test);
			return "redirect:/Index";
		}
		return "Index";
	}

	/**
	 * This method adds attributes to the model and opens the requested HTML page
	 * The test results will be shown
	 * 
	 * @param model Model is created with the attributes from the array lists
	 * @return An HTML page for the test results
	 */
	@RequestMapping(value = { "/Test_Result_KH" }, method = RequestMethod.GET)
	public String showResultKH(Model model) {
		model.addAttribute("name_results", name_results);
		model.addAttribute("charas", charas);
		model.addAttribute("stats", stats);
		return "Test_Result_KH";
	}

	/**
	 * This method deletes the object with the picked answers from the test of the user
	 * and opens the requested HTML page
	 * 
	 * @param model References the model which supplies the attributes from the object
	 * @param nameForm_Test References the class of the object's type
	 * 
	 * @return An HTML page which represents the home menu
	 */
	@RequestMapping(value = { "/Test_Result_KH" }, method = RequestMethod.POST)
	public String deleteUserKH(Model model, @ModelAttribute("nameForm_Test") NameForm_Test nameForm_Test) {

		for (Name_Test name_Test : name_results) {
			name_results.remove(name_Test);
			return "redirect:/Index";
		}
		return "Index";
	}

	/**
	 * This method adds attributes to the model and opens the requested HTML page
	 * The test results will be shown
	 * 
	 * @param model Model is created with the attributes from the array lists
	 * @return An HTML page for the test results
	 */
	@RequestMapping(value = { "/Test_Result_SU" }, method = RequestMethod.GET)
	public String showResultSU(Model model) {
		model.addAttribute("name_results", name_results);
		model.addAttribute("charas", charas);
		model.addAttribute("stats", stats);
		return "Test_Result_SU";
	}

	/**
	 * This method deletes the object with the picked answers from the test of the user
	 * and opens the requested HTML page
	 * 
	 * @param model References the model which supplies the attributes from the object
	 * @param nameForm_Test References the class of the object's type
	 * 
	 * @return An HTML page which represents the home menu
	 */
	@RequestMapping(value = { "/Test_Result_SU" }, method = RequestMethod.POST)
	public String deleteUserSU(Model model, @ModelAttribute("nameForm_Test") NameForm_Test nameForm_Test) {

		for (Name_Test name_Test : name_results) {
			name_results.remove(name_Test);
			return "redirect:/Index";
		}
		return "Index";
	}

	/**
	 * This method adds attributes to the model and opens the requested HTML page
	 * The test results will be shown
	 * 
	 * @param model Model is created with the attributes from the array lists
	 * @return An HTML page for the test results
	 */
	@RequestMapping(value = { "/Test_Result_SH" }, method = RequestMethod.GET)
	public String showResultSH(Model model) {
		model.addAttribute("name_results", name_results);
		model.addAttribute("charas", charas);
		model.addAttribute("stats", stats);
		return "Test_Result_SH";
	}

	/**
	 * This method deletes the object with the picked answers from the test of the user
	 * and opens the requested HTML page
	 * 
	 * @param model References the model which supplies the attributes from the object
	 * @param nameForm_Test References the class of the object's type
	 * 
	 * @return An HTML page which represents the home menu
	 */
	@RequestMapping(value = { "/Test_Result_SH" }, method = RequestMethod.POST)
	public String deleteUserSH(Model model, @ModelAttribute("nameForm_Test") NameForm_Test nameForm_Test) {

		for (Name_Test name_Test : name_results) {
			name_results.remove(name_Test);
			return "redirect:/Index";
		}
		return "Index";
	}

	/**
	 * This method adds attributes to the model and opens the requested HTML page
	 * Created comments in the forum will be shown
	 * 
	 * @param model Is a model for supplying the object attributes and rendering the view
	 * @return An HTML page called 'Forum_List' which is the forum/comment box
	 */
	@RequestMapping(value = { "/Forum_List" }, method = RequestMethod.GET)
	public String listForum(Model model) {
		model.addAttribute("users", users);
		return "Forum_List";
	}

	/**
	 * This method deletes posts/comments from the forum and opens the view
	 * 
	 * @param model Is a model which is referenced and supplies the attributes
	 * @param nameForm_Test Is the class of the object's type
	 * 
	 * @return The HTML page called 'Forum_List' which is the forum/comment box
	 */
	@RequestMapping(value = { "/Forum_List" }, method = RequestMethod.POST)
	public String deleteUserForum_Test(Model model, @ModelAttribute("nameForm_Test") NameForm_Test nameForm_Test) {

		for (Name_Test name_Test : name_results) {
			name_results.remove(name_Test);
			return "redirect:/Forum_List";
		}
		return "/Forum_List";
	}

	/**
	 * This method opens HTML page when requested and stores user input for attributes
	 * of the object
	 * 
	 * @param model Is a model which supplies the attributes of the object
	 * and renders the view
	 * @return An HTML page which shows the view for posting in forum/comment box
	 */
	@RequestMapping(value = { "/Forum_Post" }, method = RequestMethod.GET)
	public String addForumPost(Model model) {
		UserForm userForm = new UserForm();
		model.addAttribute("userForm", userForm);
		return "Forum_Post";
	}

	/**
	 * This method creates new post/comment when input valid, if invalid
	 * a warning shows up
	 * 
	 * @param model Is a model which supplies attributes from the object for
	 * rendering the view
	 * It also can represent the error message
	 * @param userForm Is the referenced class of the object's type
	 * @return Redirects user to the updated forum/comment box or user stays at
	 * the post HTML when invalid input
	 */
	@RequestMapping(value = { "/Forum_Post" }, method = RequestMethod.POST)
	public String savePost(Model model, @ModelAttribute("userForm") UserForm userForm) {

		// Stores object's attributes into a string
		String userID = userForm.getUserID();
		String userName = userForm.getUserName();
		String userMessage = userForm.getUserMessage();

		// Checks whether ID, userName and userMessage are existing
		if (userID != null && userID.length() > 0 && userName != null && userName.length() > 0 && userMessage != null
				&& userMessage.length() > 0) {
			// Loop goes through created objects
			for (User i : users) {
				// Checks if input ID is already taken
				if (i.getUserID().equals(userID)) {
					model.addAttribute("errorMessage", errorMessage);
					return "Forum_Post";
				}
			}
			// When input is valid the attributes are stored into an object of type 'User'
			User newUser = new User(userID, userName, userMessage);
			users.add(newUser);
			return "redirect:/Forum_List";
		}
		// If input invalid model supplies errorMessage
		model.addAttribute("errorMessage", errorMessage);
		return "Forum_Post";
	}

	/**
	 * This method shows HTML page for deleting a comment when requested by user
	 * 
	 * @param model Is a model which supplies attributes of the object
	 * @return An HTML page called 'Post_delete' for deleting a comment
	 */
	@RequestMapping(value = { "/Post_delete" }, method = RequestMethod.GET)
	public String showDeletePage(Model model) {
		UserForm userForm = new UserForm();
		model.addAttribute("userForm", userForm);
		return "Post_delete";
	}

	/**
	 * This method deletes the comment by the users ID
	 * If input is invalid an error message shows up
	 * 
	 * @param model Is a model which supplies the object's attributes
	 * @param userForm Is the referenced class of the object's type
	 * @return Redirects user to updated 'Forum_List' which is the forum/comment box where
	 * messages are shown
	 * If input invalid user stays on the delete page
	 */
	@RequestMapping(value = { "/Post_delete" }, method = RequestMethod.POST)
	public String deletePost(Model model, @ModelAttribute("userForm") UserForm userForm) {
		String userID = userForm.getUserID();

		// Loop goes through all users
		for (User user : users) {
			// Checks whether ID equals input ID
			if (user.getUserID().equals(userID)) {
				// Removes users comment when users ID is found
				users.remove(user);
				return "redirect:/Forum_List";
			}
		}
		// Error message shows up when input invalid
		model.addAttribute("errorMessage", errorMessage);
		return "Post_delete";
	}

}