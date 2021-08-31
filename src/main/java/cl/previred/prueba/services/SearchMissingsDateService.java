package cl.previred.prueba.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import cl.previred.prueba.clients.GddClient;
import cl.previred.prueba.model.Period;
import cl.previred.prueba.model.ResponseData;

@Service
public class SearchMissingsDateService {

    Logger logger = LogManager.getLogger(SearchMissingsDateService.class);

    @Autowired
    private GddClient gddClient;

    public ResponseData getData() {
	try {
	    Period period = gddClient.getPeriodos();
	    List<LocalDate> missings = getMissingsDate(period);
	    return ResponseData.builder().dates(period.getDates())
		    .missingDates(missings).endDate(period.getEndDate())
		    .id(period.getId()).startDate(period.getStartDate())
		    .build();
	} catch (Exception ex) {
	    logger.error(ex.getMessage());
	    return null;
	}
    }

    private List<LocalDate> getMissingsDate(Period period) {
	List<LocalDate> exitValues = new ArrayList<LocalDate>();

	for (int i = 0; i < getMonthOfDiference(period); i++) {
	    if (!existInPeriod(period, period.getStartDate().plusMonths(1))) {
		exitValues.add(period.getStartDate().plusMonths(1));
	    }
	}
	return exitValues;
    }

    private int getMonthOfDiference(Period period) {
	Calendar init = new GregorianCalendar();
	Calendar end = new GregorianCalendar();
	init.setTime(localDateToDate(period.getStartDate()));
	end.setTime(localDateToDate(period.getEndDate()));
	int difYears = end.get(Calendar.YEAR) - init.get(Calendar.YEAR);
	return difYears * 12 + end.get(Calendar.MONTH)
		- init.get(Calendar.MONTH);
    }

    private Date localDateToDate(LocalDate date) {

	ZoneId systemTimeZone = ZoneId.systemDefault();
	ZonedDateTime zonedDateTime = date.atStartOfDay(systemTimeZone);
	return Date.from(zonedDateTime.toInstant());
    }

    private boolean existInPeriod(Period period, LocalDate value) {
	return period.getDates().contains(value);
    }

}
