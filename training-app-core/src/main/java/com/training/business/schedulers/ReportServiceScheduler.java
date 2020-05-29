package com.training.business.schedulers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.training.infrastructure.database.report.Report;
import com.training.infrastructure.database.report.ReportRepository;
import com.training.infrastructure.database.timeslot.Timeslot;
import com.training.infrastructure.database.timeslot.TimeslotRepository;

@Stateless
public class ReportServiceScheduler {

	@Inject
	private TimeslotRepository timeslotRepository;

	@Inject
	private ReportRepository reportRepository;

	@Schedule(hour = "20", minute = "0", second = "0", persistent = false)
	public void generateReports() {

		List<Timeslot> timeslots = timeslotRepository.getByDate(LocalDate.now().atTime(8, 0));

		if (!timeslots.isEmpty()) {
			int numOfTimeslots = timeslots.size();
			int numOfApplications = (int) timeslots.stream().filter(t -> t.isShowedUp()).count();
			double percentageOfSubmitted = 0;
			if (numOfApplications == 0) {
				percentageOfSubmitted = (numOfApplications / numOfTimeslots);
			}
			reportRepository.save(createReport(numOfTimeslots, numOfApplications, percentageOfSubmitted));
		}
	}

	private Report createReport(int numOfTimeslots, int numOfApplications, double percentageOfSubmitted) {
		return Report.builder().date(LocalDate.now()).numberOfTimeslots(numOfTimeslots)
				.numberOfApplications(numOfApplications).percentageSubmitted(BigDecimal.valueOf(percentageOfSubmitted))
				.build();
	}

}
