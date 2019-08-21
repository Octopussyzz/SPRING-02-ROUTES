package com.wildcodeschool.myProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@SpringBootApplication
public class MyProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyProjectApplication.class, args);
	}

	@RequestMapping("/myDoctor/{num}")
	@ResponseBody
	public Doctor myDoctor(@PathVariable String num, @RequestParam(required = false) Optional details) {

		int newNum = Integer.parseInt(num);

		if (newNum < 9) {
			throw new ResponseStatusException(HttpStatus.SEE_OTHER, "NOPE!");
		}
		if (newNum > 13) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "BADBOY!");
		}

		int index = newNum - 9;
		Doctor chosenOne = null;

		List<ExtendedDoctor> allExDocs = new ArrayList<>();
		allExDocs.add(new ExtendedDoctor("9", "Christopher Eccleston", "13"));
		allExDocs.add(new ExtendedDoctor("10", "David Tennant", "47"));
		allExDocs.add(new ExtendedDoctor("11", "Matt Smith", "44"));
		allExDocs.add(new ExtendedDoctor("12", "Peter Capaldi", "40"));
		allExDocs.add(new ExtendedDoctor("13", "Jodie Whittaker", "11"));

		List<Doctor> allDocs = new ArrayList<>();
		allDocs.add(new Doctor("9", "Christopher Eccleston"));
		allDocs.add(new Doctor("10", "David Tennant"));
		allDocs.add(new Doctor("11", "Matt Smith"));
		allDocs.add(new Doctor("12", "Peter Capaldi"));
		allDocs.add(new Doctor("13", "Jodie Whittaker"));

		if (details.isPresent()) {
			chosenOne = allExDocs.get(index);
		} else {
			chosenOne = allDocs.get(index);
		}

		return chosenOne;
	}

	class Doctor {

		private String name;
		private String number;

		public Doctor(String number, String name) {
			this.number = number;
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public String getNumber() {
			return number;
		}
	}

	class ExtendedDoctor extends Doctor {

		private String numberOfEpisodes;

		public ExtendedDoctor(String number, String name, String numberOfEpisodes) {
			super(number, name);
			this.numberOfEpisodes = numberOfEpisodes;
		}

		public String getNumberOfEpisodes() {
			return numberOfEpisodes;
		}
	}

}
