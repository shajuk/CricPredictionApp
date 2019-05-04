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

import com.prediction.app.model.Matches;
import com.prediction.app.service.MatchService;
import com.prediction.app.service.MatchServiceImpl;

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
        public MatchService matchService() {
            return new MatchServiceImpl();
        }
    }
	
	@Autowired
	MatchService matchService;
	
	@Test
	public void saveMatch() throws ParseException{
		Matches matches=new Matches(1, "ENGLAND", "SOUTH AFRICA", formatter.parse("30/05/2019 15:00:00"), "The Oval, London","");
		matchService.saveMatch(matches);
		Matches savedMatch=matchService.findMatchByMatchNo(1);
		assertThat(matches!=savedMatch && matches.getTeam1().equals(savedMatch.getTeam1()));
	}	
	
	@Test
	public void findAllMatches(){
		List<Matches> matches=matchService.findAllMatches();
		assertThat(!matches.isEmpty());
		matches.forEach(m -> System.out.println(" Match No - "+m.getMatchNo()+" "+m.getResult()));
	}
	
	@Test
	public void updateMatch(){
		Matches savedMatch=matchService.findMatchByMatchNo(2);
		savedMatch.setResult(savedMatch.getTeam1());
		matchService.updateMatch(savedMatch);
		assertThat(savedMatch.getResult()!=null);
		findAllMatches();
	}
	
	@Test
	public void saveAllMatch() throws ParseException{
		List<Matches> matches=new ArrayList<>();
		matches.add(new Matches(1, "ENGLAND", "SOUTH AFRICA", formatter.parse("30/05/2019 15:00:00"), "The Oval, London",""));
		matches.add(new Matches(2, "WEST INDIES", "PAKISTAN", formatter.parse("31/05/2019 15:00:00"), "Trent Bridge, Nottingham",""));
		matches.add(new Matches(3, "NEW ZEALAND", "SRI LANKA", formatter.parse("01/06/2019 15:00:00"), "Cardiff Wales Stadium, Cardiff",""));
		matches.add(new Matches(4, "AFGHANISTAN", "AUSTRALIA", formatter.parse("01/06/2019 18:00:00"), "Bristol County Ground, Bristol",""));
		matches.add(new Matches(5, "SOUTH AFRICA", "BANGLADESH", formatter.parse("02/06/2019 15:00:00"), "The Oval, London",""));
		matches.add(new Matches(6, "ENGLAND", "PAKISTAN", formatter.parse("03/06/2019 15:00:00"), "Trent Bridge, Nottingham",""));
		matches.add(new Matches(7, "AFGHANISTAN", "SRI LANKA", formatter.parse("04/06/2019 15:00:00"), "Cardiff Wales Stadium, Cardiff",""));
		matches.add(new Matches(8, "SOUTH AFRICA", "INDIA", formatter.parse("05/06/2019 15:00:00"), "The Ageas Bowl, Southampton",""));
		matches.add(new Matches(9, "BANGLADESH", "NEW ZEALAND", formatter.parse("05/06/2019 18:00:00"), "The Oval, London",""));
		matches.add(new Matches(10, "AUSTRALIA", "WEST INDIES", formatter.parse("06/06/2019 15:00:00"), "Trent Bridge, Nottingham",""));
		matches.add(new Matches(11, "PAKISTAN", "SRI LANKA", formatter.parse("07/06/2019 15:00:00"), "Bristol County Ground, Bristol",""));
		matches.add(new Matches(12, "ENGLAND", "BANGLADESH", formatter.parse("08/06/2019 15:00:00"), "Cardiff Wales Stadium, Cardiff",""));
		matches.add(new Matches(13, "AFGHANISTAN", "NEW ZEALAND", formatter.parse("08/06/2019 18:00:00"), "The County Ground, Taunton",""));
		matches.add(new Matches(14, "INDIA", "AUSTRALIA", formatter.parse("09/06/2019 15:00:00"), "The Oval, London",""));
		matches.add(new Matches(15, "SOUTH AFRICA", "WEST INDIES", formatter.parse("10/06/2019 15:00:00"), "The Ageas Bowl, Southampton",""));
		matches.add(new Matches(16, "BANGLADESH", "SRI LANKA", formatter.parse("11/06/2019 15:00:00"), "Bristol County Ground, Bristol",""));
		matches.add(new Matches(17, "AUSTRALIA", "PAKISTAN", formatter.parse("12/06/2019 15:00:00"), "The County Ground, Taunton",""));
		matches.add(new Matches(18, "INDIA", "NEW ZEALAND", formatter.parse("13/06/2019 15:00:00"), "Trent Bridge, Nottingham",""));
		matches.add(new Matches(19, "ENGLAND", "WEST INDIES", formatter.parse("14/06/2019 15:00:00"), "The Ageas Bowl, Southampton",""));
		matches.add(new Matches(20, "SRI LANKA", "AUSTRALIA", formatter.parse("15/06/2019 15:00:00"), "The Oval, London",""));
		matches.add(new Matches(21, "SOUTH AFRICA", "AFGHANISTAN", formatter.parse("15/06/2019 18:00:00"), "Cardiff Wales Stadium, Cardiff",""));
		matches.add(new Matches(22, "INDIA", "PAKISTAN", formatter.parse("16/06/2019 15:00:00"), "Old Trafford, Manchester",""));
		matches.add(new Matches(23, "WEST INDIES", "BANGLADESH", formatter.parse("17/06/2019 15:00:00"), "The County Ground, Taunton",""));
		matches.add(new Matches(24, "ENGLAND", "AFGHANISTAN", formatter.parse("18/06/2019 15:00:00"), "Old Trafford, Manchester",""));
		matches.add(new Matches(25, "NEW ZEALAND", "SOUTH AFRICA", formatter.parse("19/06/2019 15:00:00"), "Edgbaston, Birmingham",""));
		matches.add(new Matches(26, "AUSTRALIA", "BANGLADESH", formatter.parse("20/06/2019 15:00:00"), "Trent Bridge, Nottingham",""));
		matches.add(new Matches(27, "ENGLAND", "SRI LANKA", formatter.parse("21/06/2019 15:00:00"), "Headingley, Leeds",""));
		matches.add(new Matches(28, "INDIA", "AFGHANISTAN", formatter.parse("22/06/2019 15:00:00"), "The Ageas Bowl, Southampton",""));
		matches.add(new Matches(29, "WEST INDIES", "NEW ZEALAND", formatter.parse("22/06/2019 18:00:00"), "Old Trafford, Manchester",""));
		matches.add(new Matches(30, "PAKISTAN", "SOUTH AFRICA", formatter.parse("23/06/2019 15:00:00"), "Lord's Cricket Ground, London",""));
		matches.add(new Matches(31, "BANGLADESH", "AFGHANISTAN", formatter.parse("24/06/2019 15:00:00"), "The Ageas Bowl, Southampton",""));
		matches.add(new Matches(32, "ENGLAND", "AUSTRALIA", formatter.parse("25/06/2019 15:00:00"), "Lord's Cricket Ground, London",""));
		matches.add(new Matches(33, "NEW ZEALAND", "PAKISTAN", formatter.parse("26/06/2019 15:00:00"), "Edgbaston, Birmingham",""));
		matches.add(new Matches(34, "WEST INDIES", "INDIA", formatter.parse("27/06/2019 15:00:00"), "Old Trafford, Manchester",""));
		matches.add(new Matches(35, "SRI LANKA", "SOUTH AFRICA", formatter.parse("28/06/2019 15:00:00"), "Emirates Riverside, Chester-le-Street",""));
		matches.add(new Matches(36, "PAKISTAN", "AFGHANISTAN", formatter.parse("29/06/2019 15:00:00"), "Headingley, Leeds",""));
		matches.add(new Matches(37, "NEW ZEALAND", "AUSTRALIA", formatter.parse("29/06/2019 18:00:00"), "Lord's Cricket Ground, London",""));
		matches.add(new Matches(38, "ENGLAND", "INDIA", formatter.parse("30/06/2019 15:00:00"), "Edgbaston, Birmingham",""));
		matches.add(new Matches(39, "SRI LANKA", "WEST INDIES", formatter.parse("01/07/2019 15:00:00"), "Emirates Riverside, Chester-le-Street",""));
		matches.add(new Matches(40, "BANGLADESH", "INDIA", formatter.parse("02/07/2019 15:00:00"), "Edgbaston, Birmingham",""));
		matches.add(new Matches(41, "ENGLAND", "NEW ZEALAND", formatter.parse("03/07/2019 15:00:00"), "Emirates Riverside, Chester-le-Street",""));
		matches.add(new Matches(42, "AFGHANISTAN", "WEST INDIES", formatter.parse("04/07/2019 15:00:00"), "Headingley, Leeds",""));
		matches.add(new Matches(43, "PAKISTAN", "BANGLADESH", formatter.parse("05/07/2019 15:00:00"), "Lord's Cricket Ground, London",""));
		matches.add(new Matches(44, "SRI LANKA", "INDIA", formatter.parse("06/07/2019 15:00:00"), "Headingley, Leeds",""));
		matches.add(new Matches(45, "AUSTRALIA", "SOUTH AFRICA", formatter.parse("06/07/2019 18:00:00"), "Old Trafford, Manchester",""));
		matches.add(new Matches(46, "1ST", "4TH", formatter.parse("09/07/2019 15:00:00"), "Old Trafford, Manchester",""));
		matches.add(new Matches(47, "2ND", "3RD", formatter.parse("11/07/2019 15:00:00"), "Edgbaston, Birmingham",""));
		matches.add(new Matches(48, "TBC", "TBC", formatter.parse("14/07/2019 15:00:00"), "Lord's Cricket Ground, London",""));
		matchService.saveAllMatches(matches);
		
	}
}
