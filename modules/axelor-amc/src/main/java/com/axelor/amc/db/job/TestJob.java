package com.axelor.amc.db.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDetail detail = context.getJobDetail();
	    JobDataMap data = context.getJobDetail().getJobDataMap();

	    String name = detail.getKey().getName();
	    String desc = detail.getDescription();

	    System.err.println("Job fired: " + name + " (" + desc + ")");
	    if (data != null && data.size() > 0) {
	      for (String key : data.keySet()) {
	        System.err.println("    " + key + " = " + data.getString(key));
	      }
	    }
		
	}

}
