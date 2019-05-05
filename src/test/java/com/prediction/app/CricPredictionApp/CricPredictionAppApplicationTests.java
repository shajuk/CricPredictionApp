package com.prediction.app.CricPredictionApp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

import com.prediction.app.model.Championprediction;
import com.prediction.app.model.Dailyprediction;
import com.prediction.app.model.DailypredictionId;
import com.prediction.app.model.Finalprediction;
import com.prediction.app.model.Game;
import com.prediction.app.model.Semifinalprediction;
import com.prediction.app.model.User;
import com.prediction.app.service.ChampionPredictionService;
import com.prediction.app.service.ChampionPredictionServiceImpl;
import com.prediction.app.service.DailyPredictionService;
import com.prediction.app.service.DailyPredictionServiceImpl;
import com.prediction.app.service.FinalPredictionService;
import com.prediction.app.service.FinalPredictionServiceImpl;
import com.prediction.app.service.MatchService;
import com.prediction.app.service.MatchServiceImpl;
import com.prediction.app.service.SemiFinalPredictionService;
import com.prediction.app.service.SemiFinalPredictionServiceImpl;
import com.prediction.app.service.UserService;
import com.prediction.app.service.UserServiceImpl;

/**
 * @author Shaju K
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(
		  locations = "classpath:application.properties")
public class CricPredictionAppApplicationTests {

	SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	
	@TestConfiguration
    static class PredictionAppTestContextConfiguration {
  
		@Bean
	    public UserService userService(){
	        	return new UserServiceImpl();
	    }
		 
        @Bean
        public MatchService matchService() {
            return new MatchServiceImpl();
        }
        
        @Bean
        public DailyPredictionService dailyPredictionService(){
        	return new DailyPredictionServiceImpl();
        }
        
        @Bean
        public SemiFinalPredictionService semiFinalPredictionService(){
        	return new SemiFinalPredictionServiceImpl();
        }
        
        @Bean
        public FinalPredictionService finalPredictionService(){
        	return new FinalPredictionServiceImpl();
        }
        
        @Bean
        public ChampionPredictionService championPredictionService(){
        	return new ChampionPredictionServiceImpl();
        }
    }
	
	@Autowired
	UserService userService;
	
	@Test
	public void saveUser(){
		User user1=new User();
		user1.setFirstname("Shaju");
		user1.setLastname("Kuppelan");
		user1.setLocation("Kochi");
		user1.setUsername("373962");
		user1.setPassword("123");
		userService.saveUser(user1);
		
		User user2=new User();
		user2.setFirstname("Shibu");
		user2.setLastname("Kuppelan");
		user2.setLocation("Kochi");
		user2.setUsername("373963");
		user2.setPassword("123");
		userService.saveUser(user2);
		
		User user=new User();
		user.setFirstname("Sandhya");
		user.setLastname("KU");
		user.setLocation("Chennai");
		user.setUsername("373964");
		user.setPassword("123");
		userService.saveUser(user);
	}
	
	@Autowired
	MatchService matchService;
	
	@Test
	public void saveMatch() throws ParseException{
		Game matches=new Game(1, "ENGLAND", "SOUTH AFRICA", formatter.parse("30/05/2019 15:00:00"), "The Oval, London","");
		matchService.saveMatch(matches);
		Game savedMatch=matchService.findMatchByMatchNo(1);
		assertThat(matches!=savedMatch && matches.getTeam1().equals(savedMatch.getTeam1()));
	}	
	
	@Test
	public void findAllMatches(){
		List<Game> matches=matchService.findAllMatches();
		assertThat(!matches.isEmpty());
		matches.forEach(m -> System.out.println(" Match No - "+m.getMatchNo()+" "+m.getResult()));
	}
	
	@Test
	public void updateMatch(){
		Game savedMatch=matchService.findMatchByMatchNo(2);
		savedMatch.setResult(savedMatch.getTeam2());
		matchService.updateMatch(savedMatch);
		assertThat(savedMatch.getResult()!=null);
		findAllMatches();
	}
	
	@Test
	public void saveAllMatches() throws ParseException{
		List<Game> matches=new ArrayList<>();
		matches.add(new Game(1, "ENGLAND", "SOUTH AFRICA", formatter.parse("30/05/2019 15:00:00"), "The Oval, London",""));
		matches.add(new Game(2, "WEST INDIES", "PAKISTAN", formatter.parse("31/05/2019 15:00:00"), "Trent Bridge, Nottingham",""));
		matches.add(new Game(3, "NEW ZEALAND", "SRI LANKA", formatter.parse("01/06/2019 15:00:00"), "Cardiff Wales Stadium, Cardiff",""));
		matches.add(new Game(4, "AFGHANISTAN", "AUSTRALIA", formatter.parse("01/06/2019 18:00:00"), "Bristol County Ground, Bristol",""));
		matches.add(new Game(5, "SOUTH AFRICA", "BANGLADESH", formatter.parse("02/06/2019 15:00:00"), "The Oval, London",""));
		matches.add(new Game(6, "ENGLAND", "PAKISTAN", formatter.parse("03/06/2019 15:00:00"), "Trent Bridge, Nottingham",""));
		matches.add(new Game(7, "AFGHANISTAN", "SRI LANKA", formatter.parse("04/06/2019 15:00:00"), "Cardiff Wales Stadium, Cardiff",""));
		matches.add(new Game(8, "SOUTH AFRICA", "INDIA", formatter.parse("05/06/2019 15:00:00"), "The Ageas Bowl, Southampton",""));
		matches.add(new Game(9, "BANGLADESH", "NEW ZEALAND", formatter.parse("05/06/2019 18:00:00"), "The Oval, London",""));
		matches.add(new Game(10, "AUSTRALIA", "WEST INDIES", formatter.parse("06/06/2019 15:00:00"), "Trent Bridge, Nottingham",""));
		matches.add(new Game(11, "PAKISTAN", "SRI LANKA", formatter.parse("07/06/2019 15:00:00"), "Bristol County Ground, Bristol",""));
		matches.add(new Game(12, "ENGLAND", "BANGLADESH", formatter.parse("08/06/2019 15:00:00"), "Cardiff Wales Stadium, Cardiff",""));
		matches.add(new Game(13, "AFGHANISTAN", "NEW ZEALAND", formatter.parse("08/06/2019 18:00:00"), "The County Ground, Taunton",""));
		matches.add(new Game(14, "INDIA", "AUSTRALIA", formatter.parse("09/06/2019 15:00:00"), "The Oval, London",""));
		matches.add(new Game(15, "SOUTH AFRICA", "WEST INDIES", formatter.parse("10/06/2019 15:00:00"), "The Ageas Bowl, Southampton",""));
		matches.add(new Game(16, "BANGLADESH", "SRI LANKA", formatter.parse("11/06/2019 15:00:00"), "Bristol County Ground, Bristol",""));
		matches.add(new Game(17, "AUSTRALIA", "PAKISTAN", formatter.parse("12/06/2019 15:00:00"), "The County Ground, Taunton",""));
		matches.add(new Game(18, "INDIA", "NEW ZEALAND", formatter.parse("13/06/2019 15:00:00"), "Trent Bridge, Nottingham",""));
		matches.add(new Game(19, "ENGLAND", "WEST INDIES", formatter.parse("14/06/2019 15:00:00"), "The Ageas Bowl, Southampton",""));
		matches.add(new Game(20, "SRI LANKA", "AUSTRALIA", formatter.parse("15/06/2019 15:00:00"), "The Oval, London",""));
		matches.add(new Game(21, "SOUTH AFRICA", "AFGHANISTAN", formatter.parse("15/06/2019 18:00:00"), "Cardiff Wales Stadium, Cardiff",""));
		matches.add(new Game(22, "INDIA", "PAKISTAN", formatter.parse("16/06/2019 15:00:00"), "Old Trafford, Manchester",""));
		matches.add(new Game(23, "WEST INDIES", "BANGLADESH", formatter.parse("17/06/2019 15:00:00"), "The County Ground, Taunton",""));
		matches.add(new Game(24, "ENGLAND", "AFGHANISTAN", formatter.parse("18/06/2019 15:00:00"), "Old Trafford, Manchester",""));
		matches.add(new Game(25, "NEW ZEALAND", "SOUTH AFRICA", formatter.parse("19/06/2019 15:00:00"), "Edgbaston, Birmingham",""));
		matches.add(new Game(26, "AUSTRALIA", "BANGLADESH", formatter.parse("20/06/2019 15:00:00"), "Trent Bridge, Nottingham",""));
		matches.add(new Game(27, "ENGLAND", "SRI LANKA", formatter.parse("21/06/2019 15:00:00"), "Headingley, Leeds",""));
		matches.add(new Game(28, "INDIA", "AFGHANISTAN", formatter.parse("22/06/2019 15:00:00"), "The Ageas Bowl, Southampton",""));
		matches.add(new Game(29, "WEST INDIES", "NEW ZEALAND", formatter.parse("22/06/2019 18:00:00"), "Old Trafford, Manchester",""));
		matches.add(new Game(30, "PAKISTAN", "SOUTH AFRICA", formatter.parse("23/06/2019 15:00:00"), "Lord's Cricket Ground, London",""));
		matches.add(new Game(31, "BANGLADESH", "AFGHANISTAN", formatter.parse("24/06/2019 15:00:00"), "The Ageas Bowl, Southampton",""));
		matches.add(new Game(32, "ENGLAND", "AUSTRALIA", formatter.parse("25/06/2019 15:00:00"), "Lord's Cricket Ground, London",""));
		matches.add(new Game(33, "NEW ZEALAND", "PAKISTAN", formatter.parse("26/06/2019 15:00:00"), "Edgbaston, Birmingham",""));
		matches.add(new Game(34, "WEST INDIES", "INDIA", formatter.parse("27/06/2019 15:00:00"), "Old Trafford, Manchester",""));
		matches.add(new Game(35, "SRI LANKA", "SOUTH AFRICA", formatter.parse("28/06/2019 15:00:00"), "Emirates Riverside, Chester-le-Street",""));
		matches.add(new Game(36, "PAKISTAN", "AFGHANISTAN", formatter.parse("29/06/2019 15:00:00"), "Headingley, Leeds",""));
		matches.add(new Game(37, "NEW ZEALAND", "AUSTRALIA", formatter.parse("29/06/2019 18:00:00"), "Lord's Cricket Ground, London",""));
		matches.add(new Game(38, "ENGLAND", "INDIA", formatter.parse("30/06/2019 15:00:00"), "Edgbaston, Birmingham",""));
		matches.add(new Game(39, "SRI LANKA", "WEST INDIES", formatter.parse("01/07/2019 15:00:00"), "Emirates Riverside, Chester-le-Street",""));
		matches.add(new Game(40, "BANGLADESH", "INDIA", formatter.parse("02/07/2019 15:00:00"), "Edgbaston, Birmingham",""));
		matches.add(new Game(41, "ENGLAND", "NEW ZEALAND", formatter.parse("03/07/2019 15:00:00"), "Emirates Riverside, Chester-le-Street",""));
		matches.add(new Game(42, "AFGHANISTAN", "WEST INDIES", formatter.parse("04/07/2019 15:00:00"), "Headingley, Leeds",""));
		matches.add(new Game(43, "PAKISTAN", "BANGLADESH", formatter.parse("05/07/2019 15:00:00"), "Lord's Cricket Ground, London",""));
		matches.add(new Game(44, "SRI LANKA", "INDIA", formatter.parse("06/07/2019 15:00:00"), "Headingley, Leeds",""));
		matches.add(new Game(45, "AUSTRALIA", "SOUTH AFRICA", formatter.parse("06/07/2019 18:00:00"), "Old Trafford, Manchester",""));
		matches.add(new Game(46, "1ST", "4TH", formatter.parse("09/07/2019 15:00:00"), "Old Trafford, Manchester",""));
		matches.add(new Game(47, "2ND", "3RD", formatter.parse("11/07/2019 15:00:00"), "Edgbaston, Birmingham",""));
		matches.add(new Game(48, "TBC", "TBC", formatter.parse("14/07/2019 15:00:00"), "Lord's Cricket Ground, London",""));
		matchService.saveAllMatches(matches);
	}
	
	
	
	@Autowired
	DailyPredictionService dailyPredictionService;
	
	@Test
	public void saveDailyPrediction(){
		Game match1=matchService.findMatchByMatchNo(1);
		Game match2=matchService.findMatchByMatchNo(2);
		Game match3=matchService.findMatchByMatchNo(3);
		
		User shaju=userService.findUserByUsername("373962");
		User shibu=userService.findUserByUsername("373963");
		User sandhya=userService.findUserByUsername("373964");
		
		//Match #1 Prediction
		Dailyprediction dp1=new Dailyprediction();
		DailypredictionId dailypredictionId1=new DailypredictionId(shaju.getId(),match1.getMatchNo());
		dp1.setId(dailypredictionId1);
		dp1.setPrediction("ENGLAND");
		dailyPredictionService.saveDailyPrediction(dp1);
		
		Dailyprediction dp2=new Dailyprediction();
		DailypredictionId dailypredictionId2=new DailypredictionId(shibu.getId(),match1.getMatchNo());
		dp2.setId(dailypredictionId2);
		dp2.setPrediction("SOUTH AFRICA");
		dailyPredictionService.saveDailyPrediction(dp2);
		
		Dailyprediction dp3=new Dailyprediction();
		DailypredictionId dailypredictionId3=new DailypredictionId(sandhya.getId(),match1.getMatchNo());
		dp3.setId(dailypredictionId3);
		dp3.setPrediction("SOUTH AFRICA");
		dailyPredictionService.saveDailyPrediction(dp3);
		
		//Match #2 Prediction
		Dailyprediction dp4=new Dailyprediction();
		DailypredictionId dailypredictionId4=new DailypredictionId(shaju.getId(),match2.getMatchNo());
		dp4.setId(dailypredictionId4);
		dp4.setPrediction("WEST INDIES");
		dailyPredictionService.saveDailyPrediction(dp4);
		
		Dailyprediction dp5=new Dailyprediction();
		DailypredictionId dailypredictionId5=new DailypredictionId(shibu.getId(),match2.getMatchNo());
		dp5.setId(dailypredictionId5);
		dp5.setPrediction("PAKISTAN");
		dailyPredictionService.saveDailyPrediction(dp5);
		
		Dailyprediction dp6=new Dailyprediction();
		DailypredictionId dailypredictionId6=new DailypredictionId(sandhya.getId(),match2.getMatchNo());
		dp6.setId(dailypredictionId6);
		dp6.setPrediction("WEST INDIES");
		dailyPredictionService.saveDailyPrediction(dp6);
		
		//Match #3 Prediction
		Dailyprediction dp7=new Dailyprediction();
		DailypredictionId dailypredictionId7=new DailypredictionId(shaju.getId(),match3.getMatchNo());
		dp7.setId(dailypredictionId7);
		dp7.setPrediction("NEW ZEALAND");
		dailyPredictionService.saveDailyPrediction(dp7);
		
		Dailyprediction dp8=new Dailyprediction();
		DailypredictionId dailypredictionId8=new DailypredictionId(shibu.getId(),match3.getMatchNo());
		dp8.setId(dailypredictionId8);
		dp8.setPrediction("NEW ZEALAND");
		dailyPredictionService.saveDailyPrediction(dp8);
		
		Dailyprediction dp9=new Dailyprediction();
		DailypredictionId dailypredictionId9=new DailypredictionId(sandhya.getId(),match3.getMatchNo());
		dp9.setId(dailypredictionId9);
		dp9.setPrediction("SRI LANKA");
		dailyPredictionService.saveDailyPrediction(dp9);
	}
	
	@Test
	public void findDailyPredictionByUser(){
		User shaju=userService.findUserByUsername("373962");
		List<Dailyprediction> dailyprediction=dailyPredictionService.findDailyPredictionByUser(shaju);
		dailyprediction.forEach(
				dp->System.out.println("User Id: "+dp.getUser().getId()
						+", MatchNo: "+dp.getGame().getMatchNo()
						+", Prediction: "+dp.getPrediction()
						));
	}
	
	@Test
	public void findDailyPredictionByMatch(){
		Game match3=matchService.findMatchByMatchNo(3);
		List<Dailyprediction> dailyprediction=dailyPredictionService.findDailyPredictionByMatches(match3);
		dailyprediction.forEach(
				dp->System.out.println("User Id: "+dp.getUser().getId()
						+", MatchNo: "+dp.getGame().getMatchNo()
						+", Prediction: "+dp.getPrediction()
						));
	}
	
	@Test
	public void findAllDailyPredictions(){
		
		List<Dailyprediction> dailyprediction=dailyPredictionService.findAllDailyPredictions();
		dailyprediction.forEach(
				dp->System.out.println("User Id: "+dp.getUser().getId()
						+", MatchNo: "+dp.getGame().getMatchNo()
						+", Prediction: "+dp.getPrediction()
						));
	}
	
	@Autowired
	SemiFinalPredictionService semiFinalPredictionService;
	
	@Test
	public void saveSemiFinalPrediction(){
		User shaju=userService.findUserByUsername("373962");
		User shibu=userService.findUserByUsername("373963");
		User sandhya=userService.findUserByUsername("373964");
		
		Semifinalprediction sfp1=new Semifinalprediction(shaju,"INDIA", "SOUTH AFRICA", "PAKISTAN","ENGLAND");
		semiFinalPredictionService.saveSemiFinalPrediction(sfp1);
		
		Semifinalprediction sfp2=new Semifinalprediction(shibu,"INDIA", "SOUTH AFRICA", "AUSTRALIA","ENGLAND");
		semiFinalPredictionService.saveSemiFinalPrediction(sfp2);
		
		Semifinalprediction sfp3=new Semifinalprediction(sandhya,"INDIA", "SOUTH AFRICA", "SRI LANKA","WEST INDIES");
		semiFinalPredictionService.saveSemiFinalPrediction(sfp3);
	}
	
	@Test
	public void updateSemiFinalPrediction(){
		User sandhya=userService.findUserByUsername("373964");
		Semifinalprediction sfp3=semiFinalPredictionService.findSemiFinalPredictionByUser(sandhya);
		sfp3.setTeam4("PAKISTAN");
		semiFinalPredictionService.saveSemiFinalPrediction(sfp3);
	}
	
	@Test
	public void findSemiFinalPredictionByUser(){
		User shaju=userService.findUserByUsername("373962");
		Semifinalprediction sfp=semiFinalPredictionService.findSemiFinalPredictionByUser(shaju);
		System.out.println("Username: " +sfp.getUser().getUsername()+" Team1: "+sfp.getTeam1()
				+" Team2: "+sfp.getTeam2()+" Team3: "+sfp.getTeam3()+" Team4: "+sfp.getTeam4());
	}
	
	@Test
	public void findAllSemiFinalPredictions(){
		List<Semifinalprediction> semifinalpredictions=semiFinalPredictionService.findAllSemiFinalPredictions();
		semifinalpredictions.forEach( sfp ->
		System.out.println("Username: " +sfp.getUser().getUsername()+" Team1: "+sfp.getTeam1()
				+" Team2: "+sfp.getTeam2()+" Team3: "+sfp.getTeam3()+" Team4: "+sfp.getTeam4())
				);
	}
	
	@Autowired
	FinalPredictionService finalPredictionService;
	
	@Test
	public void saveFinalPrediction(){
		User shaju=userService.findUserByUsername("373962");
		User shibu=userService.findUserByUsername("373963");
		User sandhya=userService.findUserByUsername("373964");
		Finalprediction fp1=new Finalprediction(shaju,"INDIA","PAKISTAN");
		finalPredictionService.saveFinalPrediction(fp1);
		Finalprediction fp2=new Finalprediction(shibu,"INDIA","SOUTH AFRICA");
		finalPredictionService.saveFinalPrediction(fp2);
		Finalprediction fp3=new Finalprediction(sandhya,"INDIA","ENGLAND");
		finalPredictionService.saveFinalPrediction(fp3);
	}
	
	@Test
	public void updateFinalPrediction(){
		User sandhya=userService.findUserByUsername("373964");
		Finalprediction fp3=finalPredictionService.findFinalPredictionByUser(sandhya);
		fp3.setTeam2("AUSTRALIA");
		finalPredictionService.saveFinalPrediction(fp3);
	}
	
	@Test
	public void findFinalPredictionByUser(){
		User sandhya=userService.findUserByUsername("373964");
		Finalprediction sfp=finalPredictionService.findFinalPredictionByUser(sandhya);
		System.out.println("Username: " +sfp.getUser().getUsername()+" Team1: "+sfp.getTeam1()
				+" Team2: "+sfp.getTeam2());
	}
	
	@Test
	public void findAllFinalPredictions(){
		List<Finalprediction> finalPredictions=finalPredictionService.findAllFinalPredictions();
		finalPredictions.forEach(fp ->
		System.out.println("Username: " +fp.getUser().getUsername()+" Team1: "+fp.getTeam1()
				+" Team2: "+fp.getTeam2())
				);
	}
	
	@Autowired
	ChampionPredictionService championPredictionService;
	
	@Test
	public void saveChampionPrediction(){
		User shaju=userService.findUserByUsername("373962");
		User shibu=userService.findUserByUsername("373963");
		User sandhya=userService.findUserByUsername("373964");
		Championprediction fp1=new Championprediction(shaju,"INDIA");
		championPredictionService.saveChampionPrediction(fp1);
		Championprediction fp2=new Championprediction(shibu,"INDIA");
		championPredictionService.saveChampionPrediction(fp2);
		Championprediction fp3=new Championprediction(sandhya,"ENGLAND");
		championPredictionService.saveChampionPrediction(fp3);
	}
	
	@Test
	public void updateChampionPrediction(){
		User sandhya=userService.findUserByUsername("373964");
		Championprediction fp3=championPredictionService.findChampionPredictionByUser(sandhya);
		fp3.setPrediction("INDIA");
		championPredictionService.saveChampionPrediction(fp3);
	}
	
	@Test
	public void findChampionPredictionByUser(){
		User sandhya=userService.findUserByUsername("373964");
		Championprediction fp3=championPredictionService.findChampionPredictionByUser(sandhya);
		System.out.println("Username: " +fp3.getUser().getUsername()+" Winner: "+fp3.getPrediction());
	}
	
	@Test
	public void findAllChampionPredictions(){
		List<Championprediction> championpredictions=championPredictionService.findAllChampionPredictions();
		championpredictions.forEach(fp3 ->
		System.out.println("Username: " +fp3.getUser().getUsername()+" Winner: "+fp3.getPrediction())
				);
	}
}
