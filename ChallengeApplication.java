package DoctorWho.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller
@SpringBootApplication
public class ChallengeApplication {

	String[][] tableDoctorStrings = {
		{"9", "William Hartnell", "134", "55"},
		{"10", "Patrick Troughton", "119", "46"},
		{"11", "Jon Pertwee", "128", "50"},
		{"12", "Tom Baker", "172", "40"},
		{"13", "Peter Davison", "69", "29"},
	};

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
		}
		@RequestMapping("/doctor/{id}")
		@ResponseBody
		public Doctor getDoctor(@PathVariable int id,@RequestParam(required = false) boolean details) {
			if (id >= 9 && id <= 13){     
				if(details == true)  {
				return new ExtendedDoctor(tableDoctorStrings[id - 9][0], tableDoctorStrings[id - 9][1], tableDoctorStrings[id - 9][2],tableDoctorStrings[id - 9][3]);// id=ligne on part de 9 donc -9
				}
				return new Doctor(tableDoctorStrings[id - 9][0], tableDoctorStrings[id - 9][1]);
			}
			else if(id >= 1 && id <= 8){
			throw new ResponseStatusException(HttpStatus.SEE_OTHER);//SEE OTHER renvoie page 303 see other
			}
				else{
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Impossibe de récupérer l'incarnation." + id);// id = numéro d'incarnationNOT FOUD = 404	
			}
			
		}
		class Doctor {

			private String name;
			private String number;
	
			public Doctor(String number, String name) {
				this.name = name;
				this.number = number;
			}
	
			public String getName() {
				return name;
			}
	
			public String getNumber() {
				return number;
			}
		}

		class ExtendedDoctor extends Doctor {

			private String ageStart;
			private String numberOfEpisode; 
		
			public ExtendedDoctor(String name, String number, String ageStart,String numberOfEpisode) {
				super(name, number);
				this.ageStart = ageStart;
				this.numberOfEpisode = numberOfEpisode;
			}

		public String getAgeStart() {
			return ageStart;
		}

		public void setAgeStart(String ageStart) {
			this.ageStart = ageStart;
		}

		public String getNumberOfEpisode() {
			return numberOfEpisode;
		}

		public void setNumberOfEpisode(String numberOfEpisode) {
			this.numberOfEpisode = numberOfEpisode;
		}
		
		
		}

}
